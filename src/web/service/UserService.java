package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.entity.User;

import java.util.List;

/**
 * Created by AaahhhXin on 2016/7/8.
 */
@Service
public class UserService {
    private UserDao userDao;
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public User login(User user){
        User user_in_db = userDao.selectuserbyusername(user);
        if(user_in_db==null){//-1代表没有用户
            user_in_db = new User();
            user_in_db.setUser_id(-1);
        }else if(!user_in_db.getPassword().equals(user.getPassword())){//-2代表密码错误
            user_in_db = new User();
            user_in_db.setUser_id(-2);
        }
        return user_in_db;
    }
    public User register(User user){
        User user_in_db = userDao.selectuserbyusername(user);
        if(user_in_db!=null){//-1代表有这个用户
            user_in_db = new User();
            user_in_db.setUser_id(-1);
            return user_in_db;
        }else{//没有这个用户
            userDao.insert(user);
            return userDao.selectuserbyusername(user);
        }
    }
    public User getUserByID(Integer user_id){
        User user = new User();
        user.setUser_id(user_id);
        return  userDao.selectUserByUserID(user);
    }
    public void useIntegrate(Integer consuming,Integer user_id){
        User user = new User();
        user.setUser_id(user_id);
        user = userDao.selectUserByUserID(user);
        if(user!=null) {

            user.setintegrate(user.getintegrate() - consuming);
            userDao.updateintegrate(user);
        }
        return;
    }
    public void addIntegrate(Integer consuming,Integer user_id){
        User user = new User();
        user.setUser_id(user_id);
        user = userDao.selectUserByUserID(user);
        if(user!=null) {

            user.setintegrate(user.getintegrate() + consuming);
            userDao.updateintegrate(user);
        }
        return;
    }
    public User changePass(User user){
        User user_in_db = userDao.selectUserByUserID(user);
        if(user_in_db==null){
            user.setUser_id(-2);
        }
        if(user_in_db.getPassword().equals(user.getUser_name())){
            userDao.updatepassword(user);
        }else{
            user.setUser_id(-1);
        }
        return user;
    }
    public List<User> getAllUser(){
        return userDao.selectAllUser();
    }
    public void deleteUser(Integer user_id){
        User user = new User();
        user.setUser_id(user_id);
        userDao.delete(user);
    }
    public void changeUser(User user){
        userDao.update(user);
    }
}