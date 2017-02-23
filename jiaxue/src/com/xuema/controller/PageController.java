package com.xuema.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gson.bean.UserInfo;
import com.xuema.bean.Orders;
import com.xuema.bean.User;
import com.xuema.service.DBService;
import com.xuema.util.SessionUtil;
import com.xuema.util.WebUtil;
import com.xuema.util.XuemaUtil;


@Controller
public class PageController {
    
	@Autowired
	DBService dbService;
	
	static Set<String> phoneRequirePages = new HashSet<String>();
    static {
        phoneRequirePages.add("enroll");
        phoneRequirePages.add("babyArrived");
        phoneRequirePages.add("myCommentList");
        phoneRequirePages.add("personalCenter");
        phoneRequirePages.add("news");
    }
    static Set<String> excludeUserInfoPages = new HashSet<String>();
    static {
        excludeUserInfoPages.add("index");
        excludeUserInfoPages.add("newsDetail");
        excludeUserInfoPages.add("paytest");
    }
	
    @RequestMapping(value = "/page/**")
    public ModelAndView page(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //check login
        User user = SessionUtil.getUserInfo(request, dbService);
    	//get page value
    	String url = request.getRequestURI();
    	int index = url.indexOf("page/");
    	String page = url.substring(index + 5);
    	
    	if (gotoBindPhone(page, user)){
    	    if (WebUtil.isWeixin(request)){
    	        response.sendRedirect("http://open.weixin.qq.com/connect/oauth2/authorize?appid="+XuemaUtil.AppId+"&redirect_uri=" + XuemaUtil.domain_name + "/jiaxue/page/login&response_type=code&scope=snsapi_base&state=wx#wechat_redirect");
    	    } else {
    	        response.sendRedirect(XuemaUtil.domain_name + "/jiaxue/page/login");
    	    }
    	    return null;
    	}
    	
    	if (page.equals("login")){
    	    //如果已经绑定，且有手机号，直接进入index
    	    if (user != null && !StringUtils.isEmpty(user.getPhone())){
    	        ModelAndView v = new ModelAndView("index");
    	        return null;
    	    }
    	    ModelAndView v = new ModelAndView(page);
    	    UserInfo ui = SessionUtil.getLoginUserInfo(request);
    	    if (ui == null){
    	        if (WebUtil.isWeixin(request)){
    	            response.sendRedirect("http://open.weixin.qq.com/connect/oauth2/authorize?appid="+XuemaUtil.AppId+"&redirect_uri=" + XuemaUtil.domain_name + "/jiaxue/page/login&response_type=code&scope=snsapi_base&state=wx#wechat_redirect");
    	        } else {
    	            addAttribute(request, page, v);
    	            return v;
                }
    	        return null;
    	    }
    	    v.addObject("openId", ui.getOpenid());
    	} else if (user == null && !excludeUserInfoPages.contains(page)){
    	    if (WebUtil.isWeixin(request)){
    	        response.sendRedirect("http://open.weixin.qq.com/connect/oauth2/authorize?appid="+XuemaUtil.AppId+"&redirect_uri=" + XuemaUtil.domain_name + "/jiaxue/page/"+page+"&response_type=code&scope=snsapi_base&state=wx#wechat_redirect");
    	    } else {
    	        response.sendRedirect(XuemaUtil.domain_name + "/jiaxue/page/"+page);
    	    }
	        return null;
    	}
    	
    	ModelAndView v = new ModelAndView(page);
    	addAttribute(request, page, v);
        return v;
    } 
    
    public boolean gotoBindPhone(String page, User user){
        if (needPhone(page)){
            if (user == null || StringUtils.isEmpty(user.getPhone())){
                return true;
            }
        }
        return false;
    }
    
    private boolean needPhone(String page){
        if (phoneRequirePages.contains(page)){
            return true;
        }
        return false;
    }
    
    private void addAttribute(HttpServletRequest request, String page, ModelAndView v) {
        if (page.equals("orderConfirm")){
            String oid = request.getParameter("oid");
            if (!StringUtils.isEmpty(oid)){
                Orders o = dbService.getOrder(oid);
                v.addObject("orders", o);
            }
        }
    }
}
