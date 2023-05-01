<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="sidebar visible-lg"><!--右侧>992px显示-->
    <div class="widget widget-tabs">
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active">
                <a href="#centre" aria-controls="centre" role="tab" data-toggle="tab">会员中心</a>
            </li>
            <li role="presentation" >
                <a href="#notice" aria-controls="notice" role="tab" data-toggle="tab">网站公告</a>
            </li>
            <li role="presentation">
                <a href="#contact" aria-controls="contact" role="tab" data-toggle="tab">联系站长</a>
            </li>
        </ul>
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane centre active" id="centre">
                <c:choose>
                    <c:when test="${user==null}">
                        <div class="unlogin" style="display: inline-block">
                            <h4>快来加入我们吧！</h4>
                            <p>
                                <a data-toggle="modal" data-target="#loginModal" class="btn btn-primary">立即登录</a>
                                <a data-toggle="modal" data-target="#registerModal" class="btn btn-default">现在注册</a>
                            </p>
                        </div>
                        <div class="login" style="display: none">
                            <img src="/static/images/icon/icon.png">
                            <div class="login-info">
                                <p>欢迎您：${user.userName}</p>
                                <a class="btn btn-info btn-xs" href="/user/index.html"><i class="fa fa-location-arrow">个人中心</i></a>
                                <a class="btn btn-danger btn-xs" href="/signout"><i class="fa fa-sign-out">退出系统</i></a>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="unlogin" style="display: none">
                            <h4>需要登录才能进入会员中心</h4>
                            <p>
                                <a data-toggle="modal" data-target="#loginModal" class="btn btn-primary">立即登录</a>
                                <a data-toggle="modal" data-target="#registerModal" class="btn btn-default">现在注册</a>
                            </p>
                        </div>
                        <div class="login" style="display: inline-block">
                            <img src="${user.avatar==null?'/static/images/icon/icon.png':user.avatar}">
                            <div class="login-info">
                                <p>欢迎您：${user.userName}</p>
                                <a class="btn btn-info btn-xs" href="/user/index.html"><i class="fa fa-location-arrow">个人中心</i></a>
                                <a class="btn btn-danger btn-xs" href="/signout"><i class="fa fa-sign-out">退出系统</i></a>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div role="tabpanel" class="tab-pane notice" id="notice">
                <ul>
                    <c:forEach items="${noticeList}" var="notice">
                        <li>
                            <time datetime="${notice.createTime}">${notice.createTime}</time>
                            <a href="/notice/info/${notice.id}.html" target="_blank">${notice.title}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <div role="tabpanel" class="tab-pane contact" id="contact">
                <h2>Email:<br/>
                    <a href="mailto:2523**242@qq.com" data-toggle="tooltip" data-placement="bottom"
                       title="2523**242@qq.com">2523**242@qq.com</a></h2>
            </div>
        </div>
    </div>
    <div class="sentence"><strong>每日一句</strong>
        <h2 id="widget-date"></h2>
        <p>当您的才华还撑不起你的野心时，那么你就应该静下心来学习。</p>
    </div>
    <div id="search" class="sidebar-block search" role="search">
        <h2 class="title"><strong>搜索</strong></h2>
        <form class="navbar-form" action="/novel.html" method="get">
            <div class="input-group">
                <input type="text" class="form-control" size="35" name="wd" placeholder="请输入关键字">
                <span class="input-group-btn">
                    <button class="btn btn-default btn-search" type="submit">搜索</button>
                </span>
            </div>
        </form>
    </div>
    <div class="sidebar-block recommend">
        <h2 class="title"><strong>热门推荐</strong></h2>
        <ul>
            <c:forEach items="${novelListOrderByRecoms}" var="novel">
                <li>
                    <a target="_blank" href="/novel/info/${novel.id}.html">
                        <span class="thumb"><img src="${novel.coverUrl}" alt=""></span>
                        <span class="text">${novel.title}</span> <span class="text-muted">推荐(${novel.recommends})</span>
                    </a>
                </li>
            </c:forEach>

        </ul>
    </div>
    <div class="sidebar-block recommend favors">
        <h2 class="title"><strong>收藏排行</strong></h2>
        <ul>
            <c:forEach items="${novelListOrderByFavors}" var="novel">
                <li>
                    <a target="_blank" href="/novel/info/${novel.id}.html">
                        <span class="thumb"><img src="${novel.coverUrl}" alt=""></span>
                        <span class="text">${novel.title}</span> <span class="text-muted">收藏(${novel.favors})</span>
                    </a>
                </li>
            </c:forEach>

        </ul>
    </div>
</aside>
