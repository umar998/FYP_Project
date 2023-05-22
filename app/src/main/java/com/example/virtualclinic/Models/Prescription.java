package com.example.virtualclinic.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Prescription implements Parcelable {
    private int appointment_id;
    private String medicine_name;
    private String duration;
    private String timings;
    private String date;

    public Prescription(int appointment_id, String medicine, String duration, String timings, String date) {
        this.appointment_id = appointment_id;
        this.medicine_name = medicine;
        this.duration = duration;
        this.timings = timings;
        this.date=date;
    }

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getMedicine() {
        return medicine_name;
    }

    public void setMedicine(String medicine) {
        this.medicine_name = medicine;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTiming() {
        return timings;
    }

    public void setTiming(String timings) {
        this.timings = timings;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String Date) {
        this.date = Date;
    }

    protected Prescription(Parcel in) {
        appointment_id = in.readInt();
        timings = in.readString();
        medicine_name = in.readString();
        duration = in.readString();
        date=in.readString();
    }

    public static final Parcelable.Creator<Prescription> CREATOR = new Parcelable.Creator<Prescription>() {
        @Override
        public Prescription createFromParcel(Parcel in) {
            return new Prescription(in);
        }

        @Override
        public Prescription[] newArray(int size) {
            return new Prescription[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(appointment_id);
        dest.writeString(timings);
        dest.writeString(medicine_name);
        dest.writeString(duration);
        dest.writeString(date);
    }


}
