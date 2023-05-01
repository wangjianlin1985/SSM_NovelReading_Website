<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>牛码小说后台登陆</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@include file="/static/comm/admin/taglib.jsp" %>
    <style type="text/css">
        .login-box-body .form-control-feedback{
            left: 0;
        }

        .login-box-body input{
            padding-left: 30px;
        }

        .login-box-body .input-group-addon{
            padding: 0;
            border-radius: 0;
        }
    </style>
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <b>牛码小说后台登陆</b>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">Sign in to start your session</p>

        <form action="/admin/login" method="post" id="admin_login_form">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" placeholder="用户名" id="username">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" placeholder="密码" id="password">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <div class="input-group">
                    <input type="text" class="form-control" id="code" required="" placeholder="验证码">
                    <span class="input-group-addon"><img id="codeimg" src="/captcha.jpg" height="32px" width="100px" alt="点我刷新" onclick="codeimg.src='/captcha.jpg?v='+Math.random()"></span>
                </div>
                <span class="glyphicon glyphicon-check form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat" id="login4Admin">登陆</button>
                </div>
                <!-- /.col -->
            </div>
        </form>
    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->
<script type="text/javascript">

    $("#admin_login_form").submit(function (e) {

        // 阻止表单默认提交
        e.preventDefault();
        var username = $("#username").val();
        var password = $("#password").val();
        var code = $("#code").val();
        if (username == "" || username == null) {
            layer.msg("用户名不能为空！", {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
            $("#username").focus();
            return;
        }
        if (password == "" || password == null) {
            layer.msg("密码不能为空！", {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
            $("#password").focus();
            return;
        }
        //与后台交互
        var data = {"username":username,"password":password,"code":code}
        $.ajax({
            url: "/admin/login",
            type: "POST",
            dataType: "JSON",
            contentType: "application/json",
            data: JSON.stringify(data),
            beforeSend: function () {
                // 禁用按钮防止重复提交
                $("#login4Admin").attr({disabled: "disabled"});
            },
            success: function (data) {
                if (data.code == "200") {
                    layer.msg(data.message, {icon: 1, time: 2000, shade: [0.5, '#393D49']});
                    location.href = "/admin/index";
                } else {
                    layer.msg(data.message, {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
                    $("#login4Admin").removeAttr("disabled");
                }
            },
            complete: function () {
                $("#login4Admin").removeAttr("disabled");
            },
            error: function (data) {
                layer.msg(data.message, {icon: 5, time: 2000});
                $("#login4Admin").removeAttr("disabled");
            }
        });

    });

</script>

</body>
</html>
