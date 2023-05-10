package com.example.virtualclinic.Models;

public class SeniorDoctorLogin {
    private int srdoc_id;
    private String full_name;
    private String email;
    private String password;
    private String role;
    private int status;

    public SeniorDoctorLogin(int srdoc_id, String full_name, String email, String password, String role, int status) {
        this.srdoc_id = srdoc_id;
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public int getSrdoc_id() {
        return srdoc_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public int getStatus() {
        return status;
    }
}
