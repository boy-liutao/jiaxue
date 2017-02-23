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
    <title>个人中心</title>
</head>
<body id="personal_wrap">
    <div id="personal_header">
        <div class="floor1">
            <img class="personal_avatar" src="<%=user.getImg() %>" alt=""/>
            <button class="personal_editinfo" id="edit_btn">编辑资料</button>
            <div class="clearfix"></div>
        </div>
        <div class="floor2">
            <h2 class="parent_name"><%=user.getParent() %></h2>
            <h5><span><%=user.getName() %></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span> <%=user.getSex()==0?"未知":(user.getSex()==1?"男":"女") %>
</span>&nbsp;&nbsp;|&nbsp;&nbsp;<span><%=user.getGrade() %></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span><%=user.getInschool() %></span></h5>
        </div>
    </div>
    <div class="personal_block" id="myorder_btn">
        <div class="personal_block_inner">
            <img class="personal_icon" src="../img/personal01.png" alt="" style="width: 0.3333rem;height: 0.52rem;"/>
            <span style="margin-left: 0.4rem">我的订单</span>
            <img class="personal_arrow" src="../img/arrow.png" alt=""/>
        </div>
    </div>
    <div class="personal_block" id="fruit_btn">
        <div class="personal_block_inner">
            <img class="personal_icon" src="../img/personal02.png" alt="" style="width: 0.4667rem;height: 0.4667rem;"/>
            <span style="margin-left: 0.2667rem">水果加餐</span>
            <img class="personal_arrow" src="../img/arrow.png" alt=""/>
            <span class="personal_tit">剩余<span id="fruitRemain"></span>次，立即购买</span>
        </div>
    </div>
    <div class="personal_block" id="dinner_btn">
        <div class="personal_block_inner">
            <img class="personal_icon" src="../img/personal03.png" alt="" style="width: 0.4133rem;height: 0.48rem;"/>
            <span style="margin-left: 0.32rem">晚餐</span>
            <img class="personal_arrow" src="../img/arrow.png" alt=""/>
            <span class="personal_tit">剩余<span id="dinnerRemain"></span>次，立即购买</span>
        </div>
    </div>
    <div class="personal_block" id="feedback_btn">
        <div class="personal_block_inner">
            <img class="personal_icon" src="../img/personal04.png" alt="" style="width: 0.3733rem;height: 0.44rem;"/>
            <span style="margin-left: 0.36rem">意见反馈</span>
            <img class="personal_arrow" src="../img/arrow.png" alt=""/>
        </div>
    </div>
    <div id="personal_footer">
        <h5>北京家学天地网络科技有限公司</h5>
        <h5>©2016&nbsp;All&nbsp;Rights&nbsp;Reserved</h5>
    </div>
    <div id="personal_popup_mask" style="display:none">
        <div id="personal_popup">
            <div class="personal_popup_inner">
                <span>您尚未报名托管，进入"首页-家学报名"可快速报名</span>
            </div>
        </div>
    </div>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/main.js?v=1.0"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script>
loadRemain();
var sid = <%=user.getSid()%>
function loadRemain(){
	ajaxUtil(null, mainpath+"/user/remain.do", function(response){
		var map = response.map;
		$("#fruitRemain").html(map.fruitRemain);
		$("#dinnerRemain").html(map.dinnerRemain);
	});
}
$("#edit_btn").click(function(){
	window.location.href="editInfo";
});
$("#myorder_btn").click(function(){
	window.location.href="myOrder";
});
$("#fruit_btn").click(function(){
	if (parseInt(sid) > 0){
		window.location.href="fruitRenewal";
	} else {
		$('#personal_popup_mask').fadeIn();
		$('#personal_popup_mask').delay(1500).fadeOut(1000);
	}
});
$("#dinner_btn").click(function(){
	if (parseInt(sid) > 0){
		window.location.href="dinnerRenewal";
	} else {
		$('#personal_popup_mask').fadeIn();
		$('#personal_popup_mask').delay(1500).fadeOut(1000);
	}
});
$("#feedback_btn").click(function(){
	window.location.href="feedback";
});
</script>
</body>
</html>