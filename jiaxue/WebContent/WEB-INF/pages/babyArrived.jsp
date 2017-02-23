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
    <title>宝贝到家</title>
</head>
<body id="baby_wrap">
	<div id="baby_mask">
        <img src="" alt=""/>
    </div>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/main.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript">
loadDaily();
function loadDaily(){
	ajaxUtil(null,mainpath+"/daily/list.do",function(response){
		var theHtml = "";
		var cs = response.list;
		for (var i in cs){
			var c = cs[i];
			if (c.nurseryStatus) {
				theHtml += "<section class=\"baby_section\"> ";
				theHtml += "	<header>";
				theHtml += "	  <h5>"+c.nurseryTimeText+"</h5>";
				theHtml += "	</header>";
				theHtml += "	<article>";
				theHtml += "	  <div class=\"signed_message\">";
				theHtml += "	    <h2><strong>签到信息</strong></h2>";
				theHtml += "	    <p>"+c.nurseryInfo+"</p>";
				theHtml += "	  </div>";
				theHtml += "	  <div class=\"signed_img\">";
				theHtml += "	    <img src='"+c.nurseryImg+"'></img>"; 
				theHtml += "	  </div>";
			 	theHtml += "		<div class=\"clearfix\"></div>";  
				theHtml += "	</article>";
				theHtml += "</section>";
			}
		}
		$('#baby_wrap').append(theHtml);
		if (cs.length == 0){
			window.location.href="arrivingNone";
		}
	});
}
$(function () {
    $('.signed_img').each(function () {
        $(this).click(function () {
            var imgSrc = $(this).find('img').attr("src");
            console.log(imgSrc);
            var theImage = new Image();
            theImage.src = imgSrc;
            var imageWidth = theImage.width;
            var imageHeight = theImage.height;

            var ratio = imageHeight/imageWidth;
            var oHeight = ratio * 10 +'rem';

            $('#baby_mask').css('display','-webkit-box').addClass('enlarge');
            $('#baby_mask img').attr('src',imgSrc).css('height',oHeight);
        });
    });
    $('#baby_mask').click(function () {
        $(this).hide();
    });
});
</script>
</body>
</html>