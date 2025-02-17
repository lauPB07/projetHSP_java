package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.HistoriqueConnexion;
import com.example.projethsp.Entity.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistoriqueConnexionRepository {

    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();


    public ArrayList<HistoriqueConnexion> recupererHistorique() {
        ArrayList<HistoriqueConnexion> liste = new ArrayList<>();
        String sql = "SELECT * FROM historique2connexion ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new HistoriqueConnexion(resultatRequette.getString("nom"),resultatRequette.getString("prenom"),resultatRequette.getDate("date"),resultatRequette.getTime("heure")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;
    }
}
