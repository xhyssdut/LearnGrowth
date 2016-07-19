package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.CourseDao;
import web.dao.User_CourseDao;
import web.entity.Course;
import web.entity.User;
import web.entity.User_Course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by AaahhhXin on 2016/7/12.
 */
@Service
public class UserCourseService {
    private User_CourseDao user_courseDao;
    private CourseDao courseDao;

    @Autowired
    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Autowired
    public void setUser_courseDao(User_CourseDao user_courseDao) {
        this.user_courseDao = user_courseDao;
    }

    public void changeState(User_Course user_course) {
        if (user_courseDao.findByCourseAndUser(user_course) != null) {
            user_courseDao.updateState(user_course);
        } else {
            user_courseDao.insert(user_course);
        }
    }

    public List<Course> getCourseByUser_State(Integer user_id, Integer state) {
        List<Course> courseList = courseDao.findAllCourse();
        List<Course> newcourseList = new ArrayList<>();
        for (Iterator<Course> i = courseList.iterator(); i.hasNext(); ) {
            Course course = i.next();
            User_Course user_course = new User_Course();
            user_course.setUser_id(user_id);
            user_course.setCourse_id(course.getCourse_id());
            user_course = user_courseDao.findByCourseAndUser(user_course);
            if (state == 0) {
                if (user_course == null) {
                    newcourseList.add(course);
                }
            }else {
                if (user_course != null) {
                    newcourseList.add(course);
                }
            }
        }
        return newcourseList;
    }
    public void deleteUser(Integer user_id){
        User_Course user_course = new User_Course();
        user_course.setUser_id(user_id);
        user_courseDao.deleteUser(user_course);
    }
    public void deleteCourse(Integer course_id){
        User_Course user_course = new User_Course();
        user_course.setCourse_id(course_id);
        user_courseDao.deleteCourse(user_course);
    }
    public User_Course getState(User_Course user_course){
        return user_courseDao.findByCourseAndUser(user_course);
    }
}
