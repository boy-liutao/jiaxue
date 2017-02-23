<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1,maximum-scale=1,user-scalable =no">
    <script type="text/javascript" src="../js/flexible.js"></script>
    <script type="text/javascript" src="../js/flexible_css.js"></script>
    <link type="text/css" rel="stylesheet" href="../css/main.css"/>
    <title>家学资讯</title>
</head>
<body id="news_wrap">
   
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/main.js?v=1.0"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript">

loadNews();
function loadNews(){
	ajaxUtil(null,mainpath+"/news/list.do",function(response){
		var theHtml = "";
		var cs = response.list;
		for (var i in cs){
			var c = cs[i];
			/******************** modified by sunqi for linkの位置を変更  2016/05/11  start ********************/
			/* theHtml += "<div class=\"news_block\"> "; */
			if(c.type == "资讯"){
				theHtml += "	<a href=\"newsDetail?id="+c.id+"\" ><div class=\"news_block\">";
			}else{
				theHtml += "	<a href=\"activityDetail?id="+c.id+"\" ><div class=\"news_block\">";
			}
			/******************** modified by sunqi linkの位置を変更　2016/05/11  end ********************/
			
			theHtml += "	<hgroup class=\"news_tit\">";
			/******************** modified by sunqi for linkの位置を変更　2016/05/11  start ********************/
			/* if (c.type == "资讯") {
				theHtml += "		<a href=\"newsDetail?id="+c.id+"\" ><h2>"+c.title+"</h2></a>";
			} else {
				theHtml += "		<a href=\"activityDetail?id="+c.id+"\" ><h2>"+c.title+"</h2></a>";
			} */
				theHtml += "		<h2>"+c.title+"</h2>";
			/******************** modified by sunqi for linkの位置を変更　2016/05/11  end ********************/
			
			/******************** deleted by sunqi for 詳細内容なスタイルを変更　2016/05/10  start ********************/
			/* theHtml += "			 <hr/>";
			var address = "家学天地";
			if (c.school != null){
				address = "家学天地·" + c.school.name;
			}
			theHtml += "				<h5 class=\"news_address\">"+address+"</h5>";
			theHtml += "				<h5 class=\"news_time\">"+c.dateLabel+"</h5>";
			theHtml += "				</hgroup>"; */
			/******************** deleted by sunqi for 詳細内容なスタイルを変更　2016/05/10  end ********************/
			
			/******************** added by sunqi 2016/05/10  for 詳細内容なスタイルを変更　start ********************/
			var str = c.content;
			var reg =  /\s/g;
			var content = str.replace(reg,"");
			if(content.length > 30){
			content = content.substring(0,30)+'...';
			}
			theHtml += "<h5 class=\"news_content\">"+content+"</h5>";
			theHtml += "				</hgroup>";
			/******************** added by sunqi 2016/05/10  for 詳細内容なスタイルを変更 end ********************/
			theHtml += "				<img class=\"news_image\" src='"+c.img+"'></img>"; 
		 	theHtml += "				<div class=\"clearfix\"></div>";  
			theHtml += "</div>";
			theHtml +="</a>";
		}
		$('#news_wrap').append(theHtml);
		
	});
	
}
</script>
</body>
</html>