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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import web.entity.Catena;
import web.entity.Course;
import web.service.CatenaService;
import web.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created by AaahhhXin on 2016/7/14.
 */
@Controller
public class CatenaControl {
    private CatenaService catenaService;
    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setCatenaService(CatenaService catenaService) {
        this.catenaService = catenaService;
    }

    @RequestMapping(value = "deleteCatena")
    @ResponseBody
    public void deleteCatena(@RequestBody String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        Integer catena_id = jsonObject.get("catena_id").getAsInt();
        catenaService.deleteCatena(catena_id);
        List<Course> courseList = courseService.getCourseByCatenaID(catena_id);
        for (Iterator<Course> i = courseList.iterator(); i.hasNext(); ) {
            Course course = i.next();
            course.setCatena_id(65535);
            courseService.updateCatena_id(course);
        }
    }

    @RequestMapping(value = "changeCatena", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void changeCatena(HttpServletRequest request) {
        Catena catena = new Catena();
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
                    if (name.equals("changecatena_id")) {
                        catena.setCatena_id(Integer.parseInt(value));
                        Catena delete = new Catena();
                        delete.setCatena_id(Integer.parseInt(value));
                        delete = catenaService.getCatenasByCatenaID(delete);
                        String deleteimg = delete.getImg_url();
                        String path = request.getSession().getServletContext().getRealPath("") + "imgs\\";
                        File file = new File(path + deleteimg);
                        boolean aka = file.delete();
                    } else if (name.equals("changecatena_name")) {
                        catena.setCatena_name(value);
                    } else if (name.equals("changecatena_des")) {
                        catena.setCatena_description(value);
                    } else if (name.equals("changecatena_salary")) {
                        catena.setSalary(value);
                    }
                } else {
                    //如果是文件字段
                    String path = request.getSession().getServletContext().getRealPath("") + "imgs\\";
                    String fileName = item.getName();
                    System.out.println(path+fileName);
                    catena.setImg_url(fileName);
                    File file = new File(path + fileName);
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
        catenaService.updateCatena(catena);
    }

    @RequestMapping(value = "deleteCourseinCatena")
    @ResponseBody
    public void deleteCourseinCatena(@RequestBody String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        Integer course_id = jsonObject.get("course_id").getAsInt();
        Integer catena_id = jsonObject.get("catena_id").getAsInt();
        Integer catena_position = jsonObject.get("course_position").getAsInt();
        List<Course> courseList = courseService.getCourseByCatenaID(catena_id);
        for (Iterator<Course> i = courseList.iterator(); i.hasNext(); ) {
            Course temp = i.next();
            if (temp.getCourse_position() > catena_position) {
                temp.setCourse_position(temp.getCourse_position() - 1);
                courseService.updateCatenaPosition(temp);
            }
        }
        Course course = new Course();
        course.setCatena_id(65535);
        course.setCourse_id(course_id);
        courseService.updateCatena_id(course);

    }

    @RequestMapping(value = "UpCourseinCatena")
    @ResponseBody
    public void UpCourseInCatena(@RequestBody String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        Integer catena_id = jsonObject.get("catena_id").getAsInt();
        Integer catena_position = jsonObject.get("course_position").getAsInt();
        Course course = new Course();
        course.setCatena_id(catena_id);
        course.setCourse_position(catena_position);
        courseService.exchangeCatenaPosition(course, true);
    }

    @RequestMapping(value = "DownCourseinCatena")
    @ResponseBody
    public void DownCourseInCatena(@RequestBody String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        Integer catena_id = jsonObject.get("catena_id").getAsInt();
        Integer catena_position = jsonObject.get("course_position").getAsInt();
        Course course = new Course();
        course.setCatena_id(catena_id);
        course.setCourse_position(catena_position);
        courseService.exchangeCatenaPosition(course, false);
    }

    @RequestMapping(value = "addCatena", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void addCatena(HttpServletRequest request) {
        Catena catena = new Catena();
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
                    if (name.equals("addcatena_name")) {
                        catena.setCatena_name(value);
                    } else if (name.equals("addcatena_des")) {
                        catena.setCatena_description(value);
                    } else if (name.equals("addcatena_salary")) {
                        catena.setSalary(value);
                    }
                } else {
                    String path = request.getSession().getServletContext().getRealPath("") + "imgs\\";
                    String fileName = item.getName();
                    File file = new File(path + fileName);
                    catena.setImg_url(fileName);
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
        catenaService.insert(catena);
    }
}
