<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AaahhhXin
  Date: 2016/7/9
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <link href=<c:url value="/css/main.css"/> rel="stylesheet"/>
    <script src=<c:url value="/js/jquery-3.0.0.min.js"/>></script>
    <script src=<c:url value="/js/jquerysession.js"/>></script>
    <script src=<c:url value="/js/main.js" />></script>
    <title>Title</title>
</head>
<body>
<div class="navi">
    <a class="navi-button" href=<c:url value="/main"/>>首页</a>
    <a class="user-button" id="user-name-label" href="#"></a>
    <a class="user-button" href=<c:url value="/manage"/>>个人中心</a>
</div>
<div class="classpath">
    <div class="learnbox">
        <c:choose>
            <c:when test="${catenas.size()==0}">
                <div class="title">
                    <strong><a>职业路径图:暂时没有课程</a></strong>
                </div>
            </c:when>
            <c:otherwise>
                <div class="title">
                    <strong><a>职业路径图</a></strong>
                </div>
                <div class="learncard">
                    <c:forEach items="${catenas}" var="tel">
                        <a href="catena/${tel.catena_id}">
                            <img src=<c:url value="/imgs/${tel.img_url}"/> />
                            <h2>${tel.catena_name}</h2>
                            <p>月薪${tel.salary}</p>
                            <p>${tel.catena_description}</p>
                            <button class="greenbtn2">立即学习</button>
                        </a>
                    </c:forEach>
                    <div style="clear:both"></div>
                </div>
            </c:otherwise>
        </c:choose>

    </div>

</div>

</body>
</html>