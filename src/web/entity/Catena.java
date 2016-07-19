package web.entity;

/**
 * Created by AaahhhXin on 2016/7/10.
 */
public class Catena {
    private Integer catena_id;
    private String catena_name;

    private String salary;
    private String catena_description;
    private String img_url;

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCatena_description() {
        return catena_description;
    }

    public void setCatena_description(String catena_description) {
        this.catena_description = catena_description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public Integer getCatena_id() {
        return catena_id;
    }

    public void setCatena_id(Integer catena_id) {
        this.catena_id = catena_id;
    }

    public String getCatena_name() {
        return catena_name;
    }

    public void setCatena_name(String catena_name) {
        this.catena_name = catena_name;
    }

}
