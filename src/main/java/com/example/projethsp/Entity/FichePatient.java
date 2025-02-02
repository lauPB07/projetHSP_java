package com.example.projethsp.Entity;

public class FichePatient {
    private int id_fichePatient;
    private int ref_userPatient;
    private int ref_userCreer;

    public FichePatient(int id_fichePatient, int ref_userPatient, int ref_userCreer) {
        this.id_fichePatient = id_fichePatient;
        this.ref_userPatient = ref_userPatient;
        this.ref_userCreer = ref_userCreer;
    }

    public int getId_fichePatient() {
        return id_fichePatient;
    }

    public void setId_fichePatient(int id_fichePatient) {
        this.id_fichePatient = id_fichePatient;
    }

    public int getRef_userPatient() {
        return ref_userPatient;
    }

    public void setRef_userPatient(int ref_userPatient) {
        this.ref_userPatient = ref_userPatient;
    }

    public int getRef_userCreer() {
        return ref_userCreer;
    }

    public void setRef_userCreer(int ref_userCreer) {
        this.ref_userCreer = ref_userCreer;
    }
}
