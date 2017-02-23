/**
 * Created by sunqi on 16/3/29.
 */
$('document').ready(function () {
    $('body').on('click','.close',function () {
        $('.footer').hide();
    });
    $('body').on('click','.download', function () {
        //判断是否是微信内置浏览器打开
        var ua = navigator.userAgent.toLowerCase();
        if(ua.match(/MicroMessenger/i)=="micromessenger") {
            window.location='http://dwz.cn/app-X';
            return true;
        }
        /*--------- 判断是否安装了学吗app ---------*/
        if (navigator.userAgent.match(/(iPhone|iPod|iPad);?/i)) {
            var loadDateTime = new Date();
            window.setTimeout(function() {
                var timeOutDateTime = new Date();
                if (timeOutDateTime - loadDateTime < 5000) {
                    window.location = "https://itunes.apple.com/cn/app/xue-ma-you-zhi-jiao-yu-dian/id1004966607?l=en&mt=8";
                } else {
                    window.close();
                }
            },25);
            window.location = "com.jxtd.xuema://123";
        } else if (navigator.userAgent.match(/android/i)) {
            var loadDateTime = new Date();
            window.setTimeout(function() {
                var timeOutDateTime = new Date();
                if (timeOutDateTime - loadDateTime < 5000) {
                    window.location = "http://openbox.mobilem.360.cn/index/d/sid/3027071";
                } else {
                    window.close();
                }
            },25);
            window.location = "xuema";
        }
    });
    $('body').on('click','.reserveBtn', function () {
        window.location.href = "http://www.shishengclub.com/xuema_tg";
    });
});