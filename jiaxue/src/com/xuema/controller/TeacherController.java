package com.xuema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xuema.bean.Manager;
import com.xuema.bean.User;
import com.xuema.service.DBService;
import com.xuema.util.SessionUtil;
import com.xuema.util.XuemaUtil;


@Controller
public class TeacherController {
    
	@Autowired
	DBService dbService;
	
	
    @RequestMapping(value = "/t/**")
    public ModelAndView t(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //check login
        Manager m = SessionUtil.getManagerInfo(request, dbService);
    	//get t value
    	String url = request.getRequestURI();
    	int index = url.indexOf("t/");
    	String t = url.substring(index + 2);
    	
    	
    	if (t.equals("loginTeacher")){
    	    ModelAndView v = new ModelAndView(t);
    	    return v;
    	} else if (m == null){
	        response.sendRedirect("http://open.weixin.qq.com/connect/oauth2/authorize?appid="+XuemaUtil.AppId+"&redirect_uri="+ XuemaUtil.domain_name + "/jiaxue/t/loginTeacher&response_type=code&scope=snsapi_base&state=wx#wechat_redirect");
	        return null;
    	}
    	ModelAndView v = new ModelAndView(t);
    	addAttribute(request, t, v);
        return v;
    } 
    
    private void addAttribute(HttpServletRequest request, String t, ModelAndView v) {
        if (t.equals("tchat")){
            String uid = request.getParameter("uid");
            User u = dbService.getUser(Integer.valueOf(uid));
            v.addObject("user", u);
        }
    }

    public boolean gotoBindPhone(String t, Manager user){
        if (user == null || StringUtils.isEmpty(user.getPhone())){
            return true;
        }
        return false;
    }
    
}
