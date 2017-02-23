package com.xuema.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xuema.aop.LoginCheck;
import com.xuema.bean.Daily;
import com.xuema.bean.User;
import com.xuema.service.DBService;
import com.xuema.util.JsonUtil;
import com.xuema.util.SessionUtil;
import com.xuema.util.WebUtil;

@Controller
@LoginCheck
@RequestMapping(value="/daily")
public class DailyController extends BaseController{
    @Autowired
    DBService dbService;
    
    @RequestMapping(value = "list.do")
    public void list(HttpServletRequest request,HttpServletResponse response, String callback) throws JsonProcessingException, IOException{
        User u = SessionUtil.getLoginUser(request);
        List<Daily> daily = dbService.listDaily(u.getId());
        WebUtil.response(response, callback, JsonUtil.getListObjectNode(daily).toString());
    }
    
}
