package com.example.virtualclinic.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JuniorDoctorLogin implements Serializable {
    @SerializedName("jrdoc_id")
    @Expose
    private int jrdoc_id;

    @SerializedName("full_name")
    @Expose
    private String full_name;

    @SerializedName("father__name")
    @Expose
    private String father__name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("dob")
    @Expose
    private String dob;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("contact")
    @Expose
    private String contact;

    @SerializedName("role")
    @Expose
    private String role;

    @SerializedName("status")
    @Expose
    private int status;

    public JuniorDoctorLogin(int jrdoc_id, String full_name, String father__name, String email, String password, String dob, String gender, String contact, String role, int status) {
        this.jrdoc_id = jrdoc_id;
        this.full_name = full_name;
        this.father__name = father__name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.contact = contact;
        this.role = role;
        this.status = status;
    }

    public int getJrdoc_id() {
        return jrdoc_id;
    }

    public void setJrdoc_id(int jrdoc_id) {
        this.jrdoc_id = jrdoc_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getFather_name() {
        return father__name;
    }

    public void setFather_name(String father_name) {
        this.father__name = father_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
