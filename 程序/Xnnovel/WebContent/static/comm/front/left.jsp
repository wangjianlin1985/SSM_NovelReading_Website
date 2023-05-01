<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div class="hidden-xs header"><!--超小屏幕不显示-->
        <h1 class="logo">
            <a href="/" title="项目设计"></a></h1>
        <ul class="nav hidden-xs-nav">
            <input type="hidden" value="${type}" id="type">
            <li><a href="/"><span class="glyphicon glyphicon-home"></span>首页</a></li>
            <li <c:if test="${type==1}">class="active"</c:if>><a href="/novel.html?type=1"><span
                    class="glyphicon glyphicon-erase"></span>玄幻魔法</a></li>
            <li <c:if test="${type==2}">class="active"</c:if>><a href="/novel.html?type=2"><span
                    class="glyphicon glyphicon-inbox"></span>武侠修真</a></li>
            <li <c:if test="${type==3}">class="active"</c:if>><a href="/novel.html?type=3"><span
                    class="glyphicon glyphicon-globe"></span>都市言情</a></li>
            <li <c:if test="${type==4}">class="active"</c:if>><a href="/novel.html?type=4"><span
                    class="glyphicon glyphicon-globe"></span>历史军事</a></li>
            <li <c:if test="${type==5}">class="active"</c:if>><a href="/novel.html?type=5"><span
                    class="glyphicon glyphicon-globe"></span>网游竞技</a></li>
            <li <c:if test="${type==6}">class="active"</c:if>><a href="/novel.html?type=6"><span
                    class="glyphicon glyphicon-globe"></span>科幻小说</a></li>
            <li <c:if test="${type==7}">class="active"</c:if>><a href="/novel.html?type=7"><span
                    class="glyphicon glyphicon-globe"></span>恐怖灵异</a></li>
            <li <c:if test="${type==8}">class="active"</c:if>><a href="/novel.html?type=8"><span
                    class="glyphicon glyphicon-globe"></span>女生小说</a></li>
            <li <c:if test="${type==9}">class="active"</c:if>><a href="/novel.html?type=9"><span
                    class="glyphicon glyphicon-globe"></span>其他小说</a></li>
        </ul>
        <div class="feeds">
            <a class="feed feed-dnzm" href="http://www.baidu.com" target="_blank"><i></i>项目设计</a>
            <a class="feed feed-weixin" data-toggle="popover" data-trigger="hover" title="微信扫一扫" data-html="true"
               data-content="<img src='/static/images/weixin.png' alt=''>" href="javascript:;"
               target="_blank"><i></i>关注微信
            </a>
            <a class="feed feed-bilibili" href="https://www.baidu.com/385794766/" target="_blank"><i></i>哔哩哔哩</a>
            <a class="feed feed-rss" href="/index.html" target="_blank"><i></i>订阅本站</a>

        </div>
        <div class="wall"><a href="friendly.html" target="_blank">友情链接</a> | <a href="tags.html" target="_blank">标签云</a>
        </div>
    </div>
</header>

<!--登录注册模态框-->
<div class="modal fade user-select" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="/Admin/Index/login" method="post" id="login_form" >
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="loginModalLabel">登录</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="loginUserName">用户名</label>
                        <input type="text" class="form-control" id="loginUserName" placeholder="请输入用户名" autofocus
                               maxlength="15" autocomplete="off" required>
                    </div>
                    <div class="form-group">
                        <label for="loginPassword">密码</label>
                        <input type="password" class="form-control" id="loginPassword" placeholder="请输入密码"
                               maxlength="18" autocomplete="new-password" required>
                    </div>
                    <div class="text-right">
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="submit" class="btn btn-primary" id="login_submit">登录</button>
                        </div>
                    </div>
                    <a href="" data-toggle="modal" data-dismiss="modal" data-target="#registerModal">还没有账号？点我注册</a>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 注册窗口 -->
<div id="registerModal" class="modal fade" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            <div class="modal-title">
                <h1 class="text-center">注册</h1>
            </div>
            <div class="modal-body">
                <form class="form-group" action="" id="register_form">
                    <div class="form-group">
                        <label for="registerUserName">用户名</label>
                        <input class="form-control" type="text" placeholder="请输入用户名" autocomplete="off" id="registerUserName">
                    </div>
                    <div class="form-group">
                        <label for="registerUserName">昵称</label>
                        <input class="form-control" type="text" placeholder="请输入昵称" autocomplete="off" id="registerNickName">
                    </div>
                    <div class="form-group">
                        <label for="registerPassword">密码</label>
                        <input class="form-control" type="password" placeholder="请输入密码" autocomplete="new-password" id="registerPassword">
                    </div>
                    <div class="form-group">
                        <label for="registerConfirmPassword">再次输入密码</label>
                        <input class="form-control" type="password" placeholder="请输入确认密码" autocomplete="new-password" id="registerConfirmPassword">
                    </div>
                    <div class="text-right">
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="submit" class="btn btn-primary" id="register_submit">注册</button>
                        </div>
                    </div>
                    <a href="" data-toggle="modal" data-dismiss="modal" data-target="#loginModal">已有账号？点我登录</a>
                </form>
            </div>
        </div>
    </div>
</div>