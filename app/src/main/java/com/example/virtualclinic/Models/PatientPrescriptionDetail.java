package com.example.virtualclinic.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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
