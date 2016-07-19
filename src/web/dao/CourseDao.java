package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import web.entity.Catena;
import web.entity.Course;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AaahhhXin on 2016/7/10.
 */
@Repository
public class CourseDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    //查找对应课程的每一节课
    public List<Course> findCourseByCatenaID(Catena catena) {
        String sql = "select * from course where catena_id =:catena_id order by course_position";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(catena);
        List<Course> courseList = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new BeanPropertyRowMapper<>(Course.class));
        if (courseList.size() == 0) {
            return new ArrayList<>();
        } else {
            return courseList;
        }
    }
    public Course findCourseByCatenaIDAndCoursePosition(Course course) {
        String sql = "select * from course where catena_id =:catena_id and course_position=:course_position";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(course);
        List<Course> courseList = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new BeanPropertyRowMapper<>(Course.class));
        if (courseList.size() == 0) {
            return null;
        } else {
            return courseList.get(0);
        }
    }
    public List<Course> findAllCourse() {
        String sql = "select * from course";
        List<Course> courseList = namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Course.class));
        if (courseList.size() == 0) {
            return new ArrayList<>();
        } else {
            return courseList;
        }
    }
    public Course findCourseByCourseID(Course course) {
        String sql = "select * from course where course_id =:course_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(course);
        List<Course> courseList = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new BeanPropertyRowMapper<>(Course.class));
        if (courseList.size() == 0) {
            return null;
        } else {
            return courseList.get(0);
        }
    }
    public void insert(Course course) {
        String sql = "insert into course(course_name,course_position,catena_id,m_integrate,a_integrate,course_description,course_url)" +
                " values(:course_name,:course_position,:catena_id,:m_integrate,:a_integrate,:course_description,:course_url)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(course);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public void update(Course course) {
        String sql = "UPDATE course SET course_name=:course_name," +
                "m_integrate=:m_integrate,a_integrate=:a_integrate,course_description=:course_description  WHERE course_id=:course_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(course);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public void updateCoursePositon(Course course) {
        String sql = "UPDATE course SET course_position=:course_position WHERE course_id=:course_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(course);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public void updateCatenaID(Course course) {
        String sql = "UPDATE course SET catena_id=:catena_id WHERE course_id=:course_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(course);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public void updateCourseUrl(Course course) {
        String sql = "UPDATE course SET course_url=:course_url WHERE course_id=:course_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(course);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public void delete(Course course) {
        String sql = "delete from course where course_id=:course_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(course);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }
}
