<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <title>${chapterVo.title}</title>
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
            <header class="news_header">
                <h2 class="title">
                    <a href="javascript:void(0);">网站公告</a>
                    <a href="/notice/info/${notice.id}.html"><small>${notice.title}</small></a>
                </h2>
            </header>
            <div class="content-block new-content">
                <div class="row comments">
                    <header class="notice-header">
                        <h1 class="notice-title"><a href="/notice/info/${notice.id}.html">${notice.title}</a></h1>
                        <div class="notice-meta">
                            <span>
                                ${notice.createTime}
                            </span>
                        </div>
                    </header>
                    <article class="notice-content">
                        ${notice.content}
                    </article>
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

</script>
</body>
</html>
