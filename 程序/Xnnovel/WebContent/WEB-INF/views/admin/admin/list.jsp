<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>管理员管理</title>
  <%@include file="/static/comm/admin/taglib.jsp" %>
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
        系统用户
        <small>管理员管理</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 系统用户</a></li>
        <li class="active">管理员管理</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <!-- Small boxes (Stat box) -->
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <div class="panel panel-default" style="margin-bottom: -10px;">
                <div class="panel-body">
                  <div class="form-group">
                    <div class="col-sm-2">
                      <input type="text" class="form-control"  placeholder="请输入用户名" id="username" autocomplete="off">
                    </div>
                    <div class="col-sm-2">
                      <button  type="button" class="btn btn-default" id="btn_search">
                        <span class="fa fa-search" aria-hidden="true"></span>查询
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="box-body">
              <div id="toolbar">
                <a data-toggle="modal" data-target="#adminEditModal" class="btn btn-primary btn-sm"><i class="fa fa-plus">添加</i></a>
              </div>
              <table id="adminTable"></table>
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
      <b>Version</b> 1.0
    </div>
    <strong>Copyright &copy; 2019-2099 <a href="http://www.baidu.com">项目设计</a>.</strong> All rights reserved.
  </footer>

  <div class="control-sidebar-bg"></div>
</div>
<!-- /wrapper -->

<!-- 修改或者添加用户模态框 -->
<div class="modal fade" id="adminEditModal" tabindex="-1" role="dialog" aria-labelledby="adminEditModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
          &times;
        </button>
        <h4 class="modal-title" id="modelLabel_edit">
          管理员编辑
        </h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" role="form">
          <div class="form-group">
            <div class="col-sm-10">
              <input type="hidden" class="form-control" id="id_edit"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">用户名</label>
            <div class="col-sm-10">
              <input type="text" class="form-control" id="username_edit" placeholder="请输入用户名" autocomplete="off"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label">密码</label>
            <div class="col-sm-10">
              <input type="password" class="form-control" id="password_edit" placeholder="请输入密码" autocomplete="new-password"/>
            </div>
          </div>

        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="save()" id="save">提交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal -->
</div>


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
                 maxlength="18" autocomplete="new-password" required>
        </div>
        <div class="form-group">
          <label class="control-label" for="loginModalVerifyCode">验证码</label>
          <div class="input-group">
            <input class="form-control" id="loginModalVerifyCode"  type="text" placeholder="请输入验证码">
            <span class="input-group-addon"><img id="verifyCodeUrl" src="" height="30px" width="100px" alt="点我刷新" onclick="refresh()"></span>
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

<!-- Bootstrap-table -->
<script type="text/javascript" src="/static/js/admin/lib/bootstrap-table.min.js"></script>
<script type="text/javascript" src="/static/js/admin/lib/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript">
    $(function () {

        //初始化table
        var oTable = new TableInit();
        oTable.Init();

        //2.初始化Button的点击事件
        var oButtonInit = new ButtonInit();
        oButtonInit.Init();


        //绑定添加按钮点击时间
        $("#add").click(function () {
            $('#id').val('');
            $('#username_edit').val('');
            $('#password_edit').val('');
            $('#email_edit').val('');
            $('#adminEditModel').modal('show');
        });

    });

    var TableInit = function () {
        var oTableInit = new Object();
        //初始化Table
        oTableInit.Init = function () {
            $('#adminTable').bootstrapTable({
                //带*号的为分页必须配置
                url: "/admin/admin/list",         //请求后台的URL（*）
                method: 'post',                      //请求方式（*）
                toolbar: '#toolbar',                //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: true,                     //是否启用排序
                sortName:"create_time",
                sortOrder: "desc",                   //排序方式
                queryParams: oTableInit.queryParams,//传递参数（*）
                queryParamsType: 'limit',           //limit或者''
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber:1,                       //初始化加载第一页，默认第一页
                pageSize:10,                       //每页的记录行数（*）
                pageList: [10, 30, 50, 100,'ALL'],        //可供选择的每页的行数（*）
                //search: true,                      //是否启用搜索框：感觉作用不大
                //strictSearch: true,                //设置为 true启用 全匹配搜索，否则为模糊搜索 ： 感觉作用不大
                showColumns: true,                  //是否显示所有的列
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                uniqueId: "id",                     //每一行的唯一标识，一般为主键列
                showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
                showPaginationSwitch:true,          //是否显示 数据条数选择框
                cardView: false,                    //是否显示详细视图
                detailView: false,                   //是否显示父子表
                contentType: "application/x-www-form-urlencoded;charset=UTF-8", //解决POST提交问题
                showExport: true,                     //是否显示导出
                exportDataType: "basic",              //basic', 'all', 'selected'.表示导出的模式是当前页、所有数据还是选中数据。
                columns: [
                    {checkbox: true},
                    {field: 'id', title: '序号'},
                    {field: 'username', title: '用户名'},
                    {field: 'createTime', title: '创建时间',width:'140px'},
                    {
                        field: '操作',
                        title: '操作',
                        width:'200px',
                        events: {
                            'click .reset': function(e, value, row, index) {
                                reset(row.id);
                            },
                            'click .remove': function(e, value, row, index) {
                                remove(row.id);
                            },
                        },
                        formatter: function (value, row, index) {
                            return [
                                '<div class="btn-group btn-group-xs">',
                                '<button class="reset btn btn-primary"><i class="fa fa-retweet">密码重置</i></button>',
                                '<button class="remove btn btn-danger"><i class="fa fa-remove">删除</i></button>',
                                '</div>',
                            ].join('');
                        }},
                ],
            });
        };

        //得到查询的参数
        oTableInit.queryParams = function (params) {
            // 特别说明：
            // 如果queryParamsType=limit,params包含{limit, offset, search, sort, order}
            // 如果queryParamsType!=limit,params包含{pageSize, pageNumber, searchText, sortName, sortOrder}
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                limit: params.limit,   //页面大小
                start:params.offset, //页码
                sort: params.sort,	//排序列名
                order:params.order,	//排序方式
                search:params.search,//搜索框参数
                username:$("#username").val(),
            };
            return temp;
        };
        return oTableInit;
    };

    var ButtonInit = function () {
        var oInit = new Object();
        var postdata = {};

        oInit.Init = function () {
            //初始化页面上面的按钮事件
            $("#btn_search").click(function () {
                $('#adminTable').bootstrapTable(('refresh'));
            })
        };
        return oInit;
    };

    function save(){

        var id = $("#id_edit").val();
        var username = $.trim($("#username_edit").val());
        var password = $.trim($("#password_edit").val());
        if(username=="" || username==null){
            layer.msg("请输入用户名！", {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
            $("#username_edit").focus();
            return;
        }
        if(password=="" || password==null){
            layer.msg("请输入密码！", {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
            $("#password_edit").focus();
            return;
        }
        var data = {"id":id,"username":username,"password":password};
        var url = "/admin/admin/save";
        if(id != null && id != ""){
            url = "/admin/admin/update";
        }
        $.ajax({
            url: url,
            type: "POST",
            dataType: "JSON",
            contentType: "application/json",
            data: JSON.stringify(data),
            beforeSend: function () {
                // 禁用按钮防止重复提交
                $(".edit").attr({disabled: "disabled"});
            },
            success: function (data) {
                if (data.code == "200") {
                    layer.msg(data.message, {icon: 1, time: 2000, shade: [0.5, '#393D49']});
                    $('#adminTable').bootstrapTable(('refresh'));
                }else if(data.code == "403"){
                    $("#verifyCodeUrl").attr('src',"/captcha.jpg?t="+$.now());
                    $("#loginModal").modal("show");
                } else {
                    layer.msg(data.message, {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
                }
                $(".edit").attr({disabled: "disabled"});
            },
            complete: function () {
                $(".edit").attr({disabled: "disabled"});
            },
            error: function (data) {
                layer.msg(data.message, {icon: 5, time: 2000})
                $(".edit").attr({disabled: "disabled"});
            }
        });

    }

    function remove(id) {
        var index = layer.confirm();
        layer.confirm('确认要删除吗？', {
            btn : [ '确定', '取消' ]//按钮
        }, function(index) {
            layer.close(index);
            var data = {"id":id};
            $.ajax({
                url: "/admin/admin/remove",
                type: "POST",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(data),
                beforeSend: function () {
                    // 禁用按钮防止重复提交
                    $("#remove").attr({disabled: "disabled"});
                },
                success: function (data) {
                    if (data.code == "200") {
                        layer.msg(data.message, {icon: 1, time: 2000, shade: [0.5, '#393D49']});
                        $('#adminTable').bootstrapTable(('refresh'));
                    }else if(data.code == "403"){
                        $("#verifyCodeUrl").attr('src',"/captcha.jpg?t="+$.now());
                        $("#loginModal").modal("show");
                    } else {
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


    function reset(id) {

        var data = {'id':id};
        $.ajax({
            url: '/admin/admin/reset',
            type: "POST",
            dataType: "JSON",
            contentType: "application/json",
            data: JSON.stringify(data),
            beforeSend: function () {
                // 禁用按钮防止重复提交
                $(".reset").attr({disabled: "disabled"});
            },
            success: function (data) {
                if (data.code == "200") {
                    layer.msg(data.message, {icon: 1, time: 2000, shade: [0.5, '#393D49']});
                    $('#adminTable').bootstrapTable(('refresh'));
                }else if(data.code == "403"){
                    $("#verifyCodeUrl").attr('src',"/captcha.jpg?t="+$.now());
                    $("#loginModal").modal("show");
                } else {
                    layer.msg(data.message, {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
                }
                $(".reset").attr({disabled: "disabled"});
            },
            complete: function () {
                $(".reset").attr({disabled: "disabled"});
            },
            error: function (data) {
                layer.msg(data.message, {icon: 5, time: 2000})
                $(".reset").attr({disabled: "disabled"});
            }
        });

    }
</script>
</body>
</html>
