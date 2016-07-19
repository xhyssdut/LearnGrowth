package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.AdminDao;
import web.entity.Admin;

/**
 * Created by AaahhhXin on 2016/7/14.
 */
@Service
public class AdminService {
    private AdminDao adminDao;

    @Autowired
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }
    public Admin login(Admin admin){
        Admin admin_in_db = adminDao.selectAdminByAdminName(admin);
        if(admin_in_db==null){//-1代表没有用户
            admin_in_db = new Admin();
            admin_in_db.setAdmin_id(-1);
        }else if(!admin_in_db.getPassword().equals(admin.getPassword())){//-2代表密码错误
            admin_in_db = new Admin();
            admin_in_db.setAdmin_id(-2);
        }
        return admin_in_db;
    }
    public Admin changePass(Admin admin){
        Admin admin_in_db = adminDao.selectAdminByAdminID(admin);
        if(admin_in_db.getPassword().equals(admin.getAdmin_name())){
            adminDao.updatepassword(admin);
        }else{
            admin.setAdmin_id(-1);
        }
        return admin;
    }
}
