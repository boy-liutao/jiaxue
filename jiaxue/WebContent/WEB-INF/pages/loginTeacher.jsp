<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1,maximum-scale=1,user-scalable =no">
    <script type="text/javascript" src="../js/flexible.js"></script>
    <script type="text/javascript" src="../js/flexible_css.js"></script>
    <link type="text/css" rel="stylesheet" href="../css/style.css"/>
    <title>绑定账号</title>
</head>
<body id="login_wrap">
<div id="loginT_wrap">
    <div id="loginT_tel">
        <img class="login_item" src="../img/login01.png" alt="" style="width: 0.3733rem;height: 0.64rem;"/>
        <div class="login_item">
            <span>手机号</span>
            <input id="phone" type="text" placeholder="请输入手机号"/>
        </div>
        <div class="clearfix"></div>
    </div>

    <div id="login_validate">
        <img class="login_item" src="../img/login02.png" alt="" style="width: 0.48rem;height: 0.68rem;"/>
        <div class="login_item">
            <span>验证码</span>
            <input type="text" id="code" placeholder="请输入验证码"/>
        </div>
        <div class="login_item code" onclick="getCode()" id="code-btn" >获取验证码</div>
        <div class="clearfix"></div>
    </div>
    <div id="register_frame">
        <button class="teacher_register_btn" onclick="login()">绑定账号</button>
    </div>
</div>
<!--绑定失败，提示信息-->
<div id="bind_failed" style="display: none">
    <div class="bind_failed_popup">
        <h5>系统中无此手机号，请联系家学托管中心</h5>
        <h5 class="tit2">录入您的基本信息！</h5>
        <hr/>
        <h5 class="bind_failed_btn">好的</h5>
    </div>
</div>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/main.js"></script>
<script>
function getCode(){
	var phone=$("#phone").val();
	if (!isPhoneNumber(phone)) return;
	ajaxUtil({phone:phone},mainpath+"/manager/sendCode.do", function(){
		settime("#code-btn");
	}, function(reason, code){
		if (code ==1 ){
			$("#bind_failed").show();
		} else {
			alert("发送验证码失败，请稍后再试试");
		}
	});
}
function login(){
	var phone=$("#phone").val();
	var code=$("#code").val();
	ajaxUtil({phone:phone,code:code}, mainpath+"/manager/bindPhone.do", function(){
		var from = urlparameter("from");
		if (from == ""){
			from = "teacher"
		}
		window.location.href=from;
	}, function(reason, code){
		alert(reason);
	});
	
} 
function isPhoneNumber(phone){
	var reg = /^1\d{10}$/;
	if (!reg.test(phone)){
		alert("手机号码好像不太对！");
		return false;
	}
	return true;
}

var countdown=60; 
function settime(btn) { 
	if (countdown == 0) { 
		$(btn).attr("onclick", "getCode()");    
		$(btn).html("获取验证码"); 
		countdown = 60; 
	} else { 
		$(btn).attr("onclick", "");    
		$(btn).html(countdown + "秒"); 
		countdown--; 
		setTimeout(function() { 
			settime(btn) 
		},1000) 
	} 
} 
$(".bind_failed_btn").click(function(){
	$("#bind_failed").hide();
});
</script>
</body>
</html>