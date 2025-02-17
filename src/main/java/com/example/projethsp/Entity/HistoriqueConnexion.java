package com.example.projethsp.Entity;

import java.sql.Date;
import java.sql.Time;

public class HistoriqueConnexion {
    private String nom;
    private String prenom;
    private Date date;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public HistoriqueConnexion(String nom, String prenom, Date date, Time heure) {
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.heure = heure;
    }

    private Time heure;

}
