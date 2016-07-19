package web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.entity.Catena;
import web.entity.Course;
import web.entity.User;
import web.service.CatenaService;
import web.service.CourseService;
import web.service.UserCourseService;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created by AaahhhXin on 2016/7/13.
 */
@Controller
public class ManageControl {
    private CatenaService catenaService;
    private UserService userService;
    private CourseService courseService;
    private UserCourseService userCourseService;

    @Autowired
    public void setUserCourseService(UserCourseService userCourseService) {
        this.userCourseService = userCourseService;
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCatenaService(CatenaService catenaService) {
        this.catenaService = catenaService;
    }

    @RequestMapping(value = "getUnpaidCourse",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUnpaidCourse(@RequestBody String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        int user_id = jsonObject.get("user_id").getAsInt();
        List<Course> courseList = userCourseService.getCourseByUser_State(user_id, 0);
        Gson gson = new Gson();
        return gson.toJson(courseList);
    }
    @RequestMapping(value = "getPaidCourse",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPaidCourse(@RequestBody String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        int user_id = jsonObject.get("user_id").getAsInt();
        List<Course> courseList = userCourseService.getCourseByUser_State(user_id, 1);
        Gson gson = new Gson();
        return gson.toJson(courseList);
    }
    @RequestMapping(value = "getintegrate",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getIntegrate(@RequestBody String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        int user_id = jsonObject.get("user_id").getAsInt();
        User user = userService.getUserByID(user_id);
        Gson gson = new Gson();
        return gson.toJson(user);
    }
    @RequestMapping(value = "changeCourse", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void changeCatena(HttpServletRequest request) {
        Course course = new Course();
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        // 判断enctype属性是否为multipart/form-data

// Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

// Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

// Parse the request
        try {
            List<?> items = upload.parseRequest(request);

            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                    //如果是普通表单字段
                    String name = item.getFieldName();
                    String value = item.getString("utf-8");
                    if (name.equals("changecourse_id")) {
                        course.setCourse_id(Integer.parseInt(value));
                        Course delete = new Course();
                        delete.setCourse_id(Integer.parseInt(value));
                        delete = courseService.getCourseByCourseID(delete.getCourse_id());
                        String deleteVideo = delete.getCourse_url();
                        String path = request.getSession().getServletContext().getRealPath("") + "videos\\";
                        File file = new File(path+deleteVideo);
                        boolean aka = file.delete();
                    } else if (name.equals("changecourse_name")) {
                        course.setCourse_name(value);
                    } else if (name.equals("changecourse_des")) {
                        course.setCourse_description(value);
                    } else if (name.equals("changecourse_catenaid")) {
                        course.setCatena_id(Integer.parseInt(value));
                        course.setCourse_position(catenaService.getCourseCounts(Integer.parseInt(value))+1);;
                    } else if (name.equals("changecourse_minte")) {
                        course.setM_integrate(Integer.parseInt(value));
                    }else if (name.equals("changecourse_ainte")) {
                        course.setA_integrate(Integer.parseInt(value));
                    }
                } else {
                    //如果是文件字段
                    String path = request.getSession().getServletContext().getRealPath("") + "videos\\";
                    System.out.println(path);
                    String fileName = item.getName();
                    course.setCourse_url(fileName);
                    File file = new File(path+fileName);
                    file.createNewFile();
                    InputStream ins = item.getInputStream();
                    OutputStream ous = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = ins.read(buffer)) > -1)
                        ous.write(buffer, 0, len);
                    ous.close();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        courseService.update(course);
    }
    @RequestMapping(value = "addCourse", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void addCatena(HttpServletRequest request) {
        Course course = new Course();
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        // 判断enctype属性是否为multipart/form-data

// Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

// Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

// Parse the request
        try {
            List<?> items = upload.parseRequest(request);

            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                    //如果是普通表单字段
                    String name = item.getFieldName();
                    String value = item.getString("utf-8");
                    if (name.equals("addcourse_name")) {
                        course.setCourse_name(value);
                    } else if (name.equals("addcourse_des")) {
                        course.setCourse_description(value);
                    } else if (name.equals("addcourse_catenaid")) {
                        course.setCatena_id(Integer.parseInt(value));
                        course.setCourse_position(catenaService.getCourseCounts(Integer.parseInt(value))+1);;
                    } else if (name.equals("addcourse_minte")) {
                        course.setM_integrate(Integer.parseInt(value));
                    }else if (name.equals("addcourse_ainte")) {
                        course.setA_integrate(Integer.parseInt(value));
                    }
                } else {
                    //如果是文件字段
                    String path = request.getSession().getServletContext().getRealPath("") + "videos\\";
                    String fileName = item.getName();
                    course.setCourse_url(fileName);
                    File file = new File(path+fileName);
                    file.createNewFile();
                    InputStream ins = item.getInputStream();
                    OutputStream ous = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = ins.read(buffer)) > -1)
                        ous.write(buffer, 0, len);
                    ous.close();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        courseService.insert(course);
    }
}
