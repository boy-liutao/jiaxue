<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1,maximum-scale=1,user-scalable =no">
    <script type="text/javascript" src="../js/flexible.js"></script>
    <script type="text/javascript" src="../js/flexible_css.js"></script>
    <link type="text/css" rel="stylesheet" href="../css/main.css"/>
    <title>我的订单</title>
</head>
<body id="order_wrap">
    
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript">
    
    loadmyOrder();
    
    function loadmyOrder(){
    	ajaxUtil(null,mainpath+"/order/list.do",function(response){
    		var html = "";
    		var address = "家学天地-";
    		var cs = response.list;
    		for (var i in cs){
    			var c = cs[i];
    			html+= "<a href=\"orderInfo?id="+c.id+"\"><div class=\"order_block\"> ";
    			html+= "<div class=\"order_block_inner\">";
    			html+= "<div class=\"floor1\">";
    			html+= "<span>状态 </span>";
 				html+= "<span class=\"order_status\">"+statusEnum[c.status]+" </span>";
    			html+= "<span class=\"order_time\">"+c.updateTime+" </span>";
    			html+= "</div>";
    			html+= "<hr/>";
    			html+= "<div class=\"floor2\">";
    			if (c.school != null){
    				addr = address + c.school.name;
   				    html+= "<strong class=\"order_address\">"+addr+" </strong>";
				   	if(c.status==0){
						html+= "<button class=\"order_payment\" onclick='pay(\""+c.id+"\")'>立即支付 </button>";
    				}
				   	if(c.status==4){
						html+= "<button class=\"order_purchase\" onclick='buyAgain()'>再次购买 </button>";
    				}
   				}
    			html+= "</div>";
    			html+= "<hr/>";
    			html+= "<table class=\"floor3\">";
    			html+= "<tr>";
    			if(c.nurseryCount== 0){
    				html+= "<td class=\"td1\"></td>";
                    html+= "<td class=\"td2\"></td>";
                    html+= "<td class=\"td3\"></td>";
    			}else{
	    			html+= "<td class=\"td1\">托管</td>";
	                html+= "<td class=\"td2\">"+c.nurseryCount+"学期</td>";
	                html+= "<td class=\"td3\">￥"+c.nurseryMoney+"</td>";
    			}
                html+= "</tr>";
                html+= "<tr>";
                if(c.fruitCount==0){
                	html+= "<td class=\"td1\"></td>";
                    html+= "<td class=\"td2\"></td>";
                    html+= "<td class=\"td3\"></td>";
                }else{
	                html+= "<td class=\"td1\">水果加餐</td>";
	                html+= "<td class=\"td2\">"+c.fruitCount+"次</td>";
	                html+= "<td class=\"td3\">￥"+c.fruitMoney+"</td>";
                }
                html+= "</tr>";
                html+= "<tr>";
                if(c.dinnerCount==0){
                	 html+= "<td class=\"td1\"></td>";
                     html+= "<td class=\"td2\"></td>";
                     html+= "<td class=\"td3\"></td>";
                }else{
	                html+= "<td class=\"td1\">营养晚餐</td>";
	                html+= "<td class=\"td2\">"+c.dinnerCount+"次</td>";
	                html+= "<td class=\"td3\">￥"+c.dinnerMoney+"</td>";
                }
                html+= "</tr>";
                html+= "</table>";
                html+= "<hr/>";
                html+= "<div class=\"floor4\">";
                html+= "<b>总价</b>";
                html+= "<strong class=\"order_total\">￥"+c.money+" </strong>";
                html+= "<span class=\"order_number\">"+c.id+" </span>";
                html+= "<span class=\"order_number_tit\">订单号： </span></a>";
                html+= "</div>";
                html+= "</div>";
                html+= "</div>";
    		}
    		$('#order_wrap').append(html);
    		if (cs.length == 0){
    			window.location.href="orderNone";
    		}
    	});
    }
</script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/main.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
function buyAgain(){
	window.location.href="enroll";
}
function pay(oid){
	window.location.href="orderConfirm?oid="+oid;
}
</script>
</body>
</html>