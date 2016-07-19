package web.Util;

import com.google.gson.Gson;
import web.entity.Admin;
import web.entity.User;
import web.entity.User_Course;

/**
 * Created by AaahhhXin on 2016/7/8.
 */
public class GsonUtil {
    public static User StringToUser(String string){
        Gson gson = new Gson();
        User user = gson.fromJson(string, User.class);
        return user;
    }
    public static String UserToString(User user){
        Gson gson = new Gson();
        return gson.toJson(user,User.class);
    }
    public static Admin StringToAdmin(String string){
        Gson gson = new Gson();
        Admin admin = gson.fromJson(string, Admin.class);
        return admin;
    }
    public static String AdminToString(Admin admin){
        Gson gson = new Gson();
        return gson.toJson(admin,Admin.class);
    }
    public static  User_Course StringToUser_Course(String user_course){
        Gson gson = new Gson();
        return gson.fromJson(user_course,User_Course.class);
    }
    public static String UserCourseToString(User_Course user_course){
        Gson gson = new Gson();
        return gson.toJson(user_course,User_Course.class);
    }
}
