package com.example.virtualclinic.Models;

import java.util.Collection;

public class patientsfullrecord{
    private int patient_id;
    private String cnic;
    private String full_name;
    private String relation;
    private String relative_name;
    private String dob;
    private String gender;
    private String date;
    private String time;
    private int vitalID;
    private String blood_pressure;
    private String sugar;
    private String temperature;
    private String symptoms;
    private byte[] image;
    private int visit_id;
    private int jrdoc_id;
    private String AssignedDatetime;

    public patientsfullrecord(int patient_id, String cnic, String full_name, String relation, String relative_name, String dob, String gender, String date, String time, int vitalID, String blood_pressure, String sugar, String temperature, String symptoms, byte[] image, int visit_id, int jrdoc_id, String assignedDatetime) {
        this.patient_id = patient_id;
        this.cnic = cnic;
        this.full_name = full_name;
        this.relation = relation;
        this.relative_name = relative_name;
        this.dob = dob;
        this.gender = gender;
        this.date = date;
        this.time = time;
        this.vitalID = vitalID;
        this.blood_pressure = blood_pressure;
        this.sugar = sugar;
        this.temperature = temperature;
        this.symptoms = symptoms;
        this.image = image;
        this.visit_id = visit_id;
        this.jrdoc_id = jrdoc_id;
        AssignedDatetime = assignedDatetime;
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

    public int getVitalID() {
        return vitalID;
    }

    public void setVitalID(int vitalID) {
        this.vitalID = vitalID;
    }

    public String getBlood_pressure() {
        return blood_pressure;
    }

    public void setBlood_pressure(String blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

    public String getAssignedDatetime() {
        return AssignedDatetime;
    }

    public void setAssignedDatetime(String assignedDatetime) {
        AssignedDatetime = assignedDatetime;
    }
}
