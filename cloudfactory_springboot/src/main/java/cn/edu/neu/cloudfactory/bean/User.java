package cn.edu.neu.cloudfactory.bean;

public class User {
    private int user_id;
    private String user_account;
    private String user_password;
    private String user_name;
    private String user_phone;
    private int user_role;
    private int user_factory;

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_account='" + user_account + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_role=" + user_role +
                ", user_factory=" + user_factory +
                '}';
    }

    public User(String user_account, String user_password, String user_name, String user_phone, int user_role, int user_factory) {
        this.user_account = user_account;
        this.user_password = user_password;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_role = user_role;
        this.user_factory = user_factory;
    }

    public User() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public int getUser_role() {
        return user_role;
    }

    public void setUser_role(int user_role) {
        this.user_role = user_role;
    }

    public int getUser_factory() {
        return user_factory;
    }

    public void setUser_factory(int user_factory) {
        this.user_factory = user_factory;
    }
}
