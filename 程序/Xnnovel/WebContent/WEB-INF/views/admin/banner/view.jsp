<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>轮播查看</title>
    <link rel="stylesheet" href="/static/css/bootstrap-fileinput.css">
    <%@include file="/static/comm/admin/taglib.jsp" %>
    <script type="text/javascript" src="/static/js/admin/lib/bootstrap-fileinput.js"></script>
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
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="/static/comm/admin/header.jsp" %>
    <%@include file="/static/comm/admin/left.jsp" %>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                轮播管理
                <small>轮播查看</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 轮播管理</a></li>
                <li class="active">轮播查看</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <!-- Small boxes (Stat box) -->
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-body">
                            <div class="form-group">
                                <label for="title">主题</label>
                                <input style="width: 504px" type="text" class="form-control" id="title" name="title" placeholder="请输入主题" value="${banner.title}" disabled>
                            </div>
                            <div class="form-group">
                                <label for="title">链接</label>
                                <input style="width: 504px" type="text" class="form-control" id="link" name="link" placeholder="请输入链接" value="${banner.link}" disabled>
                            </div>
                            <div class="form-group">
                                <label for="banner">图片</label>
                                <div   id="banner" style="display: block">
                                    <div class="fileinput fileinput-new" data-provides="fileinput" id="uploadImageDiv" >
                                        <div class="fileinput-new thumbnail" style="width: 504px; height: 260px;">
                                            <img src="${banner.url}" alt="" />
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.col -->
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0.0
        </div>
        <strong>
            <p>
                Copyright © 项目设计 All Rights Reserved. 备案号： 粤ICP备xxxx号
            </p>
        </strong>
    </footer>
    <div class="control-sidebar-bg"></div>
</div>
<!-- /wrapper -->
<!--登录模态框-->
<div class="modal fade user-select" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel">
    <div class="modal-dialog" role="document" style="max-width: 420px;margin-top: 100px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="loginModalLabel">管理员登录</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="loginModalUserNmae">用户名</label>
                    <input type="text" class="form-control" id="loginModalUserNmae" placeholder="请输入用户名" autofocus
                           maxlength="15" autocomplete="off" required>
                </div>
                <div class="form-group">
                    <label for="loginModalUserPwd">密码</label>
                    <input type="password" class="form-control" id="loginModalUserPwd" placeholder="请输入密码"
                           maxlength="18" autocomplete="off" required>
                </div>
                <div class="form-group ">
                    <label class="control-label" for="loginModalVerifyCode">验证码</label>
                    <div class="input-group">
                        <input class="form-control" id="loginModalVerifyCode" type="text" placeholder="请输入验证码">
                        <span class="input-group-addon"><img id="verifyCodeUrl" src="" height="30px" width="100px"
                                                             alt="点我刷新" onclick="refresh()"></span>
                    </div>
                </div>
                <div class="form-group ">
                    <label>
                        <input type="checkbox" name="rememberMe" value="1" id="rememberMe" checked="checked"> 记住登陆？（公共场合请不要勾选）
                    </label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="login4Admin()" id="login4Admin">登录</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/static/js/admin/banner_edit.js"></script>
</body>
</html>
