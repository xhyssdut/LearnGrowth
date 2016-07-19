<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AaahhhXin
  Date: 2016/7/11
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href=<c:url value="/css/catena.css"/> rel="stylesheet"/>
    <script src=<c:url value="/js/jquery-3.0.0.min.js"/>></script>
    <script src=<c:url value="/js/jquerysession.js"/>></script>
    <script src=<c:url value="/js/catena.js" />></script>
</head>
<body>
<div class="navi">
    <a class="navi-button" href=<c:url value="/main"/>>首页</a>
    <a class="user-button" id="user-name-label" href="#"></a>
    <a class="user-button" href=<c:url value="/manage"/>>个人中心</a>
</div>
<div class="wrap">
    <h1 class="caption">${catena.catena_name}</h1>
    <div class="lesson-list">
        <header>
            <ul class="status">
                <li><i class="learn-state learned"></i>已学习</li>
                <li><i class="learn-state bought"></i>已购买</li>
                <li><i class="learn-state none"></i>未购买</li>
            </ul>
        </header>
        <c:choose>
            <c:when test="${course_stateList.size()==0}">
                <div class="course odd"><a>暂时还没有视频哟！！</a></div>
            </c:when>
            <c:otherwise>
                <c:forEach items="${course_stateList}" var="itercourse" varStatus="obj">
                    <c:if test="${obj.count%4==0||obj.count%4==3}">
                        <c:choose>
                            <c:when test="${itercourse.state==0}">
                                <div class="course even"><a href=<c:url value="/course/${itercourse.catena_id}/${itercourse.course_id}"/>>${itercourse.course_position}.${itercourse.course_name}<i class="learn-state none"></i></a></div>
                            </c:when>
                            <c:when test="${itercourse.state==1}">
                                <div class="course even"><a href=<c:url value="/course/${itercourse.catena_id}/${itercourse.course_id}"/>>${itercourse.course_position}.${itercourse.course_name}<i class="learn-state bought"></i></a></div>
                            </c:when>
                            <c:when test="${itercourse.state==2}">
                                <div class="course even"><a href=<c:url value="/course/${itercourse.catena_id}/${itercourse.course_id}"/>>${itercourse.course_position}.${itercourse.course_name}<i class="learn-state learned"></i></a></div>
                            </c:when>
                        </c:choose>
                    </c:if>
                    <c:if test="${obj.count%4==1||obj.count%4==2}">
                        <c:choose>
                            <c:when test="${itercourse.state==0}">
                                <div class="course odd"><a href=<c:url value="/course/${itercourse.catena_id}/${itercourse.course_id}"/>>${itercourse.course_position}.${itercourse.course_name}<i class="learn-state none"></i></a></div>
                            </c:when>
                            <c:when test="${itercourse.state==1}">
                                <div class="course odd"><a href=<c:url value="/course/${itercourse.catena_id}/${itercourse.course_id}"/>>${itercourse.course_position}.${itercourse.course_name}<i class="learn-state bought"></i></a></div>
                            </c:when>
                            <c:when test="${itercourse.state==2}">
                                <div class="course odd"><a href=<c:url value="/course/${itercourse.catena_id}/${itercourse.course_id}"/>>${itercourse.course_position}.${itercourse.course_name}<i class="learn-state learned"></i></a></div>
                            </c:when>
                        </c:choose>
                    </c:if>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
