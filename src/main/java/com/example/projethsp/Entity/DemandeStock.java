package com.example.projethsp.Entity;

public class DemandeStock {

    private int id;

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    private int idProduit;
    private String description;
    private String nom;
    private String prenom;
    private String libelle;
    private int nb;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public boolean isValidate() {
        return isValidate;
    }

    public void setValidate(boolean validate) {
        isValidate = validate;
    }

    public DemandeStock(int id,int idProduit, String description, String nom, String prenom, String libelle, int nb, boolean isValidate) {
        this.id = id;
        this.idProduit = idProduit;
        this.description = description;
        this.nom = nom;
        this.prenom = prenom;
        this.libelle = libelle;
        this.nb = nb;
        this.isValidate = isValidate;
    }

    private boolean isValidate ;


}
