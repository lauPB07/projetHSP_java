package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.Chambre;
import com.example.projethsp.Entity.DemandeProduit;
import com.example.projethsp.Entity.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChambreRepository {
    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();
    public ArrayList<Chambre> selectChambre() {
        ArrayList<Chambre> liste = new ArrayList<>();
        String sql = "SELECT * FROM chambre ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
           if (resultatRequette.next()) {
               liste.add(new Chambre(resultatRequette.getInt(1),resultatRequette.getString(2),resultatRequette.getInt(3)));
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;

    }
}
