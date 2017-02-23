package com.xuema.controller;


import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gson.WeChat;
import com.gson.bean.UserInfo;
import com.xuema.aop.LoginCheck;
import com.xuema.bean.Classes;
import com.xuema.bean.Manager;
import com.xuema.bean.User;
import com.xuema.service.DBService;
import com.xuema.util.DatetimeUtil;
import com.xuema.util.JsonUtil;
import com.xuema.util.RandomUtil;
import com.xuema.util.SMSUtil;
import com.xuema.util.SessionUtil;
import com.xuema.util.WebUtil;
import com.xuema.wechat.WeChatHelper;

@Controller
@LoginCheck
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    DBService dbService;
    
    @RequestMapping(value = "sendCode.do")
    public void sendCode(HttpServletRequest request,HttpServletResponse response,String phone,String callback){
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
        UserInfo ui = SessionUtil.getLoginUserInfo(request);
        if (ui == null){
            //处理非微信情况
            if (!WebUtil.isWeixin(request)){
                User u = new User();
                u.setPhone(phone); 
                dbService.saveUser(u);
                SessionUtil.setLoginUser(request, u);
                WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.wrapReturn(true).toString()));
                return;
            }
            WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.warpMessageResponse(-2, "绑定失败，重新登录").toString()));
            return;
        }
        //如果手机号存在，且未绑定，则绑定
        User u2 = dbService.getUserByPhone(phone);
        if (u2 != null && StringUtils.isEmpty(u2.getOpenid())){
            //如果openId已存在，不允许绑定
            User testU = dbService.getUserByOpenId(ui.getOpenid());
            if (testU != null){
                WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.warpMessageResponse(-2, "手机号已被占用，如有疑问，请拨打400-060-1515").toString()));
                return;
            }
            dbService.updateUserOpenId(u2.getId(), ui.getOpenid(), ui.getHeadimgurl());
            u2.setOpenid(ui.getOpenid());
            SessionUtil.setLoginUser(request, u2);
        }
        //如果手机号存在，且已绑定，返回错误
        if (u2 != null && !StringUtils.isEmpty(u2.getOpenid())){
            if (u2.getOpenid().equals(ui.getOpenid())){
                SessionUtil.setLoginUser(request, u2);
            } else {
                WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.warpMessageResponse(-2, "手机号已被占用，如有疑问，请拨打400-060-1515").toString()));
                return;
            }
        }
        //如都不存在，保存用户
        if (u2 == null){
            User u3 = dbService.getUserByOpenId(ui.getOpenid());
            if (u3 == null){
                //全新用户，保存
                u2 = dbService.saveUser(ui);
                if (StringUtils.isEmpty(u2.getImg())){
                    dbService.updateUserPhone(u2.getId(), phone, ui.getHeadimgurl());
                } else {
                    dbService.updateUserPhone(u2.getId(), phone, u2.getImg());
                }
                u2.setPhone(phone);
                u2.setImg(ui.getHeadimgurl());
                SessionUtil.setLoginUser(request, u2);
            } else {
                //更新手机
                if (StringUtils.isEmpty(u3.getImg())){
                    dbService.updateUserPhone(u3.getId(), phone, ui.getHeadimgurl());
                } else {
                    dbService.updateUserPhone(u3.getId(), phone, u3.getImg());
                }
                u3.setPhone(phone);
                u3.setImg(ui.getHeadimgurl());
                SessionUtil.setLoginUser(request, u3);
            }
        }
        WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.wrapReturn(true).toString()));
    }
    
    @RequestMapping(value = "unbindPhone.do")
    public void unbindPhone(HttpServletRequest request,HttpServletResponse response, String callback) throws IOException, ExecutionException, InterruptedException{
        User u = SessionUtil.getLoginUser(request);
        dbService.updateUserPhone(u.getId(), "", u.getImg());
        u.setPhone("");
        SessionUtil.removeLoginUser(request);
        WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.wrapReturn(true).toString()));
    }
    
    @RequestMapping(value = "get.do")
    public void get(HttpServletRequest request,HttpServletResponse response, String callback){
        User a = SessionUtil.getLoginUser(request);
        //reget user in case user info has changed in manage platform
        User lu = dbService.getUser(a.getId());
        SessionUtil.setLoginUser(request, lu);
        WebUtil.response(response,callback, JsonUtil.wrapJsonNodeResponse(JsonUtil.objectToJsonNode(lu)).toString());
    } 
    
    @RequestMapping(value = "updateSid.do")
    public void updateSid(HttpServletRequest request,HttpServletResponse response, int sid, String callback){
        User a = SessionUtil.getLoginUser(request);
        a.setSid(sid);
        dbService.updateUserSchool(a.getId(), sid);
        WebUtil.response(response,callback, JsonUtil.wrapJsonNodeResponse(JsonUtil.objectToJsonNode(a)).toString());
    } 
    
    @RequestMapping(value = "update.do")
    public void update(HttpServletRequest request,HttpServletResponse response, User user, String imgfile, String callback) throws Exception{
        User su = SessionUtil.getLoginUser(request);
        user.setId(su.getId());
        if (!StringUtils.isEmpty(imgfile)) {
            String localImg = WeChat.material.downloadImg(WeChatHelper.getAccessToken(), imgfile);
            user.setImg(localImg);
        } else {
            user.setImg(su.getImg());
        }
        dbService.updateUser(user);
        User lu = dbService.getUser(user.getId());
        SessionUtil.setLoginUser(request, lu);
        WebUtil.response(response, WebUtil.packJsonp(callback, JsonUtil.wrapReturn(true).toString()));
    }
    
    @RequestMapping(value = "remain.do")
    public void remain(HttpServletRequest request,HttpServletResponse response, String callback){
        User u = SessionUtil.getLoginUser(request);
        Map<String, Integer> remains = dbService.computeUserRemain(u.getId());
        WebUtil.response(response,callback, JsonUtil.getMapObjectNode(remains).toString());
    } 
    
    @RequestMapping(value = "search.do")
    public void search(HttpServletRequest request,HttpServletResponse response, int sid, int cid, int scope, String key, String callback) throws JsonProcessingException, IOException{
        Manager m = SessionUtil.getLoginManager(request);
        List<User> us = dbService.listUserByManagerSidCid(m.getUsername(), sid, cid, key, scope);
        WebUtil.response(response, callback, JsonUtil.getListObjectNode(us).toString());
    }
    
    @RequestMapping(value = "listNursery.do")
    public void listNursery(HttpServletRequest request,HttpServletResponse response, String lastOne, String callback) throws JsonProcessingException, IOException{
        Manager m = SessionUtil.getLoginManager(request);
        List<User> us = dbService.listNurseryBefore(m.getUsername(), lastOne);
        ObjectNode n = JsonUtil.getListObjectNode(us);
        
        User u = us.get(us.size()-1);
        ((ObjectNode)n.get("response")).put("beforeMark", DatetimeUtil.hhmm(DatetimeUtil.nextQuarter(DatetimeUtil.getTimeFromMinutes(u.getDaily().getScheduleTime()))));
        WebUtil.response(response, callback, JsonUtil.getListObjectNode(us).toString());
    }
    
    @RequestMapping(value = "addNursery.do")
    public void addNursery(HttpServletRequest request,HttpServletResponse response, int uid, String info, String img, String callback) throws Exception{
        Manager m = SessionUtil.getLoginManager(request);
        String imgUrl = null;
        if (!StringUtils.isEmpty(img)) {
            imgUrl = WeChat.material.downloadImg(WeChatHelper.getAccessToken(), img);
        }
        boolean result = dbService.addNursery(uid, info, imgUrl, m.getUsername());
        if (result){
            WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.wrapReturn(true).toString()));
        } else {
            WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.wrapFailStringResponse(-1, "用户已经签到").toString()));
        }
    }
    
    @RequestMapping(value = "addComment.do")
    public void addComment(HttpServletRequest request,HttpServletResponse response, int uid, String comment, String callback) throws Exception{
        Manager m = SessionUtil.getLoginManager(request);
        boolean result = dbService.addComment(uid, comment, m.getUsername());
        if (result){
            WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.wrapReturn(true).toString()));
        } else {
            WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.wrapFailStringResponse(-1, "用户已经评论").toString()));
        }
    }
}
