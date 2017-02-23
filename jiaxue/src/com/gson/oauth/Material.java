/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2014 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.gson.oauth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.gson.util.HttpKit;
import com.xuema.util.PictureUtil;

/**
 * 永久素材接口
 *
 * @author ade
 * @description 用于上传，查看，修改，删除永久素材
 */
public class Material {

    private static final String LIST_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=";
    private static final String COUNT_URL = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=";
    private static final String DEATIL_URL = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=";
    private static final String download_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token={0}&media_id={1}";
    
    /**
     * 获得素材列表
     * @param accessToken
     * @param start
     * @param size
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public JSONObject listMaterial(String accessToken, int start, int size, String type) throws IOException, ExecutionException, InterruptedException{
        Map<String,Object> json = new HashMap<String,Object>();
        json.put("type", type);
        json.put("offset", start);
        json.put("count", size);
        String reslut = HttpKit.post(LIST_URL.concat(accessToken), JSONObject.toJSONString(json));
        if (StringUtils.isNotEmpty(reslut)) {
            return JSONObject.parseObject(reslut);
        }
        return null;
    }
    
    /**
     * 获得素材数量
     * @param accessToken
     * @param type 为news, voice, video, image中的一个 
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public long count(String accessToken, String type) throws Exception{
        String reslut = HttpKit.get(COUNT_URL.concat(accessToken), null);
        if (StringUtils.isNotEmpty(reslut)) {
            JSONObject o = JSONObject.parseObject(reslut);
            return o.getLongValue(type+"_count");
        }
        return 0;
    }
    
    public JSONObject getMaterial(String accessToken, String mid) throws Exception {
        JSONObject para = new JSONObject();
        para.put("media_id", mid);
        para.put("type", "video");
        String reslut = HttpKit.post(DEATIL_URL.concat(accessToken), para.toString());
        if (StringUtils.isNotEmpty(reslut)) {
            JSONObject o = JSONObject.parseObject(reslut);
            return o;
        }
        return null;
    }
    
    public static void main(String[] args) throws Exception {
//        String token = WeChatHelper.getAccessToken();
        String token = "HyZiEVCm6kvUFs_g03QwuGZlkGSzZukbv0t9hVgpuxzcJ-zUD6KnUoOln-9jmn8Ea7xk8GAb13dkkf-QV7QaTbNlOxhzxaEqQ3Odxt62iFA";
        System.out.println(token);
        JSONObject o = new Material().getMaterial(token, "a1D23fsXLHFboEW047lyTezEII6EFPrbeIicZxwRNko");
//        JSONObject o = new Material().listMaterial(token, 0, 3, "video");
        System.out.println(o);
    }

    public static String downloadImg(String accessToken, String mid) throws Exception{
        String url = MessageFormat.format(download_URL, accessToken, mid);
        return PictureUtil.downloadImg(url, mid + ".jpg");
    }
}
