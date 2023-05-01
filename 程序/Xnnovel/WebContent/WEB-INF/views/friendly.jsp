<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <title>友情链接</title>
    <meta name="Keywords" content=""/>
    <meta name="description" content=""/>
    <%@include file="/static/comm/front/taglib.jsp" %>
</head>

<body>
<section class="container user-select">
    <%@include file="/static/comm/front/left.jsp" %>
    <!--/超小屏幕可见-->
    <div class="content-wrap"><!--内容-->
        <div class="content">
            <div class="content-block friendly-content row">
                <h2 class="title"><strong>本站友链</strong></h2>
                <div class="col-md-4 col-xs-6">
                    <span data-toggle="tooltip" data-placement="bottom" title="点击进入 项目设计 站点">
                        <a href="http://www.baidu.com">项目设计</a></span>
                    <p>最专业的源码毕业设计分享网站</p>
                </div>
                <div class="col-md-4 col-xs-6"><span data-toggle="tooltip" data-placement="bottom" title="点击进入 哔哩哔哩 站点"><a
                        href="https://www.baidu.com/385794766/">哔哩哔哩</a></span>
                    <p>国内知名的视频弹幕网站</p>
                </div>
                <div class="col-md-4 col-xs-6">
                    <span data-toggle="tooltip" data-placement="bottom" title="点击进入 本站  站点">
                        <a href="/index.html">牛码小说网</a></span>
                    <p>免费小说阅读网站</p>
                </div>
            </div>
        </div>
    </div>
    <!--/内容-->
    <!--/内容-->
    <%@include file="/static/comm/front/right.jsp" %>
    <!--/右侧>992px显示-->
    <footer class="footer">POWERED BY &copy;<a href="http://www.baidu.com">项目设计 baidu.com</a> ALL RIGHTS RESERVED &nbsp;&nbsp;&nbsp;<span><a
            href="http://www.baidu.com" target="_blank">粤ICP备xxxx号</a></span> <span
            style="display:none"><a href="">网站统计</a></span></footer>
</section>
<div><a href="javascript:;" class="gotop" style="display:none;"></a></div>
<!--/返回顶部-->
<script type="text/javascript" src="/static/js/front/common.js"></script>
</body>
</html>
