package cn.edu.neu.cloudfactory.bean;

public class Product {
    private int p_id;
    private String p_num;
    private String p_name;
    private int p_type;
    private String p_details;

    @Override
    public String toString() {
        return "Product{" +
                "p_id=" + p_id +
                ", p_num='" + p_num + '\'' +
                ", p_name='" + p_name + '\'' +
                ", p_type=" + p_type +
                ", p_details='" + p_details + '\'' +
                '}';
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_num() {
        return p_num;
    }

    public void setP_num(String p_num) {
        this.p_num = p_num;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public int getP_type() {
        return p_type;
    }

    public void setP_type(int p_type) {
        this.p_type = p_type;
    }

    public String getP_details() {
        return p_details;
    }

    public void setP_details(String p_details) {
        this.p_details = p_details;
    }

    public Product() {
    }

    public Product(String p_num, String p_name, int p_type, String p_details) {
        this.p_num = p_num;
        this.p_name = p_name;
        this.p_type = p_type;
        this.p_details = p_details;
    }

    public Product(int p_id, String p_num, String p_name, int p_type, String p_details) {
        this.p_id = p_id;
        this.p_num = p_num;
        this.p_name = p_name;
        this.p_type = p_type;
        this.p_details = p_details;
    }
}
