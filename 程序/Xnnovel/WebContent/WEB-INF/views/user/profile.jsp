<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
    <title>我的资料</title>
    <meta name="Keywords" content=""/>
    <meta name="description" content=""/>
    <link rel="stylesheet" href="/static/css/bootstrap-fileinput.css">
    <%@include file="/static/comm/front/taglib.jsp" %>
    <script type="text/javascript" src="/static/js/bootstrap-fileinput.js"></script>
    <style type="text/css">
        .fileinput-add,.fileinput-change,.fileinput-remove{
            border-radius: 3px;
            padding: 6px 12px;
            border:1px solid transparent;
            border-color: #4cae4c;
            background-color: #5cb85c;
            color: #fff;
        }
    </style>
</head>

<body>
<section class="container user-select">
    <header>
        <div class="hidden-xs header"><!--超小屏幕不显示-->
            <h1 class="logo">
                <a href="/" title="项目设计"></a></h1>
            <ul class="nav hidden-xs-nav">
                <li><a href="/"><span class="glyphicon glyphicon-home"></span>首页</a></li>
                <li class="active"><a href="/user/profile.html"><span class="glyphicon glyphicon-erase"></span>我的资料</a></li>
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
                <form class="form-horizontal" role="form" id="user_form">
                    <div class="form-group">
                        <label for="userName" class="col-sm-2 control-label">账户</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="userName" name="userName" disabled value="${user.userName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="nickName" class="col-sm-2 control-label">昵称</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="nickName" name="nickName" placeholder="请输入昵称" value="${user.nickName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="email" name="email" placeholder="请输入邮箱" value="${user.email}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="banner" class="col-sm-2 control-label">头像</label>
                        <div class="col-sm-6">
                            <div  id="banner" style="display: block">
                                <div class="fileinput fileinput-new" data-provides="fileinput" id="uploadImageDiv" >
                                    <div class="fileinput-new thumbnail" style="width: 350px; height: 260px;">
                                        <img src="${user.avatar==null?'/static/images/icon/icon.png':user.avatar}" alt="" />
                                    </div>
                                    <div class="fileinput-preview fileinput-exists thumbnail" style="min-width: 350px; min-height: 260px;"></div>
                                    <div>
                                                <span class="btn default btn-file">
                                                    <span class="fileinput-new fileinput-add">选择图片</span>
                                                    <span class="fileinput-exists fileinput-change">更改</span>
                                                    <input type="file" name="file" id="file" />
                                                </span>
                                        <span href="#" class="fileinput-exists fileinput-remove" data-dismiss="fileinput">移除</span>
                                        <span>请选择1M以内图片</span>
                                    </div>
                                </div>
                                <div id="titleImageError" style="color: #a94442"></div>
                            </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-2">
                            <button type="submit" class="btn btn-info">修改</button>
                        </div>
                    </div>
                </form>
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
    $(function () {
        $("#user_form").submit(function (e) {
            var form = new FormData(document.getElementById("user_form"));
            // 阻止表单默认提交
            e.preventDefault();
            // 参数校验
            var nickName = $.trim($("#nickName").val());
            var email = $.trim($("#email").val());
            var fileInput = $('#file').get(0).files[0];

            if (nickName == "" || nickName == null) {
                layer.msg("请输入昵称！", {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
                $("#nickName").focus();
                return;
            }

            //与后台交互
            $.ajax({
                url:'/user/update',
                type:"post",
                data:form,
                processData:false,
                contentType:false,
                success:function(data){
                    if(data.code==200){
                        layer.msg(data.message, {icon: 1, time: 2000, shade: [0.5, '#393D49']});
                        window.location.href="/user/profile.html";
                    }
                },
                error:function(e){
                    layer.msg("上传失败，网络异常！", {icon: 1, time: 2000, shade: [0.5, '#393D49']});
                }
            });
        });
    });
</script>
</body>
</html>
