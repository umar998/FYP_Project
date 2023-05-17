package com.example.virtualclinic.Models;

public class Nurse {
    private int nurseID;
    private String full_name;
    private String email;
    private String password;
    private String role;

    public Nurse(int nurse_ID, String full_name, String email, String password, String role) {
        this.nurseID = nurse_ID;
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getNurse_ID() {
        return nurseID;
    }

    public void setNurse_ID(int nurse_ID) {
        this.nurseID = nurse_ID;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
