package com.xuema.wechat;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.gson.WeChat;
import com.gson.bean.UserInfo;
import com.gson.util.ConfKit;
import com.gson.util.HttpKit;
import com.gson.util.SHA1;
import com.xuema.util.BeanUtil;
import com.xuema.util.JsonUtil;

public class WeChatHelper {
	private static long time = System.currentTimeMillis();
	private static String token = null;
	
	private static long jstime = System.currentTimeMillis();
    private static String jstoken = null;
    
    private static String apitoken = null;
    private static long apitime = System.currentTimeMillis();
    
    
	public static synchronized String getAccessToken() throws Exception{
		long now = System.currentTimeMillis();
		if (token == null){
			token = WeChat.getAccessToken();
		} else {
			if ((now - time) > 7000000){
				time = now;
				token = WeChat.getAccessToken();
			}
		}
		return token;
	}
	
	public static synchronized Object[] getAccessTokenAndExpireTime() throws Exception{
		long now = System.currentTimeMillis();
		if (token == null){
			token = WeChat.getAccessToken();
		} else {
			if ((now - time) > 7000000){
				time = now;
				token = WeChat.getAccessToken();
			}
		}
		return new Object[]{token, time};
	}
	
	public static UserInfo getUesrInfo(String openId) throws Exception{
        UserInfo userInfo = null;
        try {
            userInfo = WeChat.user.getUserInfo(WeChatHelper.getAccessToken(), openId);
        } catch (Exception e){
            if ("invalid credential".equals(e.getMessage())){
                WeChatHelper.refreshAccessToken();
            }
            userInfo = WeChat.user.getUserInfo(WeChatHelper.getAccessToken(), openId);
        }
        return userInfo;
    }
	
	public static Map<String, String> getJSParameter(String url) throws Exception{
        String token = getJSToken();
        return sign(token, url);
    }
	
	public static Map<String, String> getJSParameter(String url, String wxcardId) throws Exception{
        String token = getJSToken();
        Map<String, String> sign =  sign(token, url);
        //add api sign
        String timestamp = sign.get("timestamp");
        String nonceStr = sign.get("nonceStr");
        String apiTicket = getAPITicket();
        String codeSign = signCoupon(timestamp, apiTicket, nonceStr, wxcardId);
        sign.put("cardsign", codeSign);
        return sign;
    }
	
	public static synchronized String getAPITicket() throws Exception{
        long now = System.currentTimeMillis();
        String accessToken = getAccessToken();
        if (apitoken == null){
            apitoken = WeChat.getAPIToken(accessToken);
        } else {
            if ((now - apitime) > 7000000){
                apitime = now;
                apitoken = WeChat.getAPIToken(accessToken);
            }
        }
        return apitoken;
    }
    
    public static String signCoupon(String timestamp, String api_ticket, String nonce_str, String card_id) {
        List<String> ps = new ArrayList<String>();
        ps.add(timestamp);
        ps.add(api_ticket);
        ps.add(nonce_str);
        ps.add(card_id);
        Collections.sort(ps);
        String str = BeanUtil.arrayToString(ps.toArray(new String[ps.size()]), "");
        return SHA1.encode(str);
    }
	
	public static synchronized String getJSToken() throws Exception{
        long now = System.currentTimeMillis();
        String accessToken = getAccessToken();
        if (jstoken == null){
            jstoken = WeChat.getJSToken(accessToken);
        } else {
            if ((now - jstime) > 7000000){
                jstime = now;
                jstoken = WeChat.getJSToken(accessToken);
            }
        }
        return jstoken;
    }
    
    public static Map<String, String> sign(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        ret.put("appId", ConfKit.get("AppId"));
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return ret;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

	public static synchronized void refreshAccessToken() throws Exception {
		token = WeChat.getAccessToken();
		time = System.currentTimeMillis();
	}
	
}
