<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>公告管理</title>
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
        公告管理
        <small>公告管理</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>公告管理</a></li>
        <li class="active">公告管理</li>
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
                      <input type="hidden" value="${novel.id}" id="novelId">
                      <input type="text" class="form-control"  placeholder="请输入公告主题" id="title">
                    </div>
                    <div class="col-sm-2">
                      <select class="form-control" id="status" name="status" autocomplete="off">
                        <option value="0">--公告状态--</option>
                        <option value="1">开启</option>
                        <option value="2">关闭</option>
                      </select>
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
                <a href="${webRoot}/admin/notice/edit" class="btn btn-primary btn-sm"><i class="fa fa-plus"></i>添加</a>
              </div>
              <table id="noticeTable"></table>
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



<!-- Bootstrap-table -->
<script src="/static/js/admin/lib/bootstrap-table.min.js"></script>
<script src="/static/js/admin/lib/bootstrap-table-zh-CN.min.js"></script>



<script>
    $(function () {

        //初始化table
        var oTable = new TableInit();
        oTable.Init();

        //2.初始化Button的点击事件
        var oButtonInit = new ButtonInit();
        oButtonInit.Init();


    });

    var TableInit = function () {
        var oTableInit = new Object();
        //初始化Table
        oTableInit.Init = function () {
            $('#noticeTable').bootstrapTable({
                //带*号的为分页必须配置
                url: "/admin/notice/list",         //请求后台的URL（*）
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
                pageSize:20,                       //每页的记录行数（*）
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
                    {field: 'id', title: '序号',visible: false},
                    {field: 'title', title: '主题',formatter:function (value,row,index) {
                            return '<a href="/admin/notice/view?id=' + row.id + '" target="_blank">' + row.title + '</a>';
                    }},
                    {field: 'status', title: '状态',formatter:function (value,row,index) {
                            var html = '';
                            if(row.status==1){
                                html = '<span class="label label-success">开启</span>';
                            }else{
                                html = '<span class="label label-danger">关闭</span>';
                            }
                            return html;
                        }},
                    {field: 'createTime', title: '更新时间'},
                    {
                        field: 'operator',
                        title: '操作',
                        width:'250px',
                        events:{
                            'click .remove': function(e, value, row, index) {
                                remove(row.id);
                            },
                            'click .pass': function(e, value, row, index) {
                                audit(row.id,1);
                            },
                            'click .unpass': function(e, value, row, index) {
                                audit(row.id,2);
                            },
                        },
                        formatter:function (value,row,index) {
                            return [
                                '<div class="btn-group btn-group-xs">',
                                '<a class="btn btn-info" href="/admin/notice/view?id='+row.id+'"><i class="fa fa-eye">查看</i></a>',
                                '<a class="btn btn-success" href="/admin/notice/edit?id='+row.id+'"><i class="fa fa-edit">修改</i></a>',
                                '<button class="remove btn btn-danger"><i class="fa fa-remove">删除</i></button>',
                                '<button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" id="audit"><span class="caret"></span>审核</button>',
                                '<ul class="dropdown-menu" style="right:0;left:initial"> <li><a href="javascript:void(0)" class="pass">开启</a></li> <li><a href="javascript:void(0)" class="unpass">关闭</a></li> </ul>',
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
                title:$("#title").val(),
                status:$("#status").val(),
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
                $('#noticeTable').bootstrapTable(('refresh'));
            })
        };
        return oInit;
    };





    function remove(id) {

        layer.confirm('确认要删除吗？', {
            btn : [ '确定', '取消' ]//按钮
        }, function() {
            var data = {"id":id};
            $.ajax({
                url: "/admin/notice/remove",
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
                        $('#noticeTable').bootstrapTable(('refresh'));
                    } else {
                        layer.msg(data.message, {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
                        $("#remove").removeAttr("disabled");
                    }
                },
                complete: function () {
                    layer.close(index);
                    $("#remove").removeAttr("disabled");
                },
                error: function (data) {
                    layer.msg(data.message, {icon: 5, time: 2000})
                }
            });
        });
    }


    function audit(id,status) {

        var data = {"id":id,"status":status};
        $.ajax({
            url: "/admin/notice/audit",
            type: "POST",
            dataType: "JSON",
            contentType: "application/json",
            data: JSON.stringify(data),
            beforeSend: function () {
//                    index = layer.msg("注册中，请稍候...", {icon: 16});
                // 禁用按钮防止重复提交
                $("#audit").attr({disabled: "disabled"});
            },
            success: function (data) {
                if (data.code == "200") {
                    layer.msg(data.message, {icon: 1, time: 2000, shade: [0.5, '#393D49']});
                    $('#noticeTable').bootstrapTable(('refresh'));
                }else if(data.code == "403"){
                    $("#verifyCodeUrl").attr('src',"/captcha.jpg?t="+$.now());
                    $("#loginModal").modal("show");
                } else {
                    layer.msg(data.message, {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
                    $("#audit").removeAttr("disabled");
                }
            },
            complete: function () {
                layer.close(index);
                $("#audit").removeAttr("disabled");
            },
            error: function (data) {
                layer.msg(data.message, {icon: 5, time: 2000})
            }
        });
    }
</script>
</body>
</html>
