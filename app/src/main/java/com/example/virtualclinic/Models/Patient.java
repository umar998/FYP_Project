package com.example.virtualclinic.Models;

public class Patient {
    public String CNIC;
    public String full_name ;
    public String relation;
    public String relative_name;
    public String DOB;
    public String Gender;

    private int patient_id;

    // Constructor and other methods here

    public void setNumOfPatients(int numOfPatients) {
        this.patient_id = numOfPatients;
    }

    public int getNumOfPatients() {
        return this.patient_id;
    }


}
