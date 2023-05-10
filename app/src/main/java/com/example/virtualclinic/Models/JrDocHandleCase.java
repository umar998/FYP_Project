package com.example.virtualclinic.Models;

import java.util.Map;

public class JrDocHandleCase {
    Map<String, Visits> x;
    Map<String,PatientObject>p;
    Map<String, Vitals>v;
    Map<String,JuniorDoctorLogin>jr;
    Map<String,AcceptCase>ac;
    Map<String,SrDocAppointments>apt;

    public Map<String, Visits> getX() {
        return x;
    }
    public void setX(Map<String, Visits> x) {
        this.x = x;
    }

    public Map<String, PatientObject> getP() {
        return p;
    }
    public void setP(Map<String, PatientObject> p) {
        this.p = p;
    }

    public Map<String, Vitals> getV() {
        return v;
    }
    public void setV(Map<String, Vitals> v) {
        this.v = v;
    }

    public Map<String, JuniorDoctorLogin> getJ() {
        return jr;
    }
    public void setJ(Map<String, JuniorDoctorLogin> jr) {
        this.jr = jr;
    }

    public Map<String, AcceptCase> getA() {
        return ac;
    }
    public void setA(Map<String, AcceptCase> ac) {
        this.ac = ac;
    }

    public Map<String, SrDocAppointments> getT() {
        return apt;
    }
    public void setT(Map<String, SrDocAppointments> apt) {
        this.apt = apt;
    }
//    private int visit_id;
//    private int patient_id;
//    private int jrdoc_id;
//    private int status;
//    private String date;
//    private String time;
//    private Date AssignedDatetime;
//    private String cnic;
//    private String full_name;
//    private String relation;
//    private String relative_name;
//    private String dob;
//    private String gender;
//    private int vitalID;
//    private String blood_pressure;
//    private String sugar;
//    private String temperature;
//    private String symptoms;
//    private String image;
//    private int rated;
//    private int acceptCaseID;
//    private int appointment_id;
//    private int srdoc_id;
//
//    public JrDocHandleCase(int visit_id, int patient_id, int jrdoc_id, int status, String date, String time, Date assignedDatetime, String cnic, String full_name, String relation, String relative_name, String dob, String gender, int vitalID, String blood_pressure, String sugar, String temperature, String symptoms, String image, int rated, int acceptCaseID, int appointment_id, int srdoc_id) {
//        this.visit_id = visit_id;
//        this.patient_id = patient_id;
//        this.jrdoc_id = jrdoc_id;
//        this.status = status;
//        this.date = date;
//        this.time = time;
//        AssignedDatetime = assignedDatetime;
//        this.cnic = cnic;
//        this.full_name = full_name;
//        this.relation = relation;
//        this.relative_name = relative_name;
//        this.dob = dob;
//        this.gender = gender;
//        this.vitalID = vitalID;
//        this.blood_pressure = blood_pressure;
//        this.sugar = sugar;
//        this.temperature = temperature;
//        this.symptoms = symptoms;
//        this.image = image;
//        this.rated = rated;
//        this.acceptCaseID = acceptCaseID;
//        this.appointment_id = appointment_id;
//        this.srdoc_id = srdoc_id;
//    }
//
//    public int getVisit_id() {
//        return visit_id;
//    }
//
//    public int getPatient_id() {
//        return patient_id;
//    }
//
//    public int getJrdoc_id() {
//        return jrdoc_id;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public Date getAssignedDatetime() {
//        return AssignedDatetime;
//    }
//
//    public String getCnic() {
//        return cnic;
//    }
//
//    public String getFull_name() {
//        return full_name;
//    }
//
//    public String getRelation() {
//        return relation;
//    }
//
//    public String getRelative_name() {
//        return relative_name;
//    }
//
//    public String getDob() {
//        return dob;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public int getVitalID() {
//        return vitalID;
//    }
//
//    public String getBlood_pressure() {
//        return blood_pressure;
//    }
//
//    public String getSugar() {
//        return sugar;
//    }
//
//    public String getTemperature() {
//        return temperature;
//    }
//
//    public String getSymptoms() {
//        return symptoms;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public int getRated() {
//        return rated;
//    }
//
//    public int getAcceptCaseID() {
//        return acceptCaseID;
//    }
//
//    public int getAppointment_id() {
//        return appointment_id;
//    }
//
//    public int getSrdoc_id() {
//        return srdoc_id;
//    }
}
