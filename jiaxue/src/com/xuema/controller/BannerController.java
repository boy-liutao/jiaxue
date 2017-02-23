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
import com.xuema.bean.Banner;
import com.xuema.service.DBService;
import com.xuema.util.JsonUtil;
import com.xuema.util.WebUtil;

@Controller
@LoginCheck
@RequestMapping(value="/banner")
public class BannerController extends BaseController{
    @Autowired
    DBService dbService;
    
    @RequestMapping(value = "list.do")
    public void list(HttpServletRequest request,HttpServletResponse response, String callback) throws JsonProcessingException, IOException{
        List<Banner> banner = dbService.listBanner();
       
        WebUtil.response(response, callback, JsonUtil.getListObjectNode(banner).toString());
    }
    
    @RequestMapping(value = "get.do")
    public void get(HttpServletRequest request,HttpServletResponse response, int id, String callback){
    	Banner a = dbService.getBanner(id);
        WebUtil.response(response,callback, JsonUtil.wrapJsonNodeResponse(JsonUtil.objectToJsonNode(a)).toString());
    } 
}
