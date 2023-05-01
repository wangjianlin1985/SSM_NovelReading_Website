<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <title>牛码小说网</title>
    <meta name="Keywords" content=""/>
    <meta name="description" content=""/>
    <%@include file="/static/comm/front/taglib.jsp" %>
</head>

<body>
<section class="container user-select">
    <%@include file="/static/comm/front/left.jsp" %>
    <!--/超小屏幕可见-->
    <div class="content-wrap"><!--内容-->
        <div class="content">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel"> <!--banner-->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <c:forEach items="${bannerList}" var="banner" varStatus="status">
                        <div class="item ${status.index==0?'active':''}">
                            <a href="${banner.link}" target="_blank">
                                <img src="${banner.cover}" alt=""/>
                            </a>
                            <div class="carousel-caption">${banner.title}</div>
                            <span class="carousel-bg"></span>
                        </div>
                    </c:forEach>
                </div>
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> <span class="sr-only">Next</span>
                </a>
            </div>
            <!--/banner-->
            <div class="content-block hot-content hidden-xs">
                <h2 class="title"><strong>本站排行</strong></h2>
                <ul>
                    <c:forEach items="${novelListOrderByClick}" var="novel" varStatus="status">
                        <li ${status.index==0?'class="large"':''}>
                            <a href="/novel/info/${novel.id}.html" target="_blank">
                                <img src="${novel.coverUrl}" alt="">
                                <h3> ${novel.title} </h3>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="content-block new-content">
                <h2 class="title"><strong>最近更新</strong></h2>
                <div class="row">
                    <c:forEach var="novel" items="${novelList}" varStatus="status">
                        <div class="news-list">
                            <div class="news-img col-xs-5 col-sm-5 col-md-2">
                                <a target="_blank" href=""><img src="${novel.coverUrl}" alt=""> </a>
                            </div>
                            <div class="news-info col-xs-7 col-sm-7 col-md-10">
                                <dl>
                                    <dt>
                                        <c:if test="${novel.typeId==1}">
                                            <a class="cat" href="/novel.html?type=1">
                                                玄幻魔法
                                                <i></i>
                                            </a>
                                        </c:if>
                                        <c:if test="${novel.typeId==2}">
                                            <a class="cat" href="/novel.html?type=2">
                                                武侠修真
                                                <i></i>
                                            </a>
                                        </c:if>
                                        <c:if test="${novel.typeId==3}">
                                            <a class="cat" href="/novel.html?type=3">
                                                都市言情
                                                <i></i>
                                            </a>
                                        </c:if>
                                        <c:if test="${novel.typeId==4}">
                                            <a class="cat" href="/novel.html?type=4">
                                                历史军事
                                                <i></i>
                                            </a>
                                        </c:if>
                                        <c:if test="${novel.typeId==5}">
                                            <a class="cat" href="/novel.html?type=5">
                                                网游竞技
                                                <i></i>
                                            </a>
                                        </c:if>
                                        <c:if test="${novel.typeId==6}">
                                            <a class="cat" href="/novel.html?type=6">
                                                科幻小说
                                                <i></i>
                                            </a>
                                        </c:if>
                                        <c:if test="${novel.typeId==7}">
                                            <a class="cat" href="/novel.html?type=7">
                                                恐怖灵异
                                                <i></i>
                                            </a>
                                        </c:if>
                                        <c:if test="${novel.typeId==8}">
                                            <a class="cat" href="/novel.html?type=8">
                                                女生小说
                                                <i></i>
                                            </a>
                                        </c:if>
                                        <c:if test="${novel.typeId==9}">
                                            <a class="cat" href="/novel.html?type=9">
                                                其他小说
                                                <i></i>
                                            </a>
                                        </c:if>

                                        <a class="title" href="/novel/info/${novel.id}.html"> ${novel.title} </a>
                                    </dt>
                                    <dd>
                                        <span><i class="glyphicon glyphicon-user"></i> ${novel.author}</span>
                                        <span><i class="glyphicon glyphicon-eye-open"></i> 共${novel.clicks}人点击</span>
                                        <span><i class="glyphicon glyphicon-comment"></i> ${novel.recommends}个推荐</span>
                                    </dd>
                                    <dd class="text">${novel.summaryText}</dd>
                                </dl>
                                <div class="news_bot col-sm-7 col-md-8">
                                        <span class="tags  visible-lg visible-md">
                                          <strong>最近更新：</strong> <a
                                                href="/chapter/${novel.id}.html">${novel.latestChapter.title}</a>&nbsp;&nbsp;·&nbsp;&nbsp;${novel.latestChapter.createTime}
                                        </span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>
    </div>
    <!--/内容-->
    <%@include file="/static/comm/front/right.jsp" %>
    <!--/右侧>992px显示-->
    <footer class="footer">POWERED BY &copy;<a href="http://www.baidu.com">项目设计 baidu.com</a> ALL RIGHTS RESERVED &nbsp;&nbsp;&nbsp;<span><a
            href="http://www.baidu.com" target="_blank">粤ICP备xxxx号</a></span> <span
            style="display:none"><a href="">网站统计</a></span></footer>
</section>
<div><a href="javascript:;" class="gotop" style="display:none;"></a></div>
<!--/返回顶部-->
<script type="text/javascript" src="/static/js/front/common.js"></script>
</body>
</html>
