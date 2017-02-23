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
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1,maximum-scale=1,user-scalable =no">
    <script type="text/javascript" src="../js/flexible.js"></script>
    <script type="text/javascript" src="../js/flexible_css.js"></script>
    <link type="text/css" rel="stylesheet" href="../css/newsDetail.css"/>
    <title>资讯详情</title>
</head> 
<body id="nd_wrap">
    <header>
        <hgroup>
            <h2 id="title"></h2>
            <!-- <h5>
                <span id="">家学天地·润枫学堂</span>
                <span class="current_day" >今天</span>
                <span class="current_time" id="">19：00</span>
            </h5> -->
        </hgroup>
    </header>
    <figure>
        <img src="" id="perImg" alt=""/>
        <!-- <figcaption id="content">
        </figcaption > -->
    </figure>
    <article id="nd_content"></article>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/main.js?v=1.0"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript">
loadEditUser();

function loadEditUser(){
	var aid = urlparameter("id");
	if (aid.indexOf("#rd") != -1){
		aid = aid.substring(0, aid.length-3);
	}
	if (aid != null && aid != '' && aid != undefined){
		//load user
		ajaxUtil({id:aid},mainpath+"/news/get.do",function(response){
			var theHtml = "";
			var a = response;
			$("#id").val(a.id);
			$("#updateTime").text(a.updateTime);
			$("#nd_content").html(a.content);
			$("#title").text(a.title);
			$("#perImg").attr("src", a.img);
			var address = "家学天地";
			if (a.school != null){
				address = "家学天地·" + a.school.name;
			}
			theHtml += "				<h5>";
			theHtml += "				<span >"+address+"</span>";
			theHtml += "				<span class=\"news_time\">"+a.dateLabel+"</span>";
			theHtml += "				</h5>";
			$('#title').append(theHtml);
		});
	}
}

$(function() {
	var pattern = /^http:\/\/mmbiz/;
	var patterns = /^https:\/\/mmbiz/;
	var mmsns = /^http:\/\/mmsns/;
	var mmsnss = /^https:\/\/mmsns/;
	var prefix = 'http://img01.store.sogou.com/net/a/04/link?appid=0&w=710&url=';
	$("img").each(function(){
		var src = $(this).attr('src');
		if(pattern.test(src)){
			var newsrc = prefix+src;
			$(this).attr('src',newsrc);
		}
		if(patterns.test(src)){
			var newsrc = prefix+src;
			$(this).attr('src',newsrc);
		}
		if(mmsns.test(src)){
			var newsrc = prefix+src;
			$(this).attr('src',newsrc);
		}
		if(mmsnss.test(src)){
			var newsrc = prefix+src;
			$(this).attr('src',newsrc);
		}
	});
});

</script>
</body>
</html>