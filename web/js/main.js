/**
 * Created by AaahhhXin on 2016/7/11.
 */
window.onload =function () {
    $(".user-button").first().text($.session.get("user_name"));
}