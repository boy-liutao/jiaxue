/**
 * Created by sunqi on 16/8/25.
 */
window.onload = function () {
    var oNavbar = document.getElementById('navbar');
    var rNav = document.getElementById('nav_right');
    var goTop = document.getElementById("goTop");
    function getWin(attr) {
        return document.documentElement[attr] || document.body[attr];
    }
    var timer = null;
    goTop.onclick = function move() {
        goTop.style.display = "none";
        window.onscroll = null;
        window.clearTimeout(timer);
        //在IE下使用的是document.documentElement
        document.documentElement.scrollTop -= 100;
        //在谷歌下使用的是document.body
        document.body.scrollTop -= 100;
        if(getWin("scrollTop") !== 0){
            //arguments.callee -->move
            //arguments.caller -->move()
            timer = window.setTimeout(arguments.callee,10);
        }else{
            window.onscroll = scrollFn;
        }
    };
    function scrollFn() {
        var curTop = getWin("scrollTop");
        //导航
        if(curTop > 120){
            oNavbar.style.background = "white";
        }else{
            oNavbar.style.background = "transparent";
        }
        rNav.style.display = curTop > 400 ? "block" : "none";
        goTop.style.display = curTop > 1000 ? "block" : "none";
    }
    window.onscroll = scrollFn;
};
//banner轮播
jQuery(document).ready(function($) {
    /**
     * identifier variable must be unique ID
     */
    var sangar = $('#sangar-example').sangarSlider({
        animation : 'fade', // horizontal-slide, vertical-slide, fade
        animationSpeed : 1000, // how fast animtions are
        timer : true, // true or false to have the timer
        width : 1580, // slideshow width
        height : 600 // slideshow height
    });
});
//    相册展示
$('#gallery1').imagesGrid({
    images: [
        'album/1.jpg',
        { src: 'album/2.jpg', alt: 'Second image', title: 'Second image' },
        'album/3.jpg',
//            { src: 'img/4.jpg', caption: 'Beautiful forest' },
        'album/4.jpg',
        'album/5.jpg',
        'album/6.jpg',
        { src: 'album/8.jpg', alt: 'Second image', title: 'Second image' },
        'album/11.jpg'
//            { src: 'album/13.jpg'}
    ]
});
$('#gallery1').click(function () {
    $('#nav_right').fadeOut();
});

$(function () {
    //导航点击高亮
    $('.navbar-nav li').each(function () {
        $(this).click(function () {
            $(this).addClass('active').siblings('li').removeClass('active');
        });
    });
    $('#tab-ul li').click(function () {
        $(this).children('p').toggle();
        if($(this).children('p').css('display') == 'block'){
            $(this).children('h4').css('color','#0599D9');
            $(this).find('.tab6_img').addClass('rotate');
        }else{
            $(this).children('h4').css('color','#595757');
            $(this).find('.tab6_img').removeClass('rotate');
        }
    });
    //滑动关闭按钮
    $('#close_btn').click(function () {
        $('#register_mask').slideUp();
    });
    $('#book_arrive').click(function () {
        if($('#register_mask').css('display') == "block"){
            $('#register_mask').slideUp();
        }else{
            $('#register_mask').slideDown();
        }
    });
    //选择学堂
    $('.school_lab').each(function () {
        $(this).click(function () {
//                alert($(this).text());
            $(this).css('color','#0599D9').siblings('label').css('color','#595757');
        });
    });

    //tab2习惯力养成 转盘轮动
    var index = 0;
    var timer = null;
    var timeout = false;
    $('.tab1_tit').eq(0).css('color','#595757');
    function roll(){
        index++;
        if(index == 5){
            index = -1;
        }
        //if(timeout){
        //    return;
        //}
        window.clearTimeout(timer);
        $('.tab1_tit').eq(index).css('color','#595757').addClass('enlarge_tit').siblings('h4').css('color','#9FA0A0').removeClass('enlarge_tit');
        $('.tab1_icon').eq(index).addClass('enlarge').siblings('img').removeClass('enlarge');
        window.setTimeout(roll,1500);
    };
    roll();

    //$('.tab1_icon').each(function () {
    //    $(this).mouseover(function () {
    //        timeout = true;
    //        index = $('.tab1_icon').index(this);
    //        window.clearTimeout(timer);
    //        $('.tab1_tit').eq(index).css('color','#595757').addClass('enlarge_tit').siblings('h4').css('color','#9FA0A0').removeClass('enlarge_tit');
    //        $('.tab1_icon').eq(index).addClass('enlarge_over').siblings('img').removeClass('enlarge_over');
    //        $('.tab1_icon').eq(index).siblings('img').removeClass('enlarge');
    //    });
    //    $(this).mouseout(function () {
    //        index = $('.tab1_icon').index(this);
    //        //roll();
    //        $('.tab1_icon').eq(index).removeClass('enlarge_over');
    //    });
    //});
});
$("#sumit_btn").click(function(){
	applyVisit();
	});
function applyVisit(){
	var u_name = $("#u_name").val();
	var u_phone = $("#u_phone").val();
	var u_date = $("#u_date").val();
	var u_sex = $("input[name='sex']:checked").val();
	var u_school = $("input[name='school']:checked").val();
	if (u_name == ""){
		alert("请输入姓名！");
		return;
	}
	if (u_sex == undefined){
		alert("请输入性别！");
		return;
	}
	if (u_phone == ""){
		alert("请输入电话！");
		return;
	}
	if (u_date == ""){
		alert("请输入到访日期！");
		return;
	}
	if (u_school == undefined){
		alert("请选择到访学堂！");
		return;
	}
	$.getScript('http://zuo.shishengclub.com/jiaxue/visit/add.do?name='+u_name+'&phone='+u_phone+'&arrivingTime='+u_date+'&sex='+u_sex+'&schoolName='+u_school+'&callback=showSucc');
	
}
function showSucc(r){
	if (r.code == 1){
		alert("预约成功！");
	} else {
		alert("预约失败！");
	}
}