package cn.edu.neu.cloudfactory.bean;

public class Equipment {
    private int e_id;
    private String e_num;
    private String e_name;
    private int e_type;
    private String e_details;
    private String e_state;
    private int e_factory;
    private String e_leasestate;

    @Override
    public String toString() {
        return "Equipment{" +
                "e_id=" + e_id +
                ", e_num='" + e_num + '\'' +
                ", e_name='" + e_name + '\'' +
                ", e_type=" + e_type +
                ", e_details='" + e_details + '\'' +
                ", e_state='" + e_state + '\'' +
                ", e_factory=" + e_factory +
                ", e_leasestate='" + e_leasestate + '\'' +
                '}';
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getE_num() {
        return e_num;
    }

    public void setE_num(String e_num) {
        this.e_num = e_num;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public int getE_type() {
        return e_type;
    }

    public void setE_type(int e_type) {
        this.e_type = e_type;
    }

    public String getE_details() {
        return e_details;
    }

    public void setE_details(String e_details) {
        this.e_details = e_details;
    }

    public String getE_state() {
        return e_state;
    }

    public void setE_state(String e_state) {
        this.e_state = e_state;
    }

    public int getE_factory() {
        return e_factory;
    }

    public void setE_factory(int e_factory) {
        this.e_factory = e_factory;
    }

    public String getE_leasestate() {
        return e_leasestate;
    }

    public void setE_leasestate(String e_leasestate) {
        this.e_leasestate = e_leasestate;
    }

    public Equipment() {
    }

    public Equipment(String e_num, String e_name, int e_type, String e_details, String e_state, int e_factory, String e_leasestate) {
        this.e_num = e_num;
        this.e_name = e_name;
        this.e_type = e_type;
        this.e_details = e_details;
        this.e_state = e_state;
        this.e_factory = e_factory;
        this.e_leasestate = e_leasestate;
    }

    public Equipment(int e_id, String e_num, String e_name, int e_type, String e_details, String e_state, int e_factory, String e_leasestate) {
        this.e_id = e_id;
        this.e_num = e_num;
        this.e_name = e_name;
        this.e_type = e_type;
        this.e_details = e_details;
        this.e_state = e_state;
        this.e_factory = e_factory;
        this.e_leasestate = e_leasestate;
    }
}
