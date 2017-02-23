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
	User u = SessionUtil.getLoginUser(request);
%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1,maximum-scale=1,user-scalable =no">
    <script type="text/javascript" src="../js/flexible.js"></script>
    <script type="text/javascript" src="../js/flexible_css.js"></script>
    <link type="text/css" rel="stylesheet" href="../css/main.css?v=1.0"/>
    
    <style type="text/css">
        #scroller {
            position:absolute;
            -webkit-touch-callout:none;
            -webkit-tap-highlight-color:rgba(0,0,0,0);
            width:100%;
            /*height: 17.7867rem;*/
            padding:0;
        }
        #pullDown .pullDownIcon{
            display:none;
            -webkit-transition-property:-webkit-transform;
            -webkit-transition-duration:250ms;
            margin-left: 4.7rem;
        }
        #pullUp .pullUpIcon  {
            display:none;
            -webkit-transition-property:-webkit-transform;
            -webkit-transition-duration:250ms;
            margin-left: 4rem;
        }

        #pullDown.flip .pullDownIcon,#pullUp.flip .pullUpIcon {
            -webkit-transform:rotate(0deg) translateZ(0);
        }

        #pullDown.loading .pullDownIcon, #pullUp.loading .pullUpIcon {
            background-position:0 100%;
            -webkit-transform:rotate(0deg) translateZ(0);
            -webkit-transition-duration:0ms;

            -webkit-animation-name:loading;
            -webkit-animation-duration:2s;
            -webkit-animation-iteration-count:infinite;
            -webkit-animation-timing-function:linear;
        }

        @-webkit-keyframes loading {
            from { -webkit-transform:rotate(0deg) translateZ(0); }
            to { -webkit-transform:rotate(360deg) translateZ(0); }
        }
    </style>
    
    <title>我的家评</title>
</head>
<body>
<div id="comment1_container">
	<div id="scroller">
		<div id="pullDown">
            <img id="pullDownIcon" class="pullDownIcon" src="../img/loading.png" style="width: 0.5rem;height: 0.5rem"/>
        </div>
		<article id="chat_panel">
	    </article>
    </div>
    <footer id="footer">
        <textarea id="student_mymessage" class="student_mymessage"></textarea>
        <blockquote class="send" id="stu_send">发送</blockquote>
        <div class="clearfix"></div>
    </footer>
</div>
<div id="baby_mask">
    <img src="" alt=""/>
</div>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/iscroll.js"></script>
<script type="text/javascript" src="../js/common.js?v=1.0"></script>
<script type="text/javascript" src="../js/main.js?v=1.0"></script>
<script type="text/javascript">
	var firstId = 0;
	var firstTime = null;
	var lastId = 0;
	var size = 10;
	var manager = urlparameter("manager");
	var userImg = '<%=u.getImg()%>';
	var uid = '<%=u.getId()%>';
	loadOlderChat();
	setInterval(function () {
    	loadNewerChat();
    },10000);
    function loadOlderChat(){
    	ajaxUtil({mark:lastId,size:size,manager:manager},mainpath+"/chat/loadOlder.do",function(response){   
			var list = response.list;
			var length = list.length;
			var afterTime = null;
			for (var i in list){
				var c = list[i];
				var showTime = "";
				if (afterTime - c.createTime > 300*1000){
					showTime = formatShowTime(c.createTime);
				}
				if (afterTime == null){
					showTime = formatShowTime(c.createTime);
				}
				afterTime = c.createTime;
				var html = "";
				var headImgClass = "";
				var imgClass = "";
				var titleClass = "";
				var chatArrow = "";
				//头像
				if (c.toUser){
					html +="<header><h5 class='immediately_time' style='display: block;'>"+showTime+"</h5><ul class='content'><li>";
					headImgClass = "teacher_avatar";
					imgClass = "teacher_message_img";
					titleClass = "teacher_message_tit";
					chatArrow="teacher_chat_arrow";
					arrowImg = "../img/chatArrow.png";
					html +="	<img src='"+c.managerImg+"' class='"+headImgClass+"'>";
				} else {
					html +="<article><h5 class='immediately_time' style='display: block;'>"+showTime+"</h5><ul class='content'><li>";
					headImgClass = "myavatar_stu";
					imgClass = "stu_message_img";
					titleClass = "stu_message_tit";
					chatArrow="stu_chat_arrow";
					arrowImg = "../img/chatArrow2.png";
					html +="	<img src='"+userImg+"' class='"+headImgClass+"'>";
				}
				//聊天信息
				if (c.imgs != null && c.imgs != "null"){
					html +="	<img src='"+c.imgs+"' class='"+imgClass+"'>";
				} else {
					html +="    <img class='"+chatArrow+"' src='"+arrowImg+"' style='width: 0.2933rem;height: 0.4rem;'/>";
					html +="	<blockquote class='"+titleClass+"'>"+c.content+"</blockquote>";
				}
				html +="	<div class='clearfix'></div>";
				if (c.toUser){
					html +="</li></ul></header>";
				} else {
					html +="</li></ul></article>";
				}
				$("#chat_panel").prepend(html);
				lastId = c.id;
				if (firstId == 0){
					firstId = c.id;
				}
				firstTime = c.createTime;
			}
		});
    }
    function loadNewerChat(){
    	ajaxUtil({mark:firstId,size:size,manager:manager},mainpath+"/chat/loadNewer.do",function(response){   
    		var list = response.list;
			for (var i in list){
				var html = "";
				var c = list[i];
				var showTime = "";
				if (c.createTime - firstTime > 300*1000){
					showTime = formatShowTime(c.createTime);
				}
				var imgArrow = "../img/chatArrow.png";
				html +="<header><h5 class='immediately_time' style='display: block;'>"+showTime+"</h5><ul class='content'><li><li>";
				html +="	<img src='"+c.managerImg+"' class='teacher_avatar'>";
				if (c.imgs != null && c.imgs != "null"){
					html +="	<img src='"+c.imgs+"' class='teacher_message_img'>";
				} else {
					html +="    <img class='teacher_chat_arrow' src='"+imgArrow+"' style='width: 0.2933rem;height: 0.4rem;'/>";
					html +="	<blockquote class='teacher_message_tit'>"+c.content+"</blockquote>";
				}
				html +="	<div class='clearfix'></div>";
				html +="</li></ul></header>";
				$("#chat_panel").append(html);
				firstId = c.id;
				firstTime = c.createTime;
			}
		});
    }
    //点击发送
    $("#stu_send").click(function(){
    	$('.immediately_time').show();
        var omessage = $('#student_mymessage').val();
        if (omessage == "") {
        	alert("发送内容不能为空！");
            return false;
        }
        var showTime = "";
		if (new Date().getTime() - firstTime > 300*1000){
			showTime = formatShowTime(new Date().getTime());
		}
		var imgArrow = "../img/chatArrow2.png";
        var ohtml = "<ul class='nI'><li><h5 class='immediately_time' style='display: block;'>"+showTime+"</h5><ul class='content'>" +
			"<img class='myavatar_stu' src='"+userImg+"' />" +
			"<img class='stu_chat_arrow' src='"+imgArrow+"' style='width: 0.2933rem;height: 0.4rem;'/>"+
			"<blockquote class='stu_message_tit' style='margin-bottom: 0.2rem'>" + omessage + "</blockquote>" +
			"<div class='clearfix'></div>"
			"</li></ul>";
		$('#chat_panel').append(ohtml);
		$('#student_mymessage').val("");
		moveBottom();
        ajaxUtil({uid:uid,manager:manager,content:omessage},mainpath+"/chat/add.do",function(response){
        	firstId = response.id;
        	myScroll.refresh();
		});    
    });
    //点击用户头像
    $('.teacher_avatar').click(function () {
        var imgSrc = $(this).attr("src");
        getImageWidth(imgSrc, function (w,h) {
            var ratio = h/w;
            var imgHeight = ratio * 10 +'rem';
            $('#baby_mask').css('display','-webkit-box').addClass('enlarge');
            $('#baby_mask img').attr('src',imgSrc).css('height',imgHeight);
        })
    });
    $('body').on('click','.myavatar_stu', function () {
        var imgSrc = $(this).attr("src");
        getImageWidth(imgSrc, function (w,h) {
            var ratio = h/w;
            var imgHeight = ratio * 10 +'rem';
            $('#baby_mask').css('display','-webkit-box').addClass('enlarge');
            $('#baby_mask img').attr('src',imgSrc).css('height',imgHeight);
        })
    });
    $('#baby_mask').click(function () {
        $(this).hide();
    });
    function getImageWidth(url,callback){
        var img = new Image();
        img.src = url;

        // 如果图片被缓存，则直接返回缓存数据
        if(img.complete){
            callback(img.width, img.height);
        }else{
            // 完全加载完毕的事件
            img.onload = function(){
                callback(img.width, img.height);
            }
        }
    }
    function moveBottom(){
    	// var t = $("#footer").offset().top;
    	// $(window).scrollTop(t);//滚动到底部

        myScroll.scrollToElement(document.getElementById('chat_panel').lastChild, 200);      
    } 
    
    var myScroll, pullDownEl, pullDownOffset, pullUpEl, pullUpOffset;
    
    /**
     * 下拉刷新 （自定义实现此方法）
     * myScroll.refresh();		// 数据加载完成后，调用界面更新方法
     */
    function pullDownAction () {
    	 loadOlderChat();
    	 myScroll.refresh(); 	//数据加载完成后，调用界面更新方法
    }

    /**
     * 初始化iScroll控件
     */
    function loaded() {
        pullDownEl = document.getElementById('pullDown');
        pullDownOffset = pullDownEl.offsetHeight;
        pullDownicon = document.getElementById('pullDownIcon');

        myScroll = new iScroll('comment1_container', {
            scrollbarClass: 'myScrollbar', /* 重要样式 */
            useTransition: false, /* 此属性不知用意，本人从true改为false */
            onRefresh: function () {
                if (pullDownEl.className.match('loading')) {
                    pullDownEl.className = '';
                    pullDownicon.style.display = 'none';

                }
            },
            onScrollMove: function () {
                if (this.y > 5 && !pullDownEl.className.match('flip')) {
                    pullDownEl.className = 'flip';
                    this.minScrollY = 0;
                } else if (this.y < 5 && pullDownEl.className.match('flip')) {
                    pullDownEl.className = '';
                    this.minScrollY = -pullDownOffset;
                } 
            },
            onScrollEnd: function () {
                if (pullDownEl.className.match('flip')) {
                    pullDownicon.style.display = 'block';
                    pullDownEl.className = 'loading';
                    pullDownAction();
                }
            }
        });

        setTimeout(function () { document.getElementById('comment1_container').style.left = '0'; }, 800);
    }

    //初始化绑定iScroll控件
    document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
    document.addEventListener('DOMContentLoaded', loaded, false);


    // by Ariose
    $('#comment1_container').height($(window).height() - $('#footer').height());
</script>
</body>
</html>