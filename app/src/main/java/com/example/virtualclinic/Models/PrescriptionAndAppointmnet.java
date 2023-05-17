package com.example.virtualclinic.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class PrescriptionAndAppointmnet implements Serializable {

//    private int appointment_id;
//    private String medicine_name;
//    private String duration;
//    private String timings;
//    private int patient_id;
//    private int jrdoc_id;
//
//    public PrescriptionAndAppointmnet(int appointment_id, String medicine_name, String duration, String timings, int patient_id, int jrdoc_id) {
//        this.appointment_id = appointment_id;
//        this.medicine_name = medicine_name;
//        this.duration = duration;
//        this.timings = timings;
//        this.patient_id = patient_id;
//        this.jrdoc_id = jrdoc_id;
//    }
//
//    public int getAppointment_id() {
//        return appointment_id;
//    }
//
//    public void setAppointment_id(int appointment_id) {
//        this.appointment_id = appointment_id;
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
//    protected PrescriptionAndAppointmnet(Parcel in) {
//        appointment_id = in.readInt();
//        timings = in.readString();
//        medicine_name = in.readString();
//        duration = in.readString();
//        patient_id=in.readInt();
//        jrdoc_id=in.readInt();
//    }
//    public static final Parcelable.Creator<PrescriptionAndAppointmnet> CREATOR = new Parcelable.Creator<PrescriptionAndAppointmnet>() {
//        @Override
//        public PrescriptionAndAppointmnet createFromParcel(Parcel in) {
//            return new PrescriptionAndAppointmnet(in);
//        }
//
//        @Override
//        public PrescriptionAndAppointmnet[] newArray(int size) {
//            return new PrescriptionAndAppointmnet[size];
//        }
//    };
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(appointment_id);
//        dest.writeString(timings);
//        dest.writeString(medicine_name);
//        dest.writeString(duration);
//        dest.writeInt(patient_id);
//        dest.writeInt(jrdoc_id);
//    }

}
