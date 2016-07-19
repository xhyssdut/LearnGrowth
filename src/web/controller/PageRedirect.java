package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.entity.*;
import web.service.CatenaService;
import web.service.CourseService;
import web.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by AaahhhXin on 2016/7/9.
 */
@Controller
public class PageRedirect {
    private CatenaService catenaService;
    private UserService userService;
    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCatenaService(CatenaService catenaService) {
        this.catenaService = catenaService;
    }

    @RequestMapping(value = "/login")
    public ModelAndView UserPageRedirect() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/admin")
    public ModelAndView AdminPageRedirect() {
        return new ModelAndView("adminlogin");
    }

    @RequestMapping(value = "/main")
    public ModelAndView MainPageRedirect() {
        List<Catena> list = catenaService.getAllCatenas();
        ModelAndView modelAndView = new ModelAndView("main");
        modelAndView.addObject("catenas", list);
        return modelAndView;
    }

    @RequestMapping(value = "/catena/{catena_id}")
    public ModelAndView CatenaPageRedirect(@PathVariable int catena_id, HttpSession httpSession) {
        Catena catena = new Catena();
        catena.setCatena_id(catena_id);
        catena = catenaService.getCatenasByCatenaID(catena);
        User_Course user_course = new User_Course();
        user_course.setUser_id((Integer) httpSession.getAttribute("user_id"));
        System.out.println(httpSession.getAttribute("user_id"));
        List<Course_State> course_stateList = catenaService.getCourseStateByCatenaID(catena, user_course);
        ModelAndView modelAndView = new ModelAndView("catena");
        modelAndView.addObject("course_stateList", course_stateList);
        modelAndView.addObject("catena", catena);
        return modelAndView;
    }

    @RequestMapping(value = "/course/{catena_id}/{course_id}")
    public ModelAndView CoursePageRedirect(@PathVariable int catena_id, @PathVariable int course_id, HttpSession httpSession) {
        Catena catena = new Catena();
        Course course = courseService.getCourseByCourseID(course_id);
        catena.setCatena_id(catena_id);
        catena = catenaService.getCatenasByCatenaID(catena);
        User_Course user_course = new User_Course();
        User user = userService.getUserByID((Integer) httpSession.getAttribute("user_id"));
        user_course.setUser_id((Integer) httpSession.getAttribute("user_id"));
        List<Course_State> course_stateList = catenaService.getCourseStateByCatenaID(catena, user_course);
        user_course = courseService.getCourseStateByCourseUser(course_id, (Integer) httpSession.getAttribute("user_id"));
        if (user_course == null) {
            user_course = new User_Course();
            user_course.setUser_id((Integer) httpSession.getAttribute("user_id"));
            user_course.setCourse_id(course_id);
            user_course.setState(0);
        }
        ModelAndView modelAndView = new ModelAndView("course");
        modelAndView.addObject("user_course", user_course);
        modelAndView.addObject("user", user);
        modelAndView.addObject("course", course);
        modelAndView.addObject("course_stateList", course_stateList);
        modelAndView.addObject("catena", catena);
        return modelAndView;
    }

    @RequestMapping(value = "/manage")
    public ModelAndView ManagePageRedirect() {
        return new ModelAndView("manage");
    }


    @RequestMapping(value = "/admin_main")
    public ModelAndView AdminMainPageRedirect() {
        return new ModelAndView("admin_main");
    }
}
