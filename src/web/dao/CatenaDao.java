package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import web.entity.Catena;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AaahhhXin on 2016/7/10.
 */
@Repository
public class CatenaDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    //查找所有的课程系列
    public List<Catena> findAllCatena() {
        String sql = "select * from catena where catena_id != 65535 order by catena_id";
        List<Catena> catenaList = namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Catena.class));
        if (catenaList.size() == 0) {
            return new ArrayList<>();
        } else {
            return catenaList;
        }
    }

    public Catena findCatenaByCatenaID(Catena catena) {
        String sql = "select * from catena where catena_id =:catena_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(catena);
        List<Catena> catenaList = namedParameterJdbcTemplate.query(sql, sqlParameterSource, new BeanPropertyRowMapper<>(Catena.class));
        if (catenaList.size() == 0) {
            return null;
        } else {
            return catenaList.get(0);
        }
    }

    public void insert(Catena catena) {
        String sql = "insert into catena(catena_name,salary,catena_description,img_url) values(:catena_name,:salary,:catena_description,:img_url)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(catena);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public void update(Catena catena) {
        String sql = "UPDATE catena SET catena_name=:catena_name," +
                "salary=:salary,catena_description=:catena_description  WHERE catena_id=:catena_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(catena);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }
    public void updateImgUrl(Catena catena) {
        String sql = "UPDATE catena SET img_url=:img_url WHERE catena_id=:catena_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(catena);
        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public void delete(Catena catena) {
        String sql = "delete from catena where catena_id=:catena_id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(catena);
        namedParameterJdbcTemplate.update(sql,sqlParameterSource);
    }
}
