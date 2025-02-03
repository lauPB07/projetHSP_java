package com.example.projethsp.Entity;

public class Demande {
    private int id;
    private String titre;
    private String description;
    private boolean valider;
    private int ref_user;

    public Demande(int id, String titre, String description, boolean valider, int ref_user) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.valider = valider;
        this.ref_user = ref_user;
    }
    public Demande(String titre, String description) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.valider = valider;
        this.ref_user = ref_user;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isValider() {
        return valider;
    }

    public void setValider(boolean valider) {
        this.valider = valider;
    }

    public int getRef_user() {
        return ref_user;
    }

    public void setRef_user(int ref_user) {
        this.ref_user = ref_user;
    }
}
