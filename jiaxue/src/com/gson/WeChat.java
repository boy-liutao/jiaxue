/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.gson;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gson.bean.Attachment;
import com.gson.bean.InMessage;
import com.gson.bean.OutMessage;
import com.gson.oauth.Group;
import com.gson.oauth.Material;
import com.gson.oauth.Menu;
import com.gson.oauth.Message;
import com.gson.oauth.Qrcod;
import com.gson.oauth.User;
import com.gson.oauth.Order;
import com.gson.util.ConfKit;
import com.gson.util.HttpKit;
import com.gson.util.Tools;
import com.gson.util.XStreamFactory;
import com.thoughtworks.xstream.XStream;

/**
 * 微信常用的API
 *
 * @author L.cm & ____′↘夏悸
 * @date 2013-11-5 下午3:01:20
 */
public class WeChat {
	private static final String ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	private static final String PAYFEEDBACK_URL = "https://api.weixin.qq.com/payfeedback/update";
	private static final String GET_MEDIA_URL= "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=";
	private static final String UPLOAD_MEDIA_URL= "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=";
	private static final String JSTOKEN_URL= "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=";
    private static final String APITOKEN_URL= "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=";
    /**
     * 消息操作接口
     */
    public static final Message message = new Message();
    /**
     * 菜单操作接口
     */
    public static final Menu menu = new Menu();
    /**
     * 用户操作接口
     */
    public static final User user = new User();
    /**
     * 分组操作接口
     */
    public static final Group group = new Group();
    
    /**
     * 分组操作接口
     */
    public static final Qrcod qrcod = new Qrcod();

    /**
     * 永久素材接口
     */
    public static final Material material = new Material();
    
    /**
     * 订单
     */
    public static final Order order = new Order();
    /**
     * 获取access_token
     * @return
     * @throws Exception
     */
    public static String getAccessToken() throws Exception {
        String appid = ConfKit.get("AppId");
        String secret = ConfKit.get("AppSecret");
        String jsonStr = HttpKit.get(ACCESSTOKEN_URL.concat("&appid=") + appid + "&secret=" + secret);
        Map<String, Object> map = JSONObject.parseObject(jsonStr);
        return map.get("access_token").toString();
    }
    
    /**
     * 获取access_token
     * @return
     * @throws Exception
     */
    public static String getAccessToken(String appid,String secret) throws Exception {
        String jsonStr = HttpKit.get(ACCESSTOKEN_URL.concat("&appid=") + appid + "&secret=" + secret);
        Map<String, Object> map = JSONObject.parseObject(jsonStr);
        return map.get("access_token").toString();
    }

   /**
    * 支付反馈
    * @param openid
    * @param feedbackid
    * @return
    * @throws Exception
    */
    public static boolean payfeedback(String openid, String feedbackid) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String accessToken = getAccessToken();
        map.put("access_token", accessToken);
        map.put("openid", openid);
        map.put("feedbackid", feedbackid);
        String jsonStr = HttpKit.get(PAYFEEDBACK_URL, map);
        Map<String, Object> jsonMap = JSONObject.parseObject(jsonStr);
        return "0".equals(jsonMap.get("errcode").toString());
    }

    /**
     * 签名检查
     * @param token
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static Boolean checkSignature(String token, String signature, String timestamp, String nonce) {
        return Tools.checkSignature(token, signature, timestamp, nonce);
    }


    /**
     * 设置发送消息体
     * @param oms
     * @param msg
     * @throws Exception
     */
    private static void setMsgInfo(OutMessage oms,InMessage msg) throws Exception {
    	if(oms != null){
    		Class<?> outMsg = oms.getClass().getSuperclass();
            Field CreateTime = outMsg.getDeclaredField("CreateTime");
            Field ToUserName = outMsg.getDeclaredField("ToUserName");
            Field FromUserName = outMsg.getDeclaredField("FromUserName");

            ToUserName.setAccessible(true);
            CreateTime.setAccessible(true);
            FromUserName.setAccessible(true);

            CreateTime.set(oms, new Date().getTime());
            ToUserName.set(oms, msg.getFromUserName());
            FromUserName.set(oms, msg.getToUserName());
    	}
    }

    /**
     *消息体转换
     * @param responseInputString
     * @return
     */
    private static InMessage parsingInMessage(String responseInputString) {
        //转换微信post过来的xml内容
        XStream xs = XStreamFactory.init(false);
        xs.ignoreUnknownElements();
        xs.alias("xml", InMessage.class);
        InMessage msg = (InMessage) xs.fromXML(responseInputString);
        return msg;
    }
    
    /**
     * 获取媒体资源
     * @param accessToken
     * @param mediaId
     * @return
     * @throws IOException
     * @throws InterruptedException 
     * @throws ExecutionException 
     */
    public static Attachment getMedia(String accessToken,String mediaId) throws IOException, ExecutionException, InterruptedException{
    	String url = GET_MEDIA_URL + accessToken + "&media_id=" + mediaId;
        return HttpKit.download(url);
    }
    
    /**
     * 上传临时素材文件
     * @param type
     * @param file
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     * @throws InterruptedException 
     * @throws ExecutionException 
     */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> uploadMedia(String accessToken,String type,File file) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException, ExecutionException, InterruptedException {
        String url = UPLOAD_MEDIA_URL + accessToken +"&type="+type;
        String jsonStr = HttpKit.upload(url,file);
        return JSON.parseObject(jsonStr, Map.class);
    }
    
    /**
     * 判断是否来自微信, 5.0 之后的支持微信支付
     * @param request
     * @return
     */
 	public static boolean isWeiXin(HttpServletRequest request) {
 		String userAgent = request.getHeader("User-Agent");
 		if (StringUtils.isNotBlank(userAgent)) {
 			Pattern p = Pattern.compile("MicroMessenger/(\\d+).+");
 			Matcher m = p.matcher(userAgent);
 			String version = null;
 			if(m.find()){
 				version = m.group(1);
 			}
 			return (null != version && NumberUtils.toInt(version) >= 5); 
 		}
 		return false;
 	}
 	

    /**js token
     * @param accessToken
     * @param mediaId
     * @return
     * @throws UnsupportedEncodingException 
     * @throws IOException
     * @throws InterruptedException 
     * @throws ExecutionException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public static String getJSToken(String accessToken) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException, ExecutionException, InterruptedException{
        String url = JSTOKEN_URL + accessToken;
        String jsonStr =  HttpKit.get(url);
        Map<String, Object> map = JSONObject.parseObject(jsonStr);
        return map.get("ticket").toString();
    }

    /**
     * 获取api ticket
     */
    public static String getAPIToken(String accessToken) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException, ExecutionException, InterruptedException{
        String url = APITOKEN_URL + accessToken + "&type=wx_card";
        String jsonStr =  HttpKit.get(url);
        Map<String, Object> map = JSONObject.parseObject(jsonStr);
        return map.get("ticket").toString();
    }
}
