package com.example.virtualclinic.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PatientObject implements Serializable {

    @SerializedName("patient_id")
    @Expose
    private int patient_id;
    @SerializedName("cnic")
    @Expose
    private String cnic;

    @SerializedName("full_name")
    @Expose
    private String full_name;

    @SerializedName("relation")
    @Expose
    private String relation;

    @SerializedName("relative_name")
    @Expose
    private String relative_name;

    @SerializedName("dob")
    @Expose
    private String dob;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("time")
    @Expose
    private String time;

    public PatientObject(int patient_id, String cnic, String full_name, String relation, String relative_name, String dob, String gender, String date, String time) {
        this.patient_id = patient_id;
        this.cnic = cnic;
        this.full_name = full_name;
        this.relation = relation;
        this.relative_name = relative_name;
        this.dob = dob;
        this.gender = gender;
        this.date = date;
        this.time = time;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getRelative_name() {
        return relative_name;
    }

    public void setRelative_name(String relative_name) {
        this.relative_name = relative_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
}
