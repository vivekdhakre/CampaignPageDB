package com.campaign.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.util.Properties;

/**
 * @author Vivek on 3/7/17.
 * @project CampaignPageDB
 * @package com.campaign.util
 */
public class Constants {

    private static Logger logger = LoggerFactory.getLogger(Constants.class);

    public static String MSG_PUSH_API="http://49.50.67.32/smsapi/httpapi.jsp?username=ahoytr&password=ahoytr&from=UREWAR&to=<MSISDN>&text=<MESSAGE>&coding=0";

    public static String OTP_MESSAGE;

    static {
        URL url = Constants.class.getResource("/application.properties");
        try(InputStream is = url.openStream()){
            Properties properties = new Properties();
            properties.load(is);
            OTP_MESSAGE = properties.getProperty("OTP_MESSAGE");

        }catch (Exception e){

        }
    }



    public static String getServerPath(HttpServletRequest request) {
        String serverPath = "http://localhost:8080";
//        String serverPath = "https://ads.uahoy.in";
        return serverPath+request.getContextPath();
    }

    public static String getOtp(int size) {

        StringBuilder generatedToken = new StringBuilder();
        try {
            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
            for (int i = 0; i < size; i++) {
                generatedToken.append(number.nextInt(9));
            }
        } catch (Exception e) {
            logger.error(StackTrace.getRootCause(e,Constants.class.getName()));
        }
        return generatedToken.toString();
    }

    public static String processURL(String paramString){
        StringBuilder builder = new StringBuilder();
        BufferedReader br = null;
        try
        {
            URL url = new URL(paramString);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(10000);

            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String str;
            while ((str = br.readLine()) != null)
                builder.append(str);

        }catch (Exception e){
            builder.append(e.getMessage());
            logger.error(StackTrace.getRootCause(e,Constants.class.getName()));
        }finally {
            try{if(br!=null)br.close();}catch(Exception e){}
        }
        return builder.toString();
    }

    public static String post(String paramString){
        StringBuilder builder = new StringBuilder();
        BufferedReader br = null;
        try
        {
            URL url = new URL(paramString);
            HttpURLConnection urlConnection =(HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(25000);
            urlConnection.setRequestMethod("POST");

            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String str;
            while ((str = br.readLine()) != null)
                builder.append(str);

        }catch (Exception e){
            builder.append(e.getMessage());
            logger.error(StackTrace.getRootCause(e,Constants.class.getName()));
        }finally {
            try{if(br!=null)br.close();}catch(Exception e){}
        }
        return builder.toString();
    }

    public static void  writeImage(HttpServletResponse response, File image) throws Exception {

        try(OutputStream out = response.getOutputStream();
            InputStream in = new FileInputStream(image)) {
            response.setContentType("image/png");
            int size = in.available();
            byte[] content = new byte[size];
            in.read(content);
            out.write(content);
        } catch (Exception e) {
            throw e;
        }
    }

}
