package cn.edu.neu.cloudfactory.bean;

import java.util.Date;

public class Order {
    private int o_id;
    private String o_num;
    private int o_pid;
    private int o_amount;
    private String o_delivery;
    private String o_ddl;
    private int o_user;//经销商
    private String u_name;
    private String u_phone;
    private String u_address;
    private String o_state;

    /*
    * 订单状态：
    * 未保存
    * 已保存
    * 已发布
    * 待选标
    * 已接单
    * 生产中
    * 已完工
    * 已发货
    * 已完成
    *
    */

    public Order(String o_num, int o_pid, int o_amount, String o_delivery, String o_ddl, int o_user, String u_name, String u_phone, String u_address) {
        this.o_num = o_num;
        this.o_pid = o_pid;
        this.o_amount = o_amount;
        this.o_delivery = o_delivery;
        this.o_ddl = o_ddl;
        this.o_user = o_user;
        this.u_name = u_name;
        this.u_phone = u_phone;
        this.u_address = u_address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "o_id=" + o_id +
                ", o_num='" + o_num + '\'' +
                ", o_pid=" + o_pid +
                ", o_amount=" + o_amount +
                ", o_delivery='" + o_delivery + '\'' +
                ", o_ddl='" + o_ddl + '\'' +
                ", o_user=" + o_user +
                ", u_name='" + u_name + '\'' +
                ", u_phone='" + u_phone + '\'' +
                ", u_address='" + u_address + '\'' +
                ", o_state='" + o_state + '\'' +
                '}';
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public String getO_num() {
        return o_num;
    }

    public void setO_num(String o_num) {
        this.o_num = o_num;
    }

    public int getO_pid() {
        return o_pid;
    }

    public void setO_pid(int o_pid) {
        this.o_pid = o_pid;
    }

    public int getO_amount() {
        return o_amount;
    }

    public void setO_amount(int o_amount) {
        this.o_amount = o_amount;
    }

    public String getO_delivery() {
        return o_delivery;
    }

    public void setO_delivery(String o_delivery) {
        this.o_delivery = o_delivery;
    }

    public String getO_ddl() {
        return o_ddl;
    }

    public void setO_ddl(String o_ddl) {
        this.o_ddl = o_ddl;
    }

    public int getO_user() {
        return o_user;
    }

    public void setO_user(int o_user) {
        this.o_user = o_user;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_address() {
        return u_address;
    }

    public void setU_address(String u_address) {
        this.u_address = u_address;
    }

    public String getO_state() {
        return o_state;
    }

    public void setO_state(String o_state) {
        this.o_state = o_state;
    }

    public Order() {
    }

    public Order(String o_num, int o_pid, int o_amount, String o_delivery, String o_ddl, int o_user, String u_name, String u_phone, String u_address, String o_state) {
        this.o_num = o_num;
        this.o_pid = o_pid;
        this.o_amount = o_amount;
        this.o_delivery = o_delivery;
        this.o_ddl = o_ddl;
        this.o_user = o_user;
        this.u_name = u_name;
        this.u_phone = u_phone;
        this.u_address = u_address;
        this.o_state = o_state;
    }

    public Order(int o_id, String o_num, int o_pid, int o_amount, String o_delivery, String o_ddl, int o_user, String u_name, String u_phone, String u_address, String o_state) {
        this.o_id = o_id;
        this.o_num = o_num;
        this.o_pid = o_pid;
        this.o_amount = o_amount;
        this.o_delivery = o_delivery;
        this.o_ddl = o_ddl;
        this.o_user = o_user;
        this.u_name = u_name;
        this.u_phone = u_phone;
        this.u_address = u_address;
        this.o_state = o_state;
    }
}
