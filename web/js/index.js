/**
 * Created by AaahhhXin on 2016/7/5.
 */

window.onload = function () {
    $(".signup").hide();
    $("#login-link").click(function () {
        $(".login").delay(100).fadeIn(100);
        $(".signup").fadeOut(100);
        $("#signup-link").removeClass("active");
        $("#login-link").addClass("active");
    });
    $("#signup-link").click(function () {
        $(".login").fadeOut(100);
        $(".signup").delay(100).fadeIn(100);
        $("#login-link").removeClass("active");
        $("#signup-link").addClass("active");
    });
}

function confirm() {
    var a = $("#reg_password").val();
    var b = $("#pass-confirm").val();
    if (a == b) {
        return true;
    } else {
        $("#prehint").fadeIn(1000).delay(1000).fadeOut(1000);
        return false;
    }
}
function login() {
    var user = {
        "user_name": $("#logname").val(),
        "password": $("#logpassword").val(),
        "user_id": 0,
        "integrate": 0
    };
    $.ajax({
        url: "dologin",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(user),
        success: function (data) {
            user =  JSON.parse(data);
            if(-1==user.user_id){
                $("#log_hint").text("没有该用户，请确认用户名。");
                $("#log_hint").fadeIn(100).delay(1000).fadeOut(1000);
                return false;
            }else if(-2==user.user_id){
                $("#log_hint").text("密码错误，请确认用户名和密码。");
                $("#log_hint").fadeIn(100).delay(1000).fadeOut(1000);
                return false;
            }else  {
                $.session.set("user_id",user.user_id);
                $.session.set("user_name",user.user_name);
                $("#log_hint").text("登录成功！");
                $("#log_hint").fadeIn(100).delay(1000).fadeOut(1000);
                setTimeout(function(){window.location="main";},2000);
            }
            
        }
    });
}
function register() {
    if(!confirm())return false;
    else{
        var user = {
            "user_name": $("#reg_name").val(),
            "password": $("#reg_password").val(),
            "user_id": 0,
            "integrate": 500
        };
        $.ajax({
            url: "doregister",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(user),
            success: function (data) {
                user =  JSON.parse(data);
                if(-1==user.user_id){//注册成功
                    $("#reg_hint").text("已经存在该用户，请确认用户名。");
                    $("#reg_hint").fadeIn(100).delay(1000).fadeOut(800);
                    return false;
                }else{
                    $("#reg_hint").text("注册成功");
                    $("#reg_hint").fadeIn(100).delay(1000).fadeOut(800).delay(1000);
                    $(".signup").delay(2000).fadeOut(100);
                    $(".login").delay(2100).fadeIn(100);
                    $("#signup-link").removeClass("active");
                    $("#login-link").addClass("active");
                    return true;
                }
            }

        });
    }

}
