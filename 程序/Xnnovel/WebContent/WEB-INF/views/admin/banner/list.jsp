<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>轮播管理</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
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
        轮播管理
        <small>轮播管理</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 轮播管理</a></li>
        <li class="active">轮播管理</li>
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
                      <input type="text" class="form-control"  placeholder="主题名称" id="title" autocomplete="off">
                    </div>
                    <div class="col-sm-2">
                      <select class="form-control" id="status" name="status" autocomplete="off">
                        <option value="0">--请选择状态--</option>
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
                <a href="${webRoot}/admin/banner/edit" class="btn btn-primary btn-sm"><i class="fa fa-plus"></i>添加</a>
              </div>
              <table id="bannerTable"></table>
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


    });

    var TableInit = function () {
        var oTableInit = new Object();
        //初始化Table
        oTableInit.Init = function () {
            $('#bannerTable').bootstrapTable({
                //带*号的为分页必须配置
                url: "/admin/banner/list",         //请求后台的URL（*）
                method: 'post',                      //请求方式（*）
                toolbar: '#toolbar',                //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: true,                     //是否启用排序
                sortName: "create_time",
                sortOrder: "desc",                   //排序方式
                queryParams: oTableInit.queryParams,//传递参数（*）
                queryParamsType: 'limit',           //limit或者''
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                pageSize: 10,                       //每页的记录行数（*）
                pageList: [10, 30, 50, 100, 'ALL'],        //可供选择的每页的行数（*）
                //search: true,                      //是否启用搜索框：感觉作用不大
                //strictSearch: true,                //设置为 true启用 全匹配搜索，否则为模糊搜索 ： 感觉作用不大
                showColumns: true,                  //是否显示所有的列
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                uniqueId: "id",                     //每一行的唯一标识，一般为主键列
                showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
                showPaginationSwitch: true,          //是否显示 数据条数选择框
                cardView: false,                    //是否显示详细视图
                detailView: false,                   //是否显示父子表
                contentType: "application/x-www-form-urlencoded;charset=UTF-8", //解决POST提交问题
                showExport: false,                     //是否显示导出
                // exportDataType: "basic",              //basic', 'all', 'selected'.表示导出的模式是当前页、所有数据还是选中数据。
                columns: [
                    {checkbox: true},
                    {field: 'id', title: '序号'},
                    {field: 'title', title: '主题',width:"150px"},
                    {field: 'cover', title: '图片',formatter:function (value,row,index) {
                            return html = '<img width="100px" height="50px" src="'+value+'">';
                        }},
                    {field: 'link', title: '链接'},
                    {field: 'status', title: '状态',formatter:function (value,row,index) {
                            var html = '';
                            if(row.status==1){
                                html = '<span class="label label-success">启用</span>';
                            }else{
                                html = '<span class="label label-danger">关闭</span>';
                            }
                            return html;
                        }},
                    {field: 'createTime', title: '时间'},
                    {
                        field: 'operate',
                        title: '操作',
                        width: '200px',
                        events: {
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
                        formatter: function (value, row, index) {
                            return [
                                '<div class="btn-group btn-group-xs">',
                                '<a href="/admin/banner/edit?id='+row.id+'" class="edit btn btn-info"><i class="fa fa-edit">修改</i></a>',
                                '<a href="javascript:void(0);"  class="remove btn btn-danger"><i class="fa fa-remove">删除</i></a>',
                                '<a type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" id="audit"><span class="caret"></span>管理</a>',
                                '<ul class="dropdown-menu" style="right:0"> <li><a href="javascript:void(0)" class="pass">启用</a></li> <li><a href="javascript:void(0)" class="unpass">关闭</a></li> </ul>',
                                '</div>',
                            ].join('');
                        }
                    },
                ],
                // paginationHAlign: 'right', //指定 分页条 在水平方向的位置。'left' or 'right'
                // paginationVAlign: 'bottom', //指定 分页条 在垂直方向的位置。'top' or 'bottom' or 'bonth'
                // paginationDetailHAlign: 'left', //指定 分页详细信息 在水平方向的位置。'left' or 'right'
                // paginationPreText: '?',//指定分页条中上一页按钮的图标或文字
                // paginationNextText: '?',//指定分页条中下一页按钮的图标或文字
            });
        };

        //得到查询的参数
        oTableInit.queryParams = function (params) {
            // 特别说明：
            // 如果queryParamsType=limit,params包含{limit, offset, search, sort, order}
            // 如果queryParamsType!=limit,params包含{pageSize, pageNumber, searchText, sortName, sortOrder}
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                limit: params.limit,   //页面大小
                start: params.offset, //页码
                sort: params.sort,	//排序列名
                order: params.order,	//排序方式
                search: params.search,//搜索框参数
                title: $("#title").val(),
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
                $('#bannerTable').bootstrapTable(('refresh'));
            })
        };
        return oInit;
    };


    //删除小说
    function remove(id) {


        var index = layer.confirm();
        layer.confirm('删除操作数据不可恢复，确定要删除吗？', {
            btn: ['确定', '取消']//按钮
        }, function () {
            var data={"id":id};
            $.ajax({
                url: "/admin/banner/delete",
                type: "POST",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(data),
                beforeSend: function () {
//                    index = layer.msg("注册中，请稍候...", {icon: 16});
                    // 禁用按钮防止重复提交
                    $(".remove").attr({disabled: "disabled"});
                },
                success: function (data) {
                    if (data.code == "200") {
                        layer.msg(data.message, {icon: 1, time: 2000, shade: [0.5, '#393D49']});
                        $('#bannerTable').bootstrapTable(('refresh'));
                    } else {
                        layer.msg(data.message, {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
                        $(".remove").removeAttr("disabled");
                    }
                },
                complete: function () {
                    layer.close(index);
                    $(".remove").removeAttr("disabled");
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
            url: "/admin/banner/audit",
            type: "POST",
            dataType: "JSON",
            contentType: "application/json",
            data: JSON.stringify(data),
            beforeSend: function () {
                // 禁用按钮防止重复提交
                $("#audit").attr({disabled: "disabled"});
            },
            success: function (data) {
                if (data.code == "200") {
                    layer.msg(data.message, {icon: 1, time: 2000, shade: [0.5, '#393D49']});
                    $('#bannerTable').bootstrapTable(('refresh'));
                }else if(data.code == "403"){
                    $("#verifyCodeUrl").attr('src',"/captcha.jpg?t="+$.now());
                    $("#loginModal").modal("show");
                } else {
                    layer.msg(data.message, {icon: 5, time: 2000, shift: 6, shade: [0.5, '#393D49']});
                }
                $("#audit").removeAttr("disabled");
            },
            complete: function () {
                $("#audit").removeAttr("disabled");
            },
            error: function (data) {
                layer.msg(data.message, {icon: 5, time: 2000});
                $("#audit").removeAttr("disabled");
            }
        });
    }
</script>
</body>
</html>
