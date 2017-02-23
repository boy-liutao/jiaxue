function wxSDKConfig(callbackFunc, apiList, callparams){
	$.ajax({
		type:'POST',
		dataType:'json',
		url: mainpath+'/wechatjs.do',
		data: {url:location.href.split('#')[0]},
        success: function(response) {
        	//wx sdk
        	var data=response.response.map;
        	wx.config({
        	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        	    appId: data.appId, // 必填，公众号的唯一标识
        	    timestamp: data.timestamp, // 必填，生成签名的时间戳
        	    nonceStr: data.nonceStr, // 必填，生成签名的随机串
        	    signature: data.signature,// 必填，签名，见附录1
        	    jsApiList: apiList // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
        	});
        	wx.ready(function(){
        		if (callbackFunc != null) {
        			callbackFunc(data);
        		}
        	});
        	
        },
        error: function(x, e) {
        	alert("微信超时连接，点确定后重试");
        	window.location.href = window.location.href;
        },
        complete: function(x) {
        	//alert("call complete");
        }
	});
}
