/**
 * Created by AaahhhXin on 2016/7/14.
 */
function login() {
    var admin = {
        "admin_name": $("#logname").val(),
        "password": $("#logpassword").val(),
        "admin_id": 0
    };
    $.ajax({
        url: "admin_login",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(admin),
        success: function (data) {
            admin = JSON.parse(data);
            if (-1 == admin.admin_id) {
                $("#log_hint").text("没有该用户，请确认用户名。");
                $("#log_hint").fadeIn(100).delay(1000).fadeOut(1000);
                return false;
            } else if (-2 == admin.admin_id) {
                $("#log_hint").text("密码错误，请确认用户名和密码。");
                $("#log_hint").fadeIn(100).delay(1000).fadeOut(1000);
                return false;
            } else {
                $.session.set("admin_id", admin.admin_id);
                $.session.set("admin_name", admin.admin_name);
                $("#log_hint").text("登录成功！");
                $("#log_hint").fadeIn(100).delay(1000).fadeOut(1000);
                setTimeout(function () {
                    window.location = "admin_main";
                }, 2000);
            }

        }
    });
}