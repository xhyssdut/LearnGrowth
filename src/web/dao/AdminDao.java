package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import web.entity.Admin;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by AaahhhXin on 2016/7/14.
 */
@Repository
public class AdminDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void insert(Admin admin) {
        String sql = "insert into admin(admin_name,password) values(:admin_name,:password,:integrate)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(admin);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public Admin selectAdminByAdminName(Admin admin) {
        String sql = "select * from admin where admin_name = :admin_name";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(admin);
        List<Admin> userList = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new BeanPropertyRowMapper<>(Admin.class));
        if (userList.size() == 0) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    public Admin selectAdminByAdminID(Admin admin) {
        String sql = "select * from admin where admin_id = :admin_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(admin);
        List<Admin> userList = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new BeanPropertyRowMapper<>(Admin.class));
        if (userList.size() == 0) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    public void updatepassword(Admin admin) {
        String sql = "UPDATE admin SET password=:password WHERE admin_id=:admin_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(admin);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }
}
