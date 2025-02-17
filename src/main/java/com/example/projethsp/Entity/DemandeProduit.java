package com.example.projethsp.Entity;

public class DemandeProduit {
    private int ref_demande;
    private int ref_produit;
    private int nb_produit;
    private boolean valider;
    private String nom;

    public DemandeProduit(int ref_demande, int ref_produit, int nb_produit, boolean valider) {
        this.ref_demande = ref_demande;
        this.ref_produit = ref_produit;
        this.nb_produit = nb_produit;
        this.valider = valider;
    }

    public DemandeProduit(int ref_demande, int ref_produit, int nb_produit, boolean valider, String nom) {
        this.ref_demande = ref_demande;
        this.ref_produit = ref_produit;
        this.nb_produit = nb_produit;
        this.valider = valider;
        this.nom = nom;
    }

    public int getRef_demande() {
        return ref_demande;
    }

    public void setRef_demande(int ref_demande) {
        this.ref_demande = ref_demande;
    }

    public int getRef_produit() {
        return ref_produit;
    }

    public void setRef_produit(int ref_produit) {
        this.ref_produit = ref_produit;
    }

    public int getNb_produit() {
        return nb_produit;
    }

    public void setNb_produit(int nb_produit) {
        this.nb_produit = nb_produit;
    }

    public boolean isValider() {
        return valider;
    }

    public void setValider(boolean valider) {
        this.valider = valider;
    }

    @Override
    public String toString() {
        return "DemandeProduit{" +
                "ref_demande=" + ref_demande +
                ", ref_produit=" + ref_produit +
                ", nb_produit=" + nb_produit +
                ", valider=" + valider +
                ", nom='" + nom + '\'' +
                '}';
    }
}
