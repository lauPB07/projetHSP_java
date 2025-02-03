package com.example.projethsp.Entity;

import java.sql.Date;
import java.sql.Time;

public class FicheProduit {

    private int id;
    private String libelle;
    private String description;
    private float prix;
    private int nbStocker;
    private int niv;
    private int fournisseur;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNbStocker() {
        return nbStocker;
    }

    public void setNbStocker(int nbStocker) {
        this.nbStocker = nbStocker;
    }



    public int getNiv() {
        return niv;
    }

    public void setNiv(int niv) {
        this.niv = niv;
    }

    public int getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(int fournisseur) {
        this.fournisseur = fournisseur;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public FicheProduit(int id, String libelle, String description, float prix, int nbStocker, int niv, int fournisseur, int user) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.prix = prix;
        this.nbStocker = nbStocker;
        this.niv = niv;
        this.fournisseur = fournisseur;
        this.user = user;
    }

    public FicheProduit(int id, String libelle, int nbStocker) {
        this.id = id;
        this.libelle = libelle;
        this.nbStocker = nbStocker;
    }

    private int user;

    @Override
    public String toString() {
        return "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", nbStocker=" + nbStocker;
    }
}
