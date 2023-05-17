package com.example.virtualclinic.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class PatientsReport implements Serializable {
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

    @SerializedName("appointment_id")
    @Expose
    private int appointment_id;


    @SerializedName("jrdoc_id")
    @Expose
    private int jrdoc_id;


    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("srdoc_id")
    @Expose
    private int srdoc_id;

    @SerializedName("visit_id")
    @Expose
    private int visit_id;

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

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public int getJrdoc_id() {
        return jrdoc_id;
    }

    public void setJrdoc_id(int jrdoc_id) {
        this.jrdoc_id = jrdoc_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSrdoc_id() {
        return srdoc_id;
    }

    public void setSrdoc_id(int srdoc_id) {
        this.srdoc_id = srdoc_id;
    }

    public int getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(int visit_id) {
        this.visit_id = visit_id;
    }
}
