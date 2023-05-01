<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="main-header">
    <!-- Logo -->
    <a href="/admin/index" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>牛</b></span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>小说网后台管理</b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="/static/images/icon/icon.png" class="user-image" alt="User Image">
                        <span class="hidden-xs">项目设计</span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="/static/images/icon/icon.png" class="img-circle" alt="User Image">
                            <p>
                                ${admin.username}
                            </p>
                        </li>

                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="/admin/index" class="btn btn-default btn-flat">我的主页</a>
                            </div>
                            <div class="pull-right">
                                <a href="/admin/logout" class="btn btn-default btn-flat">登出</a>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>
