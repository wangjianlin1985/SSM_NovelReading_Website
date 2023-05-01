<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录-牛码小说网</title>
    <%@include file="/static/comm/front/taglib.jsp" %>
    <link rel="stylesheet" type="text/css" href="/static/css/login.css">
</head>
<body>
<div class="sucaihuo-container">
    <div class="demo form-bg" style="padding: 100px 0;height: 100%;">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <form class="form-horizontal" id="login_form" method="post">
                        <span class="heading">用户登录</span>
                        <div class="form-group">
                            <input type="text" class="form-control" id="loginUserName" placeholder="用户名或电子邮件">
                            <i class="fa fa-user"></i>
                        </div>
                        <div class="form-group help">
                            <input type="password" class="form-control" id="loginPassword" placeholder="密　码">
                            <i class="fa fa-lock"></i>
                            <%--<a href="http://demo.demohuo.top/modals/34/3432/demo/#" class="fa fa-question-circle"></a>--%>
                        </div>
                        <div class="form-group">
                            <%--<div class="main-checkbox">--%>
                                <%--<input type="checkbox" value="None" id="checkbox1" name="check">--%>
                                <%--<label for="checkbox1"></label>--%>
                            <%--</div>--%>
                            <%--<span class="text">记住密码？（公共场合请谨慎勾选）</span>--%>
                            <button type="submit" class="btn btn-default" id="login_submit">登录</button>
                        </div>
                        <a class="footing" href="/register.html">没有账号？去注册</a>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
    <script type="text/javascript">
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
                        window.location.href = "/index.html";
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
    </script>
</body>
</html>