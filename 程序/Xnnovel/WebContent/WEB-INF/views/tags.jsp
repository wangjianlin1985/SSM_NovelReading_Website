<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <title>标签云</title>
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
            <div class="row tags-content content-block">
                <h2 class="title"><strong>本站标签</strong></h2>
                <div class="col-xs-3">
                    <span class="tags" data-toggle="tooltip" data-placement="bottom" title="查看关于 玄幻魔法 的小说">
                        <a href="">玄幻魔法</a>
                    </span>
                    <p>查看关于玄幻魔法的小说</p>
                </div>
                <div class="col-xs-3">
                    <span class="tags" data-toggle="tooltip" data-placement="bottom" title="查看关于 武侠修真 的小说">
                        <a href="">武侠修真</a>
                    </span>
                    <p>查看关于武侠修真的小说</p>
                </div>
                <div class="col-xs-3">
                    <span class="tags" data-toggle="tooltip" data-placement="bottom" title="查看关于 都市言情 的小说">
                        <a href="">都市言情</a>
                    </span>
                    <p>查看关于都市言情的小说</p>
                </div>
                <div class="col-xs-3">
                    <span class="tags" data-toggle="tooltip" data-placement="bottom" title="查看关于 历史军事 的小说">
                        <a href="">历史军事</a>
                    </span>
                    <p>查看关于历史军事的小说</p>
                </div>
                <div class="col-xs-3">
                    <span class="tags" data-toggle="tooltip" data-placement="bottom" title="查看关于 网游竞技 的小说">
                        <a href="">网游竞技</a>
                    </span>
                    <p>查看关于网游竞技的小说</p>
                </div>
                <div class="col-xs-3">
                    <span class="tags" data-toggle="tooltip" data-placement="bottom" title="查看关于 科幻小说 的小说">
                        <a href="">科幻小说</a>
                    </span>
                    <p>查看关于科幻小说的小说</p>
                </div>
                <div class="col-xs-3">
                    <span class="tags" data-toggle="tooltip" data-placement="bottom" title="查看关于 恐怖灵异 的小说">
                        <a href="">恐怖灵异</a>
                    </span>
                    <p>查看关于恐怖灵异的小说</p>
                </div>
                <div class="col-xs-3">
                    <span class="tags" data-toggle="tooltip" data-placement="bottom" title="查看关于 女生小说 的小说">
                        <a href="">女生小说</a>
                    </span>
                    <p>查看关于女生小说的小说</p>
                </div>
                <div class="col-xs-3">
                    <span class="tags" data-toggle="tooltip" data-placement="bottom" title="查看关于 其他小说 的小说">
                        <a href="">其他小说</a>
                    </span>
                    <p>查看关于其他小说的小说</p>
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
<script type="text/javascript" src="/static/js/front/common.js"></script>

</body>
</html>
