package cn.edu.neu.cloudfactory.bean;

public class Equipment_Type {
    private int et_id;
    private String et_name;

    @Override
    public String toString() {
        return "Equipment_Type{" +
                "et_id=" + et_id +
                ", et_name='" + et_name + '\'' +
                '}';
    }

    public int getEt_id() {
        return et_id;
    }

    public void setEt_id(int et_id) {
        this.et_id = et_id;
    }

    public String getEt_name() {
        return et_name;
    }

    public void setEt_name(String et_name) {
        this.et_name = et_name;
    }

    public Equipment_Type() {
    }

    public Equipment_Type(String et_name) {
        this.et_name = et_name;
    }

    public Equipment_Type(int et_id, String et_name) {
        this.et_id = et_id;
        this.et_name = et_name;
    }
}
