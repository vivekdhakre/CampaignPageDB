package com.campaign.mvc.controller;

import com.campaign.mvc.service.MiscService;
import com.campaign.util.CIdDetail;
import com.campaign.util.Constants;
import com.campaign.util.StackTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;

import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

/**
 * @author Vivek on 3/7/17.
 * @project CampaignPageDB
 * @package com.campaign.mvc.controller
 */

@Controller
@SessionAttributes({"cIdDetail"})
public class CampaignController {

    private static Logger logger = LoggerFactory.getLogger(CampaignController.class);

    @Autowired
    private MiscService miscService;


    @RequestMapping(value = "/c/{cid}")
    public String campaign(@PathVariable long cid, @RequestParam(value = "type" ,required = false) String type, @RequestParam(value="id",required = false) String id, HttpServletRequest request) throws Exception {
        return "redirect:"+Constants.getServerPath(request)+"/cl/"+cid+"?type"+type+"&id="+URLEncoder.encode(id!=null?id:"","UTF-8");
    }


    @RequestMapping(value = "/cl/{cid}",method = RequestMethod.GET)
    public String campaignLocation(@PathVariable long cid, @RequestParam(value = "type" ,required = false) String type, @RequestParam(value="id",required = false) String id, HttpSession session,SessionStatus sessionStatus, HttpServletRequest request){

        String resp = null;
        String ip = request.getHeader("X-FORWARDED-FOR") !=null? request.getHeader("X-FORWARDED-FOR") :request.getRemoteAddr();
        String ua = request.getHeader("User-Agent");
        String serverPath = Constants.getServerPath(request);

        try {

            if(session.getAttribute("cIdDetail")!=null){
                resp = "cleaning session";
                return "redirect:"+serverPath+"/clean?r="+URLEncoder.encode(serverPath+"/cl/"+cid+"?type="+type+"&id="+id,"UTF-8");
            }

            if (type != null && type.trim().toLowerCase().matches("devid|gid") && id != null) {
               session.setAttribute("type", type);
                session.setAttribute("id", id);
                resp = "request parameter valid";
            } else {
                resp = "request param invalid";
            }

        }catch(Exception e){
            logger.error(StackTrace.getRootCause(e,getClass().getName()));
        }finally{
            logger.info("ip ["+ip+"], ua ["+ua+"], cid: "+cid+" | type: "+type+" | id: "+id+" | resp: "+resp);
        }

        return "redirect:"+serverPath+"/offer/{cid}";
    }

    @RequestMapping("/clean")
    public String clean(@RequestParam("r") String rparam, SessionStatus sessionStatus){
        sessionStatus.setComplete();
        logger.info("rparam: "+rparam);
        return "redirect:"+rparam;
    }

    @RequestMapping("/offer/{cid}")
    public String campaignLocationRedirect(@PathVariable long cid, Map<String,Object> map, HttpSession session, HttpServletRequest request){

        String ip = request.getHeader("X-FORWARDED-FOR") !=null? request.getHeader("X-FORWARDED-FOR") :request.getRemoteAddr();
        String ua = request.getHeader("User-Agent");
        String resp = null;
        try{

            if(session.getAttribute("cIdDetail")==null) {
                CIdDetail cIdDetail = miscService.getDetail(cid);
                cIdDetail.setServerPath(Constants.getServerPath(request));

                if (cIdDetail.getEdate().after(new Date())) {
                    String type = session.getAttribute("type") != null ? session.getAttribute("type").toString() : null;
                    String id = session.getAttribute("id") != null ? session.getAttribute("id").toString() : null;

                    if (type != null && id != null) {
                        String api = "http://uahoy.com/mobilecoupon/getcpnbyid?cid=" + cid + "&idtype=" + type + "&id=" + id.replaceAll("[^\\w\\s]", "");
                        int i = 0;

                        do {
                            resp = Constants.processURL(api);
                            i++;
                        }while (i < 2 && (resp == null || "".equals(resp.trim()) || "Read timed out".equalsIgnoreCase(resp.trim())));

                        if (resp != null && !"".equals(resp.trim()) && !"Read timed out".equals(resp.trim())) {
                            cIdDetail.setCoupon(resp);
                            cIdDetail.setUid(id.replaceAll("[^\\w\\s]", "") + "@" + type + ".com");
                        } else {
                            resp = "coupon api is taking time";
                        }
                    }
                } else {
                    resp = "Expired";
                    cIdDetail.setCoupon("Expired");
                }
                map.put("cIdDetail", cIdDetail);
            }else{
                resp ="cIdDetail is found in session";
            }

        }catch (Exception e){
            resp = "Internal Server Error";
            logger.error("cid: "+cid+" | "+ StackTrace.getRootCause(e,getClass().getName()));
        }finally{
            logger.info("ip ["+ip+"], ua ["+ua+"] cid: "+cid+" | resp: "+resp);
        }
        return "branchoffer";
    }


    @RequestMapping("/coupon/{type}/{coupon}.png")
    public void couponRender(@PathVariable int type, @PathVariable String coupon, HttpSession session, HttpServletResponse response){

        try{
            if(session.getAttribute("cIdDetail")!=null){
                CIdDetail cIdDetail = (CIdDetail)session.getAttribute("cIdDetail");
                long cid = cIdDetail.getCid();
                String uid = cIdDetail.getUid();
                File f = null;
                if(type==1){
                    f = new File("/home/apache-tomcat-8.0.35/uahoyapps/ROOT/coupon/1D/"+coupon+".png");
                }else if(type==2){
                    f = new File("/home/apache-tomcat-8.0.35/uahoyapps/ROOT/coupon/QR/"+coupon+".png");
                }else{
                    return;
                }

                if(!f.exists()){
                    String cpnurl1 = "http://uahoy.com/uahoy/couponFetch?cid=" + cid + "&ctype=" + type + "&uid=" + URLEncoder.encode(uid,"UTF-8");
                    Constants.processURL(cpnurl1);
                }
                Constants.writeImage(response,f);
            }

        }catch (Exception e){
            logger.error("coupon: "+coupon+" | type: "+type+" | "+StackTrace.getRootCause(e,getClass().getName()));
        }
    }

    @RequestMapping("/image/{name:.+}")
    public void ImageRender(@PathVariable String name, HttpServletResponse response){
        try{

            File f = new File("/home/apache-tomcat-8.0.35/marketeerapps/ahoy/dealImages/" + name);
            if(f.exists())Constants.writeImage(response,f);
        }catch (Exception e){
            logger.error("name: "+name+" | "+StackTrace.getRootCause(e,getClass().getName()));
        }
    }

}
