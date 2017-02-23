<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.xuema.util.SessionUtil"%>
<%@page import="com.xuema.bean.User"%>
<%@page import="com.xuema.bean.Manager"%>
<!DOCTYPE html>
<html lang="en">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User u = (User)request.getAttribute("user");
	Manager m = SessionUtil.getLoginManager(request);
%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1,maximum-scale=1,user-scalable =no">
    <script type="text/javascript" src="../js/flexible.js"></script>
    <script type="text/javascript" src="../js/flexible_css.js"></script>
    <link type="text/css" rel="stylesheet" href="../css/style.css"/>
    <title><%=u.getParent() %>家长</title>
    
    <style type="text/css">
        #scroller {
            position:absolute;
            -webkit-touch-callout:none;
            -webkit-tap-highlight-color:rgba(0,0,0,0);
            width:100%;
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
    
</head>
<body>
<div id="mcteacher_wrap">
	<div id="scroller">
		<div id="pullDown">
            <img id="pullDownIcon" class="pullDownIcon" src="../img/loading.png" style="width: 0.5rem;height: 0.5rem"/>
        </div>
		<div id="chat_list">
	    </div>
    </div>
</div>
<footer id="mcteacher_footer">
    <img class="photo" src="../img/teacher05.png" alt="" onclick="sendPic()"/>
    <textarea name="" id="teacher_mymessage" class="teacher_mymessage"></textarea>
    <blockquote class="send" id="teacher_send">发送</blockquote>
    <div class="clearfix"></div>
</footer>
<div id="baby_mask">
    <img src="" alt=""/>
</div>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/iscroll.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="../js/main.js"></script>
<script type="text/javascript" src="../js/wx.js"></script>
<script type="text/javascript">
	var date = null;
	var firstId = null;
	var firstTime = null;
	var size=10;
	loadDayComment();
	setInterval(function () {
    	loadNewerChat();
    },10000);
    function loadDayComment(){
    	var uid = urlparameter("uid");
    	ajaxUtil({uid:uid,date:date},mainpath+"/chat/loadByDate.do",function(response){
    		var daily = response.daily;
    		var html = "";
    		if (daily.nurseryStatus) {
	    		html += "<section class='baby_section'>";
	    		html += "<header>";
	    		html += "    <h5>"+daily.nurseryTimeText+"</h5>";
	    		html += "</header>";
	    		html += "<article>";
	    		html += "    <div class='signed_message'>";
	    		html += "        <h2><strong>签到信息</strong></h2>";
	    		html += "        <p>"+daily.nurseryInfo+"</p>";
	    		html += "    </div>";
	    		html += "    <div class='signed_img'>";
	    		html += "    <img alt='' src='"+daily.nurseryImg+"'>";
	    		html += "    </div>";
	    		html += "    <div class='clearfix'></div>";
	    		html += "</article>";
	    		html += "</section>";
    		}
    		date = formatDate(daily.date);//refresh mark data
    		var list = response.list;
   			var preTime = null;
    		for (var i in list){
    			var c = list[i];
    			html += getChatHtml(c, preTime);
    			preTime = c.createTime;
    			if (firstId < c.id){
    				firstId = c.id;
    			}
    			firstTime = c.createTime;
    		}
    		if (firstTime == null){
    			firstTime = daily.nurseryTime;
    		}
    		$("#chat_list").prepend(html);
    	})
    }
    function getChatHtml(c, preTime){
    	var html = "";
    	var d = c.createTime - preTime;
    	var showTime = "";
    	if (d > 300*1000){
    		showTime = formatShowTime(c.createTime);
    	}
    	if (c.toUser) {
			html += "<section class='mcteacher_stu_comment'>";
			html += "    <h5 class='immediately_time' style='display: block;'>"+showTime+"</h5>";
			html += "    <ul>";
			html += "        <li>";
			html += "            <img style='width: 0.9867rem;height: 0.9867rem;' alt='' src='<%=m.getImg()%>' class='teacher_avatar'>";
			if (c.imgs != null && c.imgs != undefined){
    			html += "            <img style='width: 3.28rem;height: 2.16rem;' alt='' src='"+c.imgs+"' class='teacher_message_img'>";
			} else {
				html +="    <img class='teacher_chat_arrow' src='../img/chatArrow2.png' style='width: 0.2933rem;height: 0.4rem;'/>";
				html += "            <blockquote class='teacher_message_tit'>"+c.content+"</blockquote>";
			}
			html += "            <div class='clearfix'></div>";
			html += "        </li>";
			html += "    </ul>";
			html += "</section>";
		} else {
			html += "<article class='mcteacher_teacher_content'>";
			html += "    <h5 class='immediately_time' style='display: block;'>"+showTime+"</h5>";
			html += "    <ul class='content'>";
			html += "    	<li>";
			html += "           <img src='<%=u.getImg()%>' class='myavatar_teacher'>";
			html += "           <img class='student_chat_arrow' src='../img/chatArrow.png' style='width: 0.2933rem;height: 0.4rem;'/>";
			html += "    		<blockquote style='margin-bottom: 0.2rem' class='student_message_tit'>"+c.content+"</blockquote>";
			html += "    		<div class='clearfix'></div>";
			html += "    	</li>";
			html += "    </ul>";
			html += "</article>";
		}
    	return html;
    }
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
    //点击签到信息的图片
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
    
    function loadNewerChat(){
    	var uid = urlparameter("uid");
    	ajaxUtil({mark:firstId,afterDate:date,size:size,uid:uid},mainpath+"/chat/loadUserNewer.do",function(response){   
    		var list = response.list;
			for (var i in list){
				var html = "";
				var showTime = "";
				if (new Date() - firstTime > 300*1000){
					showTime = formatShowTime(new Date());
				}
				var c = list[i];
				html += "<article class='mcteacher_teacher_content'>";
				html += "    <h5 class='immediately_time' style='display: block;'>"+showTime+"</h5>";
				html += "    <ul class='content'>";
				html += "    	<li>";
				html += "           <img src='<%=u.getImg()%>' class='myavatar_teacher'>";
				html +="            <img class='student_chat_arrow' src='../img/chatArrow.png' style='width: 0.2933rem;height: 0.4rem;'/>";
				html += "    		<blockquote style='margin-bottom: 0.2rem' class='student_message_tit'>"+c.content+"</blockquote>";
				html += "    		<div class='clearfix'></div>";
				html += "    	</li>";
				html += "    </ul>";
				html += "</article>";
				$("#chat_list").append(html);
				if (firstId < c.id){
					firstId = c.id;
				}
				firstTime = c.createTime;
			}
		});
    }
    //点击发送
    $('#teacher_send').click(function () {
    	sendMsg();
    });
    
    function sendMsg(){
    	var uid = urlparameter("uid");
    	$('.immediately_time').show();
        var omessage = $('#teacher_mymessage').val();
        if (omessage == "") {
        	alert("发送内容不能为空！");
            return false;
        }
        
        var text = getChatHtml({content:omessage,toUser:true,createTime:new Date().getTime()}, firstTime);
        
		$('#chat_list').append(text);
		$('#teacher_mymessage').val("");
		
		moveBottom();
        ajaxUtil({uid:uid,manager:"<%=m.getUsername()%>",content:omessage,toUser:true},mainpath+"/chat/add.do",function(response){
        	firstId = response.id;
        	myScroll.refresh();
		});  
    }
    
    function moveBottom(){
    	/* var t = $("#mcteacher_footer").offset().top;
    	$(window).scrollTop(t);//滚动到底部 */
    	myScroll.scrollToElement(document.getElementById('chat_list').lastChild, 200);
    } 
    
    wxSDKConfig(null, ["chooseImage","previewImage","uploadImage"], {url:location.href.split('#')[0]});

    function sendPic(){
    	 wx.chooseImage({
    	    	count: 1, 
    	        sizeType: ['compressed'], // 可以指定是原图还是压缩图，默认二者都有
    	        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
    	        success: function (res) {
    	        	var localIds = res.localIds;
    	        	//显示图片
    	            var text = getChatHtml({imgs:localIds,toUser:true});
    	    		$('#chat_list').append(text);
    	            syncUpload(localIds);
    	        }
    	    });
    }

    var syncUpload = function(localIds){
        var localId = localIds.pop();
        wx.uploadImage({
            localId: localId,
            isShowProgressTips: 1,
            success: function (res) {
                var serverId = res.serverId; // 返回图片的服务器端ID
	    		moveBottom();
                //保存
                var uid = urlparameter("uid");
	            ajaxUtil({uid:uid,manager:"<%=m.getUsername()%>",imgs:serverId,toUser:true},mainpath+"/chat/add.do",function(response){
	            	firstId = response.id;
	    		});
                
                curImg = serverId
            }
        });
    };
    
    var myScroll, pullDownEl, pullDownOffset, pullUpEl, pullUpOffset;

    /**
     * 下拉刷新 （自定义实现此方法）
     * myScroll.refresh();		// 数据加载完成后，调用界面更新方法
     */
    function pullDownAction () {
    	 loadDayComment();
    	 myScroll.refresh();		// 数据加载完成后，调用界面更新方法
    }

    /**
     * 初始化iScroll控件
     */
    function loaded() {
    	pullDownEl = document.getElementById('pullDown');
        pullDownOffset = pullDownEl.offsetHeight;
        pullDownicon = document.getElementById('pullDownIcon');

        myScroll = new iScroll('mcteacher_wrap', {
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

        setTimeout(function () { document.getElementById('mcteacher_wrap').style.left = '0'; }, 800);
    }

    //初始化绑定iScroll控件
    document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
    document.addEventListener('DOMContentLoaded', loaded, false);

    $('#mcteacher_wrap').height($(window).height() - $('#mcteacher_footer').height());
</script>

</body>
</html>