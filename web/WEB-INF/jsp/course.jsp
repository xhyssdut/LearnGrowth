<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AaahhhXin
  Date: 2016/7/12
  Time: 8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href=
          <c:url value="/css/course.css"/> rel="stylesheet"/>
    <script src=<c:url value="/js/jquery-3.0.0.min.js"/>></script>
    <script src=<c:url value="/js/jquerysession.js"/>></script>

    <title>${course.course_name}</title>
    <script>
        function videoended(o) {
            alert(o.nodeValue);
        }
        window.onload = function () {
            $(".user-button").first().text($.session.get("user_name"));
            var state = ${user_course.state};
            if (state == 0) {
                $(".buy").removeClass("alt_hid");
                $("#hid-back").css("display", "block");
            }
        }
        function videoended() {
            var json = {
                "user_id": ${user_course.user_id},
                "course_id": ${user_course.course_id},
                "state": 2
            };
            $.ajax({
                url:" <c:url value="/watchCourse/${course.a_integrate}"/>",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(json)
            });
        }
        function buyCourse() {
                if(${user.getintegrate()-course.m_integrate>0}){
                    var json = {
                        "user_id": ${user_course.user_id},
                        "course_id": ${user_course.course_id},
                        "state": 1
                    };
                    $.ajax({
                        url:" <c:url value="/buyCourse/${course.m_integrate}"/>",
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(json),
                        success: function (data) {
                            var user_course = JSON.parse(data);
                            if(user_course.state==1){
                                $("#hint").text("购买成功！")
                                $(".buy").addClass("alt_hid");
                                $("#hid-back").css("display", "none");
                            }
                        }
                    });
                }else {
                    $("#hint").text("您的积分不足！将退出到首页！");
                    setTimeout(function () {
                        window.location = "main";
                    }, 2000);
                }
        }
        function cancel() {
            history.back();
        }
    </script>
</head>
<body>
<div id="hid-back"></div>
<div class="navi">
    <a class="navi-button" href=<c:url value="/main"/>>首页</a>
    <a class="user-button" id="user-name-label" href="#"></a>
    <a class="user-button" href=<c:url value="/manage"/>>个人中心</a>
</div>
<div class="header"><span>${course.course_name}</span></div>
<div class="video">
    <video controls onended="videoended()">
        <source src="<c:url value="/videos/${course.course_url}"/>">
    </video>
</div>
<div class="buy alt_hid">
    <h2>购买窗口</h2>
    <span id ="hint">您尚未购买该视频！</span>
    <span>您的账户里还有${user.getintegrate()}积分</span>
    <span>购买之后还剩${user.getintegrate()-course.m_integrate}积分</span>
    <div class="buy-button" onclick="buyCourse()">购买</div>
    <div class="buy-button" onclick="cancel()">取消</div>
</div>

<div class="video-list">
    <div id=video-navi>课程清单</div>
    <div class="lesson-box">
        <ul>
            <c:forEach items="${course_stateList}" var="itercourse">
                <c:choose>
                    <c:when test="${itercourse.state==0}">
                        <li class="on">
                            <div class="text-box">
                                <a href=<c:url
                                        value="/course/${itercourse.catena_id}/${itercourse.course_id}"/>>${itercourse.course_name}</a>
                                <p>${itercourse.course_description}</p>
                            </div>
                        </li>
                    </c:when>
                    <c:when test="${itercourse.state==1}">
                        <li class="on">
                            <div class="text-box">
                                <a href=<c:url
                                        value="/course/${itercourse.catena_id}/${itercourse.course_id}"/>>${itercourse.course_position}.${itercourse.course_name}</a>
                                <p>${itercourse.course_description}</p>
                            </div>
                        </li>
                    </c:when>
                    <c:when test="${itercourse.state==2}">
                        <li class="on">
                            <div class="text-box">
                                <a href=<c:url
                                        value="/course/${itercourse.catena_id}/${itercourse.course_id}"/>>${itercourse.course_name}</a>
                                <p>${itercourse.course_description}</p>
                            </div>
                        </li>
                    </c:when>
                </c:choose>
            </c:forEach>
        </ul>

    </div>
</div>
</body>
</html>
