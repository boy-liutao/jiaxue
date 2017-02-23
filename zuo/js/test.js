var my = null;
var kv = {0:'学霸',1:'学仙',2:'学婊',3:'学弱',4:'学民',5:'学渣',6:'学神'};
var openId = null;
var isSub = true;
var classroomResult = {};


function loadClassroom(){
	var id = urlparameter("id");
	if (!(id == null || id == '' || id == undefined)){
		openId = id;
		ajaxUtil({id:id}, "game/loadClassroom.do", function(response){
			var classroom = response.map;
			var b = classroom.b;//霸 0
			var x = classroom.x;//仙 1
			var xb = classroom.xb;//学婊 2
			var r = classroom.r;//弱 3
			var m = classroom.m;//民 4
			var z = classroom.z;//渣 5
			var s = classroom.s;//神 6
			
			var html = "";
			
			html += printArea("学霸区", b, id);
			html += printArea("学仙区", x, id);
			html += printArea("学婊区", xb, id);
			html += printArea("学弱区", r, id);
			html += printArea("学民区", m, id);
			html += printArea("学渣区", z, id);
			html += printArea("学神区", s, id);
			html += "<div style='clear:both'></div>";
			$("#classroom").html(html);
			
			if (my != null){
				var username = my.username;
				var desc = getDesc(my.result, my.username);
				$("#result").html(desc);
			}
			
			
		});
	}
}
function start(){
	var username=$("#username").val();
	var fromid = urlparameter("fromid");
	window.location.href="game/start.do?fromid="+fromid+"&username="+username;
}

function printArea(areaName, stus, id){
	var html = "";
	html += "<div style='border:1px solid blue; width:160px;float:left'>";
	html += "<p>"+areaName+" </p>";
	if (stus != null && stus != undefined){
		for (var i in stus){
			var stu = stus[i];
			if (id != undefined && id==stu.id){
				my = stu;
				html += "<span style='color:red'>"+stu.id+"</span><a href='javascript:viewStuResult(\""+stu.id+"\")'><img style='width:32px;height:32px' src='"+stu.img+"'></img></a>";
			} else {
				html += stu.id+"<a href='javascript:viewStuResult(\""+stu.id+"\")'><img style='width:32px;height:32px' src='"+stu.img+"'></img></a>";
			}
			classroomResult[stu.id] = stu;
		}
	}
	html += "</div>";
	return html;
}

function viewStuResult(id){
	issub = urlparameter("issub");
	if (issub == "true"){
		var stu = classroomResult[id];
		var desc = getDesc(stu.result, stu.username);
		$("#result").html(desc);
	} else {
		var viewid = urlparameter("id");
		window.location.href="game/login.do?viewid="+viewid;
	}
} 

function popupSub(){
	var issub = urlparameter("issub");
	if (issub == "false"){
		alert("关注学吗才能看其它小伙伴的结果");
		$("#qrcode").html("<img src='album/qrcode.jpg'></img>");
	}
}
popupSub();



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
        	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        	    appId: data.appId, // 必填，公众号的唯一标识
        	    timestamp: data.timestamp, // 必填，生成签名的时间戳
        	    nonceStr: data.nonceStr, // 必填，生成签名的随机串
        	    signature: data.signature,// 必填，签名，见附录1
        	    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
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
	var link = "http://zuo.shishengclub.com/zuo/index.html";
	if (openId != null){
		link += "?id="+openId
	}
	var shareFriend = getShareFriend(my.result);
	wx.onMenuShareTimeline({
	    title: shareFriend.title.replace("xxx", my.username), 
	    link: link,
	    imgUrl: shareFriend.pic,
	    success: function () { 
	    	window.alert('分享朋友圈成功!');
	    },
	    cancel: function () { 
	       
	    }
	});
	var shareVar = getShare(my.result);
	wx.onMenuShareAppMessage({
		title: shareVar.title, 
	    desc: shareVar.desc.replace("xxx", my.username), 
	    link: link,
	    imgUrl: shareVar.pic,
	    type: '', 
	    dataUrl: '',
	    success: function () { 
	        alert('分享好友成功!');
	    },
	    cancel: function () { 
	        
	    }
	});
});

configWX();