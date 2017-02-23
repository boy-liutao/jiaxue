/**
 * Created by sunqi on 16/2/29.
 */
$('document').ready(function () {
    //验证手机号
    $(".tel").blur(function() {
        var tel = $(this).val();
        var reg = /^1\d{10}$/;
        if (!reg.test(tel)) {
            alert("请输入正确的手机号");
            return false;
        }
    });

    //点击获取验证码及验证码的有效时间是5分钟
    $(function(){
        /*仿刷新：检测是否存在cookie*/
        if($.cookie("captcha")){
            var count = $.cookie("captcha");
            var btn = $('.veri_btn');
            btn.val('重新获取('+count+')').attr('disabled',true).css('cursor','not-allowed');
            var resend = setInterval(function(){
                count--;
                if (count > 0){
                    btn.val(count+'秒后可重新获取').attr('disabled',true).css('cursor','not-allowed');
                    $.cookie("captcha", count, {path: '/', expires: (1/86400)*count});
                }else {
                    clearInterval(resend);
                    btn.val("获取验证码").removeClass('disabled').removeAttr('disabled style');
                }
            }, 1000);
        }

        /*点击改变按钮状态，已经简略掉ajax发送短信验证的代码*/
        $('.veri_btn').click(function(){
            var btn = $(this);
            var count = 60;
            var resend = setInterval(function(){
                count--;
                if (count > 0){
                    btn.val('重新获取('+count+')');
                    $.cookie("captcha", count, {path: '/', expires: (1/86400)*count});
                }else {
                    clearInterval(resend);
                    btn.val("获取验证码").removeAttr('disabled style');
                }
            }, 1000);
            btn.attr('disabled',true).css('cursor','not-allowed');
        });
    });

    //点击领取见面礼，跳转
    $('.receive_btn').bind('click', function () {
        var tel = $('.tel').val();
        var reg = /^1\d{10}$/;
        if (!reg.test(tel)) {
            alert("请输入手机号");
            return false;
        }
        $('.s_wrap').css('display','none');
        $('.mask_content').css('display','block');
    });

    //关闭选择上课方式页面
    $('.close').click(function () {
        $('.s_wrap').css('display','block');
        $('.mask_content').css('display','none');
    });

    //验证学习阶段和希望的上课方式,验证成功后点击提交信息，提交成功后弹出层
    $('.submit_btn').bind('click', function () {
        var _grade = $('input:radio[name="grade"]:checked').val();
        if(_grade == null){
            alert("请选择学习阶段!");
            return false;
        }
        var _hope = $('input:radio[name="hope"]:checked').val();
        if(_hope == null){
            alert("请选择您希望的上课方式!");
            return false;
        }
        $('.mask_bg').css('display','block');
        $('.mask_con').css('display','block');
    });


});