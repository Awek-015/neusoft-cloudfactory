package cn.edu.neu.cloudfactory.bean;

public class Throughput {
    private int p_id;
    private int e_id;
    private int capacity;

    @Override
    public String toString() {
        return "Throughput{" +
                "p_id=" + p_id +
                ", e_id=" + e_id +
                ", capacity=" + capacity +
                '}';
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Throughput() {
    }

    public Throughput(int p_id, int e_id, int capacity) {
        this.p_id = p_id;
        this.e_id = e_id;
        this.capacity = capacity;
    }
}
