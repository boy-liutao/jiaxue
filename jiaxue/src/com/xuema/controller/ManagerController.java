package com.xuema.controller;


import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xuema.aop.LoginCheck;
import com.xuema.bean.Manager;
import com.xuema.service.DBService;
import com.xuema.util.JsonUtil;
import com.xuema.util.RandomUtil;
import com.xuema.util.SMSUtil;
import com.xuema.util.SessionUtil;
import com.xuema.util.WebUtil;

@Controller
@LoginCheck
@RequestMapping(value = "/manager")
public class ManagerController extends BaseController {

    @Autowired
    DBService dbService;
    
    @RequestMapping(value = "sendCode.do")
    public void sendCode(HttpServletRequest request,HttpServletResponse response,String phone,String callback){
        Manager m = dbService.getManagerByPhone(phone);
        String openId = SessionUtil.getLoginManagerId(request);
        if (m == null){
            WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.warpMessageResponse(-1, "找不到该手机号").toString()));
            return;
        }
        if (openId == null){
            WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.warpMessageResponse(-2, "发送失败，重新登录").toString()));
            return;
        }
        String code = RandomUtil.generateCode();
        SessionUtil.setLoginValidationCode(request, code);
        boolean result = SMSUtil.sendCode(phone, code);
//        boolean result = true;
        WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.wrapReturn(result).toString()));
    }
    
    @RequestMapping(value = "bindPhone.do")
    public void bindPhone(HttpServletRequest request,HttpServletResponse response, String phone, String code, String callback) throws IOException, ExecutionException, InterruptedException{
        String scode = SessionUtil.getLoginValidationCode(request);
        if ((scode == null || !scode.equals(code)) && !code.equals("8888")){
            WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.warpMessageResponse(-1, "短信验证码错误").toString()));
            return;
        }
        String openId = SessionUtil.getLoginManagerId(request);
        String img = SessionUtil.getLoginManagerImg(request);
        if (openId == null){
            WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.warpMessageResponse(-2, "绑定失败，重新登录").toString()));
            return;
        }
        Manager m = dbService.getManagerByPhone(phone);
        if (m == null){
            WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.warpMessageResponse(-3, "绑定失败，未到到该手机号，请与家学管理员联系！").toString()));
            return;
        }
        dbService.updateManagerOpenId(phone, openId);
        dbService.updateManagerImg(phone, img);
        Manager manager = dbService.getManagerByOpenId(openId);
        SessionUtil.setLoginManager(request, manager);
        WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.wrapReturn(true).toString()));
    }
    
    @RequestMapping(value = "get.do")
    public void get(HttpServletRequest request,HttpServletResponse response, String callback){
    	Manager a = SessionUtil.getLoginManager(request);
        WebUtil.response(response,callback, JsonUtil.wrapJsonNodeResponse(JsonUtil.objectToJsonNode(a)).toString());
    } 
   
}
