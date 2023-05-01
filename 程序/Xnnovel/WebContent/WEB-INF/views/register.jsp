<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册-牛码小说网</title>
    <%@include file="/static/comm/front/taglib.jsp" %>
    <link rel="stylesheet" type="text/css" href="/static/css/login.css">
</head>
<body>
<div class="sucaihuo-container">
    <div class="demo form-bg" style="padding: 60px 0;height: 100%;">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <form class="form-horizontal" id="register_form">
                        <span class="heading">用户注册</span>
                        <div class="form-group">
                            <input type="text" class="form-control" id="registerUserName" placeholder="请输入用户名" autocomplete="off">
                            <i class="fa fa-user"></i>
                        </div>
                        <div class="form-group help">
                            <input type="text" class="form-control" id="registerNickName" placeholder="请输入昵称" autocomplete="off">
                            <i class="fa fa-lock"></i>
                        </div>
                        <div class="form-group help">
                            <input type="password" class="form-control" id="registerPassword" placeholder="请输入密码" autocomplete="new-password">
                            <i class="fa fa-lock"></i>
                        </div>
                        <div class="form-group help">
                            <input type="password" class="form-control" id="registerConfirmPassword" placeholder="请输入确认密码" autocomplete="new-password">
                            <i class="fa fa-lock"></i>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-default" id="register_submit">注册</button>
                        </div>
                        <a class="footing" href="/login.html">已有账号？去登录</a>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">
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
                    window.location.href = "/login.html";
                } else {
                    layer.msg(data.message, {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
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
</script>
</body>
</html>