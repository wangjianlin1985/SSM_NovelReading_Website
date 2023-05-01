<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
    <title>个人中心</title>
    <meta name="Keywords" content=""/>
    <meta name="description" content=""/>
    <%@include file="/static/comm/front/taglib.jsp" %>
</head>

<body>
<section class="container user-select">
    <header>
        <div class="hidden-xs header"><!--超小屏幕不显示-->
            <h1 class="logo">
                <a href="/" title="项目设计"></a></h1>
            <ul class="nav hidden-xs-nav">
                <li><a href="/"><span class="glyphicon glyphicon-home"></span>首页</a></li>
                <li><a href="/user/profile.html"><span class="glyphicon glyphicon-erase"></span>我的资料</a></li>
                <li><a href="/user/shelf.html"><span class="glyphicon glyphicon-erase"></span>我的书架</a></li>
                <li><a href="/user/favors.html"><span class="glyphicon glyphicon-inbox"></span>我的收藏</a></li>
                <li><a href="/user/recoms.html"><span class="glyphicon glyphicon-globe"></span>我的推荐</a></li>
                <li><a href="/user/password.html"><span class="glyphicon glyphicon-globe"></span>修改密码</a></li>
                <li><a href="/signout"><span class="glyphicon glyphicon-globe"></span>退出系统</a></li>
            </ul>
            <div class="feeds">
                <a class="feed feed-dnzm" href="http://www.baidu.com" target="_blank"><i></i>项目设计</a>
                <a class="feed feed-weixin" data-toggle="popover" data-trigger="hover" title="微信扫一扫" data-html="true"
                   data-content="<img src='/static/images/weixin.png' alt=''>" href="javascript:;"
                   target="_blank"><i></i>关注微信
                </a>
                <a class="feed feed-bilibili" href="https://www.baidu.com/385794766/" target="_blank"><i></i>哔哩哔哩</a>
                <a class="feed feed-rss" href="/index.html" target="_blank"><i></i>订阅本站</a>

            </div>
            <div class="wall"><a href="/friendly.html" target="_blank">友情链接</a> | <a href="/tags.html" target="_blank">标签云</a>
            </div>

        </div>
        <!--/超小屏幕不显示-->
        <div class="visible-xs header-xs"><!--超小屏幕可见-->
            <div class="navbar-header header-xs-logo">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#header-xs-menu" aria-expanded="false" aria-controls="navbar"><span
                        class="glyphicon glyphicon-menu-hamburger"></span></button>
            </div>
            <div id="header-xs-menu" class="navbar-collapse collapse">
                <ul class="nav navbar-nav header-xs-nav">
                    <li class="active"><a href="index.html"><span class="glyphicon glyphicon-home"></span>网站首页</a></li>
                    <li><a href=""><span class="glyphicon glyphicon-erase"></span>网站前端</a></li>
                    <li><a href=""><span class="glyphicon glyphicon-inbox"></span>后端技术</a></li>
                    <li><a href=""><span class="glyphicon glyphicon-globe"></span>管理系统</a></li>
                    <li><a href="about.html"><span class="glyphicon glyphicon-user"></span>关于我们</a></li>
                    <li><a href="friendly.html"><span class="glyphicon glyphicon-tags"></span>友情链接</a></li>
                </ul>
                <form class="navbar-form" action="search.php" method="post" style="padding:0 25px;">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="请输入关键字">
                        <span class="input-group-btn">
            <button class="btn btn-default btn-search" type="submit">搜索</button>
            </span></div>
                </form>
            </div>
        </div>
    </header>
    <!--/超小屏幕可见-->
    <div class="content-wrap"><!--内容-->
        <div class="content">
            <header class="news_header">
                <h2 class="title">
                    <a href="/novel.html?type=3">个人中心</a>
                    <a href="/novel/info/${novel.id}.html"><small>我的资料</small></a>
                </h2>
            </header>
            <div class="content-block new-content">
                <h2 class="title"><strong>全部章节</strong></h2>
                <div class="row">
                    <div class="table-responsive">
                        <table class="table table-bordered table-striped">
                            <tbody>
                            <c:forEach  begin="1" end="${chapterList.size()/3+1}" step="1" varStatus="vs">
                                <tr>
                                    <c:forEach var="chapter" items="${chapterList}" begin="${vs.count*3-3}" end="${vs.count*3-1}" step="1">
                                        <td><a href="/chapter/info/${chapter.id}">${chapter.title}</a></td>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </div>

            </div>

        </div>
    </div>
    <!--/内容-->
    <%@include file="/static/comm/front/right.jsp" %>
    <!--/右侧>992px显示-->
    <footer class="footer">POWERED BY &copy;<a href="http://www.baidu.com">项目设计 baidu.com</a> ALL RIGHTS RESERVED &nbsp;&nbsp;&nbsp;<span><a
            href="http://www.baidu.com" target="_blank">粤ICP备xxxx号</a></span> <span
            style="display:none"><a href="">网站统计</a></span></footer>
</section>
<div><a href="javascript:;" class="gotop" style="display:none;"></a></div>
<!--/返回顶部-->
<script src="/static/js/front/common.js" type="text/javascript"></script>
<script type="text/javascript">
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
</script>
</body>
</html>
