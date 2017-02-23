/**
 * Created by sunqi on 16/4/13.
 */
/*** 首页 ***/
//轮播图
var bannerItem = $('#adver_inner .adver'),
    olis = $('#adver_btn li'),
    lastIndex = 0,
    curIndex = 0,
    maxIndex = bannerItem.length - 1;
bannerItem.hide();
bannerItem.eq(0).show();
var Focus = null;
var step = 0;
clearInterval(Focus);
function focus(index){
    if(index === undefined){
        index = lastIndex + 1;
    }
    if(index > maxIndex){
        index = 0;
    }
    if(index < 0){
        index = maxIndex;
    }
    bannerItem.eq(lastIndex).hide();
    olis.eq(lastIndex).removeClass('adver_current');
    bannerItem.eq(index).show();
    olis.eq(index).addClass('adver_current');
    lastIndex = index;
    curIndex = index;
}
Focus = setInterval(focus,2000);

$(function () {
    /*** 编辑资料页 ***/
    //点击年级显示高亮
    $('.grade').each(function () {
        $(this).click(function () {
            $(this).css('background', '#0099d9').css('color', '#ffffff').css('border', 'none');
            $(this).siblings('div').css('background', '#ffffff').css('color', '#9FA0A0').css('border', '1px solid #9FA0A0');
            $("#grade").val($(this).html());
        })
    });
    //性别选择按钮切换
    $(".male_radio_img").click(function(){
    	if ($(this).attr('src', '../img/edit02.png')) {
            $(this).attr('src', '../img/edit03.png');
            $(this).next().next().attr('src', '../img/edit02.png');
            $("#sex").val(1);
        }
    });
    $(".edit_fr_inp .male").click(function(){
    	if ($(this).prev().attr('src', '../img/edit02.png')) {
            $(this).prev().attr('src', '../img/edit03.png');
            $(this).next().attr('src', '../img/edit02.png');
            $("#sex").val(1);
        }
    });
    
    $(".female_radio_img").click(function(){
    	if ($(this).attr('src', '../img/edit02.png')) {
            $(this).attr('src', '../img/edit03.png')
            $(this).prev().prev().attr('src', '../img/edit02.png');
            $("#sex").val(2);
        }
    });
    $(".edit_fr_inp .female").click(function(){
    	if ($(this).prev().attr('src', '../img/edit02.png')) {
            $(this).prev().attr('src', '../img/edit03.png')
            $(this).prev().prev().prev().attr('src', '../img/edit02.png');
            $("#sex").val(2);
        }
    });
    //点击解除绑定
    $(".edit_unbind").click(function(){
    	$('#unbind_popup_mask').fadeIn();
        $('#unbind_popup_mask').delay(1500).fadeOut(1000);
        //setTimeout(function () {
        //    $('#unbind_popup_mask').fadeOut();
        //},2000);
    });
    /*
    //点击更换头像
    $('body').on('click', '.update_avatar', function () {
        $('#editinfo_avatar_mask').slideDown('fast', function () {
            $('#avatar_popup_mask').fadeIn();
        });
    });
    /*** 家学报名 ***/
    //点击显示高亮
    $(".enroll2_block2_inner .mode,.enroll2_block3_inner .payment,.enroll2_block4_inner .needs").click(function(){
    	$(this).css('background', '#0099d9').css('color', '#ffffff').css('border', 'none');
        $(this).siblings().css('background', '#ffffff').css('color', '#9fa0a0').css('border', '1px solid #9fa0a0');
    });

    
    //点击取消选择上课地址
    $(".sa_cancel").click(function(){
    	$('#sa_wrap').slideUp();
        $("html,body").animate({scrollTop: 0}, 500);
    });
    //点击取消优惠券选择
    $(".coupon_cancel").click(function(){
    	$('#coupon_wrap').slideUp();
        $("html,body").animate({scrollTop: 0}, 500);
    });
    /*** 选择上课地址 ***/
    //课堂选择按钮切换
    $(".sa1_radio").click(function(){
    	if ($(this).attr('src', '../img/sa02.png')) {
            $(this).attr('src', '../img/sa01.png');
            $(this).parent().parent().next().next().find('img').attr('src', '../img/sa02.png');
        }
    });
    $(".sa2_radio").click(function(){
    	if ($(this).attr('src', '../img/sa02.png')) {
            $(this).attr('src', '../img/sa01.png')
            $(this).parent().parent().prev().prev().find('img').attr('src', '../img/sa02.png');
        }
    });

    
    /*** 订单详情 ***/
    var orderStatus = $('#orderinfo_wrap .order_status').text();
    if (orderStatus == '未支付') {
        $('#coupon').show();
        $('#payment_block').show();
    }
    if (orderStatus == '退费中') {
        $('#orderinfo_refund').show();
    }
    if (orderStatus == '已退费') {
        $('#orderinfo_refund').show();
    }
    if (orderStatus == '已结束') {
        $('.purchase_again').show();
    }
    
    $('.oc_changeAddress,.oc_arrow').click(function () {
        $('#sa_wrap').slideDown();
    });

    /*** i家学——教师版 ***/
    //点击输入框
    /*
    $('.initial_search_inp').click(function () {
        $(this).animate({
            width:'8.16rem',
        },200);

        $('#search_content').slideDown();
        $('#teacher_nav').hide();
        $('#teacher_content').hide();

    });
    */
    //点击清除按钮
    $('.histroy_clear').click(function () {
    	$('.history_record').empty();
    });


});