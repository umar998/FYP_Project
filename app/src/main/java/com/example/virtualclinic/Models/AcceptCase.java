package com.example.virtualclinic.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AcceptCase implements Serializable {
    @SerializedName("acceptCaseID")
    @Expose
    private int acceptCaseID;
    @SerializedName("patient_id")
    @Expose
    private int patient_id;
    @SerializedName("jrdoc_id")
    @Expose
    private int jrdoc_id;
    @SerializedName("time")
    @Expose
    private String  time;
    @SerializedName("visit_id")
    @Expose
    private int visit_id;

    public AcceptCase(int acceptCaseID, int patient_id, int jrdoc_id, String time, int visit_id) {
        this.acceptCaseID = acceptCaseID;
        this.patient_id = patient_id;
        this.jrdoc_id = jrdoc_id;
        this.time = time;
        this.visit_id = visit_id;
    }

    public int getAcceptCaseID() {
        return acceptCaseID;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public int getJrdoc_id() {
        return jrdoc_id;
    }

    public String getTime() {
        return time;
    }

    public int getVisit_id() {
        return visit_id;
    }
}
