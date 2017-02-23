<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.xuema.util.SessionUtil"%>
<%@page import="com.xuema.bean.User"%>
<!DOCTYPE html>
<html lang="en">   
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = SessionUtil.getLoginUser(request);
%>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1,maximum-scale=1,user-scalable =no">
	<script type="text/javascript" src="../js/flexible.js"></script>
    <script type="text/javascript" src="../js/flexible_css.js"></script>
    <link type="text/css" rel="stylesheet" href="../css/main.css"/>
    <title>水果续费</title>
</head>
<body id="fr_wrap">
<div class="fr_renew">
    <div class="fr_renew_inner">
        <div class="fr_renew_tit"><strong>水果加餐</strong></div>
        <div class="fr_renew_fr">
            <div class="floor1"><b style="color: #000000">￥<span id="fruitPriceShow"></span></b></div>
            <hr/>
            <div class="floor2">
                <h5>￥<span id="fruitPerTime"></span>/次，一次预收20次费用，</h5>
                <h5 class="total_tit">共计￥<span id="fruitPrice"></span>元！</h5>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<div id="fr_block3">
    <div class="fr_block3_inner">
        <img class="renew_unselected renew_btn" src="../img/edit02.png" alt=""/>
        <img class="renew_selected renew_btn" src="../img/edit03.png" alt="" style="display: none"/>
        <span>同时给加餐续费</span>
    </div>
</div>
<div class="fr_renew supper_renewwal" style="display:none">
    <div class="fr_renew_inner">
        <div class="fr_renew_tit"><strong>晚餐</strong></div>
        <div class="fr_renew_fr">
            <div class="floor1"><b style="color: #000000">￥<span id="dinnerPriceShow"></span></b></div>
            <hr/>
            <div class="floor2">
                <h5>￥<span id="dinnerPerTime"></span>/次，一次预收20次费用，</h5>
                <h5 class="total_tit">共计￥<span id="dinnerPrice"></span>元！</h5>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<div id="fr_block4" style="display:none">
    <div class="fr_block4_inner">
        <strong>优惠券</strong>
        <img class="fr_arrow" src="../img/fr1.png" alt=""/>
        <span>暂无可用优惠券</span>
    </div>
</div>
<div id="fr_block5">
    <div class="fr_block5_item payment_total">
        <b>支付:</b><b class="fr_total_cash">￥<span id="money"></span></b>
    </div>
    <div class="fr_block5_item2" id="weixin_pay">
        <span>微信支付</span>
    </div>
    <div class="fr_block5_item2" id="ali_pay" style="display: none">
        <span>支付宝支付</span>
    </div>
</div>
<div id="payment_prompt_popup" style="display: none"></div>

<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="../js/main.js?v=1.0"></script>
<script type="text/javascript" src="../js/common.js?v=1.2"></script>
<script>
loadFruit();
loadDinner();
showPay();
function loadFruit(){
	var name='price.20.fruit';
	ajaxUtil({name:name},mainpath+"/order/setting.do",function(response){
		$("#fruitPerTime").html(response.value/20);
		$("#fruitPrice").html(response.value);
		$("#fruitPriceShow").html(response.value);
		countPrice();
	});
}
function loadDinner(){
	var name='price.20.dinner';
	ajaxUtil({name:name},mainpath+"/order/setting.do",function(response){
		$("#dinnerPerTime").html(response.value/20);
		$("#dinnerPrice").html(response.value);
		$("#dinnerPriceShow").html(response.value);
	});
}
/*** 水果续费 ***/
/* $("#fr_block3 .renew_unselected").click(function(){
	$(this).hide();
    $('#fr_block3 .renew_selected').show().css('margin-left', '0.5333rem');
    countPrice();
});
$("#fr_block3 .renew_selected").click(function(){
	$(this).hide();
    $('#fr_block3 .renew_unselected').show();
    countPrice();
}); */
/*** 晚餐续费 ***/
var flag = true;
$('.fr_block3_inner').click(function () {
    if(flag){
        $('.renew_unselected').hide();
        $('.renew_selected').show().css('margin-left', '0.5333rem');
        $('.supper_renewwal').slideDown();
        countPrice();
        flag = false;
    }
    else{
        $('.renew_selected').hide();
        $('.renew_unselected').show();
        $('.supper_renewwal').slideUp();
        countPrice();
        flag = true;
    }
});
function countPrice(){
	var hasFruit = true;
	var hasDinner = $("#fr_block3 .renew_unselected").is(":hidden");
	var fruitPrice = $("#fruitPrice").html();
	var dinnerPrice = $("#dinnerPrice").html();
	var money = 0;
	if (hasFruit){
		money += parseInt(fruitPrice);
	}
	if (hasDinner){
		money += parseInt(dinnerPrice);
	}
	$("#money").html(money);
}
function showPay(){
	if (isWeiXin()){
		$("#weixin_pay").show();
	} else {
		$("#weixin_pay").show();
		//$("#ali_pay").show();
	}
}
//微信支付	
$("#weixin_pay").click(function(){
	var hasFruit = true;
	var hasDinner = $("#fr_block3 .renew_unselected").is(":hidden");
	var fruitCount = 0;
	var dinnerCount = 0;
	if (hasFruit){
		fruitCount = 20;
	}
	if (hasDinner){
		dinnerCount = 20;
	}
    $('#payment_prompt_popup').show();

	ajaxUtil({uid:<%=user.getId()%>,openId:'<%=user.getOpenid()%>',sid:<%=user.getSid()%>,nurseryUnit:-1,nurseryCount:0,fruitCount:fruitCount,dinnerCount:dinnerCount}, mainpath+"/order/weixinCreate.do", function(response){
		var o = response;
		if (typeof WeixinJSBridge == "undefined"){
		   if( document.addEventListener ){
		       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		   }else if (document.attachEvent){
		       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
		       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		   }
		}else{
		   onBridgeReady(o.appId, o.timestamp, o.nonceStr, o.payId, o.paySign);
		} 
	});
});

function onBridgeReady(appId, timestamp, nonceStr, payId, paySign){
	   WeixinJSBridge.invoke(
	       'getBrandWCPayRequest', {
	           "appId": appId,     //公众号名称，由商户传入     
	           "timeStamp":timestamp, //时间戳，自1970年以来的秒数     
	           "nonceStr":nonceStr, //随机串     
	           "package":"prepay_id="+payId,     
	           "signType":"MD5",         //微信签名方式：     
	           "paySign":paySign //微信签名 
	       },
	       function(res){
	    	   $('#payment_prompt_popup').hide();
	           if(res.err_msg == "get_brand_wcpay_request:ok" ) {     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
	        	   ajaxUtil({prepayId:payId,status:1},mainpath+"/order/payOrder.do",null);
	        	   var id = urlparameter("id");
	        	   alert("支付成功，感谢您对学吗的信任，客服随后将联系您办理托管");
	        	   window.location.href="index";
	           } else {
	        	   alert("支付未成功，如遇到支付问题请联系学吗客服");
	           }
	       }
	   ); 
}
</script>
</body>
</html>