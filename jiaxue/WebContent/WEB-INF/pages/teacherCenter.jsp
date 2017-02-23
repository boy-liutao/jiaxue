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
    
    <link type="text/css" rel="stylesheet" href="../css/style.css"/>
    <link href="../css/mobiscroll.custom-2.5.0.min.css" rel="stylesheet" type="text/css" />
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
    <div class="clearfix"></div>
</div>
<hr style="background: lightgrey;height: 0.03rem"/>
	
<div id="edit_avatar">
    <div class="imgContainer">
        <img class="avatar" src="../img/edit01.png" alt="" id="avatar"/>
    </div>
    <div class="update_avatar">更换头像</div>
</div>

<hr style="background: lightgrey;height: 0.03rem"/>
    <div id="edit_content">
	    <div class="parent_info">
	        <div class="edit_fl_tit">
	            <span><strong>家长姓名:</strong></span>
	        </div>
	        <div class="edit_fr_inp">
	            <span id="nickname"></span>
	        </div>
	        <div class="clearfix"></div>
	    </div>
	    </div>
	    <div id="edit_content">
	    <div class="parent_info">
	        <div class="edit_fl_tit">
	            <span><strong>家长电话:</strong></span>
	        </div>
	        <div class="edit_fr_inp">
	            <span id="phone"></span>
	        </div>
	        <div class="clearfix"></div>
	    </div>
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

loadEditTeacher();
function loadEditTeacher(){
	ajaxUtil(null, mainpath+"/manager/get.do",function(response){
		var a = response;
		$("#nickname").text(a.nickname);
		$("#phone").text(a.phone);
		$("#avatar").attr("src", a.img);
		$(".edit_info_tel").text(a.phone);
	});
}

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