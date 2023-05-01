/**
 * Created by yanghl on 2018/10/6.
 */

//页面加载
$('body').show();
$('.version').text(NProgress.version);
NProgress.start();
setTimeout(function () {
    NProgress.done();
    $('.fade').removeClass('out');
}, 1000);
//返回顶部按钮
$(function () {
    $(window).scroll(function () {
        if ($(window).scrollTop() > 100) {
            $(".gotop").fadeIn();
        }
        else {
            $(".gotop").hide();
        }
    });
    $(".gotop").click(function () {
        $('html,body').animate({'scrollTop': 0}, 500);
    });
});
//提示插件启用
$(function () {
    $('[data-toggle="popover"]').popover();
});
$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});
//鼠标滑过显示 滑离隐藏
$(function () {
    $(".carousel").hover(function () {
        $(this).find(".carousel-control").show();
    }, function () {
        $(this).find(".carousel-control").hide();
    });
});
$(function () {
    $(".hot-content ul li").hover(function () {
        $(this).find("h3").show();
    }, function () {
        $(this).find("h3").hide();
    });
});
//页面元素智能定位
$.fn.smartFloat = function () {
    var position = function (element) {
        var top = element.position().top; //当前元素对象element距离浏览器上边缘的距离
        var pos = element.css("position"); //当前元素距离页面document顶部的距离
        $(window).scroll(function () { //侦听滚动时
            var scrolls = $(this).scrollTop();
            if (scrolls > top) { //如果滚动到页面超出了当前元素element的相对页面顶部的高度
                if (window.XMLHttpRequest) { //如果不是ie6
                    element.css({ //设置css
                        position: "fixed", //固定定位,即不再跟随滚动
                        top: 0 //距离页面顶部为0
                    }).addClass("shadow"); //加上阴影样式.shadow
                } else { //如果是ie6
                    element.css({
                        top: scrolls  //与页面顶部距离
                    });
                }
            } else {
                element.css({ //如果当前元素element未滚动到浏览器上边缘，则使用默认样式
                    position: pos,
                    top: top
                }).removeClass("shadow");//移除阴影样式.shadow
            }
        });
    };
    return $(this).each(function () {
        position($(this));
    });
};
//启用页面元素智能定位
$(function () {
    $("#search").smartFloat();
});
//异步加载更多内容
jQuery("#pagination a").on("click", function () {
    var _url = jQuery(this).attr("href");
    var _text = jQuery(this).text();
    jQuery(this).attr("href", "javascript:viod(0);");
    jQuery.ajax(
        {
            type: "POST",
            url: _url,
            success: function (data) {
                //将返回的数据进行处理，挑选出class是news-list的内容块
                result = jQuery(data).find(".row .news-list");
                //newHref获取返回的内容中的下一页的链接地址
                nextHref = jQuery(data).find("#pagination a").attr("href");
                jQuery(this).attr("href", _url);
                if (nextHref != undefined) {
                    jQuery("#pagination a").attr("href", nextHref);
                }
                else {
                    jQuery("#pagination a").html("下一页没有了").removeAttr("href")
                }
                // 渐显新内容
                jQuery(function () {
                    jQuery(".row").append(result.fadeIn(300));
                    jQuery("img").lazyload(
                        {
                            effect: "fadeIn"
                        });
                });
            }
        });
    return false;
});

/*获取日期*/
function getDateWeek(){
    var todayDate = new Date();
    var date = todayDate.getDate();
    var month= todayDate.getMonth() +1;
    var year= todayDate.getYear();
    var dateweek = "";
    if(navigator.appName == "Netscape"){
        dateweek = dateweek + (1900+year) + "年" + month + "月" + date + "日 ";
    }

    if(navigator.appVersion.indexOf("MSIE") != -1){
        dateweek = dateweek + year + "年" + month + "月" + date + "日 ";
    }

    switch(todayDate.getDay()){
        case 0:dateweek += "星期日";break;
        case 1:dateweek += "星期一";break;
        case 2:dateweek += "星期二";break;
        case 3:dateweek += "星期三";break;
        case 4:dateweek += "星期四";break;
        case 5:dateweek += "星期五";break;
        case 6:dateweek += "星期六";break;
    }
    return dateweek;
}
// document.write(getDateWeek());
document.getElementById('widget-date').innerHTML = getDateWeek();

$(function () {
    $('#register_form').submit(function (e) {
        e.preventDefault();
        var userName = $.trim($('#registerUserName').val());
        var nickName = $.trim($('#registerNickName').val());
        var password = $.trim($('#registerPassword').val());
        var confirmPassword = $.trim($('#registerConfirmPassword').val());
        if(userName == null || userName == ''){
            layer.msg("用户名不能为空！", {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
            $("#registerUserName").focus();
            return;
        }
        if(nickName == null || nickName == ''){
            layer.msg("昵称不能为空！", {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
            $("#registerNickName").focus();
            return;
        }
        if(password == null || password == ''){
            layer.msg("密码不能为空！", {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
            $("#registerPassword").focus();
            return;
        }
        if(confirmPassword == null || confirmPassword == ''){
            layer.msg("确认密码不能为空！", {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
            $("#registerConfirmPassword").focus();
            return;
        }
        if(confirmPassword!=password){
            layer.msg("确认密码必须和密码一致！", {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
            $("#registerConfirmPassword").focus();
            return;
        }
        var data = {'userName':userName,'nickName':nickName,'password':password};
        $.ajax({
            url: '/signup',
            type: "POST",
            dataType: "JSON",
            contentType: "application/json",
            data: JSON.stringify(data),
            beforeSend: function () {
                // 禁用按钮防止重复提交
                $("#register_submit").attr({disabled: "disabled"});
            },
            success: function (data) {
                if (data.code == "200") {
                    layer.msg(data.message, {icon: 1, time: 2000, shade: [0.5, '#393D49']});
                    $("#registerModal").modal("hide");
                } else if (data.code == "403") {
                    $("#verifyCodeUrl").attr('src', "/captcha.jpg?t=" + $.now());
                    $("#registerModal").modal("show");
                }
                else {
                    layer.msg(data.message, {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
                    window.location.href = data.data
                }
                $("#register_submit").removeAttr("disabled");

            },
            complete: function () {
                $("#register_submit").removeAttr("disabled");
            },
            error: function (data) {
                layer.msg(data.message, {icon: 5, time: 2000});
                $("#register_submit").removeAttr("disabled");
            }
        });
    });


    $('#login_form').submit(function (e) {
        e.preventDefault();
        var userName = $.trim($('#loginUserName').val());
        var password = $.trim($('#loginPassword').val());
        if(userName == null || userName == ''){
            layer.msg("用户名不能为空！", {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
            $("#registerUserName").focus();
            return;
        }
        if(password == null || password == ''){
            layer.msg("密码不能为空！", {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
            $("#registerPassword").focus();
            return;
        }
        var data = {'userName':userName,'password':password};
        $.ajax({
            url: '/signin',
            type: "POST",
            dataType: "JSON",
            contentType: "application/json",
            data: JSON.stringify(data),
            beforeSend: function () {
                // 禁用按钮防止重复提交
                $("#login_submit").attr({disabled: "disabled"});
            },
            success: function (data) {
                if (data.code == "200") {
                    // layer.msg(data.message, {icon: 1, time: 2000, shade: [0.5, '#393D49']});
                    // $("#loginModal").modal("hide");
                    // $('.login img').attr('src',data.data.avatar==null?'/static/images/icon/icon.png':data.data.avatar);
                    // $('.login p').html(data.data.userName);
                    // $('.unlogin').hide();
                    // $('.login').show();
                    window.location.reload();
                } else {
                    layer.msg(data.message, {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
                }
                $("#login_submit").removeAttr("disabled");

            },
            complete: function () {
                $("#login_submit").removeAttr("disabled");
            },
            error: function (data) {
                layer.msg(data.message, {icon: 5, time: 2000});
                $("#login_submit").removeAttr("disabled");
            }
        });
    })
});