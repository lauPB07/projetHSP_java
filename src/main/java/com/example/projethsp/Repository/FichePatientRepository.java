package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.Utilisateur;
import com.example.projethsp.Entity.Utilisateurconnecte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FichePatientRepository {
    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();
    public ArrayList<Utilisateur> selectPatient() {
        ArrayList<Utilisateur> liste = new ArrayList<>();
        String sql = "SELECT ref_userPatient FROM fiche_patient WHERE valider = 0";
        String sql2 = "SELECT * FROM utilisateur WHERE id_utilisateur = ?";
        try {
            PreparedStatement requetePrepare1 = connection.prepareStatement(sql);
            ResultSet resultatRequette1 = requetePrepare1.executeQuery();
            while (resultatRequette1.next()) {
                PreparedStatement requetePrepare = connection.prepareStatement(sql2);
                requetePrepare.setInt(1, resultatRequette1.getInt(1));
                ResultSet resultatRequette = requetePrepare.executeQuery();
                if (resultatRequette.next()) {
                    liste.add(new Utilisateur(resultatRequette.getInt(1),resultatRequette.getString(2),resultatRequette.getString(3),resultatRequette.getString(4)));
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;

    }

    public void valider(int id){
        String sql = "UPDATE fiche_patient SET valider = 1 WHERE ref_userPatient = ?";

        try {
            PreparedStatement requetePrepare1 = connection.prepareStatement(sql);
            requetePrepare1.setInt(1, id);
            requetePrepare1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        sql = "INSERT INTO `historiqueaction`(`ref_user`, `action`, `date`, `heure`) VALUES (?,'Valider Fiche Patient',DATE( NOW() ),TIME(NOW()))";

        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setInt(1, Utilisateurconnecte.getInstance().getId());
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
