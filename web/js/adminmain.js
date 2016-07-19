
window.onload = function () {
    $(".user-button").first().text($.session.get("admin_name"));
    $(".tab_content").hide();
    $(".tab_content:first").show();

    /* if in tab mode */
    $("ul.tabs li").click(function () {

        $(".tab_content").hide();
        var activeTab = $(this).attr("rel");
        $("#" + activeTab).fadeIn();

        $("ul.tabs li").removeClass("active");
        $(this).addClass("active");

        $(".tab_drawer_heading").removeClass("d_active");
        $(".tab_drawer_heading[rel^='" + activeTab + "']").addClass("d_active");

    });
    /* if in drawer mode */
    $(".tab_drawer_heading").click(function () {

        $(".tab_content").hide();
        var d_activeTab = $(this).attr("rel");
        $("#" + d_activeTab).fadeIn();

        $(".tab_drawer_heading").removeClass("d_active");
        $(this).addClass("d_active");

        $("ul.tabs li").removeClass("active");
        $("ul.tabs li[rel^='" + d_activeTab + "']").addClass("active");
    });


    /* Extra class "tab_last"
     to add border to right side
     of last tab */
    $('ul.tabs li').last().addClass("tab_last");
    getCatena();
}
function getCourse() {
    $("#tab2").find("tbody").empty();
    $.ajax({
        url: "getCourse",
        type: "POST",
        data: "666",
        contentType: "application/json",
        success: function (data) {
            course_list = JSON.parse(data);
            for (var i = 0; i < course_list.length; i++) {
                var body = $("#tab2").find("tbody").first();
                var tr = document.createElement("tr");
                var name = document.createElement("td");
                $(name).text(course_list[i].course_name);
                var cname = document.createElement("td");
                $(cname).text(course_list[i].catena_name);
                var des = document.createElement("td");
                $(des).text(course_list[i].course_description);
                var minte = document.createElement("td");
                $(minte).text(course_list[i].m_integrate);
                var ainte = document.createElement("td");
                $(ainte).text(course_list[i].a_integrate);
                var url = document.createElement("td");
                $(url).text(course_list[i].course_url);
                var ondelete = document.createElement("td");
                $(ondelete).text("删除")
                $(ondelete).attr("onclick", "openCourseDelete(this)");
                $(ondelete).attr("course_id", course_list[i].course_id);
                $(ondelete).attr("catena_id", course_list[i].catena_id);
                $(ondelete).attr("course_position", course_list[i].course_position);
                var onchange = document.createElement("td");
                $(onchange).text("修改")
                $(onchange).attr("onclick", "openChangeCourse(this)");
                $(onchange).attr("catena_id", course_list[i].catena_id);
                $(onchange).attr("course_id", course_list[i].course_id);
                $(tr).append(name, cname, des, minte, ainte, url, ondelete, onchange);
                $(body).append(tr);
            }
        }
    });
}
function openCatenaAdd(){
    $("#addcatena").removeClass("alt_hid");
    $("#hid-back").css("display", "block");
}
function openCourseAdd(){
    $("#addcourse").removeClass("alt_hid");
    $("#hid-back").css("display", "block");
    $.ajax({
        url: "getCatena",
        type: "POST",
        data: "666",
        contentType: "application/json",
        success: function (data) {
            var catena_list = JSON.parse(data);
            var select = $("#addcourse_catenaid")
            for (var i = 0; i < catena_list.length; i++) {
                var option = document.createElement("option");
                $(option).attr("value",catena_list[i].catena_id)
                $(option).text(catena_list[i].catena_name);
                $(select).append(option);
            }
        }
    })
}
function addCatena() {
    var formData = new FormData($("#addCatenaForm")[0]);
    $.ajax({
        url: 'addCatena',
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            $(".window").delay(1000).addClass("alt_hid");
            $("#hid-back").delay(100).css("display", "none");
            getCatena();
        },
        error: function (returndata) {
            alert(returndata+"77777");
        }
    });
}
function openChangeCourse(o) {
    $("#changecourse").removeClass("alt_hid");
    $("#hid-back").css("display", "block");
    $("#changecourse_id").val($(o).attr("course_id"));
    $("#changeCourseSure").attr("catena_id", $(o).attr("catena_id"));
    var forms = $(o).parent().find("td");
    $("#changecourse_name").val($(forms[1]).text());
    $("#changecourse_des").val($(forms[2]).text());
    $("#changecourse_minte").val($(forms[3]).text());
    $("#changecourse_ainte").val( $(forms[4]).text());
    $.ajax({
        url: "getCatena",
        type: "POST",
        data: "666",
        contentType: "application/json",
        success: function (data) {
            var catena_list = JSON.parse(data);
            var select = $("#changecourse_catenaid")
            for (var i = 0; i < catena_list.length; i++) {
                var option = document.createElement("option");
                $(option).attr("value",catena_list[i].catena_id)
                $(option).text(catena_list[i].catena_name);
                $(select).append(option);
            }
        }
    })
}
function openCourseDelete(o) {
    $("#deletecourse").removeClass("alt_hid")
    $("#hid-back").css("display", "block");
    $("#deleteCourseSure").attr("course_id", $(o).attr("course_id"));
    $("#deleteCourseSure").attr("catena_id", $(o).attr("catena_id"));
    $("#deleteCourseSure").attr("course_position", $(o).attr("course_position"));
}
function changeCourse() {
    var formData = new FormData($("#changeCourseForm")[0]);
    $.ajax({
        url: 'changeCourse',
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            $(".window").delay(1000).addClass("alt_hid");
            $("#hid-back").delay(100).css("display", "none");
            getCourse();
        },
        error: function (returndata) {
            alert(returndata+"77777");
        }
    });
}
function addCourse() {
    var formData = new FormData($("#addCourseForm")[0]);
    $.ajax({
        url: 'addCourse',
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            $(".window").delay(1000).addClass("alt_hid");
            $("#hid-back").delay(100).css("display", "none");
            getCourse();
        },
        error: function (returndata) {
            alert(returndata+"77777");
        }
    });
}
function deleteCourse(o) {
    var json = {
        catena_id: $(o).attr("catena_id"),
        course_id: $(o).attr("course_id"),
        course_position : $(o).attr("course_position")
    };
    $.ajax({
        url: "deleteCourseinCatena",
        type: "POST",
        data: JSON.stringify(json),
        contentType: "application/json",
    });
    $.ajax({
        url: "deleteCourse",
        type: "POST",
        data: JSON.stringify(json),
        contentType: "application/json",
        success: function () {
            $("#deletecoursehint").text("删除成功！");
            $(".window").delay(1000).addClass("alt_hid");
            $("#hid-back").delay(100).css("display", "none");
            getCourse();
        }
    });


}
function getCatena() {
    $("#tab1").find("tbody").empty();
    $.ajax({
        url: "getCatena",
        type: "POST",
        data: "666",
        contentType: "application/json",
        success: function (data) {
            catena_list = JSON.parse(data);
            for (var i = 0; i < catena_list.length; i++) {
                var body = $("#tab1").find("tbody").first();
                var tr = document.createElement("tr");
                var id = document.createElement("td");
                $(id).attr("hidden","hidden")
                $(id).text(catena_list[i].catena_id);
                var name = document.createElement("td");
                $(name).text(catena_list[i].catena_name);
                var des = document.createElement("td");
                $(des).text(catena_list[i].catena_description);
                var salary = document.createElement("td");
                $(salary).text(catena_list[i].salary);
                var url = document.createElement("td");
                $(url).text(catena_list[i].img_url);
                var ondelete = document.createElement("td");
                $(ondelete).text("删除")
                $(ondelete).attr("onclick", "openCatenaDelete(this)");
                $(ondelete).attr("catena_id", catena_list[i].catena_id);
                var ondetail = document.createElement("td");
                $(ondetail).text("详情")
                $(ondetail).attr("onclick", "openCatenaDetail(this)");
                $(ondetail).attr("catena_id", catena_list[i].catena_id);
                var onchange = document.createElement("td");
                $(onchange).text("修改")
                $(onchange).attr("onclick", "openCatenaChange(this)");
                $(onchange).attr("catena_id", catena_list[i].catena_id);
                $(tr).append(name, des, salary, url, ondelete, ondetail, onchange);
                $(body).append(tr);
            }
        }
    });
}
function openCatenaDetail(o) {
    $("#detailcatena").removeClass("alt_hid")
    $("#hid-back").css("display", "block");
    $("#detailcatena").find("tbody").empty();
    var json = {
        catena_id: $(o).attr("catena_id")
    };
    $.ajax({
        url: "getCourseByCatenaId",
        type: "POST",
        data: JSON.stringify(json),
        contentType: "application/json",
        success: function (data) {
            course_list = JSON.parse(data);
            for (var i = 0; i < course_list.length; i++) {
                var body = $("#detailcatena").find("tbody").first();
                var tr = document.createElement("tr");
                var name = document.createElement("td");
                $(name).text(course_list[i].course_name);
                var des = document.createElement("td");
                $(des).text(course_list[i].course_description);
                var ondelete = document.createElement("td");
                $(ondelete).text("删除")
                $(ondelete).attr("onclick", "deleteCourseInCatena(this)");
                $(ondelete).attr("course_id", course_list[i].course_id);
                $(ondelete).attr("catena_id", course_list[i].catena_id);
                $(ondelete).attr("course_position", course_list[i].course_position);
                var onUp = document.createElement("td");
                $(onUp).text("向上")
                if (i != 0) {
                    $(onUp).attr("onclick", "UpCourseInCatena(this)");
                }
                $(onUp).attr("course_position", course_list[i].course_position);
                $(onUp).attr("catena_id", course_list[i].catena_id);
                var onDown = document.createElement("td");
                $(onDown).text("向下")
                if (i != course_list.length - 1) {
                    $(onDown).attr("onclick", "DownCourseInCatena(this)");
                }
                $(onDown).attr("course_position", course_list[i].course_position);
                $(onDown).attr("catena_id", course_list[i].catena_id);
                $(tr).append(name, des, ondelete, onUp, onDown);
                $(body).append(tr);
            }
        }
    });
}
function deleteCourseInCatena(o) {
    var json = {
        catena_id: $(o).attr("catena_id"),
        course_id: $(o).attr("course_id"),
        course_position : $(o).attr("course_position")
    };
    $.ajax({
        url: "deleteCourseinCatena",
        type: "POST",
        data: JSON.stringify(json),
        contentType: "application/json",
        success: function (data) {
            openCatenaDetail(o);
        }
    });
}
function UpCourseInCatena(o) {
    var json = {
        catena_id: $(o).attr("catena_id"),
        course_position: $(o).attr("course_position")
    };
    $.ajax({
        url: "UpCourseinCatena",
        type: "POST",
        data: JSON.stringify(json),
        contentType: "application/json",
        success: function (data) {
            openCatenaDetail(o);
        }
    });
}
function DownCourseInCatena(o) {
    var json = {
        catena_id: $(o).attr("catena_id"),
        course_position: $(o).attr("course_position")
    };
    $.ajax({
        url: "DownCourseinCatena",
        type: "POST",
        data: JSON.stringify(json),
        contentType: "application/json",
        success: function () {
            openCatenaDetail(o);
        }
    });
}
function openCatenaDelete(o) {
    $("#deletecatena").removeClass("alt_hid")
    $("#hid-back").css("display", "block");
    $("#deleteCatenaSure").attr("catena_id", $(o).attr("catena_id"));
}
function openCatenaChange(o) {
    $("#changecatena").removeClass("alt_hid");
    $("#hid-back").css("display", "block");
    $("#changecatena_id").val($(o).attr("catena_id"));
    $("#changeDetailSure").attr("catena_id", $(o).attr("catena_id"));
    var forms = $(o).parent().find("td");
    $("#changecatena_name").val($(forms[0]).text());
    $("#changecatena_des").val($(forms[1]).text());
    $("#changecatena_salary").val($(forms[2]).text());
    $("#changecatena_img").attr("src", $(forms[3]).text());
}
function changeimg(o) {

    var file = o.files[0];
    if (!/image\/\w+/.test(file.type)) {
        alert("文件必须为图片！");
        return false;
    }
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function () {
        $("#changecatena_img").attr("src", this.result)
    }
}
function getFileName(o) {
    var pos = o.lastIndexOf("\\");
    return o.substring(pos + 1);
}
function changeCatena() {
    var formData = new FormData($("#changeCatenaForm")[0]);
    $.ajax({
        url: 'changeCatena',
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            $(".window").delay(1000).addClass("alt_hid");
            $("#hid-back").delay(100).css("display", "none");
            getCatena();
        },
        error: function (returndata) {
            alert(returndata+"77777");
        }
    });
}
function deleteCatena(o) {
    var json = {
        "catena_id": o.getAttribute("catena_id")
    };
    $.ajax({
        url: "deleteCatena",
        type: "POST",
        data: JSON.stringify(json),
        contentType: "application/json",
        success: function () {
            $("#deletehint").text("删除成功！");
            $(".window").delay(1000).addClass("alt_hid");
            $("#hid-back").delay(100).css("display", "none");
            getCatena();
        }
    });
}

function getUser() {
    $("#tab3").find("tbody").empty();
    $.ajax({
        url: "getUser",
        type: "POST",
        data: "666",
        contentType: "application/json",
        success: function (data) {
            user_list = JSON.parse(data);
            for (var i = 0; i < user_list.length; i++) {
                var body = $("#tab3").find("tbody").first();
                var tr = document.createElement("tr");
                var name = document.createElement("td");
                $(name).text(user_list[i].user_name);
                var pass = document.createElement("td");
                $(pass).text(user_list[i].password);
                var integrate = document.createElement("td");
                $(integrate).text(user_list[i].integrate);
                var ondelete = document.createElement("td");
                $(ondelete).text("删除");
                $(ondelete).attr("onclick", "opendeleteUser(this)");
                $(ondelete).attr("user_id", user_list[i].user_id);
                var onchange = document.createElement("td");
                $(onchange).text("修改");
                $(onchange).attr("onclick", "openChangeUser(this)");
                $(onchange).attr("user_id", user_list[i].user_id);
                $(tr).append(name, pass, integrate, ondelete, onchange);
                $(body).append(tr);
            }
        }
    });
}
function openChangeUser(o){
    $("#changeUser").removeClass("alt_hid")
    $("#hid-back").css("display", "block");
    $("#changeUserSure").attr("user_id", $(o).attr("user_id"));
    var forms = $(o).parent().find("td");
    $("#changeUserName").val($(forms[0]).text());
    $("#changeUserPassword").val($(forms[1]).text());
    $("#changeUserIntegrate").val($(forms[2]).text());
}
function changeUser(o) {
    var json = {
        "user_id": $(o).attr("user_id"),
        "user_name": $("#changeUserName").val(),
        "password": $("#changeUserPassword").val(),
        "integrate": $("#changeUserIntegrate").val()
    };
    
    $.ajax({
        url: 'changeUser',
        type: 'POST',
        data: JSON.stringify(json),
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        success: function () {
            $(".window").delay(1000).addClass("alt_hid");
            $("#hid-back").delay(100).css("display", "none");
            getUser();
        },
        error: function (returndata) {
        }
    });
}
function opendeleteUser(o){
    $("#deletecourse").removeClass("alt_hid")
    $("#hid-back").css("display", "block");
    $("#deleteUserSure").attr("user_id", $(o).attr("user_id"));
}
function deleteUser(o) {
    var json = {
        "user_id": o.getAttribute("user_id"),
        "user_name": $("#changeUserName").val(),
        "password": $("#changeUserPassword").val(),
        "integrate": $("#changeUserIntegrate").val()
    };
    $.ajax({
        url: "changeUser",
        type: "POST",
        data: JSON.stringify(json),
        contentType: "application/json",
        success: function () {
            $("#deletehint").text("更改成功！");
            $(".window").delay(1000).addClass("alt_hid");
            $("#hid-back").delay(100).css("display", "none");
            getUser();
        }
    });
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
            "admin_name": $("#old-password").val(),
            "password": $("#new-password").val(),
            "admin_id": $.session.get("admin_id")
        };
        $.ajax({
            url: "changeAdminPass",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(user),
            success: function (data) {
                admin = JSON.parse(data);
                if (-1 == admin.admin_id) {
                    $("#reg_hint").text("密码错误，请重新输入！");
                    $("#reg_hint").fadeIn(100).delay(1000).fadeOut(800);
                    return false;
                } else {
                    $("#reg_hint").text("密码更改成功");
                    $("#reg_hint").fadeIn(100).delay(1000).fadeOut(800);
                }
            }

        });
    }
}

function cancel() {
    $(".window").addClass("alt_hid");
    $("#hid-back").css("display", "none");
}