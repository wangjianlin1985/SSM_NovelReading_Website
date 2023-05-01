<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>轮播编辑</title>
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
                <small>轮播编辑</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 轮播管理</a></li>
                <li class="active">轮播编辑</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <!-- Small boxes (Stat box) -->
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-body">
                            <form role="form" action="/banner/post" method="post" enctype="multipart/form-data" id="banner_form">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="id" name="id" value="${banner.id}"
                                           style="display: none">
                                </div>
                                <div class="form-group">
                                    <label for="title">主题</label>
                                    <input style="width: 504px" type="text" class="form-control" id="title" name="title" placeholder="请输入主题"
                                           value="${banner.title}">
                                </div>
                                <div class="form-group">
                                    <label for="title">链接</label>
                                    <input style="width: 504px" type="text" class="form-control" id="link" name="link" placeholder="请输入链接"
                                           value="${banner.link}">
                                </div>
                                <div class="form-group">
                                    <label for="banner">图片：750 x 300px</label>
                                    <div   id="banner" style="display: block">
                                        <div class="fileinput fileinput-new" data-provides="fileinput" id="uploadImageDiv" >
                                            <div class="fileinput-new thumbnail" style="width: 750px; height: 300px;">
                                                <img src="${banner.cover==null?'/static/images/1.jpg':banner.cover}" alt="" />
                                            </div>
                                            <div class="fileinput-preview fileinput-exists thumbnail" style="min-width: 750px; min-height: 300px;"></div>
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
                                <div class="form-group">
                                    <button type="submit" class="btn btn-info">提交</button>
                                </div>
                            </form>
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
<script type="text/javascript">

    $(function () {
        $("#banner_form").submit(function (e) {
            var form = new FormData(document.getElementById("banner_form"));
            // 阻止表单默认提交
            e.preventDefault();
            // 参数校验
            var id = $("#id").val();
            var title = $("#title").val();
            var link = $("#link").val();
            var fileInput = $('#file').get(0).files[0];

            if (title == "" || title == null) {
                layer.msg("请输入主题！", {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
                $("#title").focus();
                return;
            }

            if (link == "" || link == null) {
                layer.msg("请输入链接！", {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
                $("#title").focus();
                return;
            }

            if((id==null||id=='')&&!fileInput){
                layer.msg("请选择图片！", {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
                return;
            }
            var url = '';
            if (id == null || id == "") {
                url = "/admin/banner/save";
            } else {
                url = "/admin/banner/update";
            }
            //与后台交互
            $.ajax({
                url:url,
                type:"post",
                data:form,
                processData:false,
                contentType:false,
                success:function(data){
                    if(data.code==200){
                        layer.msg(data.message, {icon: 1, time: 2000, shade: [0.5, '#393D49']});
                        window.location.href="/admin/banner";
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
