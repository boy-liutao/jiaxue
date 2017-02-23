package com.xuema.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gson.WeChat;
import com.xuema.aop.LoginCheck;
import com.xuema.bean.Chat;
import com.xuema.bean.Daily;
import com.xuema.bean.Manager;
import com.xuema.bean.User;
import com.xuema.service.DBService;
import com.xuema.util.JsonUtil;
import com.xuema.util.SessionUtil;
import com.xuema.util.WebUtil;
import com.xuema.wechat.WeChatHelper;

@Controller
@LoginCheck
@RequestMapping(value = "/chat")
public class ChatController extends BaseController {

    @Autowired
    DBService dbService;
    
    @RequestMapping(value = "list.do")
    public void get(HttpServletRequest request,HttpServletResponse response, String callback){
        User a = SessionUtil.getLoginUser(request);
        if(a!=null){
        	List<Chat> cs = dbService.listChat(a.getId());
        	WebUtil.response(response, callback, JsonUtil.getListObjectNode(cs).toString());
        }
    } 
    
    @RequestMapping(value = "add.do")
    public void add(HttpServletRequest request,HttpServletResponse response,int uid,String manager,String imgs, String content, boolean toUser, String callback) throws Exception{
    	Chat chat=new Chat();
    	chat.setUid(uid);
    	chat.setManager(manager);
    	String imgUrl = null;
        if (!StringUtils.isEmpty(imgs)) {
            imgUrl = WeChat.material.downloadImg(WeChatHelper.getAccessToken(), imgs);
            chat.setImgs(imgUrl);
        }
    	chat.setContent(content);
    	chat.setReadStatus(0);
    	chat.setToUser(toUser);
    	dbService.addChat(chat);
    	WebUtil.response(response,callback, JsonUtil.wrapJsonNodeResponse(JsonUtil.objectToJsonNode(chat)).toString());
    }
    
    @RequestMapping(value = "loadOlder.do")
    public void loadOlder(HttpServletRequest request,HttpServletResponse response, String manager, int mark, int size, String callback){
    	User u = SessionUtil.getLoginUser(request);
    	List<Chat> cs=dbService.loadOlderChat(u.getId(),manager, mark, size);
    	if(cs!=null){
    		WebUtil.response(response, callback, JsonUtil.getListObjectNode(cs).toString());
    	}
    } 
    
    @RequestMapping(value = "loadNewer.do")
    public void loadNewer(HttpServletRequest request,HttpServletResponse response, String manager, int mark, String callback){
        User u = SessionUtil.getLoginUser(request);
        if (u == null)
            return;
        List<Chat> cs=dbService.loadNewerChat(u.getId(),manager, mark);
        if(cs!=null){
            WebUtil.response(response, callback, JsonUtil.getListObjectNode(cs).toString());
        }
    } 
    
    @RequestMapping(value = "loadUserNewer.do")
    public void loadNewer(HttpServletRequest request,HttpServletResponse response, int uid, int mark, Date afterDate, String callback){
        Manager m = SessionUtil.getLoginManager(request);
        if (m == null)
            return;
        List<Chat> cs= null;
        if (mark > 0){
            cs = dbService.loadNewerChat(uid,m.getUsername(), mark);
        } else {
            cs = dbService.loadNewerChat(uid,m.getUsername(), afterDate);
        }
        if(cs!=null){
            WebUtil.response(response, callback, JsonUtil.getListObjectNode(cs).toString());
        }
    }
    
    @RequestMapping(value = "loadByDate.do")
    public void loadByDate(HttpServletRequest request,HttpServletResponse response, int uid, Date date, String callback) throws ParseException{
        Manager m = SessionUtil.getLoginManager(request);
        Daily d = dbService.getDaily(uid, date);
        List<Chat> cs=dbService.loadChatByDate(uid, m.getUsername(), d.getDate(), date);
        ObjectNode node = JsonUtil.getListObjectNode(cs);
        ((ObjectNode)node.get("response")).put("daily", JsonUtil.objectToJsonNode(d));
        if(cs!=null){
            WebUtil.response(response, callback, node.toString());
        }
    } 
}
