<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.xuema.bean.UserPojo"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	// out.println(basePath);
%>

<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script type="text/javascript" src="js/flexible_css.js"></script>
    <script type="text/javascript" src="js/flexible.js"></script>
    <link type="text/css" rel="stylesheet" href="css/maina.css"/>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>
    <script src="js/common.js"></script>
    <script src="js/um.js"></script>
    <title>首次注册专享大礼</title>
    
</head>
<body>

    <div class="s_wrap" style="display: block">
        <img  class="s_background" src="img/background.jpg" alt=""/>
        <div class="s_register" style="display: block">
            <div class="ipt">
                <img class="iphone_icon" src="img/iphone.png" alt=""/>
                <input type="text" class="tel" id="phone" placeholder="请输入手机号"/>
            </div>
            <div class="verification">
                <input type="text" class="veri_inp" id="code"  placeholder="请输入验证码"/>
                <button type="button" class="veri_btn" id="code-btn" onclick="getCode()">获取验证码</button>
            </div>
          
            
            <div>
             <button class="receive_btn" type="button" onclick="isLogin()" />
            </div>
        </div>
        <div class="s_enrolled" style="display: none;">
            <span class="tele">呀，礼包溜了</span>
            <span class="tip">快来学吗，更多好礼等你拿！</span>
            <div class="see_btn"></div>
        </div>
        <div class="down_btn"></div>
    </div>


    <div class="mask_content" style="display: none">
        <div style="height: 0.75rem"></div>
        <div class="c_header">
            <span>学习阶段</span>
            <img class="close" src="img/close.png" alt=""/>
        </div>
        <hr/>
        <div class="c_grade">
            <div class="selects">
            			
                	 	
                	 	
                <input type="radio" value="1" name="grade" id="grade1">
                <label for="grade1" class="s_tip">小学</label>
            </div>
            <div class="selects dis">
               <input type="radio" value="2" name="grade" id="grade2">
                <label for="grade2" class="s_tip">初中</label>
            </div>
            <div class="selects dis">
                <input type="radio" value="3" name="grade" id="grade3">
                <label for="grade3" class="s_tip">高中</label>
            </div>
        </div>
        <div class="c_want">
            <span>您希望</span>
        </div>
        <hr/>
        <div class="c_hope">
            <div class="selects">
                <input type="radio" id="hope3" name="hope" class="hope3" value="3">
                <label for="hope3" class="s_tip">一对一视频授课</label>
            </div>
            <div class="selects dis">
                <input type="radio" id="hope1" name="hope" class="hope1" value="1">
                <label for="hope1" class="s_tip">老师到您家上课</label>
            </div>
            <div class="selects dis">
                <input type="radio" id="hope2" name="hope" class="hope2" value="2">
                <label for="hope2" class="s_tip">到学吗学堂上课</label>
            </div>
      
                	 	
        </div>
        <div class="c_name">
            <span>姓名</span>
        </div>
        <div class="inp">
            <input class="inp_name"  id="uname" type="text" placeholder="我们如何称呼您"/>
        </div>
       
        <div>
             <button class="submit_btn" type="button" onclick="addUserLog()" />
        </div>
    </div>

<div class="mask_bg" style="display: none"></div>
<div class="mask_con" style="display: none">
    <img class="congratulation" src="img/congratulation.png" alt=""/>
    <img class="used_btn" src="img/use.png" alt=""/>
    
</div>
<script style="text/javascript">
    $('document').ready(function () {
   	$('.used_btn').click(function(){
   		window.location.href = "down.html";
   	});
   
        //验证手机号
        $(".tel").blur(function() {
            var tel = $(this).val();
            var reg = /^1\d{10}$/;
            if (!reg.test(tel)) {
                alert("请输入正确的手机号");
               
                return false;
            }
        });
        //点击领取见面礼，跳转
        $('.receive_btn').bind('click', function () {
            var tel = $('.tel').val();
            var reg = /^1\d{10}$/;
            if (!reg.test(tel)) {
                alert("请输入手机号");
                return false;
            }
            $('.s_wrap').css('display','none');
            $('.mask_content').css('display','block');
        });

        //关闭选择上课方式页面
        $('.close').click(function () {
            $('.s_wrap').css('display','block');
            $('.mask_content').css('display','none');
        });
        //验证学习阶段和希望的上课方式,验证成功后点击提交信息，提交成功后弹出层
        $('.submit_btn').bind('click', function () {
            var _grade = $('input:radio[name="grade"]:checked').val();
            if(_grade == null){
                alert("请选择学习阶段!");
                return false;
            };
            var _hope = $('input:radio[name="hope"]:checked').val();
            if(_hope == null){
                alert("请选择您希望的上课方式!");
                return false;
            };
            $('.mask_bg').css('display','block');
            $('.mask_con').css('display','block');
        });


    });
</script>
</body>
</html>