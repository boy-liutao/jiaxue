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
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1,maximum-scale=1,user-scalable =no">
    <script type="text/javascript" src="../js/flexible.js"></script>
    <script type="text/javascript" src="../js/flexible_css.js"></script>
    <link type="text/css" rel="stylesheet" href="../css/main.css"/>
    <title>订单详情-XXX</title>
</head>
<body id="orderinfo_wrap">
   
<script type="text/javascript" src="../js/jquery.min.js"></script>
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
loadOrderInfo();
function loadOrderInfo(){
	
	var aid = urlparameter("id");
	if (aid != null && aid != '' && aid != undefined){
		//load user
		ajaxUtil({id:aid},mainpath+"/order/getOrder.do",function(response){
			var html = "";
			var address = "家学天地-";
			var c = response;
			var sex = "男";
			html+= "<div id=\"orderinfo_block1\"> ";
			html+= "<div id=\"orderinfo_block1_inner\">";
			html+= "<div class=\"order_status_block\">状态:<strong class=\"order_status\">"+statusEnum[c.status]+"</strong></div>";
			html+= " <div class=\"order_number_block\">订单号:<span class=\"order_number\">"+c.id+"</span></div>";
			html+= "<button class=\"purchase_again\" style=\"display: none\" onclick='buyAgain()'>继续购买</button>";
			html+= " </div> ";
			html+= " </div>";
			html+= "<div id=\"orderinfo_block2\"> ";
			html+= "<div id=\"orderinfo_block2_inner\">";
			html+= "<div class=\"deposit_tit\"><strong>托管地点</strong></div>";
			html+= " <div class=\"deposit_fr\">";
			if (c.school != null){
				addr = address + c.school.name;
				    html+= "<div class=\"floor1\"><strong>"+addr+"</strong></div>";
				}
			html+= "  <hr/> ";
			if (c.school != null){
				html+= "  <div class=\"floor2\">"+c.school.address+"</div>";
				}
			html+= " </div> ";
			html+= "  <div class=\"clearfix\"></div>";
			html+= " </div>";
			html+= " </div>";
			if(c.startDate!=null){
			html+= "<div id=\"orderinfo_block3\"> ";
			html+= "<div id=\"orderinfo_block3_inner\">";
			html+= " <div class=\"tit\"><strong>入托日期</strong></div>";
			html+= " <div class=\"deposit_date\"><strong class=\"orderinfo_date\">"+formatDate(c.startDate)+"</strong></div>";
			html+= "  <div class=\"clearfix\"></div>";
			html+= " </div>";
			html+= " </div>"; 
			}
			if(c.endDate!=null){
				html+= "<div id=\"orderinfo_block3\"> ";
				html+= "<div id=\"orderinfo_block3_inner\">";
				html+= " <div class=\"tit\"><strong>结束日期</strong></div>";
				html+= " <div class=\"deposit_date\"><strong class=\"orderinfo_date\">"+formatDate(c.endDate)+"</strong></div>";
				html+= "  <div class=\"clearfix\"></div>";
				html+= " </div>";
				html+= " </div>"; 
			}
			html+= "<div id=\"orderinfo_block4\"> ";
			html+= "<div id=\"orderinfo_block4_inner\">";
			html+= " <div class=\"account_fl\">";
			html+= " <div class=\"floor1\"><strong>账户信息</strong></div>";
			html+= "  <div class=\"floor2\"><strong >孩子</strong></div>";
			html+= " </div>";
			html+= "   <div class=\"account_fr\">";
			html+= " <div class=\"floor1\"><strong class=\"orderinfo_parentname\">"+c.user.parent+"</strong><span class=\"parent_tel\">"+c.user.phone+"</span></div>";
			html+= "  <hr/>";
			if(c.user==2){
				sex="女";
			}
			html+= " <div class=\"floor2\"><span class=\"child_tag\">"+c.user.name+"</span><span class=\"child_tag\">,"+sex+"</span><span class=\"child_tag\">,"+c.user.inschool+"</span><span class=\"child_tag\">,"+c.user.grade+"</span></div>";
			html+= " </div>";
			html+= " <div class=\"clearfix\"></div>";
			html+= " </div>";
			html+= " </div>";
			html+= "<div id=\"orderinfo_block5\"> ";
			html+= "<div id=\"orderinfo_block5_inner\">";
			html+= " <div class=\"ordered_info_fl\">";
			html+= " <div ><strong>订单信息</strong></div>";
			html+= " </div>";  
			html+= "   <div class=\"ordered_info_fr\">";
			html+= " <div class=\"floor\">";  
			if(c.nurseryCount== 0){
				html+= "<div class=\"td1\"></div>";
                html+= "<div class=\"td2\"></div>";
                html+= "<div class=\"td3\"></div>";
			}else{
    			html+= "<div class=\"td1\">托管</div>";
                html+= "<div class=\"td2\">"+c.nurseryCount+"学期</div>";
                html+= "<div class=\"td3\">￥"+c.nurseryMoney+"</div>";
			}
			html+= "  <div class=\"clearfix\"></div>";  
			html+= "  </div>";  
			html+= " <div class=\"floor\">";  
			if(c.fruitCount==0){
            	html+= "<div class=\"td1\"></div>";
                html+= "<div class=\"td2\"></div>";
                html+= "<div class=\"td3\"></div>";
            }else{
                html+= "<div class=\"td1\">水果加餐</div>";
                html+= "<div class=\"td2\">"+c.fruitCount+"次</div>";
                html+= "<div class=\"td3\">￥"+c.fruitMoney+"</div>";
            }
			html+= "  <div class=\"clearfix\"></div>";  
			html+= "  </div>";         
			html+= " <div class=\"floor\">";  
			if(c.dinnerCount==0){
           	 html+= "<div class=\"td1\"></div>";
                html+= "<div class=\"td2\"></div>";
                html+= "<div class=\"td3\"></div>";
           }else{
               html+= "<div class=\"td1\">营养晚餐</div>";
               html+= "<div class=\"td2\">"+c.dinnerCount+"次</div>";
               html+= "<div class=\"td3\">￥"+c.dinnerMoney+"</div>";
           }	 
			html+= "  <div class=\"clearfix\"></div>";  
			html+= "  </div>";     
			html+= "   <hr/>"; 
			html+= "  <div class=\"footer\"><strong style=\"font-weight: 600\">总价</strong><strong class=\"total_price\">￥"+c.money+"</strong></div>"; 
			html+= "  </div>"; 
			html+= "   <div class=\"clearfix\"></div>"; 
			html+= "  </div>"; 
			html+= "  </div>"; 
			html+= " <div id=\"coupon\" style=\"display: none\">"; 
			html+= "  <div class=\"coupon_inner\">"; 
			html+= "  <strong>优惠券</strong>"; 
			html+= "  <span>-￥0</span>"; 
			html+= "  </div>";     
			html+= "  </div>"; 
			html+= " <div id=\"payment_block\" style=\"display: none\">"; 
			html+= "  <div class=\"payment_block_item payment_total\">"; 
			html+= "  <b>支付:</b><b class=\"order_total_cash\">￥"+c.money+"</b>"; 
			html+= "  </div>";
			html+= "  <div class=\"payment_block_item2\" id=\"weixin_pay\">";
			html+= "  <span>微信支付</span>";
			html+= "   </div>"; 
			html+= "   <div class=\"payment_block_item2\" id=\"ali_pay\" style=\"display:none\">"; 
			html+= "  <span>支付宝支付</span>";     
			html+= "   </div>";  
			html+= "   </div>"; 
			html+= " <div id=\"orderinfo_refund\" style=\"display: none\">"; 
			html+= "  <div id=\"orderinfo_refund_inner\">"; 
			html+= "  <div class=\"orderinfo_refund_fl\">"; 
			html+= "  <div ><strong>退费信息</strong></div>";     
			html+= "    </div>"; 
			html+= "   <div class=\"orderinfo_refund_fr\">"; 
			html+= "   <div class=\"floor\">";     
			html+= "   <div class=\"td1\">申请日期</div>"; 
			if(c.returnApplyDate==null){
				html+= "   <div class=\"td2\"></div>"; 
			}else{
				html+= "   <div class=\"td2\">"+formatDate(c.returnApplyDate)+"</div>";
			}
			html+= "   <div class=\"clearfix\"></div>"; 
			html+= "   </div>"; 
			if(c.status==3){
				html+= "   <div class=\"floor\">";     
				html+= "   <div class=\"td1\">退费完成</div>"; 
				html+= "   <div class=\"td2\">"+formatDate(c.returnDoneDate)+"</div>"; 
				html+= "   <div class=\"clearfix\"></div>"; 
				html+= "   </div>"; 
			}
			html+= "   <div class=\"floor\">"; 
			html+= "   <div class=\"td1\">托管退费</div>"; 
			html+= "   <div class=\"td2\">按合同约定退费</div>"; 
			if(c.status==3){
				html+= "   <div class=\"td3\">￥"+c.returnNurseryMoney+"</div>"; 
			}
			html+= "   <div class=\"clearfix\"></div>"; 
			html+= "   </div>"; 
			html+= "   <div class=\"floor\">"; 
			html+= "   <div class=\"td1\">水果加餐</div>"; 
			html+= "   <div class=\"td2\">￥"+c.returnFruitMoney+"</div>"; 
			html+= "   <div class=\"clearfix\"></div>"; 
			html+= "    </div>"; 
			html+= "    <div class=\"floor\">"; 
			html+= "   <div class=\"td1\">营养晚餐</div>"; 
			html+= "   <div class=\"td2\" >￥"+c.returnDinnerMoney+"</div>";    
			html+= "   <div class=\"clearfix\"></div>";        
			html+= "    </div>";            
			html+= "   <hr/>";
			var tatal = c.returnFruitMoney + c.returnDinnerMoney+c.returnNurseryMoney;
			html+= "  <div class=\"footer\">";          
			html+= "   <strong>退费合计</strong>";
			if(c.status==2){
			html+= "   <strong class=\"refund_total\">协商中</strong>";                    
			} 
			if(c.status==3){
				html+= "   <strong class=\"refund_total\">"+tatal+"</strong>";    
			}
			html+= "    </div>";
			html+= "    </div>";  
			html+= "    </div>";  
			html+= "    <div class=\"clearfix\"></div>";                
			html+= "   </div>";                 
			$('#orderinfo_wrap').append(html);
		});
	}
}


function buyAgain(){
	window.location.href="enroll";
}
function pay(oid){
	window.location.href="orderConfirm?oid="+oid;
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
</script>
</body>
</html>