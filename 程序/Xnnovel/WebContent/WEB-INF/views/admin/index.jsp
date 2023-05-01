<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>后台管理中心</title>
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
        主页
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <%--<li class="active"></li>--%>
      </ol>
    </section>
	<div style="padding: 20px;">
        <h1>>>本系统技术点介绍</h1>
        <ul>
        	<li><h3>后台服务器端采用spring+springmvc+mybatis框架整合。</h3></li>
        	<li><h3>前端页面使用bootstrap+jquery+layui等框架以及ajax技术。</h3></li>
        	<li><h3>数据库使用了轻量级mysql关系数据库。</h3></li>
        	<li><h3>小说数据源是顶点小说网，使用java的jsonp进行爬取。</h3></li>
        	<li><h3>不仅支持小说的爬取，还支持小说和章节的手动添加。</h3></li>
        	<li><h3>整个小说网功能完善，包含一般的小说网站所必须的基本操作，例如用户注册、收藏、书架、评论等等一系列操作。</h3></li>
        </ul>
     </div>
    
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
</body>
</html>
