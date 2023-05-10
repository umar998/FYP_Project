package com.example.virtualclinic.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Visits implements Serializable {
    @SerializedName("visit_id")
    @Expose
    private int visit_id;

    @SerializedName("jrdoc_id")
    @Expose
    private int jrdoc_id;
    @SerializedName("status")
    @Expose
    private int status;
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("AssignedDatetime")
    @Expose
    private String AssignedDatetime;

    public Visits(int visit_id, int jrdoc_id, int status, String date, String time, String assignedDatetime) {
        this.visit_id = visit_id;
        this.jrdoc_id = jrdoc_id;
        this.date = date;
        this.time = time;
        this.status=status;
        AssignedDatetime = assignedDatetime;
    }

    public int getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(int visit_id) {
        this.visit_id = visit_id;
    }

    public int getJrdoc_id() {
        return jrdoc_id;
    }

    public void setJrdoc_id(int jrdoc_id) {
        this.jrdoc_id = jrdoc_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAssignedDatetime() {
        return AssignedDatetime;
    }

    public void setAssignedDatetime(String assignedDatetime) {
        AssignedDatetime = assignedDatetime;
    }
}
