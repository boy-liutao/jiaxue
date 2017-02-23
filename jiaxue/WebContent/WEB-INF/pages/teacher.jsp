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
    <div id="teacher_header">
        <input class="initial_search_inp" type="text" id="key" placeholder="搜索学生"/>
        <!--<img class="search_icon" src="../img/teacher00.png" alt=""/>-->
        <button id="teacher_search_btn" onclick="search()">搜索</button>
    </div>
    <div id="search_content" style="display: none">
        <div class="history_search" style="display: none">
            <div>
                <span class="history_tit">搜索历史：</span>
                <span class="histroy_clear">清除</span>
            </div>
            <hr style="margin-top: 0.2rem;margin-bottom: 0.3733rem;"/>
            <div class="history_record">
                <button>李大伟</button>
                <button>李大</button>
                <button>李</button>
                <button>李大伟</button>
                <button>李大伟</button>
            </div>
        </div>
    </div>
    <div id="teacher_nav">
        <div class="teacher_nav_header">
            <div class="teacher_nav_school">
                <figure class="select_school_block">
                    <figcaption>所有学堂</figcaption>
                    <img class="select_school_arrow" src="../img/teacher01.png" alt="" />
                    <div class="clearfix"></div>
                </figure>
            </div>
            <div class="teacher_nav_class">
                <figure class="select_class_block">
                    <figcaption>所有班级</figcaption>
                    <img class="select_class_arrow" src="../img/teacher01.png" alt="" />
                    <div class="clearfix"></div>
                </figure>
            </div>
        </div>
        <div class="teacher_content" id="user_list">
        </div>
        <div id="teacher_footer">
            <h5>北京家学天地网络科技有限公司</h5>
            <h5>©2016&nbsp;All&nbsp;Rights&nbsp;Reserved</h5>
        </div>
        <!-- <img id="teacher_hide_mask" src="../img/teacher03.png" alt="" style="display: none"/> -->
        <!--页面底端教师信息-->
        <!-- <div id="teacher_foot_mask" style="display: none">
            <img id="foot_mask_show" src="../img/teacher03.png" alt="" style="width: 0.64rem;height: 0.1067rem;margin: 0.1rem auto 0;"/>
            <div class="foot_mask_content">
                <img class="teacher_myavatar" src="../img/edit01.png" alt=""/>
                <div class="foot_mask_fr">
                    <h4>林溪老师</h4>
                    <h5>已绑定账号：<span class="teacher_tel">13621273456</span></h5>
                    <div class="floor3">
                        <button class="change_avatar">更换头像</button>
                        <button class="teacher_unbind">解除绑定</button>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div> -->
    </div>
    <div id="unbind_whether_mask" style="display: none;">
        <div class="unbind_prompt">
            <h5>解除绑定后，您将无法继续使</h5>
            <h5 class="tit2">用i家学老师版，是否继续？</h5>
            <hr style="background: lightgrey;height: 0.03rem;"/>
            <hgroup class="unbind_prompt_confirm">
                <h5 class="unbind_cancel">取消</h5>
                <h5 class="unbind_continue">继续</h5>
                <div class="clearfix"></div>
            </hgroup>
        </div>
    </div>
    <div id="unbind_popup_mask" style="display: none">
        <div id="unbind_popup">
            <span>您的账号已解除绑定</span>
        </div>
    </div>
    <!--选择学堂和班级的遮罩层-->
    <div id="teacher_select_mask" style="display: none">
        <ul class="mask_select_school" id="mask_select_school" style="display: none">
        </ul>
        <ul class="mask_select_class" id="mask_select_class" style="display: none">
        </ul>
        <div class="clearfix"></div>
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
    <!--点击下一步拍照上传页面-->
    <!-- 
    <div id="babySign_uppic_mask" style="display: none">
        <div class="uppic_popup" id="uppic_popup">
            <h2>上传图片签到</h2>
            <figure>
                <img src="../img/edit05.png" alt="" style="width: 0.96rem;height: 0.8133rem;"/>
                <figcaption class="tit1">从相册中选择</figcaption>
            </figure>
            <figure>
                <img src="../img/edit06.png" alt="" style="width: 0.8667rem;height: 0.76rem;"/>
                <figcaption class="tit2">拍照</figcaption>
            </figure>
            <div class="clearfix"></div>
        </div>
    </div>
     -->
     
    <!-- <div id="babySign_uppic_mask" style="display: none">
        <div class="uppic_popup" id="uppic_popup" style="height:auto">
            <h2 id="nursery"></h2>
            <figure style="width:100%">
                <img id="nurseryImg" alt="" style="width: 1.96rem;height: 1.8133rem; margin-bottom:0.4rem"/>
	            <button class="babySign_btn" onclick="addNursery()" style="border: 1px solid lightgrey; border-radius: 0.2rem; font-size: 0.28rem; height: 0.5867rem; letter-spacing: 0.01rem; background-color: rgb(0, 153, 217); width: 1.2rem;; margin-bottom:0.4rem"> 发 送  </button>
            </figure>
            <div class="clearfix"></div>
        </div>
    </div> -->
    <!--发送评论-->
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
</div>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js?v=1.1"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="../js/wx.js"></script>
<script type="text/javascript">
	loadUser(null);
	loadSchool();
	function loadSchool(){
		ajaxUtil(null, mainpath+"/school/listAll.do", function(response){
			var list = response.list;
			for (var i in list){
				var s = list[i];
				$("#mask_select_school").append("<li data="+s.id+" onclick='loadClasses(null, this)'>"+s.name+"</li>");
			}
		});
	}
    //点击选择学堂
    $(".teacher_nav_school").click(function(){
    		if($('.select_school_arrow').attr("src") =="../img/teacher01.png"){
            $('.select_school_arrow').attr("src","../img/teacher02.png");
            $('#teacher_select_mask').slideDown();
            $('.mask_select_school').slideDown();
        }
        else{
            $('.select_school_arrow').attr("src","../img/teacher01.png");
            $('#teacher_select_mask').slideUp();
            $('.mask_select_school').slideUp();

        }
    });
    
    function loadClasses(sid, schoolLi){
    	var oval = $(schoolLi).text();
        $('#teacher_select_mask').slideUp();
        $('.mask_select_school').slideUp();
        $('.select_school_block figcaption').text(oval);
        $('.select_school_arrow').attr("src","../img/teacher01.png");
        //load classes
        var sid = $(schoolLi).attr("data");
        $('.select_class_block figcaption').text("所有班级");
        $("#mask_select_class").html("");
        ajaxUtil({sid:sid}, mainpath+"/classes/listByManagerSid.do", function(response){
        	var list = response.list;
			for (var i in list){
				var c = list[i];
				$("#mask_select_class").append("<li data="+c.id+" onclick=\"loadUser('"+c.id+"', this)\">"+c.name+"</li>");
			}
        });
    }
    
    function loadUser(key, cl){
        $('#teacher_select_mask').slideUp();
        $('.mask_select_class').slideUp();
        $('.select_class_arrow').attr("src","../img/teacher01.png");
        var cid = -1;
        var url = "/user/listByManager.do";
        if (cl != undefined){
	    	var oval = $(cl).text();
	        $('.select_class_block figcaption').text(oval);
	        cid = $(cl).attr("data");
	        url = "/user/listByCid.do";
        }
        ajaxUtil({cid:cid,key:key}, mainpath+url, function(response){
        	var list = response.list;
        	var html="";
        	for (var i in list){
        		var u = list[i];
        		html += "<div class='teacher_content_block' id='r_"+ u.id +"'>";
        		html += "    <img class='teacher_stu_avatar' src='"+u.img+"' alt='' style='width: 1.6267rem;height: 1.6267rem'/>";
        		if (u.lastChat != null && u.lastChat.readStatus==0 && u.lastChat.toUser==0){
    				html += "<div class='unread_icon'></div>";
    			}
        		html += "    <div class='teacher_block_fr'>";
        		html += "        <h2 class='teacher_stu_username'>"+u.name+"</h2>";
        		html += "        <h4><span>"+u.school.name+"</span>&nbsp;/&nbsp;<span>"+u.inschool+"</span>&nbsp;/&nbsp;<span>"+u.grade+"</span>&nbsp;</h4>";
        		var scheduleTime = "";
        		var sep = "";
    			if (u.daily.nurseryStatus){
        			scheduleTime = "已签到";
        			sep = ",";
    			} 
    			if (u.daily.commentStatus){
    				scheduleTime += sep+"已评价";
    			}
        		if (isEmpty(scheduleTime)){
        			scheduleTime = "今日接孩子时间：</span><span>"+u.daily.scheduleTime+"</span>";
        		}
        		html += "        <h5 class='nursery_status'>"+scheduleTime+"</h5>";
        		html += "    </div>";
        		if (!u.daily.nurseryStatus){
    	    		html += '    <button onclick=nurseryUser("'+u.id+'","'+trimStr(u.name)+'") class="babySign_btn" >宝贝签到</button>';
    	    		html += '    <button onclick=addComment("'+u.id+'","'+u.name+'") class="writeComments_btn" style="display:none">写评语</button>';
        		} else {
        			if (!u.daily.commentStatus){
	    	    		html += '    <button class="writeComments_btn" onclick=addComment("'+u.id+'","'+u.name+'")>写评语</button>';
        			} else {
        				html += '    <button class="chat_btn" onclick=addComment("'+u.id+'","'+u.name+'") style="background:#efefef;color:#9fa0a0">聊天</button>';
        			}
        		}
        		html += "    <div class='clearfix'></div>";
        		html += "</div>";
        	}
        	$("#user_list").html(html);
        });
    }
    //点击选择班级
    $('.teacher_nav_class').click(function () {
        if($('.select_class_arrow').attr("src") =="../img/teacher01.png"){
            $('.select_class_arrow').attr("src","../img/teacher02.png");
            $('#teacher_select_mask').slideDown();
            $('.mask_select_class').slideDown();
        } else{
            $('.select_class_arrow').attr("src","../img/teacher01.png");
            $('#teacher_select_mask').slideUp();
            $('.mask_select_class').slideUp();
        }
    });
    
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
    //点击页面底部遮罩层
    $('#teacher_hide_mask').click(function () {
        $(this).hide();
        $('#teacher_foot_mask').fadeIn();
    });
    $('#foot_mask_show').click(function () {
        $('#teacher_foot_mask').fadeOut();
        $('#teacher_hide_mask').show();
    });

    //解除绑定
    $('.teacher_unbind').click(function () {
        $('#unbind_whether_mask').fadeIn();
    });
    $('.unbind_cancel').click(function () {
        $('#unbind_whether_mask').fadeOut();
    });
    $('.unbind_continue').click(function () {
        $('#unbind_whether_mask').fadeOut();
        $('#unbind_popup_mask').delay(1000).fadeIn(1000);
        $('#unbind_popup_mask').delay(1500).fadeOut(1000);
    });
    //点击所要div外其他位置，整个遮罩层隐藏(选择学校和课堂的遮罩层)
    window.onload= function () {
        var selectSchool = document.getElementById('mask_select_school');
        var selectClass = document.getElementById('mask_select_class');
        var selectMask = document.getElementById('teacher_select_mask');
        selectMask.onclick = function (event) {
            var e = event || window.event;
            var aim = e.srcElement || e.target;
            if(e.srcElement){
                var aim = e.srcElement;
                if(aim != selectSchool && aim != selectClass){
                    selectMask.style.display = 'none';
                    $('.select_school_arrow').attr("src","../img/teacher01.png");
                    $('.select_class_arrow').attr("src","../img/teacher01.png");
                }
            }else{
                var aim = e.target;
                if(aim != selectSchool && aim != selectClass){
                    selectMask.style.display = 'none';
                    $('.select_school_arrow').attr("src","../img/teacher01.png");
                    $('.select_class_arrow').attr("src","../img/teacher01.png");
                }
            }
        };
    };
    
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