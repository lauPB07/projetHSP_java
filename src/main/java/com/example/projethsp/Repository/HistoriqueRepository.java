package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.HistoriqueAction;
import com.example.projethsp.Entity.HistoriqueConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistoriqueRepository {

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

    public ArrayList<HistoriqueAction> recupererHistoriqueAction(){
        ArrayList<HistoriqueAction> liste = new ArrayList<>();
        String sql = "SELECT * FROM historiqueaction2 ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new HistoriqueAction(resultatRequette.getString("nomUser"),resultatRequette.getString("prenomUser"),resultatRequette.getString("action"),resultatRequette.getDate("date"),resultatRequette.getTime("heure")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;

    }
}
