<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1,maximum-scale=1,user-scalable =no">
    <script type="text/javascript" src="../js/flexible.js"></script>
    <script type="text/javascript" src="../js/flexible_css.js"></script>
    <link type="text/css" rel="stylesheet" href="../css/style.css?v=2.0"/>
    
    <title>i家学-老师版</title>
</head>
<body>
	<div id="teacher_wrap">
	    <div class="teacher_nav_header">
	        <p style="text-align:center;font-size:1.4em;margin-top:20px">已自动为你选择<span id="now-time"></span>前接送的孩子</p>
	    </div>
    	<div class="teacher_content" >
	      	<div id="user_list"></div>
	      	<p style="text-align:center;font-size:1.4em;margin-top:20px">
	      		<a href="#">选择更多孩子</a><br/>
	       	<a href="#" style="text-align:center;font-size:1.4em;margin-top:20px">下一步</a>
	      	</p>
        </div>
	      <div id="teacher_footer">
	          <h5>北京家学天地网络科技有限公司</h5>
	          <h5>©2016&nbsp;All&nbsp;Rights&nbsp;Reserved</h5>
	      </div>
    </div>
    <!--宝贝签到页面-->
    <div id="teacher_babySign_mask" style="display: none">
        <div class="babySign_content">
            <div class="babySign_content_header">
                <strong>宝贝签到</strong>
                <span>先给家长捎句话吧</span>
            </div>
            <ul class="babySign_content_center">
                <li><span class="username"></span>已安全到达托管中心啦，您就放心吧！</li>
                <li><span class="username"></span>家学托管，不负所托，我们已把<span class="username"></span>接到托管中心了！</li>
                <li><span class="username"></span>孩子健康成长，比什么都重要，您看<span class="username"></span>小同学，吃水果时多快乐啊！</li>
                <li>接孩子是大事，我们从不苟且，看，小<span class="username"></span>已经在吃水果了！</li>
                <li><span class="username"></span>安全到达托管中心，已经开始专心地写作业了！</li>
                <li><span class="username"></span>今天很开心，已经接到她了，您尽管放心就好！</li>
                <li>托管无小事，安全到达最重要，<span class="username"></span>已经到托管中心了！</li>
                <li><span class="username"></span>在托管中心已经开始和小伙伴们一起享用美味的加餐了！</li>
                <li><span class="username"></span>到托管中心啦，吃过水果，就开始写作业了！</li>
                <li><span class="username"></span>今天很乖，现在已经到托管中心了，您就放心吧！</li>
            </ul>
            <div class="babySign_content_footer">
                <input class="babySign_inp" type="text"/>
                <label><span class="babysign_next_btn">下一步</span></label>
            </div>
        </div>
    </div>
    <div id="babySign_uppic_mask" style="display: none">
        <div class="uppic_popup" id="uppic_popup">
            <h2 id="nursery"></h2>
            <figure>
                <img id="nurseryImg" alt="" style="width: 1.7333rem;height: 1.7067rem; "/>
            </figure>
            <hr style="background-color: lightgrey;color: lightgrey;"/>
            <div class="babySign_btn" onclick="addNursery()">发送</div>
        </div>
    </div>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js?v=1.1"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="../js/wx.js"></script>
<script type="text/javascript">
	loadUser();
    var lastOne = null;
    function loadUser(before){
        var url = "/user/listNursery.do";
        if (before == undefined){
        	before = null;
        }
        ajaxUtil({lastOne:lastOne}, mainpath+url, function(response){
        	var list = response.list;
        	var html="";
        	for (var i in list){
        		var u = list[i];
        		html += "<div class='teacher_content_block' id='r_"+ u.id +"'>";
        		html += "<input type='checkbox' style='float:left;margin-top:1rem;margin-right:0.5rem' class='nursery-check'/>";
        		html += "    <img class='teacher_stu_avatar' src='"+u.img+"' alt='' style='width: 1.6267rem;height: 1.6267rem'/>";
        		if (u.lastChat != null && u.lastChat.readStatus==0 && u.lastChat.toUser==0){
    				html += "<div class='unread_icon'></div>";
    			}
        		html += "    <div class='teacher_block_fr'>";
        		html += "        <h2 class='teacher_stu_username'>"+u.name+"</h2>";
        		html += "        <h4><span>"+u.school.name+"</span>&nbsp;/&nbsp;<span>"+u.inschool+"</span>&nbsp;/&nbsp;<span>"+u.grade+"</span>&nbsp;</h4>";
        		html += "    </div>";
        		html += "<p style='float:right;margin-top:20px;margin-right:10px'>"+u.daily.scheduleTime+"</p>";
        		html += "    <div class='clearfix'></div>";
        		html += "</div>";
        		lastOne = u.daily.scheduleTime;
        	}
        	var beforeMark = response.beforeMark;
        	$("#now-time").html(beforeMark);
        	$("#user_list").append(html);
        });
    }
    
    var curUid;
    var curImg;
    var curName;
    function nurseryUser(uid, uname){
    	curUid = uid;
    	curName = uname;
    	$(".username").html(uname);
    	$('.babySign_inp').val("");
    	$('#teacher_babySign_mask').slideDown();
    }
    //点击默认的文本
    $('.babySign_content_center li').each(function () {
        $(this).click(function () {
            var oval = $(this).text();
            $(this).addClass('changefontColor').siblings('li').removeClass('changefontColor');
            $('.babySign_inp').val(oval);
        });
    });
    //点击宝贝签到弹出页面中的“下一步”
    $('.babysign_next_btn').click(function () {
        $('#teacher_babySign_mask').fadeOut();
        //$('#babySign_uppic_mask').fadeIn();
        $('#nursery').html($('.babySign_inp').val());
        //直接调出照片页面
        handImgUpload();
    });
    
    function addNursery(){
    	var info = $("#nursery").html();
    	ajaxUtil({uid:curUid,info:info,img:curImg}, mainpath+"/user/addNursery.do", function(){
    		$('#babySign_uppic_mask').fadeOut();
    		$("#r_"+curUid).find(".babySign_btn").hide();
    		$("#r_"+curUid).find(".writeComments_btn").show();
    		$("#r_"+curUid).find(".nursery_status").html("已签到");
    	}, function(reason, code){
    		alert("签到失败，原因：" + reason);
    	});
    }
    
    function addComment(uid, uname){
    	var encUname = encodeURIComponent(uname);
    	window.location.href="myCommentTeacher?uid="+uid+"&uname="+encUname;
    }
    
    function search(){
    	var key = $("#key").val();
    	$('.select_school_block figcaption').text("所有学堂");
    	$('.select_class_block figcaption').text("所有班级");
    	loadUser(key);
    }
    
    //配置照片
    wxSDKConfig(null, ["chooseImage","previewImage","uploadImage"], {url:location.href.split('#')[0]});

    function handImgUpload(){
    	 wx.chooseImage({
    	    	count: 1, 
    	        sizeType: ['compressed'], // 可以指定是原图还是压缩图，默认二者都有
    	        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
    	        success: function (res) {
    	        	var localIds = res.localIds;
    	            $("#nurseryImg").attr("src", localIds);
    	            $('#babySign_uppic_mask').fadeIn();
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
                curImg = serverId
            }
        });
    };

</script>
<script type="text/javascript" src="../js/main.js"></script>
</body>
</html>