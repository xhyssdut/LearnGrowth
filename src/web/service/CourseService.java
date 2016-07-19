package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.CourseDao;
import web.dao.User_CourseDao;
import web.entity.Catena;
import web.entity.Course;
import web.entity.Course_State;
import web.entity.User_Course;

import java.util.List;

/**
 * Created by AaahhhXin on 2016/7/12.
 */
@Service
public class CourseService {
    private CourseDao courseDao;
    private User_CourseDao user_courseDao;

    @Autowired
    public void setUser_courseDao(User_CourseDao user_courseDao) {
        this.user_courseDao = user_courseDao;
    }

    @Autowired
    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public Course getCourseByCourseID(Integer course_id) {
        Course course = new Course();
        course.setCourse_id(course_id);
        return courseDao.findCourseByCourseID(course);
    }

    public User_Course getCourseStateByCourseUser(Integer course_id, Integer user_id) {
        User_Course user_course = new User_Course();
        user_course.setCourse_id(course_id);
        user_course.setUser_id(user_id);
        return user_courseDao.findByCourseAndUser(user_course);
    }

    public List<Course> getAllCourse() {
        return courseDao.findAllCourse();
    }

    public List<Course> getCourseByCatenaID(Integer catena_id) {
        Catena catena = new Catena();
        catena.setCatena_id(catena_id);
        return courseDao.findCourseByCatenaID(catena);
    }

    public void updateCatena_id(Course course) {
        courseDao.updateCatenaID(course);
    }

    public void exchangeCatenaPosition(Course course, boolean up) {
        Course anothercourse = new Course();
        anothercourse.setCatena_id(course.getCatena_id());
        if (up) {
            anothercourse.setCourse_position(course.getCourse_position() - 1);
        } else {
            anothercourse.setCourse_position(course.getCourse_position() + 1);
        }
        course = courseDao.findCourseByCatenaIDAndCoursePosition(course);
        anothercourse = courseDao.findCourseByCatenaIDAndCoursePosition(anothercourse);
        if (up) {
            course.setCourse_position(course.getCourse_position() - 1);
            anothercourse.setCourse_position(anothercourse.getCourse_position() + 1);
        }else {
            course.setCourse_position(course.getCourse_position() + 1);
            anothercourse.setCourse_position(anothercourse.getCourse_position() - 1);
        }
        courseDao.updateCoursePositon(course);
        courseDao.updateCoursePositon(anothercourse);
    }
    public void updateCatenaPosition(Course course){
        courseDao.updateCoursePositon(course);
    }
    public void deleteCourse(Course course){
        courseDao.delete(course);
        User_Course user_course = new User_Course();
        user_course.setCourse_id(course.getCourse_id());
        user_courseDao.deleteCourse(user_course);
    }
    public void update(Course course){
        courseDao.update(course);
        courseDao.updateCourseUrl(course);
        courseDao.updateCoursePositon(course);
        courseDao.updateCatenaID(course);
    }
    public void insert(Course course){
        courseDao.insert(course);
    }
}
