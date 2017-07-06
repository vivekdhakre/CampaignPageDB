package com.campaign.util;


/**
 * @author Vivek
 */

public class StackTrace {


    public static String getRootCause(Exception e, String className) {
        for (StackTraceElement element : e.getStackTrace()) {
            if (element.getClassName().equals(className)) {
                return "Exception : " + e + " at  [" + element + " ]";
            }
        }
        return null;
    }

}
