<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
    <title>我的书架</title>
    <meta name="Keywords" content=""/>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="/static/css/bootstrap-fileinput.css">
    <%@include file="/static/comm/front/taglib.jsp" %>
    <script type="text/javascript" src="/static/js/bootstrap-fileinput.js"></script>
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
                <li class="active"><a href="/user/shelf.html"><span class="glyphicon glyphicon-erase"></span>我的书架</a></li>
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
                    <a href="/novel/info/${novel.id}.html"><small>我的书架</small></a>
                </h2>
            </header>
            <div class="content-block new-content">
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>小说名</th>
                            <th>阅读章节</th>
                            <th>阅读时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${shelfList.size()>0}">
                            <c:forEach items="${shelfList}" var="shelf" >
                                <tr>
                                    <td><a href="/novel/info/${shelf.novel.id}.html">${shelf.novel.title}</a></td>
                                    <td>
                                        <c:if test="${shelf.progress!=null}">
                                            <a href="/chapter/info/${shelf.progress.chapter.id}.html">${shelf.progress.chapter.title}</a>
                                        </c:if>
                                        <c:if test="${shelf.progress==null}">
                                            <a href="/novel/info/${shelf.progress.novel.id}.html">开始阅读吧.</a>
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${shelf.progress!=null}">
                                            ${shelf.progress.modifyTime}
                                        </c:if>
                                        <c:if test="${shelf.progress==null}">
                                            --
                                        </c:if>
                                    </td>
                                    <td><button type="button" class="btn btn-danger btn-xs" id="remove"><i class="fa fa-remove" onclick="remove(${shelf.id})">移出书架</i></button></td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${shelfList.size()<=0}">
                            <tr>
                                <td colspan="3" style="text-align: center">书架中暂无书籍！</td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
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
    
    
    function remove(id) {

        var index = layer.confirm();
        layer.confirm('确认要移出书架吗？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
            layer.close(index);
            var data = {"id":id};
            $.ajax({
                url: "/shelf/delete",
                type: "POST",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(data),
                beforeSend: function () {
//                    index = layer.msg("注册中，请稍候...", {icon: 16});
                    // 禁用按钮防止重复提交
                    $("#remove").attr({disabled: "disabled"});
                },
                success: function (data) {
                    if (data.code == "200") {
                        layer.msg(data.message, {icon: 1, time: 2000, shade: [0.5, '#393D49']});
                        window.location.reload();
                    } else{
                        layer.msg(data.message, {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
                        $("#remove").removeAttr("disabled");
                    }
                },
                complete: function () {
                    $("#remove").removeAttr("disabled");
                },
                error: function (data) {
                    layer.msg(data.message, {icon: 5, time: 2000})
                }
            });
        });
        
    }
</script>
</body>
</html>
