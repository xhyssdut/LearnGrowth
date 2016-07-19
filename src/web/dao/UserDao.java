package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import web.entity.Course;
import web.entity.User;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by AaahhhXin on 2016/7/8.
 */
@Repository
public class UserDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void insert(User user) {
        String sql = "insert into user(user_name,password,integrate) values(:user_name,:password,:integrate)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public User selectuserbyusername(User user) {
        String sql = "select * from user where user_name = :user_name";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        List<User> userList = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new BeanPropertyRowMapper<>(User.class));
        if (userList.size() == 0) {
            return null;
        } else {
            return userList.get(0);
        }
    }
    public List<User> selectAllUser() {
        String sql = "select * from user";
        List<User> userList = namedParameterJdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class));
        if (userList.size() == 0) {
            return null;
        } else {
            return userList;
        }
    }

    public User selectUserByUserID(User user) {
        String sql = "select * from user where user_id = :user_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        List<User> userList = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new BeanPropertyRowMapper<>(User.class));
        if (userList.size() == 0) {
            return null;
        } else {
            return userList.get(0);
        }
    }
    public void update(User user){
        String sql = "UPDATE USER SET user_name=:user_name, password=:password ,integrate=:integrate WHERE user_id=:user_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }
    public void updatepassword(User user) {
        String sql = "UPDATE USER SET password=:password WHERE user_id=:user_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public void updateintegrate(User user) {
        String sql = "UPDATE USER SET integrate=:integrate WHERE user_id=:user_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }
    public void delete(User user) {
        String sql = "delete from user where user=:user_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }
}
