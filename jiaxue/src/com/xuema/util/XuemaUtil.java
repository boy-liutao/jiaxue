package com.xuema.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.gson.util.ConfKit;



public class XuemaUtil {
    public static final String HOST_ROOT = "/jiaxue";
    
    public static final String domain_name = ConfKit.get("domain_name");
    
    public static final String AppId = ConfKit.get("AppId"); 
    
    public static final String WX_REDIRECT_HEAD = "http://open.weixin.qq.com/connect/oauth2/authorize?appid="+AppId+"&redirect_uri=";
    
    public static final String WX_REDIRECT_TAIL = "&response_type=code&scope=snsapi_base&state=wx#wechat_redirect";

    public static final String LOGIN_MANAGER_KEY = "login_manager";

    public static final String SPLITTER = ",";
    
    private static Properties prop_ = new Properties();
    
    public static String XUEMA_HOME = System.getenv("XUEMA_HOME");
    static {
        if (XUEMA_HOME == null){
            XUEMA_HOME = "d:/temp";
        }
        InputStream inStream = XuemaUtil.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            prop_.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getProperty(String key){
        Object obj = prop_.get(key);
        return obj == null ? null:obj.toString();
    }
    public static void main(String[] args) {
        System.out.println(getProperty("domain"));
    }

}
