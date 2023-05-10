package com.example.virtualclinic.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SrDocAppointments  implements Serializable {
    @SerializedName("appointment_id")
    @Expose
    private int appointment_id;

    @SerializedName("patient_id")
    @Expose
    private int patient_id;

    @SerializedName("jrdoc_id")
    @Expose
    private int jrdoc_id;

    @SerializedName("date")
    @Expose
   // private float rating;
    private String date;

    @SerializedName("time")
    @Expose
    private String time;

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("srdoc_id")
    @Expose
    private int srdoc_id;

    @SerializedName("visit_id")
    @Expose
    private int visit_id;


    public int getAppointment_id() {
        return appointment_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public int getJrdoc_id() {
        return jrdoc_id;
    }

//    public float getRating() {
//        return rating;
//    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getStatus() {
        return status;
    }

    public int getSrdoc_id() {
        return srdoc_id;
    }

    public int getVisit_id() {
        return visit_id;
    }

//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(appointment_id);
//        dest.writeInt(patient_id);
//        dest.writeInt(je);
//    }
}
