//added by sunqi 2016/03/02 start
/*
 * 智能机浏览器版本信息:
 *
 */
var browser = {
	versions: function () {
		var u = navigator.userAgent, app = navigator.appVersion;
		return {//移动终端浏览器版本信息
			trident: u.indexOf('Trident') > -1, //IE内核
			presto: u.indexOf('Presto') > -1, //opera内核
			webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
			gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
			mobile: !!u.match(/AppleWebKit.*Mobile/i) || !!u.match(/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE/), //是否为移动终端
			ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
			android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
			iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
			iPad: u.indexOf('iPad') > -1, //是否iPad
			webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
		};
	} (),
	language: (navigator.browserLanguage || navigator.language).toLowerCase()
};
if (browser.versions.iPhone || browser.versions.iPad || browser.versions.ios) {
	window.location.href = "https://itunes.apple.com/cn/app/xue-ma-you-zhi-jiao-yu-dian/id1004966607?l=en&mt=8";
	//https://itunes.apple.com/cn/app/xue-ma-you-zhi-jiao-yu-dian/id1004966607?l=en&mt=8
}
if (browser.versions.android) {
	window.location.href = "http://openbox.mobilem.360.cn/index/d/sid/3027071";
	//http://openbox.mobilem.360.cn/index/d/sid/3027071
}
//added by sunqi 2016/03/02 end

var isS=2;

function getCode(){
	var phone=$("#phone").val();
	if (!isPhoneNumber(phone)) return;
	ajaxUtil({phone:phone}, contextpath+"/user/sendCode.do", function(){
		alert("发送成功！");
		settime("#code-btn");
	}, function(){
		alert("发送验证码失败，请稍后再试");
	});
}

function rememberMe(phone){
	$.cookie('xuema_login_status', 'true', { expires: 365, path: '/' });
	$.cookie('xuema_login_phone', phone, { expires: 365, path: '/' });
	$.cookie('xuema_login_type', isS, { expires: 365, path: '/' });
}
function login(){
	var agree = $("#agreeProtocal").is(':checked');
	if (!agree) {
		alert("请先阅读并同意《学骊用户协议》");
		return ;
	}
	var remember =  $("#rememberMe").is(':checked');
	var phone=$("#phone").val();
	var code=$("#code").val();
	ajaxUtil({phone:phone,code:code,type:isS}, contextpath+"/user/login", function(){
		if (remember){
			rememberMe(phone);
		}
		var from = urlparameter("from");
		if (from == ""){
			from = "index.jsp"
		}
		window.location.href=from;
	}, function(){
		alert("登录失败，请稍后再试试");
	});
	
} 

function isLogin(){
	

	var phone=$("#phone").val();
	var code=$("#code").val();
	ajaxUtil({phone:phone,code:code,type:isS}, contextpath+"/user/isLogin", function(){
		alert("注册成功");
	}, function(){
		alert("已经注册");

		//modified by sunqi 2016/03/02 start
		//window.location.href="down.html";
		if (browser.versions.iPhone || browser.versions.iPad || browser.versions.ios) {
			window.location.href = "https://itunes.apple.com/cn/app/xue-ma-you-zhi-jiao-yu-dian/id1004966607?l=en&mt=8";
			//https://itunes.apple.com/cn/app/xue-ma-you-zhi-jiao-yu-dian/id1004966607?l=en&mt=8
		}
		if (browser.versions.android) {
			window.location.href = "http://openbox.mobilem.360.cn/index/d/sid/3027071";
			//http://openbox.mobilem.360.cn/index/d/sid/3027071
		}
		//modified by sunqi 2016/03/02 end
	});
}

function addUserLog(){
	
	var grade = $('input:radio[name="grade"]:checked').val();
	
	var hope= $('input:radio[name="hope"]:checked').val();
	
	var uname=$("#uname").val();
	
	var userName=$("#phone").val();
	
	ajaxUtil({grade:grade,hope:hope,uname:uname, userName:userName}, contextpath+"/user/addUserLog", function(){
		//alert("添加统计信息成功");
	}, function(){
		alert("添加统计信息失败");
		
	});
	
} 

function isPhoneNumber(phone){
	var reg = /^1\d{10}$/;
	if (!reg.test(phone)){
		alert("手机号码好像不太对！");
		return false;
	}
	return true;
}

var countdown=60; 
function settime(btn) { 
	if (countdown == 0) { 
		$(btn).attr("onclick", "getCode()");    
		$(btn).html("获取验证码"); 
		countdown = 60; 
	} else { 
		$(btn).attr("onclick", "");    
		$(btn).html(countdown + "秒"); 
		countdown--; 
		setTimeout(function() { 
			settime(btn) 
		},1000) 
	} 
} 
