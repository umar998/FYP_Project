package com.example.virtualclinic.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Vitals implements Serializable {
    @SerializedName("blood_pressure")
    @Expose
    private String blood_pressure;

    @SerializedName("sugar")
    @Expose
    private String sugar;

    @SerializedName("temperature")
    @Expose
    private String temperature;

    @SerializedName("symptoms")
    @Expose
    private String symptoms;

    @SerializedName("vitalID")
    @Expose
    private int vitalID;

    @SerializedName("image")
    @Expose
    private String image;

    public Vitals(int vitalid, String bp, String sugar, String temp, String symptoms, String image) {
        this.vitalID = vitalid;
        this.blood_pressure = bp;
        this.sugar = sugar;
        this.temperature = temp;
        this.symptoms = symptoms;
        this.image = image;
    }

    public int getVitalID() {
        return vitalID;
    }

    public void setVitalID(String bp) {
        this.vitalID = vitalID;
    }

    public String getBp() {
        return blood_pressure;
    }

    public void setBp(String bp) {
        this.blood_pressure = bp;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getTemp() {
        return temperature;
    }

    public void setTemp(String temp) {
        this.temperature = temp;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
