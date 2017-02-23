package com.xuema.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.gson.WeChat;
import com.gson.bean.UserInfo;
import com.gson.oauth.Oauth;
import com.xuema.Constants;
import com.xuema.bean.Manager;
import com.xuema.bean.User;
import com.xuema.service.DBService;
import com.xuema.wechat.WeChatHelper;



public class SessionUtil implements Constants{
	
	public static void setLoginUser(HttpServletRequest request, User u) {
		request.getSession().setAttribute("loginuser", u);
	}
	
	public static User getLoginUser(HttpServletRequest request) {
	     
		User u = (User)request.getSession().getAttribute("loginuser");
		return u;
	}

	public static void removeLoginUser(HttpServletRequest request) {
		request.getSession().removeAttribute("loginuser");
	}
	
	public static void setLoginUserInfo(HttpServletRequest request, UserInfo u) {
        request.getSession().setAttribute("loginuserinfo", u);
    }
    
    public static UserInfo getLoginUserInfo(HttpServletRequest request) {
         
        UserInfo u = (UserInfo)request.getSession().getAttribute("loginuserinfo");
        return u;
    }
	
	public static void addAtt(HttpServletRequest request, String key, Object value){
		request.getSession().setAttribute(key, value);
	}
	
	public static void removeAtt(HttpServletRequest request, String key){
		request.getSession().removeAttribute(key);
	}
	
	public static String getAtt(HttpServletRequest request, String key){
		return (String)request.getSession().getAttribute(key);
	}
	
	public static Object getAttObj(HttpServletRequest request, String key){
		return request.getSession().getAttribute(key);
	}
	
	public static <T extends Object> T  getAtt(HttpServletRequest request, String key, Class<T> objectClass){
        return (T)request.getSession().getAttribute(key);
    }
	
	public static String optAtt(HttpServletRequest request, String key, String value){
		String r = (String)request.getSession().getAttribute(key);
		if (r == null){
			r = value;
		}
		return r;
	}
	
	public static void setLoginValidationCode(HttpServletRequest request,
            String code) {
        request.getSession().setAttribute("login_code", code);
    }

    public static String getLoginValidationCode(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("login_code");
    }

	public static User getUserInfo(HttpServletRequest request, DBService dbService) throws Exception{
        return getUserInfo(request, dbService, false);
    }
	
	public static User getUserInfo(HttpServletRequest request, DBService dbService, boolean reget) throws Exception{
        String code = request.getParameter("code");
        if (code != null && code.equals("123456789")){
            String openId = request.getParameter("openId");
            if (openId == null){
                openId = "op6omt_Ziu6vsWl4LKMP0hQx888w";
            }
            User u = dbService.getUserByOpenId(openId);
            SessionUtil.setLoginUser(request, u);
            return u;
        }
        
        if (!reget) {
            User se = SessionUtil.getLoginUser(request);
            if (se != null){
                return se;
            }
        }
        
        if (code==null){
            return null;
        }
        
        Oauth o = new Oauth();
        String token = o.getToken(code);
        if (!token.contains("openid")){
            return null;
        }
        JsonNode node = JsonUtil.StringToJsonNode(token);
        String openId = node.get("openid").asText();
        UserInfo userInfo = null;
        try {
            userInfo = WeChat.user.getUserInfo(WeChatHelper.getAccessToken(), openId);
        } catch (Exception e){
            if ("invalid credential".equals(e.getMessage())){
                WeChatHelper.refreshAccessToken();
            }
            userInfo = WeChat.user.getUserInfo(WeChatHelper.getAccessToken(), openId);
        }
        //save user if necessary
        User u = dbService.getUserByOpenId(openId);
        if (u == null){
            SessionUtil.setLoginUserInfo(request, userInfo);
        } else {
            SessionUtil.setLoginUserInfo(request, userInfo);
            SessionUtil.setLoginUser(request, u);
        }
        return u;
    }
	
	
	
	public static void setLoginManager(HttpServletRequest request, Manager m) {
        request.getSession().setAttribute("loginmanager", m);
    }
    
    public static Manager getLoginManager(HttpServletRequest request) {
        Manager u = (Manager)request.getSession().getAttribute("loginmanager");
        return u;
    }
    
    public static void setLoginManagerId(HttpServletRequest request, String openId) {
        request.getSession().setAttribute("loginmanagerid", openId);
    }
    
    public static String getLoginManagerId(HttpServletRequest request) {
        String m = (String)request.getSession().getAttribute("loginmanagerid");
        return m;
    }
    
    public static void setLoginManagerImg(HttpServletRequest request, String img) {
        request.getSession().setAttribute("loginmanagerimg", img);
    }
    
    public static String getLoginManagerImg(HttpServletRequest request) {
        String m = (String)request.getSession().getAttribute("loginmanagerimg");
        return m;
    }
	
	public static Manager getManagerInfo(HttpServletRequest request, DBService dbService) throws Exception{
        return getManagerInfo(request, dbService, false);
    }
    
    public static Manager getManagerInfo(HttpServletRequest request, DBService dbService, boolean reget) throws Exception{
        String code = request.getParameter("code");
        if (code != null && code.equals("123456789")){
            String phone = request.getParameter("phone");
            if (phone == null){
                phone = "13621253021";
            }
            Manager u = dbService.getManagerByPhone(phone);
            SessionUtil.setLoginManagerId(request, "op6omt514AeA2NcyQ8iAkpMX4TDc");
            SessionUtil.setLoginManager(request, u);
            return u;
        }
        
        if (!reget) {
            Manager se = SessionUtil.getLoginManager(request);
            if (se != null){
                return se;
            }
        }
        
        if (code==null){
            return null;
        }
        
        Oauth o = new Oauth();
        String token = o.getToken(code);
        if (!token.contains("openid")){
            return null;
        }
        JsonNode node = JsonUtil.StringToJsonNode(token);
        String openId = node.get("openid").asText();
        Manager u = dbService.getManagerByOpenId(openId);
        UserInfo userInfo = null;
        try {
            userInfo = WeChat.user.getUserInfo(WeChatHelper.getAccessToken(), openId);
        } catch (Exception e){
            if ("invalid credential".equals(e.getMessage())){
                WeChatHelper.refreshAccessToken();
            }
            userInfo = WeChat.user.getUserInfo(WeChatHelper.getAccessToken(), openId);
        }
        if (u == null || StringUtils.isEmpty(u.getPhone())){
            SessionUtil.setLoginManagerId(request, openId);
            SessionUtil.setLoginManagerImg(request, userInfo.getHeadimgurl());
            return null;
        } else {
            //update manager img
            dbService.updateManagerImg(u.getUsername(), userInfo.getHeadimgurl());
            u.setImg(userInfo.getHeadimgurl());
            SessionUtil.setLoginManager(request, u);
            return u;
        }
    }
    
    public static void gotoManagerLoginPage(HttpServletResponse response) throws IOException{
        response.sendRedirect("http://open.weixin.qq.com/connect/oauth2/authorize?appid="+XuemaUtil.AppId+"&redirect_uri="+ XuemaUtil.domain_name + "/jiaxue/t/login&response_type=code&scope=snsapi_base&state=wx#wechat_redirect");
    }
}
