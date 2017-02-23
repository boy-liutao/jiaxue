package com.xuema.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xuema.aop.LoginCheck;
import com.xuema.bean.Activity;
import com.xuema.service.DBService;
import com.xuema.util.JsonUtil;
import com.xuema.util.WebUtil;

@Controller
@LoginCheck
@RequestMapping(value="/activity")
public class ActivityController extends BaseController{
    @Autowired
    DBService dbService;
    
    @RequestMapping(value = "get.do")
    public void get(HttpServletRequest request,HttpServletResponse response, int id, String callback){
    	Activity a = dbService.getActivity(id);
        WebUtil.response(response,callback, JsonUtil.wrapJsonNodeResponse(JsonUtil.objectToJsonNode(a)).toString());
    } 
}
