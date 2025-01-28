package com.example.projethsp.Entity;

public class Demande {
    private int id;
    private String description;
    private String ref_user;

    public Demande(int id, String description) {
        this.id = id;
        this.description = description;
    }

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

    public String getRef_user() {
        return ref_user;
    }

    public void setRef_user(String ref_user) {
        this.ref_user = ref_user;
    }
}
