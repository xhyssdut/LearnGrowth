package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import web.entity.User_Course;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AaahhhXin on 2016/7/10.
 */
@Repository
public class User_CourseDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    public User_Course findByCourseAndUser(User_Course user_course){
        String sql = "select * from user_course where user_id = :user_id and course_id=:course_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user_course);
        List<User_Course> user_courseList = namedParameterJdbcTemplate.query(sql,sqlParameterSource,new BeanPropertyRowMapper<>(User_Course.class));
        if(user_courseList.size() == 0){
            return null;
        }else {
            return user_courseList.get(0);
        }
    }
    public List<User_Course> findByUserAndState(User_Course user_course){
        String sql = "select * from user_course where user_id = :user_id and state=:state";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user_course);
        List<User_Course> user_courseList = namedParameterJdbcTemplate.query(sql,sqlParameterSource,new BeanPropertyRowMapper<>(User_Course.class));
        if(user_courseList.size() == 0){
            return new ArrayList<User_Course>();
        }else {
            return user_courseList;
        }
    }
    public List<User_Course> findByUser(User_Course user_course){
        String sql = "select * from user_course where user_id = :user_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user_course);
        List<User_Course> user_courseList = namedParameterJdbcTemplate.query(sql,sqlParameterSource,new BeanPropertyRowMapper<>(User_Course.class));
        if(user_courseList.size() == 0){
            return new ArrayList<>();
        }else {
            return user_courseList;
        }
    }
    public void insert(User_Course user_course){
        String sql = "insert into user_course(user_id,course_id,state)" +
                " values(:user_id,:course_id,:state)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user_course);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }
    public void delete(User_Course user_course){
        String sql = "delete from user_course where course_id=:course_id and user_id=:user_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user_course);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }
    public void deleteCourse(User_Course user_course){
        String sql = "delete from user_course where course_id=:course_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user_course);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }
    public void deleteUser(User_Course user_course){
        String sql = "delete from user_course where user_id=:user_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user_course);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }
    public void updateState(User_Course user_course){
        String sql = "UPDATE user_course SET state=:state WHERE user_id=:user_id and course_id=:course_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user_course);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }
}
