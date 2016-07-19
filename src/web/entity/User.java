package web.entity;


/**
 * Created by AaahhhXin on 2016/7/7.
 */
public class User {
    private Integer user_id;//用户id
    private String user_name;//用户名
    private String password;//用户密码
    private Integer integrate;//用户当前拥有的积分

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getintegrate() {
        return integrate;
    }

    public void setintegrate(Integer integrate) {
        this.integrate = integrate;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
