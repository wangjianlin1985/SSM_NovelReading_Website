<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="/static/images/icon/icon.png" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${admin.username}</p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">导航栏</li>
            <li><a href="/admin/index"><i class="fa fa-dashboard"></i> <span>主页</span></a></li>
            <li class="treeview">
                <a href="javascript:void(0)">
                    <i class="fa fa-book"></i>
                    <span>小说管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/admin/novel"><i class="fa fa-circle-o"></i> <span>小说管理</span></a></li>
                    <li><a href="/admin/novel/edit"><i class="fa fa-circle-o"></i> <span>小说发布</span></a></li>
                    <li><a href="/admin/chapter"><i class="fa fa-circle-o"></i> <span>章节管理</span></a></li>
                    <li style="display: none"><a href="/admin/chapter/edit"><i class="fa fa-circle-o"></i> <span>章节编辑</span></a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="javascript:void(0)">
                    <i class="fa fa-book"></i>
                    <span>分类管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/admin/type"><i class="fa fa-circle-o"></i> <span>分类管理</span></a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="javascript:void(0)">
                    <i class="fa fa-book"></i>
                    <span>轮播管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/admin/banner"><i class="fa fa-circle-o"></i> <span>轮播管理</span></a></li>
                    <li><a href="/admin/banner/edit"><i class="fa fa-circle-o"></i> <span>轮播添加</span></a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="javascript:void(0)">
                    <i class="fa fa-book"></i>
                    <span>评论管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/admin/comment"><i class="fa fa-circle-o"></i> <span>评论管理</span></a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="javascript:void(0)">
                    <i class="fa fa-book"></i>
                    <span>公告管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/admin/notice"><i class="fa fa-circle-o"></i> <span>公告管理</span></a></li>
                    <li><a href="/admin/notice/edit"><i class="fa fa-circle-o"></i> <span>公告新增</span></a></li>
                </ul>
            </li>


            <li class="treeview">
                <a href="javascript:void(0)">
                    <i class="fa fa-book"></i> <span>系统用户</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="/admin/user"><i class="fa fa-circle-o"></i> 读者管理</a></li>
                    <li><a href="/admin/admin"><i class="fa fa-circle-o"></i> 管理员管理</a></li>
                </ul>
            </li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>