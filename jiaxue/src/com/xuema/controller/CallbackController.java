package com.xuema.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gson.WeChat;
import com.gson.bean.OrderRequest;
import com.gson.bean.OrderResponse;
import com.gson.util.ConfKit;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.xuema.ali.AlipayNotify;
import com.xuema.aop.LoginCheck;
import com.xuema.service.DBService;
import com.xuema.util.WebUtil;
import com.xuema.util.XMLUtil;
import com.xuema.wechat.WeChatHelper;

@Controller
@LoginCheck
@RequestMapping(value="/callback")
public class CallbackController extends BaseController{
    @Autowired
    DBService dbService;
    
    @RequestMapping(value = "notify.do")
    public void notify(HttpServletRequest request,HttpServletResponse response, String callback) throws JsonProcessingException, IOException{
      //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //支付宝交易号

        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

        if(AlipayNotify.verify(params)){//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码

            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            
            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                    //如果有做过处理，不执行商户的业务程序
                    
                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                    //如果有做过处理，不执行商户的业务程序
                    
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }

            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
                
            WebUtil.response(response, "success"); //请不要修改或删除

            //////////////////////////////////////////////////////////////////////////////////////////
        }else{//验证失败
            WebUtil.response(response, "fail"); 
        }
    }
    
    @RequestMapping(value = "result.do")
    public void result(HttpServletRequest request,HttpServletResponse response, int id, String callback) throws UnsupportedEncodingException{
      //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号

        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //支付宝交易号

        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        
        //计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);
        
        if(verify_result){//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码

            //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //如果有做过处理，不执行商户的业务程序
            }
            
            //该页面可做页面美工编辑
            WebUtil.response(response, "验证成功<br />"); 
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

            //////////////////////////////////////////////////////////////////////////////////////////
        }else{
            //该页面可做页面美工编辑
            WebUtil.response(response, "验证失败"); 
        }
    } 
    
    @RequestMapping(value = "wxreturn.do")
    public void wxreturn(HttpServletRequest request,HttpServletResponse response, String callback) throws IOException, ExecutionException, InterruptedException{
        InputStream is = request.getInputStream();
        String inXML = inputStream2String(is);
        OrderRequest orderReq = XMLUtil.toObject(inXML, OrderRequest.class);
        String openId = orderReq.getOpenid();
        String product_id = orderReq.getProduct_id();
        OrderResponse result = WeChat.order.orderQRCode(openId, 1);
        String mch_id = ConfKit.get("mch_id");
        
        OrderResponse p = new OrderResponse();
        p.setReturn_code("SUCCESS");
        p.setAppid(ConfKit.get("AppId"));
        p.setMch_id(ConfKit.get("mch_id"));
        String nonce_str = orderReq.getNonce_str();
        p.setNonce_str(nonce_str);
        p.setPrepay_id(result.getPrepay_id());
        p.setResult_code("SUCCESS");
        
        Map<String, String> singParam = new HashMap<String, String>();
        singParam.put("return_code", "SUCCESS");
        singParam.put("appid", ConfKit.get("AppId"));
        singParam.put("mch_id", mch_id);
        singParam.put("nonce_str", nonce_str);
        singParam.put("prepay_id", result.getPrepay_id());
        singParam.put("result_code", "SUCCESS");
        p.setSign(WeChat.order.createSign(singParam, ConfKit.get("pay_apikey"), false));
        XStream xs = new XStream(new XppDriver(new XmlFriendlyReplacer("_-", "_")));
        xs.alias("xml", p.getClass());
        String xml = xs.toXML(p);
        WebUtil.responseHtml(response, xml); 
    }
    
    public static final String inputStream2String(InputStream in) throws UnsupportedEncodingException, IOException{
        if(in == null)
            return "";
        
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n, "UTF-8"));
        }
        return out.toString();
    }
}
