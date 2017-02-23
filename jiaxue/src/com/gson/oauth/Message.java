/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2014 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.gson.oauth;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.gson.bean.Article;
import com.gson.bean.Articles;
import com.gson.bean.TemplateData;
import com.gson.util.HttpKit;

/**
 * 客服消息接口
 *
 * @author L.cm
 * @date 2013-11-5 下午3:32:30
 * @description 当用户主动发消息给公众号的时候（包括发送信息、点击自定义菜单、订阅事件、扫描二维码事件、支付成功事件、用户维权），微信将会把消息数据推送给开发者，开发者在一段时间内（目前修改为48小时）可以调用客服消息接口，通过POST一个JSON数据包来发送消息给普通用户，在48小时内不限制发送次数。此接口主要用于客服等有人工消息处理环节的功能，方便开发者为用户提供更加优质的服务。
 */
public class Message {

    private static final String MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
    private static final String UPLOADNEWS_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=";
    private static final String MASS_SENDALL_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=";
    private static final String MASS_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=";
    private static final String MASS_DELETE_URL = "https://api.weixin.qq.com//cgi-bin/message/mass/delete?access_token=";
    private static final String TEMPLATE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
    private static final String UPLOAD_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token={0}type={1}";
    /**
     * 发送客服消息
     * @param accessToken
     * @param message
     * @return
     * @throws Exception
     */
    private String sendMsg(String accessToken, Map<String, Object> message) throws Exception{
		String reslut = HttpKit.post(MESSAGE_URL.concat(accessToken), JSONObject.toJSONString(message));
		return reslut;
	}
    
    /**
     * 发送文本客服消息
     * @param openId
     * @param text
     * @throws Exception 
     */
    public String sendText(String accessToken,String openId, String text) throws Exception {
        Map<String,Object> json = new HashMap<String,Object>();
        Map<String,Object> textObj = new HashMap<String,Object>();
        textObj.put("content", text);
        json.put("touser", openId);
        json.put("msgtype", "text");
        json.put("text", textObj);
    	String reslut = sendMsg(accessToken, json);
        return reslut;
    }
    
    /**
     * 发送图片消息
     * @param accessToken
     * @param openId
     * @param media_id
     * @return
     * @throws Exception
     */
    public String SendImage(String accessToken,String openId, String media_id) throws Exception{
    	Map<String,Object> json = new HashMap<String,Object>();
        Map<String,Object> textObj = new HashMap<String,Object>();
        textObj.put("media_id", media_id);
        json.put("touser", openId);
        json.put("msgtype", "image");
        json.put("image", textObj);
    	String reslut = sendMsg(accessToken, json);
        return reslut;
    }
    
    /**
     * 发送语言回复
     * @param accessToken
     * @param openId
     * @param media_id
     * @return
     * @throws Exception
     */
    public String SendVoice(String accessToken,String openId, String media_id) throws Exception{
    	Map<String,Object> json = new HashMap<String,Object>();
        Map<String,Object> textObj = new HashMap<String,Object>();
        textObj.put("media_id", media_id);
        json.put("touser", openId);
        json.put("msgtype", "voice");
        json.put("voice", textObj);
    	String reslut = sendMsg(accessToken, json);
        return reslut;
    }
    
    /**
     * 发送视频回复
     * @param accessToken
     * @param openId
     * @param media_id
     * @param title
     * @param description
     * @return
     * @throws Exception
     */
    public String SendVideo(String accessToken,String openId, String media_id,String title,String description) throws Exception{
    	Map<String,Object> json = new HashMap<String,Object>();
        Map<String,Object> textObj = new HashMap<String,Object>();
        textObj.put("media_id", media_id);
        textObj.put("title", title);
        textObj.put("description", description);
        
        json.put("touser", openId);
        json.put("msgtype", "video");
        json.put("video", textObj);
    	String reslut = sendMsg(accessToken, json);
        return reslut;
    }
    
    /**
     * 发送音乐回复
     * @param accessToken
     * @param openId
     * @param musicurl
     * @param hqmusicurl
     * @param thumb_media_id
     * @param title
     * @param description
     * @return
     * @throws Exception
     */
    public String SendMusic(String accessToken,String openId, String musicurl,String hqmusicurl,String thumb_media_id,String title,String description) throws Exception{
    	Map<String,Object> json = new HashMap<String,Object>();
        Map<String,Object> textObj = new HashMap<String,Object>();
        textObj.put("musicurl", musicurl);
        textObj.put("hqmusicurl", hqmusicurl);
        textObj.put("thumb_media_id", thumb_media_id);
        textObj.put("title", title);
        textObj.put("description", description);
        
        json.put("touser", openId);
        json.put("msgtype", "music");
        json.put("music", textObj);
    	String reslut = sendMsg(accessToken, json);
        return reslut;
    }
    
    /**
     * 发送图文回复
     * @param accessToken
     * @param openId
     * @param articles
     * @return
     * @throws Exception
     */
    public String SendNews(String accessToken,String openId, List<Articles> articles) throws Exception{
    	Map<String,Object> json = new HashMap<String,Object>();
        json.put("touser", openId);
        json.put("msgtype", "news");
        Map<String,Object> as = new HashMap<String,Object>();
        as.put("articles", articles);
        json.put("news", as);
    	String reslut = sendMsg(accessToken, json);
        return reslut;
    }
    
    /**
     * 上传图文消息素材
     * @param accessToken
     * @param articles
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     */
    public JSONObject uploadnews(String accessToken,List<Article> articles)throws IOException, ExecutionException, InterruptedException{
    	Map<String,Object> json = new HashMap<String,Object>();
    	json.put("articles", articles);
    	String reslut = HttpKit.post(UPLOADNEWS_URL.concat(accessToken), JSONObject.toJSONString(json));
    	if (StringUtils.isNotEmpty(reslut)) {
			return JSONObject.parseObject(reslut);
		}
		return null;
    }
    
    /**
     * 根据分组进行群发
     * @param accessToken
     * @param groupId
     * @param mpNewsMediaId
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     */
    public JSONObject massSendall(String accessToken,String groupId,String mpNewsMediaId)throws IOException, ExecutionException, InterruptedException{
    	Map<String,Object> json = new HashMap<String,Object>();
    	Map<String,Object> filter = new HashMap<String,Object>();
    	Map<String,Object> mpnews = new HashMap<String,Object>();
    	filter.put("group_id", groupId);
    	mpnews.put("media_id", mpNewsMediaId);
    	
    	json.put("mpnews", mpnews);
    	json.put("filter", filter);
    	json.put("msgtype", "mpnews");
    	String reslut = HttpKit.post(MASS_SENDALL_URL.concat(accessToken), JSONObject.toJSONString(json));
    	if (StringUtils.isNotEmpty(reslut)) {
			return JSONObject.parseObject(reslut);
		}
		return null;
    }
    
    /**
     * 根据分组进行群发
     * @param accessToken
     * @param groupId
     * @param mpNewsMediaId
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     */
    public JSONObject massSendallText(String accessToken,String groupId,String text)throws IOException, ExecutionException, InterruptedException{
        Map<String,Object> json = new HashMap<String,Object>();
        Map<String,Object> filter = new HashMap<String,Object>();
        Map<String,Object> textMsg = new HashMap<String,Object>();
        filter.put("is_to_all", false);
        filter.put("group_id", groupId);
        textMsg.put("content", text);
        
        json.put("text", textMsg);
        json.put("filter", filter);
        json.put("msgtype", "text");
        String reslut = HttpKit.post(MASS_SENDALL_URL.concat(accessToken), JSONObject.toJSONString(json));
        if (StringUtils.isNotEmpty(reslut)) {
            return JSONObject.parseObject(reslut);
        }
        return null;
    }
    
    /**
     * 根据OpenID列表群发
     * @param accessToken
     * @param openids
     * @param mpNewsMediaId
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     * @throws ExecutionException 
     */
    public JSONObject massSend(String accessToken,String[] openids,String mpNewsMediaId)throws IOException, InterruptedException, ExecutionException{
    	Map<String,Object> json = new HashMap<String,Object>();
    	Map<String,Object> mpnews = new HashMap<String,Object>();
    	mpnews.put("media_id", mpNewsMediaId);
    	json.put("touser", openids);
    	json.put("mpnews", mpnews);
    	json.put("msgtype", "mpnews");
    	String reslut = HttpKit.post(MASS_SEND_URL.concat(accessToken), JSONObject.toJSONString(json));
    	if (StringUtils.isNotEmpty(reslut)) {
			return JSONObject.parseObject(reslut);
		}
		return null;
    }
    
    /**
     * 根据OpenID列表群发
     * @param accessToken
     * @param openids
     * @param text
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     * @throws ExecutionException 
     */
    public JSONObject massSendText(String accessToken,String[] openids,String text)throws IOException, InterruptedException, ExecutionException{
        Map<String,Object> json = new HashMap<String,Object>();
        Map<String,Object> textObj = new HashMap<String,Object>();
        textObj.put("content", text);
        json.put("text", textObj);
        json.put("touser", openids);
        json.put("msgtype", "text");
        String reslut = HttpKit.post(MASS_SEND_URL.concat(accessToken), JSONObject.toJSONString(json));
        if (StringUtils.isNotEmpty(reslut)) {
            return JSONObject.parseObject(reslut);
        }
        return null;
    }
    
    /**
     * 删除群发
     * @param accessToken
     * @param msgid
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     */
    public JSONObject massSend(String accessToken,String msgid)throws IOException, ExecutionException, InterruptedException{
    	Map<String,Object> json = new HashMap<String,Object>();
    	json.put("msgid", msgid);
    	String reslut = HttpKit.post(MASS_DELETE_URL.concat(accessToken), JSONObject.toJSONString(json));
    	if (StringUtils.isNotEmpty(reslut)) {
			return JSONObject.parseObject(reslut);
		}
		return null;
    }
    
    /**
     * 发送模板消息
     * @param accessToken
     * @param data
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public JSONObject templateSend(String accessToken,TemplateData data) throws IOException, ExecutionException, InterruptedException{
    	String reslut = HttpKit.post(TEMPLATE_SEND_URL.concat(accessToken), JSONObject.toJSONString(data));
    	if (StringUtils.isNotEmpty(reslut)) {
			return JSONObject.parseObject(reslut);
		}
		return null;
    }
    
    /**
     * 根据OpenID列表群发
     * @param accessToken
     * @param openids
     * @param mpNewsMediaId
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws IOException
     * @throws ExecutionException 
     */
    public JSONObject massSendCoupon(String accessToken,String[] openids,String cardId)throws IOException, InterruptedException, ExecutionException{
        Map<String,Object> json = new HashMap<String,Object>();
        Map<String,Object> cardExt = new HashMap<String,Object>();
        json.put("touser", openids);
        json.put("wxcard", cardExt);
        json.put("msgtype", "wxcard");
        cardExt.put("card_id", cardId);
        String reslut = HttpKit.post(MASS_SEND_URL.concat(accessToken), JSONObject.toJSONString(json));
        if (StringUtils.isNotEmpty(reslut)) {
            return JSONObject.parseObject(reslut);
        }
        return null;
    }
    
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        String token = "976XCBnWFWXzSksikYG17rrGHQqZ1kyhCho9dEShXvUEW2jX1eKYlaxlkYQeNp_FO-yokkunqGvsnL3e5xWhSik-ps47RuB5V2U0VMXW158";
        String[] ids = new String[]{"oz45Tt1eHadsai67QGhrohomsSN0","oz45Tt_3qOX7-S987J_TyXvy_esU"};
        JSONObject r = new Message().massSendText(token, ids, "感谢亲热情参加我们的土豆红薯大比拼，我们准备了20份小礼物——500个积分（可兑换5元话费），送给20位最幸运的亲，你就是其中幸运的一个。");
        System.out.println(r);
    }
}
