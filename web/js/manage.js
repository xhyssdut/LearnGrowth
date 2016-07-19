/**
 * Created by AaahhhXin on 2016/7/13.
 */
window.onload = function () {
    $(".user-button").first().text($.session.get("user_name"));
    $(".tab_content").hide();
    $(".tab_content:first").show();

    /* if in tab mode */
    $("ul.tabs li").click(function() {

        $(".tab_content").hide();
        var activeTab = $(this).attr("rel");
        $("#"+activeTab).fadeIn();

        $("ul.tabs li").removeClass("active");
        $(this).addClass("active");

        $(".tab_drawer_heading").removeClass("d_active");
        $(".tab_drawer_heading[rel^='"+activeTab+"']").addClass("d_active");

    });
    /* if in drawer mode */
    $(".tab_drawer_heading").click(function() {

        $(".tab_content").hide();
        var d_activeTab = $(this).attr("rel");
        $("#"+d_activeTab).fadeIn();

        $(".tab_drawer_heading").removeClass("d_active");
        $(this).addClass("d_active");

        $("ul.tabs li").removeClass("active");
        $("ul.tabs li[rel^='"+d_activeTab+"']").addClass("active");
    });


    /* Extra class "tab_last"
     to add border to right side
     of last tab */
    $('ul.tabs li').last().addClass("tab_last");
    getPaidCourse();
    getintegrate();


}
function confirm() {
    var a = $("#new-password").val();
    var b = $("#new-pass-confirm").val();
    if (a == b) {
        return true;
    } else {
        $("#prehint").fadeIn(1000).delay(1000).fadeOut(1000);
        return false;
    }
}
function changePass() {
    if (!confirm())return false;
    else {
        var user = {
            "user_name": $("#old-password").val(),
            "password": $("#new-password").val(),
            "user_id": $.session.get("user_id"),
            "integrate": -1
        };
        $.ajax({
            url: "dochangePass",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(user),
            success: function (data) {
                user = JSON.parse(data);
                if (-1 == user.user_id) {
                    $("#reg_hint").text("密码错误，请重新输入！");
                    $("#reg_hint").fadeIn(100).delay(1000).fadeOut(800);
                    return false;
                } else if(-2 ==user.user_id){
                    $("#reg_hint").text("您的用户被删除，请联系管理员");
                    $("#reg_hint").fadeIn(100).delay(1000).fadeOut(800);
                    return true;
                } else {
                    $("#reg_hint").text("密码更改成功");
                    $("#reg_hint").fadeIn(100).delay(1000).fadeOut(800);
                }
            }

        });
    }
}
function getintegrate() {
    var json = {
        user_id: $.session.get("user_id")
    }
    $.ajax({
        url: "getintegrate",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(json),
        success: function (data) {
            var user = JSON.parse(data);
            $(".integrate").text(user.integrate);
        }
    });
}
function getUnpaidCourse() {
    $("#tab2").find("tbody").empty();
    var json = {
        user_id: $.session.get("user_id")
    }
    $.ajax({
        url: "getUnpaidCourse",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(json),
        success: function (data) {
            course_list = JSON.parse(data);
            for(var i =0 ;i<course_list.length;i++){
                var body = $("#tab2").find("tbody").first();
                var tr = document.createElement("tr");
                var name = document.createElement("td");
                $(name).text(course_list[i].course_name);
                var des = document.createElement("td");
                $(des).text(course_list[i].course_description);
                var integrate = document.createElement("td");
                $(integrate).text(course_list[i].m_integrate);
                var link = document.createElement("td");
                $(link).text("购买")
                $(link).attr("onclick","buyCourse(this)");
                $(link).attr("course_id",course_list[i].course_id);
                $(tr).append(name,des,integrate,link);
                $(body).append(tr);
            }
        }
    });
}
function getPaidCourse() {
    $("#tab1").find("tbody").empty();
    var json = {
        user_id: $.session.get("user_id")
    }
    $.ajax({
        url: "getPaidCourse",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(json),
        success: function (data) {
            course_list = JSON.parse(data);
            for(var i =0 ;i<course_list.length;i++){
                var body = $("#tab1").find("tbody").first();
                var tr = document.createElement("tr");
                var name = document.createElement("td");
                $(name).text(course_list[i].course_name);
                var des = document.createElement("td");
                $(des).text(course_list[i].course_description);
                var integrate = document.createElement("td");
                $(integrate).text(course_list[i].m_integrate);
                var link = document.createElement("td");
                $(link).text("前往")
                $(link).attr("onclick","goto(this)");
                $(link).attr("course_id",course_list[i].course_id);
                $(link).attr("catena_id",course_list[i].catena_id);
                $(tr).append(name,des,integrate,link);
                $(body).append(tr);
            }
        }
    });
}
function goto(o) {
    var course = o.getAttribute("course_id");
    var catena = o.getAttribute("catena_id");
    location.href = "course/"+catena+"/"+course;
}
function buyCourse(o) {
    var integrate = $(o).prev().text();
    alert(integrate);
    if ($(".integrate").text() -integrate> 0) {
        var json = {
            "user_id": $.session.get("user_id"),
            "course_id": o.getAttribute("course_id"),
            "state": 1
        };
        $.ajax({
            url: "buyCourse/"+integrate,
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(json),
            success: function (data) {
                var user_course = JSON.parse(data);
                if (user_course.state == 1) {
                    alert("购买成功！");
                    getUnpaidCourse();
                    getintegrate();
                }
            }
        });
    } else {
        alert("积分不足，购买失败！");
    }
}
