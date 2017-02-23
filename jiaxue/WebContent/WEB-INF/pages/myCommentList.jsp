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
    <title>我的家评</title>
    <style type="text/css">
        hr{
        	width:9.2rem;
            height: 1px;
            margin:0 auto;
            color: lightgrey;
            background-color: lightgrey;
        }
    </style>
</head>
<body id="chat_wrap">

 <div id="clist_wrap">
    <header>
        <h5 id="lastTime"></h5>
    </header>
    <div id="manager_list">
    </div>
</div>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/main.js?v=1.0"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript">
loadChat();

function loadChat(){
	ajaxUtil(null,mainpath+"/chat/list.do",function(response){
		var html = "";
		var cs = response.list;
		/* html+="<hr/>"; */
		for (var i in cs){
			var c = cs[i];
			html+= "<a href=\"myComment1?manager="+c.manager+"\">";
			html+= "<div class='clist_content'>";
			html+= "  <img class='clist_teacher_avatar' src='"+c.managerImg+"' alt=''/>";
			if (c.readStatus==0 && c.toUser==1){
				html += "<div class='clist_unread_icon'></div>";
			}
			html+= "  <div class='clist_content_fr'>";
			html+= "      <div class='floor1'>";
			html+= "          <span style='color:black'>"+c.managerNickname+"</span>";
			html+= "          <span class='clist_talk_time'>"+c.showTime+"</span>";
			html+= "      </div>";
			html+= "      <div class='floor2'>";
			if (!isEmpty(c.content)){
				html+= "          <span>"+c.content+"</span>";
			} else {
				html+= "          <span>[收到一张图片]</span>";
			}
			html+= "      </div>";
			html+= "  </div>";
			html+= "</div>";
			html+= "<div class='clearfix'></div>";
			html+="<hr/>";
			html+= "</a>";
			/* if ($("#lastTime").html() == ""){
				$("#lastTime").html(c.showTime);
			} */
		}
		$('#manager_list').append(html);
		if (cs.length == 0){
			window.location.href="commentNone";
		}
	});
}
</script>
</body>
</html>