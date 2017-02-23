<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">   
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1,maximum-scale=1,user-scalable =no">
	<script type="text/javascript" src="../js/flexible.js"></script>
    <script type="text/javascript" src="../js/flexible_css.js"></script>
     <link type="text/css" rel="stylesheet" href="../css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="../css/style.css?v=1.1"/>
    <title>i家学</title>
</head>
<body id="index_wrap">
<div id="adver_outer" class="carousel slide" data-ride="carousel"></div>
<div id="director">
	<div class="dir_floor">
	    <a href="babyArrived">
	        <div class="dir_fl dir1">
	            <figure>
	                <img src="../img/i07.png" alt="" style="width: 0.64rem;height: 0.8533rem;"/>
	                <figcaption class="fig1">宝贝到家</figcaption>
	            </figure>
	            <div class="unread_icon" style="display: none;"></div>
	        </div>
	    </a>
	    <a href="myCommentList">
	        <div class="dir_fr dir2">
	            <figure>
	                <img src="../img/i02.png" alt="" style="width: 0.7067rem;height: 0.7467rem;"/>
	                <figcaption class="fig2">我的家评</figcaption>
	            </figure>
	            <div class="unread_icon" style="display: none;"></div>
	        </div>
	    </a>
	    <div class="clearfix"></div>
	</div>
	<div class="dir_floor">
	    <a href="enroll">
	        <div class="dir_fl">
	            <figure>
	                <img src="../img/i03.png" alt="" style="width: 0.6933rem;height: 0.7067rem;"/>
	                <figcaption class="fig3">家学报名</figcaption>
	            </figure>
	        </div>
	    </a>
	    <a href="http://mp.weixin.qq.com/mp/homepage?__biz=MzA3Mjc2NDU4Mw==&hid=3&sn=d473054aa422f210f38f33c702182e30#wechat_redirect">
<!-- 	    <a href="http://mp.weixin.qq.com/mp/homepage?__biz=MzA3Mjc2NDU4Mw==&hid=3&sn=d473054aa422f210f38f33c702182e30#wechat_redirect"> -->
	        <div class="dir_fr">
	            <figure>
	                <img src="../img/i04.png" alt="" style="width: 0.5733rem;height: 0.6933rem;"/>
	                <figcaption class="fig4">家学资讯</figcaption>
	            </figure>
	        </div>
	    </a>
    	<div class="clearfix"></div>
	</div>
	<div class="dir_floor">
	    <a href="personalCenter">
	        <div class="dir_fl">
	            <figure>
	                <img src="../img/i05.png" alt="" style="width: 0.6267rem;height: 0.72rem;"/>
	                <figcaption class="fig5">个人中心</figcaption>
	            </figure>
	        </div>
	    </a>
	    <a href="#">
	    	<div class="dir_fr">
	        <figure>
	            <img src="../img/i06.png" alt="" style="width: 0.6667rem;height: 0.76rem;"/>
	            <figcaption class="fig6">敬请期待</figcaption>
	        </figure>
	    </div>
	    </a>
        <div class="clearfix"></div>
	</div>
    
    
</div>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.min.js"></script>
<script type="text/javascript">
loadBanner();
$('#adver_outer').carousel({
    //设置自动播放2秒
    interval:2000,
    //设置暂停按钮的事件
    pause:'hover'
});
function loadBanner(){
		ajaxUtil(null,mainpath+"/banner/list.do",function(response){
			var theHtml = "";
			var cs = response.list;
			theHtml +="<ol class='carousel-indicators'>";
			for (var i in cs){
				var c = cs[i];
				if(i == 0){
					theHtml += "<li data-target='#adver_outer' data-slide-to='0' class='active'></li>";
				}else{
					theHtml +="<li data-target='#adver_outer' data-slide-to='0'></li>";
				}
			}
			theHtml +="</ol>";
			theHtml +="<div class='carousel-inner'>";
			for (var i in cs){
				var c = cs[i];
				if(i == 0){
					theHtml += "<div class='item active'>";
					theHtml += "	 <a href='"+c.url+"' ><img src='"+c.img+"' alt=''/></a>";
					theHtml += "		</div>";
				}else{
					theHtml += "<div class='item'>";
					theHtml += "	 <a href='"+c.url+"' ><img src='"+c.img+"' alt=''/></a>";
					theHtml += "		</div>";
				}
				
			}
			theHtml += "		</div>";
			$('#adver_outer').append(theHtml);
		});
	}
</script>
</body>
</html>