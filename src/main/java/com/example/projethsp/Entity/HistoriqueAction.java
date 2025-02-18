package com.example.projethsp.Entity;

import java.sql.Date;
import java.sql.Time;

public class HistoriqueAction {

    private String nom;
    private String prenom;
    private String action;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public HistoriqueAction(String nom, String prenom, String action, Date date, Time heure) {
        this.nom = nom;
        this.prenom = prenom;
        this.action = action;
        this.date = date;
        this.heure = heure;
    }

    private Time heure;


}
