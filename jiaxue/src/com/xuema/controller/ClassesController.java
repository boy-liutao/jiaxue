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
import com.xuema.bean.Classes;
import com.xuema.bean.Manager;
import com.xuema.service.DBService;
import com.xuema.util.JsonUtil;
import com.xuema.util.SessionUtil;
import com.xuema.util.WebUtil;

@Controller
@LoginCheck
@RequestMapping(value="/classes")
public class ClassesController extends BaseController{
    @Autowired
    DBService dbService;
    
    @RequestMapping(value = "listByManagerSid.do")
    public void listBySid(HttpServletRequest request,HttpServletResponse response, int sid, String callback) throws JsonProcessingException, IOException{
        Manager m = SessionUtil.getLoginManager(request);
        List<Classes> classes = dbService.listClasses(m.getUsername(), sid);
        WebUtil.response(response, callback, JsonUtil.getListObjectNode(classes).toString());
    }

}
