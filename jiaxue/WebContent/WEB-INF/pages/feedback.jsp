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
    <title>意见反馈</title>
</head>
<body id="feedback_wrap">
    <div class="feedback_frame">
        <textarea class="feedback_textarea"></textarea>
        <div class="feedback_remind">
            <h5>如果我们做得好，请表扬一下吧;</h5>
            <h5 class="remind">如果您对我们的服务或产品还不太满意，请告诉我们，</h5>
            <h5 class="remind">我们珍视您的任何意见和建议！</h5>
        </div>
    </div>
    <button class="feedback_submit">提交</button>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/main.js"></script>
<script type="text/javascript">
    $(function () {
        $('.feedback_textarea').click(function () {
            $(this).css('opacity','1');
//            $('.feedback_remind').hide();
        });
        $('.feedback_textarea').blur(function () {
            var oval = $('.feedback_textarea').val();
            if(oval == ""){
                $(this).css('opacity','0');
            }
        });
        $('.feedback_submit').click(function () {
            var oval = $('.feedback_textarea').val();
            if(oval == ""){
                alert("不能输入为空");
                return false;
            }
            ajaxUtil({content:oval},mainpath+"/suggestion/add.do",function(response){
	           	alert("提交成功");
	           	window.location.href="index";
           	}); 
            });
    });
</script>
</body>
</html>