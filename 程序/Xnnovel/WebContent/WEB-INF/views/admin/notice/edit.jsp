<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>公告编辑</title>
    <%@include file="/static/comm/admin/taglib.jsp" %>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="/static/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="/static/ueditor/ueditor.all.js"></script>
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
                公告管理
                <small>公告编辑</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 公告管理</a></li>
                <li class="active">公告编辑</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <!-- Small boxes (Stat box) -->
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-body">
                            <form role="form" action="" method="post" enctype="multipart/form-data" id="notice_form">
                                <div class="form-group">
                                    <input type="hidden" class="form-control" id="id" name="id" value="${notice.id}">
                                </div>
                                <div class="form-group">
                                    <label for="title">公告主题</label>
                                    <input type="text" class="form-control" id="title" name="title" placeholder="请输入公告主题"
                                           value="${notice.title}">
                                </div>

                                <div class="form-group">
                                    <label for="editor">公告正文</label>
                                    <!-- 加载编辑器的容器 -->
                                    <textarea id="editor" name="content"></textarea>
                                    <textarea id="content" style="display: none">${notice.content}</textarea>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-info" id="submit_btn">提交</button>
                                </div>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </section>
    </div>

    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0
        </div>
        <strong>Copyright &copy; 2019-2099 <a href="http://www.baidu.com">项目设计</a>.</strong> All rights reserved.
    </footer>


    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- /wrapper -->


<script>
    var content = $("#content").text();
    var ue = UE.getEditor('editor');

    ue.ready(function () {
        ue.setHeight(500);
        //设置编辑器的内容
        ue.setContent(content);
        // //获取html内容，返回: <p>hello</p>
        // var html = ue.getContent();
        // //获取纯文本内容，返回: hello
        // var txt = ue.getContentTxt();
    });


    $(function () {
        $("#notice_form").submit(function (e) {
            var form = $(this);
            // 阻止表单默认提交
            e.preventDefault();
            // 参数校验
            var id = $("#id").val();
            var title = $.trim($("#title").val());
            var content = ue.getContent();
            if (title == "" || title == null) {
                layer.msg("请输入公告标题！", {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
                $("#title").focus();
                return;
            }
            if (content == "" || content == null) {
                layer.msg("请输入公告内容！", {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
                $("#content").focus();
                return;
            }
            //与后台交互
            var index;
            var data = {"id": id,"title": title, "content": content};
            var url = '';
            if (id == null || id == "") {
                url = "/admin/notice/save";
            } else {
                url = "/admin/notice/update";
            }
            $.ajax({
                url: url,
                type: "POST",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(data),
                beforeSend: function () {
//                    index = layer.msg("注册中，请稍候...", {icon: 16});
                    // 禁用按钮防止重复提交
                    $("#submit_btn").attr({disabled: "disabled"});
                },
                success: function (data) {
                    if (data.code == "200") {
                        layer.msg(data.message, {icon: 1, time: 2000, shade: [0.5, '#393D49']});
                        location.href = '/admin/notice';
                    } else {
                        layer.msg(data.message, {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
                        $("#submit_btn").removeAttr("disabled");
                    }
                },
                complete: function () {
                    layer.close(index);
                    $("#submit_btn").removeAttr("disabled");
                },
                error: function (data) {
                    layer.msg(data.message, {icon: 5, time: 2000})
                }
            });
        });
    });
</script>
</body>
</html>
