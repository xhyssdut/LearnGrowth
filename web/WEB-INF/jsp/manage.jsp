<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AaahhhXin
  Date: 2016/7/13
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href=
          <c:url value="/css/manage.css"/> rel="stylesheet"/>
    <script src=<c:url value="/js/jquery-3.0.0.min.js"/>></script>
    <script src=<c:url value="/js/jquerysession.js"/>></script>

    <script src=<c:url value="/js/manage.js"/>></script>
    <title>个人</title>
</head>
<body>
<div class="navi">
    <a class="navi-button" href=<c:url value="/main"/>>首页</a>
    <a class="user-button" id="user-name-label" href="#"></a>
    <a class="user-button" href=<c:url value="/manage"/>>个人中心</a>
</div>
<ul class="tabs">
    <li class="active" rel="tab1" onclick="getintegrate(),getPaidCourse()">已购买课程</li>
    <li rel="tab2" onclick="getintegrate(),getUnpaidCourse()">未购买课程</li>
    <li rel="tab3">修改密码</li>
    <span class="alert"><span class= "integrate"></span>
</ul>

<div class="tab_container">
    <h3 class="d_active tab_drawer_heading" rel="tab1">Tab 1</h3>
    <div id="tab1" class="tab_content">
        <table>
            <caption>Statement Summary</caption>
            <thead>
            <tr>
                <th scope="col">课程名</th>
                <th scope="col">课程描述</th>
                <th scope="col">消耗积分</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    <!-- #tab1 -->
    <h3 class="tab_drawer_heading" rel="tab2">Tab 2</h3>
    <div id="tab2" class="tab_content">
        <table>
            <caption>Statement Summary</caption>
            <thead>
            <tr>
                <th scope="col">Account</th>
                <th scope="col">Due Date</th>
                <th scope="col">Amount</th>
                <th scope="col">Period</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    <!-- #tab2 -->
    <h3 class="tab_drawer_heading" rel="tab3">Tab 3</h3>
    <div id="tab3" class="tab_content">
        <div class="signup">
            <form>
                <div class="form-input"> <input id="old-password" type="password" placeholder="请输入旧密码"/></div>
                <div class="form-input"> <input id="new-password" type="password" placeholder="请输入新密码"/></div>

                <div class="form-input"> <input id="new-pass-confirm" type="password" placeholder="请确认新密码" onblur="confirm()"/><span id="prehint">密码不匹配！</span></div>
                <div class="form-input"><button type="button" onclick="changePass()">修改密码</button></div>
                <div class="form-input"><span class="hint" id="reg_hint">66666</span></div>
            </form>
        </div>
    </div>
    <!-- #tab3 -->
</div>
<!-- .tab_container -->
</div>
</body>
</html>
