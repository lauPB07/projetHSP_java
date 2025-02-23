package com.example.projethsp.Entity;

public class Commande {

    private int idCommande;
    private int nbProduit;
    private int idUser;
    private String nomUser;
    private String prenomUser;
    private int idProduit;
    private float prixProduit;
    private float prixFinal;
    private int idStatus;
    private int idFournisseur;

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getNbProduit() {
        return nbProduit;
    }

    public void setNbProduit(int nbProduit) {
        this.nbProduit = nbProduit;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public float getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(float prixProduit) {
        this.prixProduit = prixProduit;
    }

    public float getPrixFinal() {
        return prixFinal;
    }

    public void setPrixFinal(float prixFinal) {
        this.prixFinal = prixFinal;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public Boolean getValidate() {
        return isValidate;
    }

    public void setValidate(Boolean validate) {
        isValidate = validate;
    }

    public Commande(int idCommande, int nbProduit, int idUser, String nomUser, String prenomUser, int idProduit, float prixProduit, float prixFinal, int idStatus, int idFournisseur, Boolean isValidate) {
        this.idCommande = idCommande;
        this.nbProduit = nbProduit;
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.idProduit = idProduit;
        this.prixProduit = prixProduit;
        this.prixFinal = prixFinal;
        this.idStatus = idStatus;
        this.idFournisseur = idFournisseur;
        this.isValidate = isValidate;
    }

    private Boolean isValidate;

}