package web.entity;

/**
 * Created by AaahhhXin on 2016/7/10.
 */
public class Course {
    private Integer course_id;
    private String course_name;
    private Integer course_position;
    private int catena_id;
    private int m_integrate;
    private int a_integrate;
    private String course_description;
    private String course_url;
    private String catena_name;

    public String getCatena_name() {
        return catena_name;
    }

    public void setCatena_name(String catena_name) {
        this.catena_name = catena_name;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public Integer getCourse_position() {
        return course_position;
    }

    public void setCourse_position(Integer course_position) {
        this.course_position = course_position;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }


    public int getCatena_id() {
        return catena_id;
    }

    public void setCatena_id(int catena_id) {
        this.catena_id = catena_id;
    }

    public int getM_integrate() {
        return m_integrate;
    }

    public void setM_integrate(int m_integrate) {
        this.m_integrate = m_integrate;
    }

    public int getA_integrate() {
        return a_integrate;
    }

    public void setA_integrate(int a_integrate) {
        this.a_integrate = a_integrate;
    }

    public String getCourse_description() {
        return course_description;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    public String getCourse_url() {
        return course_url;
    }

    public void setCourse_url(String course_url) {
        this.course_url = course_url;
    }
}
