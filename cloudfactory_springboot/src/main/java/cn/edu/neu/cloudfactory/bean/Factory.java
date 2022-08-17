package cn.edu.neu.cloudfactory.bean;

public class Factory {
    private int f_id;
    private String f_name;
    private String f_detail;
    private String f_state;

    @Override
    public String toString() {
        return "Factory{" +
                "f_id=" + f_id +
                ", f_name='" + f_name + '\'' +
                ", f_detail='" + f_detail + '\'' +
                ", f_state='" + f_state + '\'' +
                '}';
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getF_detail() {
        return f_detail;
    }

    public void setF_detail(String f_detail) {
        this.f_detail = f_detail;
    }

    public String getF_state() {
        return f_state;
    }

    public void setF_state(String f_state) {
        this.f_state = f_state;
    }

    public Factory() {
    }

    public Factory(String f_name, String f_detail, String f_state) {
        this.f_name = f_name;
        this.f_detail = f_detail;
        this.f_state = f_state;
    }

    public Factory(int f_id, String f_name, String f_detail, String f_state) {
        this.f_id = f_id;
        this.f_name = f_name;
        this.f_detail = f_detail;
        this.f_state = f_state;
    }
}
