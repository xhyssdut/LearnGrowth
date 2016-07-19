package web.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.Util.GsonUtil;
import web.entity.User;
import web.service.UserCourseService;
import web.service.UserService;

import javax.servlet.http.HttpSession;


/**
 * Created by AaahhhXin on 2016/7/5.
 */
@Controller
public class UserControl {
    private UserService userService;
    private UserCourseService userCourseService;
    @Autowired
    public void setUserCourseService(UserCourseService userCourseService) {
        this.userCourseService = userCourseService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/dologin")
    @ResponseBody
    public String DoLogin(@RequestBody String user, HttpSession httpSession) {
        User getuser = GsonUtil.StringToUser(user);
        User user_in_db = userService.login(getuser);
        if (user_in_db.getUser_id() > 0) {
            httpSession.setAttribute("user_id", user_in_db.getUser_id());
        }
        return GsonUtil.UserToString(user_in_db);
    }

    @RequestMapping(value = "/doregister")
    @ResponseBody
    public String Doregister(@RequestBody String user) {
        User getuser = GsonUtil.StringToUser(user);
        User result = userService.register(getuser);
        return GsonUtil.UserToString(result);
    }

    @RequestMapping(value = "/dochangePass")
    @ResponseBody
    public String DochangePass(@RequestBody String user) {
        User getuser = GsonUtil.StringToUser(user);
        User result = userService.changePass(getuser);
        return GsonUtil.UserToString(result);
    }
    @RequestMapping(value = "changeUser")
    @ResponseBody
    public void changeUser(@RequestBody String json) {
        User user = GsonUtil.StringToUser(json);
        userService.changeUser(user);
    }
    @RequestMapping(value = "deleteUser")
    @ResponseBody
    public void deleteUser(@RequestBody String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        Integer user_id = jsonObject.get("user_id").getAsInt();
        userCourseService.deleteUser(user_id);
        userService.deleteUser(user_id);
    }

}
