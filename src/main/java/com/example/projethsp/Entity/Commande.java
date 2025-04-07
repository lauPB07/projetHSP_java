package com.example.projethsp.Entity;

public class Commande {

    private int idCommande;
    private int nbProduit;
    private String fournisseur;
    private String nomUser;
    private String prenomUser;
    private float prixFinal;

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

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
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

    public float getPrixFinal() {
        return prixFinal;
    }

    public void setPrixFinal(float prixFinal) {
        this.prixFinal = prixFinal;
    }

    public String getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(String idStatus) {
        this.idStatus = idStatus;
    }

    public Commande(int idCommande, int nbProduit, String fournisseur, String nomUser, String prenomUser, float prixFinal, String idStatus) {
        this.idCommande = idCommande;
        this.nbProduit = nbProduit;
        this.fournisseur = fournisseur;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.prixFinal = prixFinal;
        this.idStatus = idStatus;
    }

    private String idStatus;


}