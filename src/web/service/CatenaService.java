package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.CatenaDao;
import web.dao.CourseDao;
import web.dao.User_CourseDao;
import web.entity.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by AaahhhXin on 2016/7/10.
 */
@Service
public class CatenaService {
    private CatenaDao catenaDao;
    private CourseDao courseDao;
    private User_CourseDao user_courseDao;

    @Autowired
    public void setUser_courseDao(User_CourseDao user_courseDao) {
        this.user_courseDao = user_courseDao;
    }

    @Autowired
    public void setCatenaDao(CatenaDao catenaDao) {
        this.catenaDao = catenaDao;
    }

    @Autowired
    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public List<Catena> getAllCatenas() {
        List<Catena> arrayList = catenaDao.findAllCatena();
        return arrayList;
    }

    public Catena getCatenasByCatenaID(Catena catena) {
        return catenaDao.findCatenaByCatenaID(catena);
    }

    public List<Course_State> getCourseStateByCatenaID(Catena catena, User_Course user_course) {
        List<Course_State> course_stateList = new ArrayList<>();
        List<Course> courseList = courseDao.findCourseByCatenaID(catena);
        List<User_Course> user_courseList = user_courseDao.findByUser(user_course);
        for (Iterator<Course> i = courseList.iterator(); i.hasNext(); ) {
            Course course = i.next();
            boolean flag = false;
            for (Iterator<User_Course> j = user_courseList.iterator(); j.hasNext(); ) {
                User_Course temp = j.next();
                if (temp.getCourse_id().equals(course.getCourse_id())) {
                    course_stateList.add(new Course_State(course, temp.getState()));
                    flag = true;
                }
            }
            if (!flag) {
                course_stateList.add(new Course_State(course, 0));
            }
        }
        return course_stateList;
    }
    public void deleteCatena(Integer catena_id){
        Catena catena = new Catena();
        catena.setCatena_id(catena_id);
        catenaDao.delete(catena);
    }
    public void updateCatena(Catena catena){
        catenaDao.update(catena);
        catenaDao.updateImgUrl(catena);
    }
    public Integer getCourseCounts(Integer catena_id){
        Catena catena = new Catena();
        catena.setCatena_id(catena_id);
        return courseDao.findCourseByCatenaID(catena).size();
    }
    public void insert(Catena catena){
        catenaDao.insert(catena);
    }
}
