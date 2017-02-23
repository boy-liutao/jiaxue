$(document).ready(function(){
	$('#s_input').click(function () {
		$('#s_font').attr('style','display:none');
	});
	setTimeout(function(){
			$(".s_opacity").css("display", "none");
	},5000);
	$("#s_btn").click(function(){
		var username=$('#s_input').val();
		//alert(username);
		reg=/^([\u4e00-\u9fa5]|[A-Za-z]|[\d]){1,16}$/;
		if(!reg.test(username)){
			alert("表要酱紫，学吗中英文通吃，但还是读不懂你的名字");
			return false;
		}
		var fromid = urlparameter("fromid");
		var channel = urlparameter("channel");
		window.location.href="game/start.do?fromid="+fromid+"&username="+username+"&channel="+channel;
	});
});
