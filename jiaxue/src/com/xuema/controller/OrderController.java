package com.xuema.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gson.WeChat;
import com.gson.bean.OrderResponse;
import com.gson.util.ConfKit;
import com.xuema.aop.LoginCheck;
import com.xuema.bean.Coupon;
import com.xuema.bean.GlobalSetting;
import com.xuema.bean.Orders;
import com.xuema.bean.School;
import com.xuema.bean.User;
import com.xuema.service.DBService;
import com.xuema.util.AlipayUtil;
import com.xuema.util.JsonUtil;
import com.xuema.util.SMSUtil;
import com.xuema.util.SessionUtil;
import com.xuema.util.WebUtil;
import com.xuema.wechat.WeChatHelper;

@Controller
@LoginCheck
@RequestMapping(value="/order")
public class OrderController extends BaseController{
    @Autowired
    DBService dbService;
    
    @RequestMapping(value = "weixinCreate.do")
    public void weixinCreate(HttpServletRequest request,HttpServletResponse response, String oid, String cid, int uid, String openId, int sid, int nurseryUnit, int nurseryCount, int fruitCount, int dinnerCount, Date startDate, String callback) throws JsonProcessingException, IOException, ExecutionException, InterruptedException{
        User u = SessionUtil.getLoginUser(request);
        if (u == null){
            u = dbService.getUser(uid);
        }
        
        Orders order = createOrder(uid, cid, sid, nurseryUnit, nurseryCount, fruitCount, dinnerCount, startDate);
        //生成微信统一订单
        String billno = WeChat.order.getMch_billno();
        OrderResponse result = WeChat.order.order(openId, billno, order.getMoney()*100);
        //保存/更新订单
        order.setBillno(billno);
        order.setPayId(result.getPrepay_id());
        if (StringUtils.isEmpty(oid) || oid.equals("-1")){
            if (u.getSid() == 0){
                dbService.updateUserSchool(u.getId(), order.getSid());
            }
            dbService.saveOrder(order);
            //检查用户是否设置学堂
            if (u.getSid() <= 0){
                dbService.setUserSid(u.getId(), order.getSid()); 
                u.setCid(order.getSid());
            }
        } else {
            order.setId(oid);
            dbService.updateOrder(order);
        }
        //绑定优惠券
        if (!StringUtils.isEmpty(cid)){
            dbService.frozenCoupon(cid, order.getId());
        }
        //生成支付参数
        ObjectNode node = (ObjectNode) JsonUtil.objectToJsonNode(order);
        String appId = ConfKit.get("AppId");
        String timestamp = WeChatHelper.create_timestamp();
        String nonceStr = ConfKit.create_nonce_str();
        Map<String, String> param = new HashMap<String, String>();
        param.put("appId", appId);
        param.put("timeStamp", timestamp);
        param.put("nonceStr", nonceStr);
        param.put("package", "prepay_id="+result.getPrepay_id());
        param.put("signType", "MD5");
        String paySign = WeChat.order.createSign(param, ConfKit.get("pay_apikey"), false);
        node.put("appId", appId);
        node.put("timestamp", timestamp);
        node.put("nonceStr", nonceStr);
        node.put("paySign", paySign);
        
        WebUtil.response(response, WebUtil.packJsonp(callback, JsonUtil.wrapJsonNodeResponse(node).toString()));
    }
    
    @RequestMapping(value = "wxpayqrcode.do")
    public void wxpayqrcode(HttpServletRequest request,HttpServletResponse response, String product_id,  String callback) throws JsonProcessingException, IOException, ExecutionException, InterruptedException{
        String appId = ConfKit.get("AppId");
        String timestamp = WeChatHelper.create_timestamp();
        String nonceStr = ConfKit.create_nonce_str();
        String mch_id = ConfKit.get("mch_id");
        Map<String, String> param = new HashMap<String, String>();
        param.put("appid", appId);
        param.put("time_stamp", timestamp);
        param.put("nonce_str", nonceStr);
        param.put("mch_id", mch_id);
        param.put("product_id", product_id);
        String paySign = WeChat.order.createSign(param, ConfKit.get("pay_apikey"), false);
        String str = "weixin://wxpay/bizpayurl?sign="+paySign+"&appid="+appId+"&mch_id="+mch_id+"&product_id="+product_id+"&time_stamp="+timestamp+"&nonce_str="+nonceStr;
        WebUtil.response(response, str);
    }
    
    @RequestMapping(value = "aliCreate.do")
    public void aliCreate(HttpServletRequest request,HttpServletResponse response, String oid, String cid, int uid, String openId, int sid, int nurseryUnit, int nurseryCount, int fruitCount, int dinnerCount, Date startDate, String callback) throws JsonProcessingException, IOException, ExecutionException, InterruptedException{
        User u = SessionUtil.getLoginUser(request);
        if (u == null){
            u = dbService.getUser(uid);
        }
        Orders order = createOrder(uid, cid, sid, nurseryUnit, nurseryCount, fruitCount, dinnerCount, startDate);
        String str = AlipayUtil.buildReturn(request);
        WebUtil.response(response, str);
    }
    
    @RequestMapping(value = "alitest.do")
    public void alitest(HttpServletRequest request,HttpServletResponse response,  String callback) throws JsonProcessingException, IOException, ExecutionException, InterruptedException{
        String str = AlipayUtil.buildReturn(request);
        WebUtil.responseHtml(response, str);
    }
    
    @RequestMapping(value = "payOrder.do")
    public void payOrder(HttpServletRequest request,HttpServletResponse response, String prepayId, String callback) throws JsonProcessingException, IOException, ExecutionException, InterruptedException{
        int rows = dbService.payOrder(prepayId);
        if (rows >= 1){
            //发短信消息
            Orders o = dbService.getOrderByPayId(prepayId);
            if (o.getNurseryCount() > 0) {
                User u = dbService.getUser(o.getUid());
                School s = dbService.getSchool(o.getSid());
                SMSUtil.sendOrderMessage(u.getPhone(), s.getName(), o.getStartDate());
            }
            //消耗优惠券
            Coupon c = dbService.getCouponByOid(o.getId());
            if (c != null){
                dbService.consumeCoupon(c.getId());
            }
            WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.wrapReturn(true).toString()));
        } else {
            WebUtil.response(response,WebUtil.packJsonp(callback,JsonUtil.wrapReturn(false).toString()));
        }
    }
    
    @RequestMapping(value = "list.do")
    public void list(HttpServletRequest request,HttpServletResponse response, String callback){
        User a = SessionUtil.getLoginUser(request);
        if(a!=null){
        	List<Orders> cs = dbService.listOreder(a.getId());
        	WebUtil.response(response, callback, JsonUtil.getListObjectNode(cs).toString());
        }
    } 
    
    @RequestMapping(value = "getOrder.do")
    public void getOrder(HttpServletRequest request,HttpServletResponse response,String id, String callback){
       
        	Orders cs = dbService.getOrder(id);
        	WebUtil.response(response,callback, JsonUtil.wrapJsonNodeResponse(JsonUtil.objectToJsonNode(cs)).toString());
    } 
    
    @RequestMapping(value = "setting.do")
    public void setting(HttpServletRequest request,HttpServletResponse response,String name, String callback){
        User a = SessionUtil.getLoginUser(request);
        if(a!=null){
        	if(name.equals("price.20.fruit")){
        		GlobalSetting cs = dbService.select(a.getSid(),name);
        		WebUtil.response(response,callback, JsonUtil.wrapJsonNodeResponse(JsonUtil.objectToJsonNode(cs)).toString());
        	}
        	if(name.equals("price.20.dinner")){
        		GlobalSetting cs = dbService.select(a.getSid(),name);
        		WebUtil.response(response,callback, JsonUtil.wrapJsonNodeResponse(JsonUtil.objectToJsonNode(cs)).toString());
        	}
        }
    } 
    
    
    private Orders createOrder(int uid, String cid, int sid, int nurseryUnit, int nurseryCount, int fruitCount, int dinnerCount, Date startDate){
      //计算费用
        School s = dbService.getSchool(sid);
        int price_term_nursery = 0;
        int price_month_nursery = 0;
        int price_day_nursery = 0;
        int price_20_fruit = 0;
        int price_20_dinner = 0;
        
        int nurseryMoney = 0;
        
        for (GlobalSetting setting : s.getSettings()){
            if (setting.getName().equals("price.term.nursery")){
                price_term_nursery = Integer.valueOf(setting.getValue());
            }
            if (setting.getName().equals("price.month.nursery")){
                price_month_nursery = Integer.valueOf(setting.getValue());
            }
            if (setting.getName().equals("price.day.nursery")){
                price_day_nursery = Integer.valueOf(setting.getValue());
            }
            if (setting.getName().equals("price.20.fruit")){
                price_20_fruit = Integer.valueOf(setting.getValue());
            }
            if (setting.getName().equals("price.20.dinner")){
                price_20_dinner = Integer.valueOf(setting.getValue());
            }
        }
        int money = 0;
        int payMoney = money;
        if (nurseryUnit == 1){
            money += price_term_nursery;
            nurseryMoney = price_term_nursery;
        }
        if (nurseryUnit == 2){
            money += nurseryCount*price_month_nursery;
            nurseryMoney = nurseryCount*price_month_nursery;
        }
        if (nurseryUnit == 3){
            money += nurseryCount*price_day_nursery;
            nurseryMoney = nurseryCount*price_day_nursery;
        }
        if (fruitCount > 0){
            money += price_20_fruit;
        }
        if (dinnerCount > 0){
            money += price_20_dinner;
        }
        if (!StringUtils.isEmpty(cid)){
            Coupon c = dbService.getCoupon(cid);
            if (money >= c.getCond()){
                payMoney = money - c.getValue();
                nurseryMoney = nurseryMoney - c.getValue();//优惠券计入托管费中
            }
        }


        Orders order = new Orders();
        order.setUid(uid);
        order.setStatus(0);
        order.setSid(sid);
        order.setNurseryUnit(nurseryUnit);
        order.setNurseryCount(nurseryCount);
        order.setFruitCount(fruitCount);
        order.setDinnerCount(dinnerCount);
        order.setNurseryMoney(nurseryMoney);
        order.setPayWay(1);
        order.setFruitCount(fruitCount);
        order.setFruitRemainCount(fruitCount);
        order.setFruitMoney(price_20_fruit);
        order.setDinnerCount(dinnerCount);
        order.setDinnerRemainCount(dinnerCount);
        order.setDinnerMoney(price_20_dinner);
        order.setStartDate(startDate);
        order.setMoney(money);
        order.setPayMoney(payMoney);
        return order;
    }
    
}
