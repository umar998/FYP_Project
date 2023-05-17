package com.example.virtualclinic.Models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class PatientPrescriptionDetail implements Serializable {

    @SerializedName("x")
    @Expose
    private SrDocAppointments srDocAppointments;

    @SerializedName("p")
    @Expose
    private Prescription prescription;

    public SrDocAppointments getSrDocAppointments() {
        return srDocAppointments;
    }

    public Prescription getPrescription() {
        return prescription;
    }
}
