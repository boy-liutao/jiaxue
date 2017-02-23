var classroomResult = {};
var my = null;
var issub = urlparameter("issub");
$(document).ready(function () {
	var fromid=urlparameter("fromid");
	var id=urlparameter("id");
	var channel=urlparameter("channel");
	var loadClassroomLink = "http://zuo.shishengclub.com/zuo/game/loadClassroom.do?callback=loadClassroom&id=";
	if (fromid != ""){
		$(".r_btn").hide();
		$(".r_wantTest").show();
		$(".r_wantTest").click(function(){
			window.location.href="index.html?fromid="+fromid+"&channel="+channel;
		});
		loadClassroomLink += fromid;
	} else {
		$(".test_btn").click(function(){
			window.location.href="index.html?channel="+channel;
		});
		$(".share_btn").click(function(){
			showShareMask();
		});
		loadClassroomLink += id;
	}
	$(".m_knowed").click(function(){
		$(this).parent().css("display", "none");
	});
	$(".m_knowed2").click(function(){
		$(this).parent().css("display", "none");
	});
	$(".m_knowed3").click(function(){
		$(this).parent().css("display", "none");
	});
	$.getScript(loadClassroomLink);
	showHintMask();
});

function loadArea(area,data){
	var id=urlparameter("id");
	var fromid=urlparameter("fromid");
	for(var i in data){
		var vi = parseInt(i)+1;
		var stu = data[i];
		var oimg = stu.img;
		var classNum = parseInt(Math.random()*11);
		var curAvatar = (id == stu.id || fromid == stu.id) ? ' curcurAvatar':'';
		var ohtml = "<a href='javascript:viewStuResult(\""+stu.id+"\")'><img id='stuimg_"+stu.id+"' src='"+oimg+"' style='width:1.5556rem;height:1.5556rem;border-radius:1.5556rem;-moz-border-radius:1.5556rem;-webkit-border-radius:1.5556rem;' class='"+area+"_circle"+vi+" avatar avatar-"+classNum+curAvatar+" mypng'>"+"</img><img id='stuimgcycle_"+stu.id+"'  class='"+area+"_circle"+vi+" avatar avatar-"+classNum+curAvatar+" mypngbg' src='img/resultpage/"+area+"Circle"+".png'></img></a>";

		$('.r_wrap').append(ohtml);
		if ((stu.id == id) || (stu.id == fromid)){
			my = stu;
			loadResultLeft(stu.result, stu.username, 5700);
		}

		classroomResult[stu.id] = stu;
	}
}

function loadResultLeft(stu_r, stu_img, timeoutV){
	$(".r_paragraph").html(getDesc(stu_r, stu_img));
	if (stu_r == 0){
		$(".r_cartoon").attr("src", "img/resultpage/xuebaCartoon.png");
	} else if (stu_r == 1){
		$(".r_cartoon").attr("src", "img/resultpage/xuexianCartoon.png");
	} else if (stu_r == 2){
		$(".r_cartoon").attr("src", "img/resultpage/xuebiaoCartoon.png");
	} else if (stu_r == 3){
		$(".r_cartoon").attr("src", "img/resultpage/xueruoCartoon.png");
	} else if (stu_r == 4){
		$(".r_cartoon").attr("src", "img/resultpage/xueminCartoon.png");
	} else if (stu_r == 5){
		$(".r_cartoon").attr("src", "img/resultpage/xuezhaCartoon.png");
	} else if (stu_r == 6){
		$(".r_cartoon").attr("src", "img/resultpage/xueshenCartoon.png");
	}

	setTimeout(function(){
		$("body").append($('.curcurAvatar').clone().addClass('curcurAvatar-' + stu_r).addClass('myclass'))
	},timeoutV);
} 

function loadClassroom(data){
	loadArea('b',data.response.map.b);
	loadArea('x',data.response.map.x);
	loadArea('xb',data.response.map.xb);
	loadArea('r',data.response.map.r);
	loadArea('m',data.response.map.m);
	loadArea('z',data.response.map.z);
	loadArea('s',data.response.map.s);
}


function showHintMask(){
	setTimeout(function(){
		if (issub == "" || issub == "false"){
			//not sub
			$(".mask_dialog2").css("display", "block");
		} else {
			//already sub
			$(".mask_dialog3").css("display", "block");
		}
	},11000);
}
function showShareMask(){
	$(".mask_dialog1").css("display", "block");
}
function showNoSubMask(){
	$(".mask_dialog2").css("display", "block");
}
function viewStuResult(id){
	channel = urlparameter("channel");
	if (issub == "true"){
		var stu = classroomResult[id];
		var desc = getDesc(stu.result, stu.username);
		var cart = getCartoon(stu.result);
		
		//cleanup area
		
		
		//remove .curcurAvatar
		$(".curcurAvatar").removeClass("curcurAvatar");
		$(".myclass").remove();
		//add avatar
		$("#stuimg_"+id).addClass("curcurAvatar");
		$("#stuimgcycle_"+id).addClass("curcurAvatar");
		loadResultLeft(stu.result, stu.username, 300);
	} else {
		var uid = urlparameter("uid");
		if (uid != ""){
			$.getScript("http://zuo.shishengclub.com/zuo/game/issub.do?callback=subAction&uid="+uid);
		} else {
			var id = urlparameter("id");
			var fromid = urlparameter("fromid");
			window.location.href="game/login.do?id="+id+"&fromid="+fromid+"&channel="+channel;
		}
	}
}

function subAction(data){
	if (!data.response){
		showNoSubMask();
	} else {
		issub = "true";
	}
}

function configWX(){
	$.ajax({
		type: 'POST',
		url: 'game/wechatjs.do',
		data:{"url":location.href.split('#')[0]},
		dataType:"json",
		async: false,
		success: function(response) {
			//wx sdk

			var data=response.response.map;
			wx.config({
				debug: false, // 开启调试模式，调用的所有的api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数
				// 信息会通过log打出，仅在pc端时才会打印。
				appId: data.appId, // 必填，公众号的唯一标识
				timestamp: data.timestamp, // 必填，生成签名的时间戳
				nonceStr: data.nonceStr, // 必填，生成签名的随即串
				signature: data.signature,// 必填，签名，见附录1
				jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS	接口列表见附录2
			});

		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
		},
		complete: function(x) {
			//alert("call complete");
		}
	});
}

wx.ready(function(){
	var id=urlparameter("id");
	var fromid=urlparameter("fromid");
	var channel=urlparameter("channel");
	
	var link = "http://zuo.shishengclub.com/zuo/game/login.do";
	if (id != ""){
		link += "?fromid="+id
	} else if (fromid != ""){
		link += "?fromid="+fromid
	} else {
		link = "http://zuo.shishengclub.com/zuo/index.html";
	}
	
	if (channel != null && channel != ""){
		link += "&channel="+channel;
	}
	var shareFriend = getShareFriend(my.result);
	wx.onMenuShareTimeline({
		//朋友圈
		title: shareFriend.title.replace("xxx", my.username),
		link: link,
		imgUrl: shareFriend.pic,
		success: function () {
			recordShare(1);
		},
		cancel: function () {

		}
	});
	var shareVar = getShare(my.result);
	wx.onMenuShareAppMessage({
		//给朋友
		title: shareVar.title,
		desc: shareVar.desc.replace("xxx", my.username),
		link: link,
		imgUrl: shareVar.pic,
		type: '',
		dataUrl: '',
		success: function () {
			recordShare(0);
		},
		cancel: function () {

		}
	});
});

configWX();

function recordShare(type){
	ajaxUtil({type:type},'game/recordShare.do', function(){

	});
}

function gotoEscape(){
	ajaxUtil({type:3},'game/recordShare.do', function(){

	});
	window.location.href="http://x.eqxiu.com/s/8oQpeQXs?eqrcode=1";
}