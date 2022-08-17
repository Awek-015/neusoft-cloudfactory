package cn.edu.neu.cloudfactory.bean;

public class Bidding {
    private int id;
    private int o_id;
    private int f_id;
    private int price;
    private String state;

    public Bidding(int o_id, int f_id) {
        this.id=0;
        this.o_id = o_id;
        this.f_id = f_id;
        this.state="否";
    }

    @Override
    public String toString() {
        return "Bidding{" +
                "id=" + id +
                ", o_id=" + o_id +
                ", f_id=" + f_id +
                ", price=" + price +
                ", state='" + state + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Bidding() {
    }

    public Bidding(int id, int o_id, int f_id, int price, String state) {
        this.id = id;
        this.o_id = o_id;
        this.f_id = f_id;
        this.price = price;
        this.state = state;
    }
}
