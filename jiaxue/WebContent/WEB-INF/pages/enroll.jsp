<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.xuema.util.SessionUtil"%>
<%@page import="com.xuema.util.BeanUtil"%>
<%@page import="com.xuema.util.DatetimeUtil"%>
<%@page import="com.xuema.bean.User"%>
<%@page import="java.lang.System"%>
<!DOCTYPE html>
<html lang="en">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = SessionUtil.getLoginUser(request);
%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1,maximum-scale=1,user-scalable =no">
    <script type="text/javascript" src="../js/flexible.js"></script>
    <script type="text/javascript" src="../js/flexible_css.js"></script>
    <link type="text/css" rel="stylesheet" href="../css/main.css"/>
    <title>家学报名</title>
</head>
<body id="enroll2_wrap">
<div id="enroll2_block">
	<input type="hidden" id="sid"/>
	<input type="hidden" id="nurseryType" value='1'/>
	<input type="hidden" id="nurseryUnit" value='1'/>
	<input type="hidden" id="nurseryCount" value='1'/>
	<input type="hidden" id="fruitCount" value='0'/>
	<input type="hidden" id="dinnerCount" value='0'/>
    <div id="enroll2_header">
    	<%if (user.getSid() == 0 || user.getSid() == -1) { %>
    	<div class="enroll2_header_inner" style="display: block" >
            <div class="floor1 change_address" style="height:1rem">
                <label style="margin-left: 4%;"><strong class="enroll2_address_title" style="font-size:0.48rem;color:black">请选择托管地点</strong></label>
                <img alt="" src="../img/enroll21.png" >
            </div>
        </div>
    	<%} else { %>
        <div class="enroll2_header_inner" style="display: block">
            <div class="floor1">
                <label><strong class="enroll2_address_title"></strong></label>
                <!-- 
                <img src="../img/enroll21.png" alt=""/>
                <span class="" id="swith_school">选择学堂</span>
                 -->
            </div>
            <hr/>
            <div class="floor2"><span class="enroll2_address_detail">地址：</span></div>
        </div>
        <%} %>
    </div>
    <div id="enroll2_block2">
        <div class="enroll2_block2_inner">
            <div><strong style="color: #0099d9;font-size: 0.42rem">托管形式</strong></div>
            <div class="floor2">
                <button class="always_deposit mode trigger1" nurseryType="1">课后托</button>
                <button class="holiday_deposit mode trigger1" nurseryType="6">周末/假日托</button>
                <button class="summerwiner_deposit mode trigger1" nurseryType="5">寒暑假托</button>
            </div>
        </div>
    </div>
    <div id="enroll2_block3">
        <div class="enroll2_block3_inner">
            <div><strong style="color: #0099d9;font-size: 0.42rem">缴费形式</strong></div>
            <div class="floor2">
            	<button class="duration_payment payment trigger2 hide evelMoney" controller1="summerwiner_deposit" nurseryUnit="4">期缴</button>
                <button class="term_payment payment trigger2 evelMoney" controller1="always_deposit" nurseryUnit="1" >学期缴</button>
                <button class="month_payment payment trigger2 evelMoney" controller1="always_deposit" nurseryUnit="2">月缴</button>
                <button class="day_payment payment trigger2 evelMoney" controller1="always_deposit holiday_deposit summerwiner_deposit" nurseryUnit="3" >日缴</button>
            </div>
            
            <div class="floor3" controller2="duration_payment month_payment day_payment" controller1="summerwiner_deposit">
                <img class="minus tit evelMoney" src="../img/enroll23.png" alt="" style="width: 0.8267rem;height: 0.8133rem;"/>
                <div class="months tit"><span class="payments">1</span>
                	<span class="duration_tit" controller2="duration_payment" style="display: none">期</span>
	                <span class="months_tit" controller2="month_payment">个月</span>
	                <span class="days_tit" controller2="day_payment" style="display: none">日</span>
                </div>
                <img class="plus tit evelMoney" src="../img/enroll22.png" alt="" style="width: 0.8267rem;height: 0.8133rem;"/>
                <div class="clearfix"></div>
            </div>
            <div class="floor3 hide" controller2="duration_payment" controller1="summerwiner_deposit">
                <span>5天/期，每周星期一至星期五为1期，连报2期9.5折，连报三期及以上9折</span>
            </div>
            
            <div class="hide" controller2="month_payment" >
	            <div><strong style="color: #0099d9;font-size: 0.42rem">每周天数</strong></div>
	            <div class="floor2">
	            	<button class="payment evelMoney" nurseryType="1">4~5天</button>
	                <button class="payment evelMoney" nurseryType="2">3天</button>
	                <button class="payment evelMoney" nurseryType="3">2天</button>
	            </div>
            </div>
        </div>
    </div>
    <div class="enroll2_block4">
        <div class="enroll2_block4_inner">
            <div><strong style="color: #0099d9;font-size: 0.42rem">水果加餐</strong></div>
            <div class="fees"><label><strong style="font-weight: 500">￥<span id="fruit_price"></span>元，</strong><span style="color: #9FA0A0;">￥<span id="fruit_unit_price"></span>元/次，一次预收20次费用</span></label></div>
            <div class="whether_need">
                <button class="fruit_need needs evelMoney" fruitCount="20">需要</button>
                <button class="fruit_unneed needs evelMoney" fruitCount="0">不需要</button>
            </div>
        </div>
    </div>
    <div class="enroll2_block4 enroll2_block5">
        <div class="enroll2_block4_inner">
            <div><strong style="color: #0099d9;font-size: 0.42rem">营养晚餐</strong></div>
            <div class="fees"><label><strong style="font-weight: 500">￥<span id="dinner_price"></span>元，</strong><span style="color: #9FA0A0;">￥<span id="dinner_unit_price"></span>元/次，一次预收20次费用</span></label></div>
            <div class="whether_need">
                <button class="supper_need needs evelMoney" dinnerCount="20">需要</button>
                <button class="supper_unneed needs evelMoney" dinnerCount="0">不需要</button>
            </div>
        </div>
    </div>
    <a href="tel:4000601515"><img class="tel_icon" src="../img/enroll25.png" alt=""/></a>
    <a href="http://p.qiao.baidu.com/im/index?siteid=8040317&ucid=18640471&cp=&cr=&cw="><img class="consult_icon" src="../img/enroll24.png" alt=""/></a>
</div>
<div id="enroll2_footer">
    <div class="need_pay"><strong>应付：￥<span id="money"></span></strong></div>
    <button class="enroll2_btn">现在报名</button>
</div>
<!--选择托管地点-->
<div id="sa_wrap">
    <div class="sa_opacity"></div>
    <div class="sa_block_title">
        <hr/>
        <h3>选择托管地点</h3>
        <hr class="second"/>
    </div>
    <hr style="color: lightgrey;background: lightgrey;height: 0.03rem;"/>
    <div class="sa_content" id="school_list">
    </div>
    <!--<div class="additional"></div>-->
    <button class="sa_cancel">取消</button>
</div>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/main.js?v=<%=System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script>
checkUserInfo();
init();
loadAllSchool();
var existSid = <%=user.getSid()%>;
function init(){
	$("#nurseryType").val(1);
	$("#nurseryUnit").val(1);
	$("#nurseryCount").val(1);
	$("#fruitCount").val(0);
	$("#dinnerCount").val(0);
}

//点击更换学堂
$(".change_address").click(function(){
    $('#sa_wrap').slideDown();
});

var schoolParams;

function loadAllSchool(){
	ajaxUtil(null, mainpath+"/school/listAll.do",function(response){
		var html = "";
		var list = response.list;
		var cur_sid = <%=user.getSid()%>;
		for (var i in list){
			var s = list[i];
			
			var schoolCheckBtn = "../img/sa02.png";
			
			html += "<div class='sa_block' id='sid_"+s.id+"'>";
			html += "    <div class='sa_address'>";
			html += "        <img class='sa1_radio address_radio' src='"+schoolCheckBtn+"' alt='' data='"+s.id+"'/>";
			html += "        <div class='sa_address_fr'>";
			html += "            <h5><strong class='address_title'>家学天地-"+s.name+"</strong></h5>";
			html += "            <h5 class='address_detail'>"+s.address+"</h5>";
			html += "        </div>";
			
			if (cur_sid == s.id){
				schoolParams = s.settings;
				$(".enroll2_address_title").html("家学天地-" + s.name);
				$(".enroll2_address_detail").html(s.address);
				$("#fruit_price").html(getSetting("price.20.fruit"));
				$("#dinner_price").html(getSetting("price.20.dinner"));
				$("#fruit_unit_price").html(getSetting("price.20.fruit")/20);
				$("#dinner_unit_price").html(getSetting("price.20.dinner")/20);
				schoolCheckBtn = "../img/sa01.png";
				$("#sid").val(s.id);
			}
			
			html += "        <div class='clearfix'></div>";
			html += "    </div>";
			html += "</div>";
			html += "<hr/>";
		}
		$("#school_list").html(html);
		//更换学堂地址
	    $('.address_radio').each(function () {
	        $(this).click(function () {
	        	//如果未选过学堂，处理变更
	        	var oTitle = $(this).next().find('.address_title').text();
	            var oDetail = $(this).next().find('.address_detail').text();
	            if (confirm("您选择了"+oTitle+"，选择后无法更改，确认吗？")){
	            	//更新用户学堂
	            	var sid = $(this).attr("data");
	            	ajaxUtil({sid:sid},mainpath+"/user/updateSid.do",null);
			    	if (existSid == 0){
			    		var inHtml = "<div class='floor1'>";
			    		inHtml += "<label><strong class='enroll2_address_title'></strong></label>";
			    		//inHtml += "<img class='change_address' src='../img/enroll21.png' alt=''/>";
			    		//inHtml += "<span class='change_address' id='swith_school'>选择学堂</span>";
			    		inHtml += "</div>";
		    			inHtml += "<hr/>";
		   				inHtml += "<div class='floor2'><span class='enroll2_address_detail'></span></div>";
			    		$(".enroll2_header_inner").html(inHtml);
			    	}
		            
		            $('#sa_wrap').fadeOut();
	            
	            	$('.enroll2_address_title').text(oTitle);
		            $('.enroll2_address_detail').text(oDetail);
		            $("html,body").animate({scrollTop: 0}, 500);
		            
		            existSid = parseInt(sid);
		            $("#sid").val(sid);
		            
		            ajaxUtil({sid:sid},mainpath+"/school/get.do",function(response){
		            	schoolParams = response.settings;
		            	$("#fruit_price").html(getSetting("price.20.fruit"));
						$("#dinner_price").html(getSetting("price.20.dinner"));
						$("#fruit_unit_price").html(getSetting("price.20.fruit")/20);
						$("#dinner_unit_price").html(getSetting("price.20.dinner")/20);
		            });
	            } else {
	            	
	            }
	            
	            
	        });
	    });
	    //学堂选择按钮切换
	    $(".sa1_radio").click(function(){
	    	if ($(this).attr('src') == "../img/sa02.png") {
	            $(this).attr('src', '../img/sa01.png');
	            $(this).parent().parent().siblings().find('.address_radio').attr('src', '../img/sa02.png');
	        } 
	    });
	});
	
	$(".trigger1").click(function(){
		var trigger = $(this);
		$("*[controller1]").each(function(){
			var controller = $(this).attr("controller1");
			var cs = controller.split(" ");
			var found = false;
			for (var i in cs){
				var c = cs[i];
				if (trigger.hasClass(c)){
					found = true;
					$(this).show();
					if (!hasAttr(this,"controller2")){
						//controller2不需要变更css
						$(this).css('background', '#ffffff').css('color', '#9fa0a0').css('border', '1px solid #9fa0a0')
					}
					break;
				}
			}
			if (!found){
				$(this).hide();
			}
		});
		$("*[controller2]").hide();
	});
	
	$(".trigger2").click(function(){
		var trigger = $(this);
		$("*[controller2]").each(function(){
			var controller = $(this).attr("controller2");
			var cs = controller.split(" ");
			var found = false;
			for (var i in cs){
				var c = cs[i];
				if (trigger.hasClass(c)){
					found = true;
					$(this).show();
					break;
				}
			}
			if (!found){
				$(this).hide();
			}
		});
	});
	
	
    //点击月交
    $("#enroll2_block3 .month_payment").click(function(){
        $('.payments').text('1');
        $('#nurseryCount').val('1');
    });
    //点击日交
    $("#enroll2_block2 .once_deposit").click(function(){
        $('.payments').text('1');
        $('#nurseryCount').val('1');
    });
    
    $("*[nurseryType]").click(function(){
        $('#nurseryType').val($(this).attr("nurseryType"));
    });
    
    $("*[nurseryUnit]").click(function(){
        $('#nurseryUnit').val($(this).attr("nurseryUnit"));
    });
    
    $("*[fruitCount]").click(function(){
        $('#fruitCount').val($(this).attr("fruitCount"));
    });
    
    $("*[dinnerCount]").click(function(){
        $('#dinnerCount').val($(this).attr("dinnerCount"));
    });
	
    //增加月数、日数
    $("#enroll2_block3 .plus").click(function(){
    	var oval = $('.payments').text();
        oval++;
        $('.payments').html(oval);
        $('#nurseryCount').val(oval);
    });
    //减少月数、日数
    $("#enroll2_block3 .minus").click(function(){
    	var oval = $('.payments').text();
        oval--;
        if (oval < 2) {
            oval = 1;
        }
        $('.payments').html(oval);
        $('#nurseryCount').val(oval);
    });
    //报名
    $(".enroll2_btn").click(function(){
    	evelMoney();
    	var sid = $("#sid").val();
    	var nurseryType = $("#nurseryType").val();
    	var nurseryUnit = $("#nurseryUnit").val();
    	var nurseryCount = $("#nurseryCount").val();
    	var fruitCount = $("#fruitCount").val();
    	var dinnerCount = $("#dinnerCount").val();
    	if (sid == "" || sid <= 0){
    		alert("请选择托管地点！");
    		return;
    	}
    	window.location.href="orderConfirm?sid="+sid+"&nurseryType="+nurseryType+"&nurseryUnit="+nurseryUnit+"&nurseryCount="+nurseryCount+"&fruitCount="+fruitCount+"&dinnerCount="+dinnerCount;
    });
}

$(".evelMoney").click(function(){
	evelMoney();
});

function evelMoney(){
	//获得参数
	var nurseryType = parseInt($("#nurseryType").val());
	var nurseryUnit = parseInt($("#nurseryUnit").val());
	var nurseryCount = parseInt($("#nurseryCount").val());
	var fruitCount = parseInt($("#fruitCount").val());
	var dinnerCount = parseInt($("#dinnerCount").val());
	var grade = parseInt("<%=DatetimeUtil.computeGrade(user.getInGradeDate())%>");
	var money = 0;
	//计算费用
	if (nurseryType == 1){
		//课后托4~5天
		if (grade >0 && grade<=2){
			//1~2年级
			if (nurseryUnit == 1){
				//学期交
				var price_term_nursery_45day_grade12 = getSetting("price.term.nursery.45day.grade12");
				money += price_term_nursery_45day_grade12;
			} else if (nurseryUnit == 2){
				//月交
				var price_month_nursery_45day_grade12 = getSetting("price.month.nursery.45day.grade12");
				money += price_month_nursery_45day_grade12*nurseryCount;
			} else if (nurseryUnit == 3){
				//日交
				var price_halfday_grade12 = getSetting("price.halfday.grade12");
				money += price_halfday_grade12*nurseryCount;
			}
		} else if (grade >2 && grade<=4){
			//3~4年级
			if (nurseryUnit == 1){
				//学期交
				var price_term_nursery_45day_grade34 = getSetting("price.term.nursery.45day.grade34");
				money += price_term_nursery_45day_grade34;
			} else if (nurseryUnit == 2){
				//月交
				var price_month_nursery_45day_grade34 = getSetting("price.month.nursery.45day.grade34");
				money += price_month_nursery_45day_grade34*nurseryCount;
			} else if (nurseryUnit == 3){
				//日交
				var price_halfday_grade34 = getSetting("price.halfday.grade34");
				money += price_halfday_grade34*nurseryCount;
			}
		} else if (grade >4 ){
			//5~6年级
			if (nurseryUnit == 1){
				//学期交
				var price_term_nursery_45day_grade56 = getSetting("price.term.nursery.45day.grade56");
				money += price_term_nursery_45day_grade56;
			} else if (nurseryUnit == 2){
				//月交
				var price_month_nursery_45day_grade56 = getSetting("price.month.nursery.45day.grade56");
				money += price_month_nursery_45day_grade56*nurseryCount;
			} else if (nurseryUnit == 3){
				//日交
				var price_halfday_grade56 = getSetting("price.halfday.grade56");
				money += price_halfday_grade56*nurseryCount;
			}
		} 
	} else if (nurseryType == 2){
		//课后托每周3天，只有月交有
		if (grade >0 && grade<=2){
			//1~2年级
			if (nurseryUnit == 3){
				//如果日交，则忽略每周3天的设置,在界面中可能存在点的是每周3天，但是日交的情况，特殊***
				var price_wholeday_grade12 = getSetting("price.wholeday.grade12");
				money += price_wholeday_grade12*nurseryCount;
			} else if (nurseryUnit == 1){
				//如果学期交，则忽略每周3天的设置,在界面中可能存在点的是每周3天，但是日交的情况，特殊***
				var price_term_nursery_45day_grade12 = getSetting("price.term.nursery.45day.grade12");
				money += price_term_nursery_45day_grade12;
			} else {
				var price_month_nursery_3day_grade12 = getSetting("price.month.nursery.3day.grade12");
				money += price_month_nursery_3day_grade12*nurseryCount;
			}
		} else if (grade >2 && grade<=4){
			//3~4年级
			if (nurseryUnit == 3){
				//如果日交，则忽略每周3天的设置,在界面中可能存在点的是每周3天，但是日交的情况，特殊***
				var price_wholeday_grade34 = getSetting("price.wholeday.grade34");
				money += price_wholeday_grade34*nurseryCount;
			} else if (nurseryUnit == 1){
				//如果学期交，则忽略每周3天的设置,在界面中可能存在点的是每周3天，但是日交的情况，特殊***
				var price_term_nursery_45day_grade34 = getSetting("price.term.nursery.45day.grade34");
				money += price_term_nursery_45day_grade34;
			} else {
				var price_month_nursery_3day_grade34 = getSetting("price.month.nursery.3day.grade34");
				money += price_month_nursery_3day_grade34*nurseryCount;
			}
		} else if (grade >4 ){
			//5~6年级
			if (nurseryUnit == 3){
				//如果日交，则忽略每周3天的设置,在界面中可能存在点的是每周3天，但是日交的情况，特殊***
				var price_wholeday_grade56 = getSetting("price.wholeday.grade56");
				money += price_wholeday_grade56*nurseryCount;
			} else if (nurseryUnit == 1){
				//如果学期交，则忽略每周3天的设置,在界面中可能存在点的是每周3天，但是日交的情况，特殊***
				var price_term_nursery_45day_grade56 = getSetting("price.term.nursery.45day.grade56");
				money += price_term_nursery_45day_grade56;
			} else {
				var price_month_nursery_3day_grade56 = getSetting("price.month.nursery.3day.grade56");
				money += price_month_nursery_3day_grade56*nurseryCount;
			}
		} 
	} else if (nurseryType == 3){
		//课后托每周2天，只有月交有
		if (grade >0 && grade<=2){
			//1~2年级
			if (nurseryUnit == 3){
				//如果日交，则忽略每周3天的设置,在界面中可能存在点的是每周3天，但是日交的情况，特殊***
				var price_wholeday_grade12 = getSetting("price.wholeday.grade12");
				money += price_wholeday_grade12*nurseryCount;
			} else if (nurseryUnit == 1){
				//如果学期交，则忽略每周3天的设置,在界面中可能存在点的是每周3天，但是日交的情况，特殊***
				var price_term_nursery_45day_grade12 = getSetting("price.term.nursery.45day.grade12");
				money += price_term_nursery_45day_grade12;
			} else {
				var price_month_nursery_2day_grade12 = getSetting("price.month.nursery.2day.grade12");
				money += price_month_nursery_2day_grade12*nurseryCount;
			}
		} else if (grade >2 && grade<=4){
			//3~4年级
			if (nurseryUnit == 3){
				//如果日交，则忽略每周3天的设置,在界面中可能存在点的是每周3天，但是日交的情况，特殊***
				var price_wholeday_grade34 = getSetting("price.wholeday.grade34");
				money += price_wholeday_grade34*nurseryCount;
			} else if (nurseryUnit == 1){
				//如果学期交，则忽略每周3天的设置,在界面中可能存在点的是每周3天，但是日交的情况，特殊***
				var price_term_nursery_45day_grade34 = getSetting("price.term.nursery.45day.grade34");
				money += price_term_nursery_45day_grade34;
			} else {
				var price_month_nursery_2day_grade34 = getSetting("price.month.nursery.2day.grade34");
				money += price_month_nursery_2day_grade34*nurseryCount;
			}
		} else if (grade >4 ){
			//5~6年级
			if (nurseryUnit == 3){
				//如果日交，则忽略每周3天的设置,在界面中可能存在点的是每周3天，但是日交的情况，特殊***
				var price_wholeday_grade56 = getSetting("price.wholeday.grade56");
				money += price_wholeday_grade56*nurseryCount;
			} else if (nurseryUnit == 1){
				//如果学期交，则忽略每周3天的设置,在界面中可能存在点的是每周3天，但是日交的情况，特殊***
				var price_term_nursery_45day_grade56 = getSetting("price.term.nursery.45day.grade56");
				money += price_term_nursery_45day_grade56;
			} else {
				var price_month_nursery_2day_grade56 = getSetting("price.month.nursery.2day.grade56");
				money += price_month_nursery_2day_grade56*nurseryCount;
			}
		} 
	} else if (nurseryType == 5){
		//寒暑假托，只有期交和日交
		if (grade >0 && grade<=2){
			//1~2年级
			if (nurseryUnit == 3){
				//日交
				var price_wholeday_grade12 = getSetting("price.wholeday.grade12");
				money += price_wholeday_grade12*nurseryCount;
			} else if (nurseryUnit == 4){
				//期交
				var price_summerwinter_duration_grade12 = getSetting("price.summerwinter.duration.grade12");
				if (nurseryCount == 1){
					money += price_summerwinter_duration_grade12*nurseryCount;
				} else if (nurseryCount == 2){
					//连报两期折扣
					var discount_2duration = getSetting("discount.2duration");
					money += price_summerwinter_duration_grade12*nurseryCount*discount_2duration;
				} else {
					//连报三期以上折扣
					var discount_3duration = getSetting("discount.3duration");
					money += price_summerwinter_duration_grade12*nurseryCount*discount_3duration;
				}
			} 
			
		} else if (grade >2 && grade<=4){
			//3~4年级
			if (nurseryUnit == 3){
				//日交
				var price_wholeday_grade34 = getSetting("price.wholeday.grade34");
				money += price_wholeday_grade34*nurseryCount;
			} else if (nurseryUnit == 4){
				//期交
				var price_summerwinter_duration_grade34 = getSetting("price.summerwinter.duration.grade34");
				if (nurseryCount == 1){
					money += price_summerwinter_duration_grade34*nurseryCount;
				} else if (nurseryCount == 2){
					//连报两期折扣
					var discount_2duration = getSetting("discount.2duration");
					money += price_summerwinter_duration_grade34*nurseryCount*discount_2duration;
				} else {
					//连报三期以上折扣
					var discount_3duration = getSetting("discount.3duration");
					money += price_summerwinter_duration_grade34*nurseryCount*discount_3duration;
				}
			} 
		} else if (grade >4 ){
			//5~6年级
			if (nurseryUnit == 3){
				//日交
				var price_wholeday_grade56 = getSetting("price.wholeday.grade56");
				money += price_wholeday_grade56*nurseryCount;
			} else if (nurseryUnit == 4){
				//期交
				var price_summerwinter_duration_grade56 = getSetting("price.summerwinter.duration.grade56");
				if (nurseryCount == 1){
					money += price_summerwinter_duration_grade56*nurseryCount;
				} else if (nurseryCount == 2){
					//连报两期折扣
					var discount_2duration = getSetting("discount.2duration");
					money += price_summerwinter_duration_grade56*nurseryCount*discount_2duration;
				} else {
					//连报三期以上折扣
					var discount_3duration = getSetting("discount.3duration");
					money += price_summerwinter_duration_grade56*nurseryCount*discount_3duration;
				}
			} 
		} else if (nurseryType == 6){
			//周末托，只有日交
			if (grade >0 && grade<=2){
				//1~2年级
				var price_wholeday_grade12 = getSetting("price.wholeday.grade12");
				money += price_wholeday_grade12*nurseryCount;
			} else if (grade >2 && grade<=4){
				//3~4年级
				var price_wholeday_grade34 = getSetting("price.wholeday.grade34");
				money += price_wholeday_grade34*nurseryCount;
			} else if (grade >4 ){
				//5~6年级
				var price_wholeday_grade56 = getSetting("price.wholeday.grade56");
				money += price_wholeday_grade56*nurseryCount;
			} 
		}
	}
	
	if (fruitCount>0){
		money += getSetting("price.20.fruit")
	}
	if (dinnerCount>0){
		money += getSetting("price.20.dinner")
	}
	$("#money").html(money);
}


function checkUserInfo(){
	//检查个人信息完整度
	var noFillItem = "<%=BeanUtil.noFillUserInfo(user)%>";
	if (!isEmpty(noFillItem)){
		alert("你的以下人个信息尚未补充完整，请前往补充！" + noFillItem);
		window.location.href="editInfo";
	}
}

function getSetting(name){
	for (var i in schoolParams){
		var p = schoolParams[i];
		if (p.name == name){
			var v = p.value;
			if (v.contains(".")){
				return parseFloat(p.value);
			} else {
				return parseInt(p.value);
			}
		}
	}
	return "";
}
</script>
</body>
</body>
</html>