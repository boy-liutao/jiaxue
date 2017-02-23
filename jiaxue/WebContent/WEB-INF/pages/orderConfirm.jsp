<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.xuema.util.SessionUtil"%>
<%@page import="com.xuema.util.StringUtil"%>
<%@page import="com.xuema.util.DatetimeUtil"%>
<%@page import="com.xuema.bean.User"%>
<%@page import="com.xuema.bean.Orders"%>
<!DOCTYPE html>
<html lang="en">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = SessionUtil.getLoginUser(request);
	Orders o = (Orders)request.getAttribute("orders");
%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1,maximum-scale=1,user-scalable =no">
    <meta content="telephone=no" name="format-detection" /> 
    <script type="text/javascript" src="../js/flexible.js"></script>
    <script type="text/javascript" src="../js/flexible_css.js"></script>
    <link type="text/css" rel="stylesheet" href="../css/main.css?v=1.0"/>
    <link href="../css/mobiscroll.custom-2.5.0.min.css" rel="stylesheet" type="text/css" />
    <title>确认订单</title>
    <style type="text/css">
        .ui-loader-default{ display:none}
        .ui-mobile-viewport{ border:none;}
        .ui-page {padding: 0; margin: 0; outline: 0}
    </style>
</head>
<body id="oc_wrap">
<div id="oc_block1">
    <div id="oc_block1_inner">
        <div class="deposit_tit"><strong>托管地点</strong></div>
        <div class="deposit_fr">
            <div class="floor1">
                <strong class="oc_address_tit"></strong>
                <!--<span>(离您最近)</span>-->
                <!-- 
                <img class="oc_arrow" src="../img/arrow.png" alt="" style="display:none"/>
                <span class="oc_changeAddress"  style="display:none">更换学堂</span>
                -->
                <div class="clearfix"></div>
            </div>
            <hr/>
            <div class="floor2 oc_address_detail"></div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<div id="oc_block2">
    <div id="oc_block2_inner">
        <div class="tit"><strong>入托日期</strong></div>
        <div class="deposit_date">
            <input class="deposit_date_inp" type="text" name="deposit_date_inp" id="deposit_date_inp"/>
            <img src="../img/edit04.png" alt=""/>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<div id="oc_block3">
    <div id="oc_block3_inner">
        <div class="account_fl">
            <div class="floor1"><strong>账户信息</strong></div>
            <div class="floor2"><strong >孩子</strong></div>
        </div>
        <div class="account_fr">
            <div class="floor1"><strong><%=StringUtil.formatNull(user.getParent()) %></strong><span class="parent_tel"><%=StringUtil.formatNull(user.getPhone()) %></span></div>
            <hr/>
            <div class="floor2"><span class="child_tag"><%=StringUtil.formatNull(user.getName()) %>，
            <%
            	if (user.getSex()==1){
                	out.print("男孩");
            	} else if (user.getSex()==2){
                	out.print("女孩");
            	} else {
            	    out.print("未知");
            	}
            %>，
            <%=StringUtil.formatNull(user.getInschool()) %>，<%=StringUtil.formatNull(user.getGrade()) %></span></div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<div id="oc_block4">
    <div id="oc_block4_inner">
        <div class="ordered_info_fl">
            <div ><strong>订单信息</strong></div>
        </div>
        <div class="ordered_info_fr">
            <div class="floor" id="nursery_summary">
                <div class="td1">托管</div>
                <div class="td2" id="nurseryUnit"></div>
                <div class="td3">￥<span id="nurseryPrice"></span></div>
                <div class="clearfix"></div>
            </div>
            <div class="floor" id="fruit_summary">
                <div class="td1">水果加餐</div>
                <div class="td2">20次</div>
                <div class="td3">￥<span id="fruitPrice"></span></div>
                <div class="clearfix"></div>
            </div>
            <div class="floor" id="dinner_summary">
                <div class="td1">营养晚餐</div>
                <div class="td2">20次</div>
                <div class="td3">￥<span id="dinnerPrice"></span></div>
                <div class="clearfix"></div>
            </div>
            <hr/>
            <div class="footer"><strong style="font-weight: 600">总价</strong><strong class="total_price">￥<span id="money"></span></strong></div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<div id="oc_block5">
    <div class="oc_block5_inner" id="coupon_btn">
        <strong>优惠券</strong>
        <img class="coupon_arrow" src="../img/fr1.png" alt=""/>
        <span id="coupon_l">暂无可用优惠券</span>
        <input style="display:none" id="coupon_v"></input>
        <input style="display:none" id="coupon_id"></input>
    </div>
</div>
<div id="oc_block6">
    <div class="oc_block6_item payment_total">
        <b>支付:</b><b class="fr_total_cash">￥<span id="moneyAfterCoupon"></span></b>
    </div>
    <div class="oc_block6_item2" id="weixin_pay">
        <span>微信支付</span>
    </div>
    <div class="oc_block6_item2" id="ali_pay" style="display:none">
        <span>支付宝支付</span>
    </div>
</div>
<!--选择托管地点-->
<div id="sa_wrap">
    <div class="sa_opacity"></div>
    <div class="sa_block_title">
        <hr/>
        <h3>选择托管地点</h3>
        <hr class="second"/>
    </div>
    <hr style="color: lightgrey;background: lightgrey;height: 0.03rem;"/>
    <div class="sa_content" id="school_list">
    </div>
    <!--<div class="additional"></div>-->
    <button class="sa_cancel">取消</button>
</div>
<div id="coupon_wrap">
	<div class="coupon_content">
        <div class="coupon_title">
            <hr class="first"/>
            <h3>选择托管地点</h3>
            <hr class="second"/>
            <div class="clearfix"></div>
        </div>
        <div class="coupon_blocks" id="coupon_list">
        </div>
    </div>
    <div class="coupon_cancel" id="coupon_cancel">取消</div>
    <!-- <div class="coupon_opacity"></div>
    <div class="coupon_block_title">
        <hr/>
        <h3>选择可用优惠券</h3>
        <hr class="second"/>
    </div>
    <hr style="color: lightgrey;background: lightgrey;height: 0.03rem;"/>
    <div class="coupon_content" id="coupon_list">
    </div>
    <div class="additional"></div>
    <button class="coupon_cancel">取消</button> -->
</div>
<div id="payment_prompt_popup" style="display: none"></div>

<script type="text/javascript" src="../js/jquery.min.js"></script>
<script src="../js/jquery.mobile-1.3.1.min.js"></script>
<script src="../js/mobiscroll.custom-2.5.0.min.js" type="text/javascript"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="../js/main.js?v=1.0"></script>
<script type="text/javascript" src="../js/common.js?v=1.2"></script>
<script>
if (isWeiXin()){
	$("#weixin_pay").show();
} else {
	$("#weixin_pay").show();
	//$("#ali_pay").show();
}
var esid = urlparameter("sid");
var enurseryUnit = urlparameter("nurseryUnit");
var enurseryCount = urlparameter("nurseryCount");
var efruitCount = urlparameter("fruitCount");
var edinnerCount = urlparameter("dinnerCount");
var oid = urlparameter("oid");
var estartDate = null;
if (oid != ""){
	//load from attribue
	esid = <%=(o==null)?-1:o.getSid()%>;
	enurseryUnit = <%=(o==null)?-1:o.getNurseryUnit()%>;
	enurseryCount = <%=(o==null)?-1:o.getNurseryCount()%>;
	efruitCount = <%=(o==null)?-1:o.getFruitCount()%>;
	edinnerCount = <%=(o==null)?-1:o.getDinnerCount()%>;
	$("#deposit_date_inp").val("<%=(o==null)?"":DatetimeUtil.formatDate(o.getStartDate())%>");
}
if (parseInt(efruitCount) <= 0){
	$("#fruit_summary").hide();
}
if (parseInt(edinnerCount) <= 0){
	$("#dinner_summary").hide();
}

//引用日期插件
var opt = {
    preset: 'date', //日期
    theme: 'sense-ui', //皮肤样式
    display: 'modal', //显示方式
    mode: 'scroller', //日期选择模式
    dateFormat: 'yy-mm-dd', // 日期格式
    setText: '确定', //确认按钮名称
    cancelText: '取消',//取消按钮名籍我
    dateOrder: 'yymmdd', //面板中日期排列格式
    dayText: '日', monthText: '月', yearText: '年', //面板中年月日文字
    endYear:2020 //结束年份
};
$("#deposit_date_inp").mobiscroll(opt).date(opt);




loadAllSchool();
function loadAllSchool(){
	ajaxUtil(null, mainpath+"/school/listAll.do",function(response){
		var html = "";
		var list = response.list;
		var cur_sid = urlparameter("sid");
		for (var i in list){
			var s = list[i];
			
			var price_term_nursery = "";
			var price_month_nursery = "";
			var price_day_nursery = "";
			var price_20_fruit = "";
			var price_20_dinner = "";
			for (var si in s.settings){
				var setting = s.settings[si];
				if (setting.name == "price.term.nursery"){
					price_term_nursery = setting.value;
				}
				if (setting.name == "price.month.nursery"){
					price_month_nursery = setting.value;
				}
				if (setting.name == "price.day.nursery"){
					price_day_nursery = setting.value;
				}
				if (setting.name == "price.20.fruit"){
					price_20_fruit = setting.value;
				}
				if (setting.name == "price.20.dinner"){
					price_20_dinner = setting.value;
				}
			}
			
			var schoolCheckBtn = "../img/sa02.png";
			if (esid == s.id){
				$(".oc_address_tit").html("家学天地-" + s.name);
				$(".oc_address_detail").html(s.address);
				if (enurseryUnit == 1){
					$("#nurseryUnit").html("1学期");
					$("#nurseryPrice").html(price_term_nursery);
				} else if (enurseryUnit == 2){
					$("#nurseryUnit").html(enurseryCount+"个月");
					$("#nurseryPrice").html(price_month_nursery);
				} else if (enurseryUnit == 3){
					$("#nurseryUnit").html(enurseryCount+"天");
					$("#nurseryPrice").html(price_day_nursery);
				} 
				$("#fruitPrice").html(price_20_fruit);
				$("#dinnerPrice").html(price_20_dinner);
				schoolCheckBtn = "../img/sa01.png";
			}
			
			html += "<div class='sa_block' id='sid_"+s.id+"'>";
			html += "    <div class='sa_address'>";
			html += "        <img class='sa1_radio address_radio' src='"+schoolCheckBtn+"' alt='' data='"+s.id+"'/>";
			html += "        <div class='sa_address_fr'>";
			html += "            <h5><strong class='address_title'>家学天地-"+s.name+"</strong></h5>";
			html += "            <h5 class='address_detail'>"+s.address+"</h5>";
			html += "        </div>";
			html += "        <div class='school_params' price_term_nursery='"+price_term_nursery+"' price_month_nursery='"+price_month_nursery+"' price_day_nursery='"+price_day_nursery+"' price_20_fruit='"+price_20_fruit+"' price_20_dinner='"+price_20_dinner+"'></div>"
			html += "        <div class='clearfix'></div>";
			html += "    </div>";
			html += "</div>";
			html += "<hr/>";
			
			
		}
		$("#school_list").html(html);
		evelMoney();
		if (cur_sid == null || cur_sid == "" || cur_sid == 0){
			$(".oc_address_tit").html("家学天地");
			$("#swith_school").html("更换学堂");
		}
		//更换学堂地址
	    $('.address_radio').each(function () {
	        $(this).click(function () {
	            var oTitle = $(this).next().find('.address_title').text();
	            var oDetail = $(this).next().find('.address_detail').text();
	            $('#sa_wrap').fadeOut();
	            $('.oc_address_tit').text(oTitle);
	            $('.oc_address_detail').text(oDetail);
	            $("html,body").animate({scrollTop: 0}, 500);
	            var sid = $(this).attr("data");
	            esid = sid;
	            
	            var price_term_nursery = $("#sid_" + sid).find(".school_params").attr("price_term_nursery");
	        	var price_month_nursery = $("#sid_" + sid).find(".school_params").attr("price_month_nursery");
	        	var price_day_nursery = $("#sid_" + sid).find(".school_params").attr("price_day_nursery");
	        	var price_20_fruit = $("#sid_" + sid).find(".school_params").attr("price_20_fruit");
	        	var price_20_dinner = $("#sid_" + sid).find(".school_params").attr("price_20_dinner");
	            
	            if (enurseryUnit == 1){
					$("#nurseryPrice").html(price_term_nursery);
				} else if (enurseryUnit == 2){
					$("#nurseryUnit").html(enurseryCount+"个月");
					$("#nurseryPrice").html(price_month_nursery);
				} else if (enurseryUnit == 3){
					$("#nurseryUnit").html(enurseryCount+"天");
					$("#nurseryPrice").html(price_day_nursery);
				} 
				$("#fruitPrice").html(price_20_fruit);
				$("#dinnerPrice").html(price_20_dinner);
	            evelMoney();
	        });
	    });
	    //学堂选择按钮切换
	    $("#sa1_radio").click(function(){
	    	if ($(this).attr('src') == "../img/sa02.png") {
	            $(this).attr('src', '../img/sa01.png');
	            $(this).parent().parent().siblings().find('.address_radio').attr('src', '../img/sa02.png');
	        } else {
	       	$(this).attr('src', '../img/sa02.png');
	            $(this).parent().parent().siblings().find('.address_radio').attr('src', '../img/sa01.png');
	        } 
	    });
	    
	});
	
    //支付	
    /* $(".payment_total").click(function(){
    	evelMoney();
    	
    }); */
   
  	//微信支付	
  	 $("#weixin_pay").click(function(){
  		var startDate = $("#deposit_date_inp").val();
  		if(startDate == ''){
  			if (enurseryCount != "" && enurseryCount > 0){
	            alert('请填写入托日期');
	            return false;
  			}
        }else{
  			$('#payment_prompt_popup').show();
  		}
  		var cid = $("#coupon_id").val();
    	ajaxUtil({uid:<%=user.getId()%>,openId:'<%=user.getOpenid()%>',sid:esid,cid:cid,nurseryUnit:enurseryUnit,nurseryCount:enurseryCount,fruitCount:efruitCount,dinnerCount:edinnerCount,startDate:startDate,oid:'<%=(o==null)?-1:o.getId()%>'}, mainpath+"/order/weixinCreate.do", function(response){
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
		        	   alert("支付成功，感谢您对我们的信任，我们的老师很快会跟您联系！");
		        	   window.location.href="index";
		           } else {
		        	   alert("支付未成功，如遇到支付问题请联系400-060-1515");
		           }
		       }
		   ); 
	}
}

//加载优惠券
loadCoupon();
function loadCoupon(){
	var money = $("#money").html();
	ajaxUtil({cond:money},mainpath+"/coupon/list.do",function(response){
		var html = "";
		var list = response.list;
		var cur_sid = urlparameter("sid");
		for (var i in list){
			var s = list[i];
			html += "<div class='coupon_block' id='sid_"+s.id+"'>";
			html += "	<img class='coupon_radio' src='../img/sa02.png' alt='' cid='"+s.id+"' cvalue='"+s.value+"'>";
			html += "	<img class='coupon_fr_back' src='../img/coupon.png' alt='' >";
			html += "	<label class='coupon_money'>￥<span class='face_value'>"+s.value+"</span></label>";
			html += "	<div class='coupon_floor'>";
			html += "		<label class='title'>优惠券</label>";
			html += "		<label class='tit1'>满<span>"+s.cond+"</span>元可用</label>";
			html += "		<hr/>";
			html += "		<label class='tit2'>有效期至："+formatDate(s.deadline)+"</label>";
			html += "	</div>";
			html += "   <div class='clearfix'></div>";
			html += "</div>";
			
			/* html += "    <div class='sa_address'>";
			html += "        <img class='sa1_radio coupon_radio' src='../img/sa02.png' alt='' cid='"+s.id+"' cvalue='"+s.value+"'/>";
			html += "        <div class='sa_address_fr'>";
			html += "            <h5><strong class='address_title'>"+s.value+"元</strong></h5>";
			html += "            <h5 class='address_detail'>有效期至："+formatDate(s.deadline)+"</h5>";
			html += "        </div>";
			html += "        <div class='clearfix'></div>";
			html += "    </div>"; */
		}
		$("#coupon_list").html(html);
		
		$('.coupon_radio').click(function () {
			if($(this).attr('src', '../img/sa02.png')){
                $(this).attr('src', '../img/sa01.png');
                $(this).parent().siblings().find('.coupon_radio').attr('src', '../img/sa02.png');
                var oText = $(this).next().next().text();
                $('#coupon_l').text("-"+oText);
                $('#coupon_wrap').slideUp();
            }
            
           /*  var value = $(this).next().next().find('.address_title').text(); */
            /* $('#coupon_wrap').fadeOut(); */
            var cid = $(this).attr("cid");
            var value = $(this).attr("cvalue");
            /* $("#coupon_l").html("-￥" + value + "元" ); */
            $("#coupon_v").val(value);
            $("#coupon_id").val(cid);
            evelMoney();
	    });
		
		if (list.length > 0){
			$("#coupon_l").html("请选择");
			$("#coupon_btn").click(function(){
				$('#coupon_wrap').slideDown();
			});
			$('#coupon_cancel').click(function () {
	            $('#coupon_wrap').slideUp();
	        });
		}
	});
    
}

function evelMoney(){
	var price_term_nursery = $("#sid_" + esid).find(".school_params").attr("price_term_nursery");
    var price_month_nursery = $("#sid_" + esid).find(".school_params").attr("price_month_nursery");
    var price_day_nursery = $("#sid_" + esid).find(".school_params").attr("price_day_nursery");
    var price_20_fruit = $("#sid_" + esid).find(".school_params").attr("price_20_fruit");
    var price_20_dinner = $("#sid_" + esid).find(".school_params").attr("price_20_dinner");
	var coupon_v = $("#coupon_v").val();
	if (coupon_v == ""){
		coupon_v = 0;
	}
    
	var money = 0;
	if (parseInt(enurseryUnit) == 1){
		money += parseInt(price_term_nursery);
	}
	if (parseInt(enurseryUnit) == 2){
		money += parseInt(enurseryCount)*parseInt(price_month_nursery);
	}
	if (parseInt(enurseryUnit) == 3){
		money += parseInt(enurseryCount)*parseInt(price_day_nursery);
	}
	if (parseInt(efruitCount) > 0){
		money += parseInt(price_20_fruit);
	}
	if (parseInt(edinnerCount) > 0){
		money += parseInt(price_20_dinner);
	}
	if (parseInt(coupon_v) > 0){
		money -= parseInt(coupon_v);
	}
	$("#money").html(money);
	$("#moneyAfterCoupon").html(money);
}
</script>
</body>
</body>
</html>