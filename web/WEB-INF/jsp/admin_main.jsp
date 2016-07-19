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
    <script src=<c:url value="/js/ajaxfileupload.js"/>></script>
    <script src=<c:url value="/js/adminmain.js"/>></script>
    <title>个人</title>
</head>
<body>
<div class="navi">
    <a class="user-button" id="user-name-label" href="#"></a>
</div>
<ul class="tabs">
    <li class="active" rel="tab1" onclick="getCatena()">系列管理</li>
    <li rel="tab2" onclick="getCourse()">课程管理</li>
    <li rel="tab3" onclick="getUser()">用户管理</li>
    <li rel="tab4">修改密码</li>
    <span class="integrate"></span>
</ul>
<div id="detailcatena" class="window alt_hid">
    <table>
        <caption>课程管理</caption>
        <thead>
        <tr>
            <th scope="col">课程名</th>
            <th scope="col">课程描述</th>
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col">添加</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
        <div class="form-input">
            <button type="button"  onclick="cancel()">关闭</button>
        </div>
    </table>
</div>
<div class="window alt_hid" id="deletecatena">
    <h2>确定删除</h2>
    <span id="deletehint">确定您要删除该系列吗？</span>
    <div class="buy-button" id="deleteCatenaSure"onclick="deleteCatena(this)">确认</div>
    <div class="buy-button"onclick="cancel()">取消</div>
</div>
<div class="window alt_hid" id="deletecourse">
    <h2>确定删除</h2>
    <span id="deletecoursehint">确定您要删除该课程吗？</span>
    <div class="buy-button" id="deleteCourseSure"onclick="deleteCourse(this)">确认</div>
    <div class="buy-button"onclick="cancel()">取消</div>
</div>
<div class="window alt_hid" id="deleteuser">
    <h2>确定删除</h2>
    <span id="deleteuserhint">确定您要删除该用户吗？</span>
    <div class="buy-button" id="deleteUserSure"onclick="deleteUser(this)">确认</div>
    <div class="buy-button"onclick="cancel()">取消</div>
</div>
<div class="window alt_hid" id="changecatena">
    <h2>修改系列</h2>
    <form id= "changeCatenaForm" enctype ="multipart/form-data" name="changeCatenaForm">
        <div class="form-input" hidden="hidden">系列名:<input id="changecatena_id" name="changecatena_id" type="text"/></div>

        <div class="form-input">系列名:<input id="changecatena_name" name="changecatena_name" type="text"/></div>
        <div class="form-input">系列描述:<input id="changecatena_des" name="changecatena_des" type="text"/></div>
        <div class="form-input">薪资<input id="changecatena_salary" name="changecatena_salary" type="text"/></div>
        <img id="changecatena_img" src="">
        <div class="form-input">图片<input type="file" name="catenaImg" onchange="changeimg(this)" /></div>
        <div class="form-input">
            <button type="button" id="changeDetailSure" onclick="changeCatena()">修改</button>
            <button type="button"  onclick="cancel()">取消</button>
        </div>
    </form>
</div>
<div class="window alt_hid" id="addcatena">
    <h2>添加系列</h2>
    <form id= "addCatenaForm" enctype ="multipart/form-data" name="addCatenaForm">
        <div class="form-input" hidden="hidden">系列名:<input id="addcatena_id" name="addcatena_id" type="text"/></div>
        <div class="form-input">系列名:<input id="addcatena_name" name="addcatena_name" type="text"/></div>
        <div class="form-input">系列描述:<input id="addcatena_des" name="addcatena_des" type="text"/></div>

        <div class="form-input">薪资<input id="addcatena_salary" name="addcatena_salary" type="text"/></div>
        <img id="addcatena_img" src="">
        <div class="form-input">图片<input type="file" name="catenaImg" onchange="changeimg(this)" /></div>
        <div class="form-input">
            <button type="button" id="addDetailSure" onclick="addCatena()">添加</button>
            <button type="button"  onclick="cancel()">取消</button>
        </div>
    </form>
</div>
<div class="window alt_hid" id="changecourse">
    <h2>修改课程</h2>
    <form id= "changeCourseForm" enctype ="multipart/form-data" name="changeCatenaForm">
        <div class="form-input" hidden="hidden"><input id="changecourse_id" name="changecourse_id" type="text"/></div>
        <div class="form-input">课程名:<input id="changecourse_name" name="changecourse_name" type="text"/></div>
        <div class="form-input">课程描述:<input id="changecourse_des" name="changecourse_des" type="text"/></div>
        <div class="form-input">所属课程</div>
        <select  id="changecourse_catenaid"name="changecourse_catenaid">

            <option value="65535">先不加入任何课程</option>
        </select>
        <div class="form-input">消耗积分<input id="changecourse_minte" name="changecourse_minte" type="text"/></div>
        <div class="form-input">生成积分<input id="changecourse_ainte" name="changecourse_ainte" type="text"/></div>
        <div class="form-input">视频<input type="file" name="courseVideo" /></div>
        <div class="form-input">
            <button type="button" id="changeCourseSure" onclick="changeCourse()">修改</button>
            <button type="button"  onclick="cancel()">取消</button>
        </div>
    </form>
</div>
<div class="window alt_hid" id="addcourse">
    <h2>添加课程</h2>
    <form id= "addCourseForm" enctype ="multipart/form-data" name="addCatenaForm">
        <div class="form-input">课程名:<input id="addcourse_name" name="addcourse_name" type="text"/></div>
        <div class="form-input">课程描述:<input id="addcourse_des" name="addcourse_des" type="text"/></div>
        <select  id="addcourse_catenaid"name="addcourse_catenaid">
            <option value="65535">先不加入任何课程</option>
        </select>
        <div class="form-input">消耗积分<input id="addcourse_minte" name="addcourse_minte" type="text"/></div>
        <div class="form-input">生成积分<input id="addcourse_ainte" name="addcourse_ainte" type="text"/></div>
        <div class="form-input">视频<input type="file" name="courseVideo" /></div>
        <div class="form-input">
            <button type="button" id="addCourseSure" onclick="addCourse()">修改</button>
            <button type="button"  onclick="cancel()">取消</button>
        </div>
    </form>
</div>
<div class="window alt_hid" id="changeUser">
    <h2>修改</h2>
    <form id= "changeUserForm">
        <div class="form-input">用户名:<input id="changeUserName" type="text"/></div>
        <div class="form-input">密码:<input id="changeUserPassword"type="text"/></div>

        <div class="form-input">积分:<input id="changeUserIntegrate"type="text"/></div>
        <div class="form-input">
            <button type="button" id="changeUserSure" onclick="changeUser(this)">修改</button>
            <button type="button"  onclick="cancel()">取消</button>
        </div>
    </form>
</div>
<div id="hid-back"></div>
<div class="tab_container">
    <h3 class="d_active tab_drawer_heading" rel="tab1">Tab 1</h3>
    <div id="tab1" class="tab_content">
        <table>
            <caption>系列管理</caption>
            <thead>
            <tr>
                <th scope="col">系列名</th>
                <th scope="col">系列描述</th>
                <th scope="col">薪资</th>
                <th scope="col">图片</th>
                <th scope="col"></th>
                <th scope="col"></th>
                <th scope="col" onclick="openCatenaAdd()">添加</th>
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
            <caption>课程管理</caption>
            <thead>
            <tr>
                <th scope="col">课程名</th>
                <th scope="col">对应系列名</th>
                <th scope="col">课程概述</th>
                <th scope="col">花费积分</th>
                <th scope="col">得到积分</th>
                <th scope="col">视频地址</th>
                <th scope="col"></th>
                <th scope="col" onclick="openCourseAdd()">添加</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    <!-- #tab2 -->
    <h3 class="tab_drawer_heading" rel="tab3">Tab 3</h3>
    <div id="tab3" class="tab_content">
        <table>
            <caption>用户管理</caption>
            <thead>
            <tr>
                <th scope="col">用户名</th>
                <th scope="col">密码</th>
                <th scope="col">积分</th>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    <!-- #tab3 -->
    <h3 class="tab_drawer_heading" rel="tab3">Tab 4</h3>
    <div id="tab4" class="tab_content">
        <div class="signup">
            <form>
                <div class="form-input"><input id="old-password" type="password" placeholder="请输入旧密码"/></div>
                <div class="form-input"><input id="new-password" type="password" placeholder="请输入新密码"/></div>

                <div class="form-input"><input id="new-pass-confirm" type="password" placeholder="请确认新密码"
                                               onblur="confirm()"/><span id="prehint">密码不匹配！</span></div>
                <div class="form-input">
                    <button type="button" onclick="changePass()">修改密码</button>
                </div>
                <div class="form-input"><span class="hint" id="reg_hint">66666</span></div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
