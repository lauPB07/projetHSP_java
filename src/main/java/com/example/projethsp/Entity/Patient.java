package com.example.projethsp.Entity;

public class Patient extends Utilisateur{
    private String telephone;
    private String rue;
    private String cp;
    private String ville;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNumSecu() {
        return numSecu;
    }

    public void setNumSecu(String numSecu) {
        this.numSecu = numSecu;
    }

    private String numSecu;

    public Patient(int id, String nom, String prenom, String email, int role, String telephone,String rue,String cp,String ville,String numSecu) {
        super(id, nom, prenom, email, role);
        this.telephone = telephone;
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
        this.numSecu = numSecu;
    }
}
