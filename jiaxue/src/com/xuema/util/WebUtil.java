package com.xuema.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuema.ResponseMessage;


public class WebUtil {

    public static Object getSessionAttribute(HttpServletRequest req, String key) {
        Object ret = null;

        try {
            ret = req.getSession(false).getAttribute(key);
        } catch (Exception e) {
        }

        return ret;
    }

    public static void response(HttpServletResponse response, String result) {
        PrintWriter pw = null;
        try {
            response.setContentType("application/json;charset=utf-8");
            pw = response.getWriter();
            pw.print(result);
            pw.flush();
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
    
    public static void responseHtml(HttpServletResponse response, String result) {
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.print(result);
            pw.flush();
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
    
    public static void response(HttpServletResponse response, String callback, String result) {
        response(response, WebUtil.packJsonp(callback, result));
    }
    
    public static void response(HttpServletResponse response, ResponseMessage result) {
        PrintWriter pw = null;
        try {
            response.setContentType("application/json;charset=utf-8");
            pw = response.getWriter();
            pw.print(JsonUtil.objectToJsonNode(result));
            pw.flush();
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    /*
     * public static Integer getLoginStatus(HttpServletRequest request){
     * HttpSession session=request.getSession();
     * if(session.getAttribute("userType")!=null){ return
     * Constants.loginStatusOk; } return Constants.loginStatusFailure; }
     */

    public static String packJsonp(String callback, String json) {
        if (json == null) {
            json = "";
        }
        if (callback == null || callback.isEmpty()) {
            return json;
        }

        return callback + "&&" + callback + '(' + json + ')';
    }
    
    public static String packJsonp(String callback, ResponseMessage response) {
        String json = null;
        if (response == null) {
            json = "";
        } else {
            json = JsonUtil.objectToJsonNode(response).toString();
        }
        if (callback == null || callback.isEmpty()) {
            return json;
        }

        return callback + "&&" + callback + '(' + json + ')';
    }
    
    public static boolean isWeixin(HttpServletRequest request){
        String agent=request.getHeader("User-Agent").toLowerCase();
        if (agent.indexOf("micromessenger") != -1){
            return true;
        }
        return false;
    }
    
}
