package com.campaign.mvc.controller;

import com.campaign.util.Constants;
import com.campaign.util.StackTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author Vivek on 4/7/17.
 * @project CampaignPageDB
 * @package com.campaign.mvc.controller
 */

@Controller
@RequestMapping("/marketeer")
public class MarketeerApiController {

    private static Logger logger = LoggerFactory.getLogger(MarketeerApiController.class);

    @RequestMapping(value="/{apiname}",method = RequestMethod.POST)
    public @ResponseBody String fetch(@PathVariable("apiname") String apiName, HttpServletRequest request){

        String resp = null;

        try{

            String api = "http://marketeer.uahoy.com/ahoy/"+apiName+"?";

            Enumeration<String> parameterNames = request.getParameterNames();
            StringBuilder builder = new StringBuilder();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                if(builder.length()==0) {
                    builder.append(paramName + "=" + request.getParameter(paramName));
                }else{
                    builder.append("&"+paramName + "=" + request.getParameter(paramName));
                }
            }

            api+=builder.toString();
            resp = Constants.post(api);

        }catch (Exception e){
            resp = "500";
            logger.error(StackTrace.getRootCause(e,getClass().getName()));
        }
        return resp;

    }
}
