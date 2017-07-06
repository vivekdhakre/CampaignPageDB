package com.campaign.mvc.controller;

import com.campaign.util.CIdDetail;
import com.campaign.util.Constants;
import com.campaign.util.StackTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author Vivek on 4/7/17.
 * @project CampaignPageDB
 * @package com.campaign.mvc.controller
 */

@RequestMapping("/user")
@Controller
@SessionAttributes({"_otp","cIdDetail"})
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value="/sotp", method = RequestMethod.POST)
    public @ResponseBody String sendOtp(@RequestParam("m") String msisdn, HttpSession session, Map<String,Object> map){
        String resp = null;
        String otp = null;
        try{
            if(session.getAttribute("cIdDetail")!=null) {

                if (msisdn != null && msisdn.trim().matches("[0-9]{10}")) {
                    otp =Constants.getOtp(4);
                    String otpMessage = Constants.OTP_MESSAGE.replace("XXXX", otp);
                    String msgPushApi = Constants.MSG_PUSH_API.replace("<MSISDN>", msisdn)
                            .replace("<MESSAGE>", URLEncoder.encode(otpMessage, "UTF-8"));
                    Constants.processURL(msgPushApi);
                    map.put("_otp",otp);
                    resp = "Success";
                } else {
                    resp = "Please Enter Valid Mobile no.";
                }
            }else{
                resp = HttpStatus.FORBIDDEN.value()+"";
            }
        }catch (Exception e){
            resp = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            logger.error("m: "+msisdn+" | "+ StackTrace.getRootCause(e,getClass().getName()));
        }
        return resp;
    }

    @RequestMapping(value="/gc", method = RequestMethod.POST)
    public @ResponseBody String verifyOtpAndGetCoupon(@ModelAttribute("_otp") String sessionOtp,@RequestParam("m") String msisdn, @RequestParam("o") String otp,Map<String,Object> map , HttpSession session){
        String resp = null;
        try{
            if(session.getAttribute("cIdDetail")!=null) {
                if(sessionOtp.equalsIgnoreCase(otp)){
                    CIdDetail cIdDetail = (CIdDetail)session.getAttribute("cIdDetail");
                    String couponUrl = "http://uahoy.com/mobilecoupon/mcoupon?cid="+cIdDetail.getCid()+"&msisdn="+msisdn+"&rname=campaignpage&platform=web";
                    String coupon = Constants.processURL(couponUrl);
                    cIdDetail.setCoupon(coupon);
                    cIdDetail.setUid(msisdn+"9");
                    map.put("cIdDetail",cIdDetail);
                    session.removeAttribute("_otp");
                    resp = coupon;
                }else{
                    resp = HttpStatus.FORBIDDEN.value()+"";

                }

            }else{
                resp = HttpStatus.UNAUTHORIZED.value()+"";
            }

        }catch (Exception e){
            resp = HttpStatus.INTERNAL_SERVER_ERROR.value()+"";
            logger.error("m: "+msisdn+" | "+ StackTrace.getRootCause(e,getClass().getName()));
        }
        return resp;
    }

    @RequestMapping(value="/scom",method = RequestMethod.POST)
    public @ResponseBody String sendCouponOnMobile(@RequestParam("m") String msisdn, HttpSession session){
        String resp = null;

        try{
            if(session.getAttribute("cIdDetail")!=null) {
                CIdDetail cIdDetail = (CIdDetail)session.getAttribute("cIdDetail");
                if(cIdDetail.getCoupon()!=null){

                    String message = cIdDetail.getSdesc()+", Use Coupon "+cIdDetail.getCoupon()+". TnC Apply";

                    String msgPushApi = Constants.MSG_PUSH_API.replace("<MSISDN>", msisdn)
                            .replace("<MESSAGE>", URLEncoder.encode(message, "UTF-8"));
                    Constants.processURL(msgPushApi);

                    resp = "Successfully Send";
                }

            }else{
                resp = HttpStatus.UNAUTHORIZED.getReasonPhrase();
            }

        }catch (Exception e){
            resp = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            logger.error("m: "+msisdn+" | "+StackTrace.getRootCause(e, getClass().getName()));
        }

        return resp;

    }
}
