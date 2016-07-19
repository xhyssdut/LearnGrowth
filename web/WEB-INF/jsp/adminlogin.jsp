<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: AaahhhXin
  Date: 2016/7/5
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ch-ZN">
<head>
    <meta charset="utf-8">
    <link href=<c:url value="/css/index.css"/> rel="stylesheet"/>
    <script src=<c:url value="/js/jquery-3.0.0.min.js"/>></script>
    <script src=<c:url value="/js/jquerysession.js"/>></script>
    <script src=<c:url value="/js/admin.js"/>></script>

    <title>Title</title>
</head>
<body>
<h2>管理员入口</h2>
<div class="login-box">
    <div id="login-header">
        <a href="#" class="active" id="login-link">登录</a>
    </div>
    <div class="login">
        <form id="loginform">
            <div class="form-input"> <input id="logname" type="email" placeholder="请输入邮箱"/></div>
            <div class="form-input"> <input id="logpassword" type="password" placeholder="请输入密码"/></div>
            <div class="form-input"><button onclick="login()" type="button">登录</button></div>
            <div class="form-input"><span class="hint" id="log_hint">666</span></div>
        </form>
    </div>
</div>
</body>


