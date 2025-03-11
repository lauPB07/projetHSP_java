package com.example.projethsp.Entity;

public class Hospitalisation {
    private int id;
    private String dateDebut;
    private String dateFin;
    private String Description;
    private int ref_chambre;
    private int ref_user;

    public Hospitalisation(String dateDebut, String dateFin, String description, int ref_chambre, int ref_user) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        Description = description;
        this.ref_chambre = ref_chambre;
        this.ref_user = ref_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getRef_chambre() {
        return ref_chambre;
    }

    public void setRef_chambre(int ref_chambre) {
        this.ref_chambre = ref_chambre;
    }

    public int getRef_user() {
        return ref_user;
    }

    public void setRef_user(int ref_user) {
        this.ref_user = ref_user;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", dateDebut='" + dateDebut + '\'' +
                ", dateFin='" + dateFin + '\'' +
                ", ref_chambre=" + ref_chambre +
                ", ref_user=" + ref_user;
    }
}
