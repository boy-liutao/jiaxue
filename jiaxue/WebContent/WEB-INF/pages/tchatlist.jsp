<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1,maximum-scale=1,user-scalable =no">
    <script type="text/javascript" src="../js/flexible.js"></script>
    <script type="text/javascript" src="../js/flexible_css.js"></script>
    <link type="text/css" rel="stylesheet" href="../css/style.css?v=2.0"/>
    
    <title>家学家话</title>
</head>
<body>
<div id="teacher_wrap">
    <div id="theader">
        <input class="initial_search_inp" type="text" id="key" placeholder="搜索学生"/>
        <img class="search_icon" src="../img/teacher00.png" alt=""/>
        <img id="refresh_btn" class="refresh_btn" src="../img/tchatlist01.png" alt="刷新"/>
        <!-- <button id="teacher_search_btn" onclick="search()">搜索</button> -->
    </div>
    <div id="teacher_nav">
        <div class="teacher_nav_header">
            <div class="teacher_nav_school">
                <figure class="select_school_block">
                	<img class="select_school_arrow" src="../img/tchatlist02.png" alt="" />
                    <figcaption>所有学堂</figcaption>
                    <div class="clearfix"></div>
                </figure>
            </div>
            <div class="teacher_nav_class">
                <figure class="select_class_block">
                	<img class="select_class_arrow" src="../img/tchatlist02.png" alt="" />
                    <figcaption>所有班级</figcaption>
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
    </div>
    <!--选择学堂和班级的遮罩层-->
    <div id="teacher_select_mask" style="display: none">
        <ul class="mask_select_school" id="mask_select_school" style="display: none">
        </ul>
        <ul class="mask_select_class" id="mask_select_class" style="display: none">
        </ul>
        <div class="clearfix"></div>
    </div>
</div>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/common.js?v=1.1"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="../js/wx.js"></script>
<script type="text/javascript">
	loadUser(null, -1);
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
    	var isSchoolShow = $('.mask_select_school').css('display');
    	/* console.log(isSchoolShow); */
    	if(isSchoolShow == 'none'){
    		$('#teacher_select_mask').slideDown();
    		$('.mask_select_school').slideDown();
    	}else{
    		$('#teacher_select_mask').slideUp();
    		$('.mask_select_school').slideUp();
    	}
    });
    //点击选择班级
    $('.teacher_nav_class').click(function () {
    	var isSchoolShow = $('.mask_select_class').css('display');
    	if(isSchoolShow == 'none'){
    		$('#teacher_select_mask').slideDown();
    		$('.mask_select_class').slideDown();
    	}else{
    		$('#teacher_select_mask').slideUp();
    		$('.mask_select_class').slideUp();
    	}
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
                }
            }else{
                var aim = e.target;
                if(aim != selectSchool && aim != selectClass){
                    selectMask.style.display = 'none';
                }
            }
        };
    };
    
    function loadClasses(sid, schoolLi){
    	var oval = $(schoolLi).text();
        $('#teacher_select_mask').slideUp();
        $('.mask_select_school').slideUp();
        $('.select_school_block figcaption').text(oval);
        /* $('.select_school_arrow').attr("src","../img/tchatlist02.png"); */
        //load classes
        var sid = $(schoolLi).attr("data");
        $('.select_class_block figcaption').text("所有班级");
        $("#mask_select_class").html("");
        ajaxUtil({sid:sid}, mainpath+"/classes/listByManagerSid.do", function(response){
        	var list = response.list;
			for (var i in list){
				var c = list[i];
				$("#mask_select_class").append("<li data="+c.id+" onclick=\"loadUser('', -1, this)\">"+c.name+"</li>");
			}
			//加载学堂内的
			loadUser("", sid);
        });
    }
    
    function loadUser(key, sid, cl){
        $('#teacher_select_mask').slideUp();
        $('.mask_select_class').slideUp();
        /* $('.select_class_arrow').attr("src","../img/teacher01.png"); */
        var cid = -1;
        var url = "/user/search.do";
        if (cl != undefined){
	    	var oval = $(cl).text();
	        $('.select_class_block figcaption').text(oval);
	        cid = $(cl).attr("data");
        }
        ajaxUtil({cid:cid,sid:sid,key:key,scope:1}, mainpath+url, function(response){
        	var list = response.list;
        	var html="";
        	for (var i in list){
        		var u = list[i];
        		html += "<div class='teacher_content_block' id='r_"+ u.id +"'>";
        		html += "<a href='tchat?uid="+u.id+"&uname="+u.name+"'>";
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
        		html += "<p style='float:right;margin-top:20px;margin-right:10px'>10:00</p>";
        		html += "    <div class='clearfix'></div>";
        		html += "</a>";
        		html += "</div>";
        	}
        	$("#user_list").html(html);
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
    
    
    function search(){
    	var key = $("#key").val();
    	$('.select_school_block figcaption').text("所有学堂");
    	$('.select_class_block figcaption').text("所有班级");
    	loadUser(key, -1);
    }
    

</script>
<script type="text/javascript" src="../js/main.js"></script>
</body>
</html>