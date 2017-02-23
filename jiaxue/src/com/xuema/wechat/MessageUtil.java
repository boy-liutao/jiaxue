package com.xuema.wechat;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.gson.WeChat;
import com.gson.bean.Articles;
import com.gson.bean.TemplateData;
import com.xuema.Constants;
import com.xuema.exception.XuemaException;

public class MessageUtil implements Constants{
    
    public static String sendWXMessage(String openId, String msg) throws XuemaException{
        try {
            String sendText = WeChat.message.sendText(WeChatHelper.getAccessToken(), openId, msg);
            return sendText;
            
        } catch (Exception e) {
            throw new XuemaException(e);
        }
    }
    
    /**
     * send wx message; if fail, send template message
     * @param openId
     * @param msg
     * @return
     * @throws XuemaException
     */
    public static boolean sendWXMessageForce(String openId, String msg, TemplateData tempData) throws XuemaException{
        //测试中，为防止误发消息给用户，先停止
        if (true){
            return true;
        }
        try {
            //send wx message
            String result = WeChat.message.sendText(WeChatHelper.getAccessToken(), openId, msg);
            JSONObject r = JSONObject.parseObject(result);
            if (r != null){
                String code = r.getString("errcode");
                if (code != null && code.equals("0")){
                    return true;
                }
            }
            
            //wx send fail, change to use template
            r = WeChat.message.templateSend(WeChatHelper.getAccessToken(), tempData);
            if (r != null){
                String code = r.getString("errcode");
                if (code != null && code.equals("0")){
                    return true;
                }
            }
            
            return false;
        } catch (Exception e) {
            throw new XuemaException(e);
        }
    }
    
    public static boolean sendWXTemlplate(TemplateData tempData) throws Exception{
    	JSONObject r = WeChat.message.templateSend(WeChatHelper.getAccessToken(), tempData);
         if (r != null){
             String code = r.getString("errcode");
             if (code != null && code.equals("0")){
                 return true;
             }
         }
         return false;
    }
    
    
    public static void sendWXNewsMessage(String openId, List<Articles> as ) throws XuemaException{
        try {
            WeChat.message.SendNews(WeChatHelper.getAccessToken(), openId, as);
        } catch (Exception e) {
            throw new XuemaException(e);
        }
    }
    
    public static void main(String[] args) throws Exception {
        
    }
    
}
