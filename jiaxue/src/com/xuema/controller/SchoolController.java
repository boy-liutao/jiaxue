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
import com.xuema.bean.School;
import com.xuema.service.DBService;
import com.xuema.util.JsonUtil;
import com.xuema.util.WebUtil;

@Controller
@LoginCheck
@RequestMapping(value="/school")
public class SchoolController extends BaseController{
    @Autowired
    DBService dbService;
    
    @RequestMapping(value = "listAll.do")
    public void listAll(HttpServletRequest request,HttpServletResponse response,String callback) throws JsonProcessingException, IOException{
        List<School> school = dbService.listSchool();
        WebUtil.response(response, callback, JsonUtil.getListObjectNode(school).toString());
    }
    
    @RequestMapping(value = "get.do")
    public void get(HttpServletRequest request,HttpServletResponse response,int sid, String callback) throws JsonProcessingException, IOException{
        School school = dbService.getSchool(sid);
        WebUtil.response(response,callback, JsonUtil.wrapJsonNodeResponse(JsonUtil.objectToJsonNode(school)).toString());
    }
}
