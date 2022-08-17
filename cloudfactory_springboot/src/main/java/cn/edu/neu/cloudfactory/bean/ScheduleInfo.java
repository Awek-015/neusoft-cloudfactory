package cn.edu.neu.cloudfactory.bean;

import java.util.Date;

public class ScheduleInfo {
    private int id;
    private int e_id;
    private String start;
    private String end;
    private int o_id;

    @Override
    public String toString() {
        return "ScheduleInfo{" +
                "id=" + id +
                ", e_id=" + e_id +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", o_id=" + o_id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public ScheduleInfo() {
    }

    public ScheduleInfo(int e_id, String start, String end, int o_id) {
        this.e_id = e_id;
        this.start = start;
        this.end = end;
        this.o_id = o_id;
    }

    public ScheduleInfo(int id, int e_id, String start, String end, int o_id) {
        this.id = id;
        this.e_id = e_id;
        this.start = start;
        this.end = end;
        this.o_id = o_id;
    }
}
