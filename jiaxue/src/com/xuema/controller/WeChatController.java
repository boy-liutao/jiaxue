package com.xuema.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xuema.service.DBService;
import com.xuema.util.JsonUtil;
import com.xuema.util.WebUtil;
import com.xuema.wechat.WeChatHelper;

@Controller
public class WeChatController {
	@Autowired
	DBService dbService;
	
	private final Logger log = Logger.getLogger(WeChatController.class);

    @RequestMapping(value = "wechatjs.do")
    public void wechatjs(HttpServletRequest request,HttpServletResponse response, String url, String callback) throws Exception{
        Map<String, String> jsParameter = WeChatHelper.getJSParameter(url);
        WebUtil.response(response, WebUtil.packJsonp(callback, JsonUtil.getMapObjectNode(jsParameter).toString()));
    }
    
    @RequestMapping(value = "wechatcardjs.do")
    public void wechatcardjs(HttpServletRequest request,HttpServletResponse response, String url, String wxcardId, String callback) throws Exception{
        Map<String, String> jsParameter = WeChatHelper.getJSParameter(url, wxcardId);
        WebUtil.response(response, WebUtil.packJsonp(callback, JsonUtil.getMapObjectNode(jsParameter).toString()));
    }
    
}
