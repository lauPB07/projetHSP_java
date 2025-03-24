package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.Chambre;
import com.example.projethsp.Entity.Demande;
import com.example.projethsp.Entity.Ordonnance;
import com.example.projethsp.Entity.Utilisateurconnecte;

import java.sql.*;
import java.util.ArrayList;

public class OrdonnanceRepository {
    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();
    public void ajouter(Ordonnance ordonnance) {
        String sql = "INSERT INTO ordonnance (contenue,ref_user,ref_patient) VALUES (?,?,?)";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setString(1,ordonnance.getContenue());
            requetePrepare.setInt(2, Utilisateurconnecte.getInstance().getId());
            requetePrepare.setInt(3, ordonnance.getRef_patient());

            requetePrepare.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void valider(Ordonnance ordonnance) {
        String sql = "UPDATE ficheproduit SET valider = ? WHERE id = ?";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setString(1,ordonnance.getContenue());
            requetePrepare.setInt(2, Utilisateurconnecte.getInstance().getId());
            requetePrepare.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        sql = "INSERT INTO `historiqueaction`(`ref_user`, `action`, `date`, `heure`) VALUES (?,'Ajout ordonance',DATE( NOW() ),TIME(NOW()))";

        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setInt(1, Utilisateurconnecte.getInstance().getId());
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Ordonnance> selectPatient() {
        ArrayList<Ordonnance> liste = new ArrayList<>();
        String sql = "SELECT * FROM ordonnance WHERE ref_patient = ?;";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setInt(1, Utilisateurconnecte.getInstance().getId());
            ResultSet resultatRequette = requetePrepare.executeQuery();
            if (resultatRequette.next()) {
                liste.add(new Ordonnance(resultatRequette.getInt(1),resultatRequette.getString(2),resultatRequette.getInt(3),resultatRequette.getInt(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;

    }
}
