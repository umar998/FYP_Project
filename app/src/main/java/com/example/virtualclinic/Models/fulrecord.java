package com.example.virtualclinic.Models;

import java.util.Map;

public class fulrecord {
    Map<String,PatientObject>p;
    Map<String, Vitals>v;
    Map<String, Visits>x;

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

    public Map<String, Visits> getX() {
        return x;
    }

    public void setX(Map<String, Visits> x) {
        this.x = x;
    }
}
