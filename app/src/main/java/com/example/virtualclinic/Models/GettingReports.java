package com.example.virtualclinic.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GettingReports implements Serializable {
//    @SerializedName("appointment_id")
//    @Expose
//    private int appointment_id;
//
//    @SerializedName("patient_id")
//    @Expose
//    private int patient_id;
//
//    @SerializedName("jrdoc_id")
//    @Expose
//    private int jrdoc_id;
//
//    @SerializedName("date")
//    @Expose
//    private String date;
//
//    @SerializedName("time")
//    @Expose
//    private String time;
//
//    @SerializedName("status")
//    @Expose
//    private int status;
//
//    @SerializedName("srdoc_id")
//    @Expose
//    private int srdoc_id;
//
//    @SerializedName("visit_id")
//    @Expose
//    private int visit_id;
//
//    @SerializedName("prescription_id")
//    @Expose
//    private int prescription_id;
//
//    @SerializedName("medicine_name")
//    @Expose
//    private String medicine_name;
//
//    @SerializedName("duration")
//    @Expose
//    private String duration;
//
//    @SerializedName("timings")
//    @Expose
//    private String timings;
//
//    public int getAppointment_id() {
//        return appointment_id;
//    }
//
//    public void setAppointment_id(int appointment_id) {
//        this.appointment_id = appointment_id;
//    }
//
//    public int getPatient_id() {
//        return patient_id;
//    }
//
//    public void setPatient_id(int patient_id) {
//        this.patient_id = patient_id;
//    }
//
//    public int getJrdoc_id() {
//        return jrdoc_id;
//    }
//
//    public void setJrdoc_id(int jrdoc_id) {
//        this.jrdoc_id = jrdoc_id;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public int getSrdoc_id() {
//        return srdoc_id;
//    }
//
//    public void setSrdoc_id(int srdoc_id) {
//        this.srdoc_id = srdoc_id;
//    }
//
//    public int getVisit_id() {
//        return visit_id;
//    }
//
//    public void setVisit_id(int visit_id) {
//        this.visit_id = visit_id;
//    }
//
//    public int getPrescription_id() {
//        return prescription_id;
//    }
//
//    public void setPrescription_id(int prescription_id) {
//        this.prescription_id = prescription_id;
//    }
//
//    public String getMedicine_name() {
//        return medicine_name;
//    }
//
//    public void setMedicine_name(String medicine_name) {
//        this.medicine_name = medicine_name;
//    }
//
//    public String getDuration() {
//        return duration;
//    }
//
//    public void setDuration(String duration) {
//        this.duration = duration;
//    }
//
//    public String getTimings() {
//        return timings;
//    }
//
//    public void setTimings(String timings) {
//        this.timings = timings;
//    }
    @SerializedName("pat")
    @Expose
    private PatientObject patientObject;

    @SerializedName("a")
    @Expose
    private SrDocAppointments srDocAppointments;


    public PatientObject getPatientObject() {
        return patientObject;
    }

    public void setPatientObject(PatientObject patientObject) {
        this.patientObject = patientObject;
    }

    public SrDocAppointments getSrDocAppointments() {
        return srDocAppointments;
    }

    public void setSrDocAppointments(SrDocAppointments srDocAppointments) {
        this.srDocAppointments = srDocAppointments;
    }
}
