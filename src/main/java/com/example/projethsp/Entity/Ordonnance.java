package com.example.projethsp.Entity;

public class Ordonnance {
    private int id;
    private String contenue;
    private int ref_user;
    private int ref_patient;

    public Ordonnance( String contenue, int ref_patient) {
        this.contenue = contenue;
        this.ref_patient = ref_patient;
    }
    public Ordonnance(int id, String contenue, int ref_user, int ref_patient) {
        this.id = id;
        this.contenue = contenue;
        this.ref_user = ref_user;
        this.ref_patient = ref_patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenue() {
        return contenue;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

    public int getRef_user() {
        return ref_user;
    }

    public void setRef_user(int ref_user) {
        this.ref_user = ref_user;
    }

    public int getRef_patient() {
        return ref_patient;
    }

    public void setRef_patient(int ref_patient) {
        this.ref_patient = ref_patient;
    }


}
