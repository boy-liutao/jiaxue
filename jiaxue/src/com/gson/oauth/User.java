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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gson.bean.UserInfo;
import com.gson.util.HttpKit;


/**
 * 用户操作接口
 * @author ____′↘夏悸
 */
public class User {
    private static Log log = LogFactory.getLog(User.class);
	private static final String USER_INFO_URI = "https://api.weixin.qq.com/cgi-bin/user/info";
	private static final String USER_GET_URI = "https://api.weixin.qq.com/cgi-bin/user/get";
	private static final String USER_BATCHGET_URI = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=";

	/**
	 * 拉取用户信息
	 * @param accessToken
	 * @param openid
	 * @return
	 * @throws IOException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public UserInfo getUserInfo(String accessToken, String openid) throws Exception {
	    //retry max = 5
	    for (int i=0;i<5;i++){
	        try {
        		Map<String, String> params = new HashMap<String, String>();
        		params.put("access_token", accessToken);
        		params.put("openid", openid);
        		String  jsonStr = HttpKit.get(USER_INFO_URI, params);
        		if(StringUtils.isNotEmpty(jsonStr)){
        			JSONObject obj = JSONObject.parseObject(jsonStr);
        			if(obj.get("errcode") != null){
        				throw new Exception(obj.getString("errmsg"));
        			}
        			UserInfo user = JSONObject.toJavaObject(obj, UserInfo.class);
        			return user;
        		}
	        } catch (Exception e){
	            Thread.sleep(1000);//sleep 1 second 
	            log.error(e);
	        }
	    }
		return null;
	}
	
	/**
	 * 获取帐号的关注者列表
	 * @param accessToken
	 * @param next_openid
	 * @return
	 */
	public JSONObject getFollwersList(String accessToken, String next_openid) throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", accessToken);
		params.put("next_openid", next_openid);
		String  jsonStr = HttpKit.get(USER_GET_URI, params);
		if(StringUtils.isNotEmpty(jsonStr)){
			JSONObject obj = JSONObject.parseObject(jsonStr);
			if(obj.get("errcode") != null){
				throw new Exception(obj.getString("errmsg"));
			}
			return obj;
		}
		return null;
	}
	
	/**
     * 获取帐号的关注者列表
     * @param accessToken
     * @param next_openid
     * @return
     */
    public List<String> getAllFollwersList(String accessToken) throws Exception{
        List<String> allUs = new ArrayList<String>();
        String nid = getAllFollwersList(allUs, accessToken,  null);
        while (!StringUtils.isEmpty(nid)){
            nid = getAllFollwersList(allUs, accessToken,  nid);
        }
        return allUs;
    }
    
    private String getAllFollwersList(List<String> allUs, String accessToken, String next_openid) throws Exception{
        JSONObject us = getFollwersList(accessToken, next_openid);
        int size = us.getIntValue("count");
        if (size == 0)
            return null;
        JSONObject data = us.getJSONObject("data");
        JSONArray oids = data.getJSONArray("openid");
        String nid = us.getString("next_openid");
        Iterator<Object> iter = oids.iterator();
        while (iter.hasNext()){
            String openId = iter.next().toString();
            allUs.add(openId);
        }
        return nid;
    }
    
	/**
     * 批量拉取用户信息
     * @param accessToken
     * @param openids, 拉取用户的openId列表，不能超过100
     * @return
     * @throws IOException 
     * @throws NoSuchProviderException 
     * @throws NoSuchAlgorithmException 
     * @throws KeyManagementException 
     */
    public List<UserInfo> getUserInfoInBatch(String accessToken, List<String> openids) throws Exception {
        JSONArray arr = new JSONArray();
        List<UserInfo> uis = new ArrayList<UserInfo>();
        for (String openid : openids){
            JSONObject u  = new JSONObject();
            u.put("openid", openid);
            arr.add(u);
        }
        JSONObject us = new JSONObject();
        us.put("user_list", arr);
        
        String  jsonStr = HttpKit.post(USER_BATCHGET_URI.concat(accessToken), JSONObject.toJSONString(us));
        if(StringUtils.isNotEmpty(jsonStr)){
            try {
                JSONObject obj = JSONObject.parseObject(jsonStr);
                if(obj.get("errcode") != null){
                    throw new Exception(obj.getString("errmsg"));
                }
                JSONArray uiArr = obj.getJSONArray("user_info_list");
                Iterator<Object> iter = uiArr.iterator();
                while (iter.hasNext()){
                    JSONObject ui = (JSONObject) iter.next();
                    UserInfo user = JSONObject.toJavaObject(ui, UserInfo.class);
                    uis.add(user);
                }
            } catch (Exception e){
                e.printStackTrace();
                log.error("not handle:" + jsonStr);
            }
        }
        return uis;
    }
    
    public static void main(String[] args) throws Exception {
        String token = "pGdyQgkHkWaeoddw3DX6OX_PSR4TXwFz0nBxH9ZFS7GZrXk0c6B7tRNoPo47KoOoPJHFtgLUJ5S0ULy5BV4eaisnWfVa23waQtS_enVxTeg";
        String openId = "oY83WjgiJKY6APp8qBfhSbwHYqnM";
        List<String> ids = new ArrayList<String>();
        ids.add("oY83WjgiJKY6APp8qBfhSbwHYqnM");
        ids.add("oY83Wjrt91G4WQ_np-qPL6DZiH9U");
        UserInfo ui = new User().getUserInfo(token, "oY83WjgiJKY6APp8qBfhSbwHYqnM");
        System.out.println(ui.getUnionid());
    }
}