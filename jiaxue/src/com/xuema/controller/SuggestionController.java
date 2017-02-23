package com.xuema.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xuema.aop.LoginCheck;
import com.xuema.bean.Suggestion;
import com.xuema.bean.User;
import com.xuema.service.DBService;
import com.xuema.util.JsonUtil;
import com.xuema.util.SessionUtil;
import com.xuema.util.WebUtil;

@Controller
@LoginCheck
@RequestMapping(value="/suggestion")
public class SuggestionController extends BaseController{
    @Autowired
    DBService dbService;
    
    
    @RequestMapping(value = "add.do")
    public void add(HttpServletRequest request,HttpServletResponse response,Suggestion suggestion , String callback){
    	 User a = SessionUtil.getLoginUser(request);
    	 suggestion.setPhone(a.getPhone());
    	 suggestion.setSubmitter(a.getParent());
    	dbService.addSuggestion(suggestion);
        WebUtil.response(response, WebUtil.packJsonp(callback, JsonUtil.wrapReturn(true).toString()));
    }
}
