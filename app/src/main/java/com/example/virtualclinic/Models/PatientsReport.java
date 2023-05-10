package com.example.virtualclinic.Models;

import java.util.ArrayList;

public class PatientsReport {
    private int patient_id;
    private String full_name;
    private String relative_name;
    private String patientdob;
    private String bp;
    private String sugar;
    private String temperature;
    private String symptoms;
    private ArrayList<Prescription> prescriptions;

    public PatientsReport(int patient_id, String full_name, String relative_name, String patientdob, String bp, String sugar, String temperature, String symptoms, ArrayList<Prescription> prescriptions) {
        this.patient_id = patient_id;
        this.full_name = full_name;
        this.relative_name = relative_name;
        this.patientdob = patientdob;
        this.bp = bp;
        this.sugar = sugar;
        this.temperature = temperature;
        this.symptoms = symptoms;
        this.prescriptions = prescriptions;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getRelative_name() {
        return relative_name;
    }

    public String getPatientdob() {
        return patientdob;
    }

    public String getBp() {
        return bp;
    }

    public String getSugar() {
        return sugar;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }
}
