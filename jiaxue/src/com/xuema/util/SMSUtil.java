package com.xuema.util;

import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Date;

public class SMSUtil {
    
    private static String url = "http://si.800617.com:4400/SendLenSms.aspx?un={0}&pwd={1}&mobile={2}&msg={3}";
    
    
    public static boolean sendCode(String phone, String code){
        String msg = "【家学天地】"+code+"（i家学验证码，10分钟内有效）";
        return sendMsg(phone, msg);
    }
    
    static String orderMsg = "【i家学】您已成功报名家学托管，托管地点：{0}，入托时间：{1}，关注“家学托管”公众号，随时了解更多托管信息。";
    public static boolean sendOrderMessage(String phone, String schoolName, Date startDate){
        String msg = MessageFormat.format(orderMsg, schoolName, DatetimeUtil.formatDate(startDate));
        return sendMsg(phone, msg);
    }
    
    static String renewMsg = "【i家学】陈小涛在家学托管班的水果加餐/晚餐的可用次数只剩下5次了，现在去续费吧！";
    public static boolean sendRenewMessage(String phone){
        return sendMsg(phone, renewMsg);
    }
    
    static String nurseryMsg = "【i家学】陈小涛在家学天地的托管服务到5月1日就到期了，现在去续费吧！";
    public static boolean sendNurseryMessage(String phone){
        return sendMsg(phone, nurseryMsg);
    }
    
    private static boolean sendMsg(String phone, String msg){
        try {
            String callUrl = MessageFormat.format(url, "bjjxtd-1","4216ac",phone, URLEncoder.encode(msg,"gb2312"));
            String res = HTTPUtil.doGet(callUrl);
            System.out.println(res);
            int s = res.indexOf("<Result>");
            int e = res.indexOf("</Result>");
            if (s > 0 && e > 0){
                String retCode = res.substring(s + 8, e);
                if (retCode != null && retCode.equals("1"))
                    return true;
            } 
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
    public static void main(String[] args) {
        sendCode("13426430224", "123456");
    }
}
