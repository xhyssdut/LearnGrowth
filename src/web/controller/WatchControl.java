package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.Util.GsonUtil;
import web.entity.User_Course;
import web.service.UserCourseService;
import web.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * Created by AaahhhXin on 2016/7/10.
 */
@Controller
public class WatchControl { //关于用户视频购买的控制器
    private UserService userService;
    private UserCourseService userCourseService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserCourseService(UserCourseService userCourseService) {
        this.userCourseService = userCourseService;
    }

    @RequestMapping(value = "buyCourse/{m_integrate}")
    @ResponseBody
    public String buyCourse(@RequestBody String jsonstring, @PathVariable Integer m_integrate) {
        User_Course user_course = GsonUtil.StringToUser_Course(jsonstring);
        userCourseService.changeState(user_course);
        userService.useIntegrate(m_integrate, user_course.getUser_id());
        user_course.setState(1);
        return GsonUtil.UserCourseToString(user_course);
    }

    @RequestMapping(value = "watchCourse/{a_integrate}")
    @ResponseBody
    public void watchCourse(@RequestBody String jsonstring, @PathVariable Integer a_integrate) {
        User_Course user_course = GsonUtil.StringToUser_Course(jsonstring);
        if(userCourseService.getState(user_course).getState()!=2) {
            userCourseService.changeState(user_course);
            userService.addIntegrate(a_integrate, user_course.getUser_id());
            user_course = GsonUtil.StringToUser_Course(jsonstring);
            userCourseService.changeState(user_course);
        }
    }
}
