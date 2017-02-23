/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2014 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.gson.oauth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import com.gson.bean.OrderRequest;
import com.gson.bean.OrderResponse;
import com.gson.util.ConfKit;
import com.gson.util.HttpKit;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.xuema.util.DatetimeUtil;
import com.xuema.util.MD5Utils;
import com.xuema.util.XMLUtil;
import com.xuema.util.XuemaUtil;

/**
 * 订单
 *
 * @author ade
 * @description 订单接口
 */
public class Order {

    private static final String ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    
    public static OrderResponse orderQRCode(String openId, int fee) throws IOException, ExecutionException, InterruptedException{
        OrderRequest p = new OrderRequest();
        String nonce = ConfKit.create_nonce_str();
        String billno = getMch_billno();
        
        p.setAppid(ConfKit.get("AppId"));
        p.setMch_id(ConfKit.get("mch_id"));
        p.setNonce_str(nonce);
        p.setBody("家学天地-托管");
        p.setOut_trade_no(billno);
        p.setTrade_type("NATIVE");
        p.setTotal_fee(fee);
        p.setSpbill_create_ip("123.57.227.131");
        p.setNotify_url(XuemaUtil.domain_name + "/jiaxue/callback/payCallback.do");
        p.setOpenid(openId);

        
        Map<String, String> singParam = new HashMap<String, String>();
        singParam.put("appid", ConfKit.get("AppId"));
        singParam.put("mch_id", ConfKit.get("mch_id"));
        singParam.put("device_info", "WEB");
        singParam.put("body", "家学天地-托管");
        singParam.put("nonce_str", nonce);
        singParam.put("out_trade_no", billno);
        singParam.put("total_fee", Integer.valueOf(fee).toString());
        singParam.put("spbill_create_ip", "123.57.227.131");
        singParam.put("notify_url", XuemaUtil.domain_name + "/jiaxue/callback/payCallback.do");
        singParam.put("openid", openId);
        singParam.put("trade_type", "NATIVE");
        
        
        p.setSign(createSign(singParam, ConfKit.get("pay_apikey"), false));
        XStream xs = new XStream(new XppDriver(new XmlFriendlyReplacer("_-", "_")));
        xs.alias("xml", p.getClass());
        String xml = xs.toXML(p);
        System.out.println(xml);
        xml = new String(xml.getBytes("utf-8"), "utf-8");  
        String returnV = HttpKit.post(ORDER_URL, xml);
        System.out.println(returnV);
        
        OrderResponse res = XMLUtil.toObject(returnV, OrderResponse.class);
        return res;
    }
    
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
    public static OrderResponse order(String openId, String billno, int fee) throws IOException, ExecutionException, InterruptedException{
        OrderRequest p = new OrderRequest();
        String nonce = ConfKit.create_nonce_str();
        
        
        p.setAppid(ConfKit.get("AppId"));
        p.setMch_id(ConfKit.get("mch_id"));
        p.setNonce_str(nonce);
        p.setBody("家学天地-托管");
        p.setOut_trade_no(billno);
        
        p.setTotal_fee(fee);
        p.setSpbill_create_ip("123.57.227.131");
        p.setNotify_url(XuemaUtil.domain_name + "/jiaxue/callback/payCallback.do");
        p.setOpenid(openId);

        
        Map<String, String> singParam = new HashMap<String, String>();
        singParam.put("appid", ConfKit.get("AppId"));
        singParam.put("mch_id", ConfKit.get("mch_id"));
        singParam.put("device_info", "WEB");
        singParam.put("body", "家学天地-托管");
        singParam.put("nonce_str", nonce);
        singParam.put("out_trade_no", billno);
        singParam.put("total_fee", Integer.valueOf(fee).toString());
        singParam.put("spbill_create_ip", "123.57.227.131");
        singParam.put("notify_url", XuemaUtil.domain_name + "/jiaxue/callback/payCallback.do");
        singParam.put("openid", openId);
        singParam.put("trade_type", "JSAPI");
        
        
        p.setSign(createSign(singParam, ConfKit.get("pay_apikey"), false));
        XStream xs = new XStream(new XppDriver(new XmlFriendlyReplacer("_-", "_")));
        xs.alias("xml", p.getClass());
        String xml = xs.toXML(p);
        System.out.println(xml);
        xml = new String(xml.getBytes("utf-8"), "utf-8");  
        String returnV = HttpKit.post(ORDER_URL, xml);
        System.out.println(returnV);
        
        OrderResponse res = XMLUtil.toObject(returnV, OrderResponse.class);
        return res;
    }
    
    public static String createSign(Map<String, String> params, String mkey, boolean encode) throws UnsupportedEncodingException {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = value.toString();
            }
            if (encode) {
                temp.append(URLEncoder.encode(valueString, "UTF-8"));
            } else {
                temp.append(valueString);
            }
        }
        String str = temp.toString();
        str += "&key="+mkey;
        return MD5Utils.getMD5(str).toUpperCase();
    }
    
    public static synchronized String getMch_billno(){
        String mch_id=ConfKit.get("mch_id");
        StringBuilder sb = new StringBuilder();
        sb.append(mch_id).append(DatetimeUtil.yyyymmdd());
        long t = System.currentTimeMillis();
        String ts = Long.valueOf(t).toString().substring(3);
        sb.append(ts);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            //sleep 1 ms for not generate dup id
        }
        return sb.toString();
    }
}
