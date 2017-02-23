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
    <link href="../css/mobiscroll.custom-2.5.0.min.css" rel="stylesheet" type="text/css" />
    <link type="text/css" rel="stylesheet" href="../css/style.css"/>
    <title>编辑资料</title>
    <style type="text/css">
        .ui-loader-default{ display:none}
        .ui-mobile-viewport{ border:none;}
        .ui-page {padding: 0; margin: 0; outline: 0}
    </style>
</head>
<body id="#edit_wrap">
<div id="edit_header">
    <h2>已绑定账号</h2>
    <h5 class="edit_info_tel"></h5>
    <div class="edit_unbind">解除绑定</div>
    <div class="clearfix"></div>
</div>
<hr style="background: lightgrey;height: 0.03rem"/>
<div id="edit_avatar">
    <div class="imgContainer">
        <img class="avatar" src="" alt="" id="avatar"/>
    </div>
    <div class="update_avatar">更换头像</div>
    <div class="clearfix"></div>
</div>
<form action="<%=path %>/user/update.do" method="post" id="update_user_form">
	<input type="hidden" id="avatar_url" name="imgfile"/>
	<input type="hidden" id="sex" name="sex"/>
	<input type="hidden" id="grade" name="grade"/>

	<div id="edit_content">
	    <div class="parent_info">
	        <div class="edit_fl_tit">
	            <span><strong>家长姓名</strong></span>
	        </div>
	        <div class="edit_fr_inp">
	            <input class="parent_info_name" name="parent" id="parent" type="text" placeholder="请输入家长姓名"/>
	        </div>
	        <div class="clearfix"></div>
	    </div>
	    <div class="parent_info">
	        <div class="edit_fl_tit">
	            <span><strong>身份证号</strong></span>
	        </div>
	        <div class="edit_fr_inp">
	            <input class="parent_info_number" name="cardId" id="cardId" type="text" placeholder="请输入身份证号码"/>
	        </div>
	        <div class="clearfix"></div>
	    </div>
	    <div class="parent_info">
	        <div class="edit_fl_tit">
	            <span><strong>电子邮箱</strong></span>
	        </div>
	        <div class="edit_fr_inp">
	            <input class="parent_info_email" name="email" id="email" type="text" placeholder="请输入电子邮箱"/>
	        </div>
	        <div class="clearfix"></div>
	    </div>
	    <div class="parent_info parent_info_bottom">
	        <div class="edit_fl_tit">
	            <span><strong>家庭住址</strong></span>
	        </div>
	        <div class="edit_fr_inp">
	            <input class="parent_info_address" name="homeAddress" id="homeAddress" type="text" placeholder="请输入家庭住址"/>
	        </div>
	        <div class="clearfix"></div>
	    </div>
	    <hr style="margin-bottom: 0.28rem"/>
	
	    <div class="child_info">
	        <div class="edit_fl_tit">
	            <span><strong>孩子姓名</strong></span>
	        </div>
	        <div class="edit_fr_inp">
	            <input class="child_info_name" type="text" name="name" id="name" placeholder="请输入孩子姓名"/>
	        </div>
	        <div class="clearfix"></div>
	    </div>
	
	    <div class="child_info">
	        <div class="edit_fl_tit">
	            <span><strong>孩子性别</strong></span>
	        </div>
	        <div class="edit_fr_inp">
	            <img class="male_radio_img" src="../img/edit02.png" alt="" style="width: 0.32rem;height: 0.32rem"/>
	            <label class="male">男</label>
	            <img class="female_radio_img" src="../img/edit02.png" alt="" style="width: 0.32rem;height: 0.32rem"/>
	            <label class="female">女</label>
	        </div>
	        <div class="clearfix"></div>
	    </div>
	    <div class="child_info">
	        <div class="edit_fl_tit">
	            <span><strong>所在学校</strong></span>
	        </div>
	        <div class="edit_fr_inp">
	            <input class="child_info_school" type="text" name="inschool" id="inschool" placeholder="请输入孩子学校"/>
	        </div>
	        <div class="clearfix"></div>
	    </div>
	    <div class="child_info">
	        <div class="edit_fl_tit">
	            <span><strong>所在年级</strong></span>
	        </div>
	        <div class="edit_fr_grade">
	            <div class="grade grade1">一年级</div>
	            <div class="grade grade2">二年级</div>
	            <div class="grade grade3">三年级</div>
	            <div class="grade grade4">四年级</div>
	            <div class="grade grade5">五年级</div>
	            <div class="grade grade6">六年级</div>
	        </div>
	        <div class="clearfix"></div>
	    </div>
	    <div class="child_info">
	        <div class="edit_fl_tit">
	            <span><strong>生日</strong></span>
	        </div>
	        <div class="edit_fr_inp">
	            <input class="child_info_birthday" type="text" name="born" id="babyBirthday" placeholder="请输入孩子出生日期"/>
	            <img class="calendar_icon" src="../img/edit04.png" alt=""/>
	        </div>
	        <div class="clearfix"></div>
	    </div>
	</div>
</form>
<div id="edit_btn">
    <div class="edit_save_btn">保存</div>
</div>

<!--弹出上传头像框-->
<div id="editinfo_avatar_mask" style="display: none"></div>
<div id="avatar_popup_mask" style="display: none">
    <div class="avatar_popup">
        <h2>上传图片更换头像</h2>
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
<!--弹出解除绑定框-->
<div id="unbind_popup_mask" style="display: none">
    <div id="unbind_popup">
        <span>您的账号已解除绑定</span>
    </div>
</div>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../js/jquery.mobile-1.3.1.min.js"></script>
<script type="text/javascript" src="../js/flexible.js"></script>
<script type="text/javascript" src="../js/flexible_css.js"></script>
<script type="text/javascript" src="../js/mobiscroll.custom-2.5.0.min.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="../js/main.js?v=1.1"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/wx.js"></script>
<script type="text/javascript">
//引用日期插件
var opt = {
    preset: 'date', //日期
    theme: 'sense-ui', //皮肤样式
    display: 'modal', //显示方式
    mode: 'scroller', //日期选择模式
    dateFormat: 'yy-mm-dd', // 日期格式
    setText: '确定', //确认按钮名称
    cancelText: '取消',//取消按钮名籍我
    dateOrder: 'yymmdd', //面板中日期排列格式
    dayText: '日', monthText: '月', yearText: '年', //面板中年月日文字
    endYear:2020 //结束年份
};
$("#babyBirthday").mobiscroll(opt).date(opt);

loadEditUser();
function loadEditUser(){
	ajaxUtil(null, mainpath+"/user/get.do",function(response){
		var a = response;
		if (a.phone == "" || a.phone == null){
			$(".edit_unbind").html("绑定手机");
		} else {
			$(".edit_info_tel").text(a.phone);
		}
		$("#name").val(a.name);
		$("#babyBirthday").val(formatDate(a.born));
		$("#sex").val(a.sex);
		$("#grade").val(a.grade);
		$("#parent").val(a.parent);
		$("#cardId").val(a.cardId);
		$("#email").val(a.email);
		$("#homeAddress").val(a.homeAddress);
		$("#inschool").val(a.inschool);
		$("#avatar").attr("src", a.img);
		$('.grade').each(function () {
	        if ($(this).html() == a.grade){
	        	$(this).css('background', '#0099d9').css('color', '#ffffff').css('border', 'none');
	            $(this).siblings('div').css('background', '#ffffff').css('color', '#9FA0A0').css('border', '1px solid #9FA0A0');
	        }
	    });
		if (a.sex == 1){
			$(".male_radio_img").attr('src', '../img/edit03.png');
            $(".male_radio_img").next().next().attr('src', '../img/edit02.png');
		} else if (a.sex == 2){
			$(".female_radio_img").attr('src', '../img/edit03.png')
            $(".female_radio_img").prev().prev().attr('src', '../img/edit02.png');
		}
	});
}

$('#edit_btn').click(function () {
 	ajaxSubmitWithFunc('#update_user_form', function(){
		alert("保存成功");
		window.location.href="index";
	})
});

$(".edit_unbind").click(function(){
	var phone = $(".edit_info_tel").html();
	if (phone != ""){
		ajaxUtil(null, mainpath+"/user/unbindPhone.do", function(){
			$('#unbind_popup_mask').fadeIn();
	        $('#unbind_popup_mask').delay(1500).fadeOut(0);
			$(".edit_info_tel").html("");
			$(".edit_unbind").html("绑定手机");
			window.location.href="index";
		});
	} else {
		window.location.href="login";
	}
	
});

$('.sex').each(function () {
    $(this).click(function () {
        var sexStr = $(this).text();
        if (sexStr == "男")
        	$("input[name='sex']").val(1); 
        if (sexStr == "女")
        	$("input[name='sex']").val(2); 
    });
});

//配置照片
wxSDKConfig(hookImg, ["chooseImage","previewImage","uploadImage"], {url:location.href.split('#')[0]});

function hookImg(){
	$(".update_avatar").click(handImgUpload);
}

function handImgUpload(){
	 wx.chooseImage({
	    	count: 1, 
	        sizeType: ['compressed'], // 可以指定是原图还是压缩图，默认二者都有
	        sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
	        success: function (res) {
	        	var localIds = res.localIds;
	            $("#avatar").attr("src", localIds);
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
            $("#avatar_url").val(serverId);
        }
    });
};


</script>
</body>
</html>