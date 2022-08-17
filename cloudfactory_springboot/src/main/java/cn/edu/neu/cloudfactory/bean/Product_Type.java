package cn.edu.neu.cloudfactory.bean;

public class Product_Type {
    private int pt_id;
    private String pt_name;

    @Override
    public String toString() {
        return "Product_Type{" +
                "pt_id=" + pt_id +
                ", pt_name='" + pt_name + '\'' +
                '}';
    }

    public int getPt_id() {
        return pt_id;
    }

    public void setPt_id(int pt_id) {
        this.pt_id = pt_id;
    }

    public String getPt_name() {
        return pt_name;
    }

    public void setPt_name(String pt_name) {
        this.pt_name = pt_name;
    }

    public Product_Type() {
    }

    public Product_Type(String pt_name) {
        this.pt_name = pt_name;
    }

    public Product_Type(int pt_id, String pt_name) {
        this.pt_id = pt_id;
        this.pt_name = pt_name;
    }
}
