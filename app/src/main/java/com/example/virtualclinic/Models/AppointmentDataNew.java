package com.example.virtualclinic.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AppointmentDataNew implements Serializable {
    @SerializedName("x")
    @Expose
    private Visits visitDetails;

    @SerializedName("p")
    @Expose
    private PatientObject patientObject;

    @SerializedName("v")
    @Expose
    private Vitals vitals;

    @SerializedName("jr")
    @Expose
    private JuniorDoctorLogin juniorDoctorLogin;

    @SerializedName("ac")
    @Expose
    private AcceptCase acceptCase;
    @SerializedName("apt")
    @Expose
    private SrDocAppointments srDocAppointments;

    public Visits getVisitDetails() {
        return visitDetails;
    }

    public void setVisitDetails(Visits visitDetails) {
        this.visitDetails = visitDetails;
    }

    public PatientObject getPatientObject() {
        return patientObject;
    }

    public void setPatientObject(PatientObject patientObject) {
        this.patientObject = patientObject;
    }

    public Vitals getVitals() {
        return vitals;
    }

    public void setVitals(Vitals vitals) {
        this.vitals = vitals;
    }

    public JuniorDoctorLogin getJuniorDoctorLogin() {
        return juniorDoctorLogin;
    }

    public void setJuniorDoctorLogin(JuniorDoctorLogin juniorDoctorLogin) {
        this.juniorDoctorLogin = juniorDoctorLogin;
    }

    public AcceptCase getAcceptCase() {
        return acceptCase;
    }

    public void setAcceptCase(AcceptCase acceptCase) {
        this.acceptCase = acceptCase;
    }

    public SrDocAppointments getSrDocAppointments() {
        return srDocAppointments;
    }

    public void setSrDocAppointments(SrDocAppointments srDocAppointments) {
        this.srDocAppointments = srDocAppointments;
    }
}
