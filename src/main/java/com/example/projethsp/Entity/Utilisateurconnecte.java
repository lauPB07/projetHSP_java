package com.example.projethsp.Entity;

public class Utilisateurconnecte extends Utilisateur {
    private static Utilisateurconnecte INSTANCE;

    private Utilisateurconnecte(int id, String nom, String prenom, String email, String mdp,int role) {
        super(id, nom, prenom, email, mdp,role);
    }

    public static boolean initInstance(Utilisateur user) {
        if (INSTANCE == null && user != null) {
            INSTANCE = new Utilisateurconnecte(user.getId(), user.getNom(), user.getPrenom(), user.getEmail(), user.getMdp(),user.getRole());
            return true;
        }
        return false;
    }

    public static Utilisateurconnecte getInstance() {
        return INSTANCE;
    }

    public static boolean clearInstance() {
        if (INSTANCE != null) {
            INSTANCE = null;
            return true;
        }
        return false;
    }
}
