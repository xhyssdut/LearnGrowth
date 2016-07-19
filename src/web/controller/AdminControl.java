package web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.Util.GsonUtil;
import web.entity.Admin;
import web.entity.Catena;
import web.entity.Course;
import web.entity.User;
import web.service.*;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

/**
 * Created by AaahhhXin on 2016/7/14.
 */
@Controller
public class AdminControl {
    private AdminService adminService;
    private CatenaService catenaService;
    private CourseService courseService;
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

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setCatenaService(CatenaService catenaService) {
        this.catenaService = catenaService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "admin_login")
    @ResponseBody
    public String DoAdminLogin(@RequestBody String admin, HttpSession httpSession) {
        Admin getadmin = GsonUtil.StringToAdmin(admin);
        Admin admin_in_db = adminService.login(getadmin);
        if (admin_in_db.getAdmin_id() > 0) {
            httpSession.setAttribute("admin_id", admin_in_db.getAdmin_id());
        }
        return GsonUtil.AdminToString(admin_in_db);
    }

    @RequestMapping(value = "getCatena", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCatena() {
        List<Catena> catenaList = catenaService.getAllCatenas();
        Gson gson = new Gson();
        return gson.toJson(catenaList);
    }

    @RequestMapping(value = "getCourse", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCourse() {
        List<Course> courseList = courseService.getAllCourse();
        for (Iterator<Course> i = courseList.iterator(); i.hasNext(); ) {
            Course course = i.next();
            Catena catena = new Catena();
            catena.setCatena_id(course.getCatena_id());
            catena = catenaService.getCatenasByCatenaID(catena);
            course.setCatena_name(catena.getCatena_name());
        }
        Gson gson = new Gson();
        return gson.toJson(courseList);
    }

    @RequestMapping(value = "changeAdminPass", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String changePassWord(@RequestBody String json) {
        Admin admin = GsonUtil.StringToAdmin(json);
        admin = adminService.changePass(admin);
        return GsonUtil.AdminToString(admin);
    }

    @RequestMapping(value = "getUser", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUser() {
        List<User> userList = userService.getAllUser();
        Gson gson = new Gson();
        return gson.toJson(userList);
    }

    @RequestMapping(value = "getCourseByCatenaId", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getCourseByCatenaId(@RequestBody String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        Integer catena_id = jsonObject.get("catena_id").getAsInt();
        List<Course> courseList = courseService.getCourseByCatenaID(catena_id);
        Gson gson = new Gson();
        return gson.toJson(courseList);
    }

    @RequestMapping(value = "deleteCourse", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void deleteCourse(@RequestBody String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        Integer course_id = jsonObject.get("course_id").getAsInt();
        Course course = new Course();
        course.setCourse_id(course_id);
        courseService.deleteCourse(course);
        userCourseService.deleteCourse(course_id);
    }

}
