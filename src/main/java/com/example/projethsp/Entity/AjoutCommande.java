package com.example.projethsp.Entity;

public class AjoutCommande {
    private String nomFournisseur;
    private String nomProduit;

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    private int quantite;

    public AjoutCommande(String nomFournisseur, String nomProduit, double prixInitial, double prixFinal, int quantite) {
        this.nomFournisseur = nomFournisseur;
        this.nomProduit = nomProduit;
        this.prixInitial = prixInitial;
        this.prixFinal = prixFinal;
        this.quantite = quantite;
    }

    private double prixInitial;

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(double prixInitial) {
        this.prixInitial = prixInitial;
    }

    public double getPrixFinal() {
        return prixFinal;
    }

    public void setPrixFinal(double prixFinal) {
        this.prixFinal = prixFinal;
    }

    private double prixFinal;


}
