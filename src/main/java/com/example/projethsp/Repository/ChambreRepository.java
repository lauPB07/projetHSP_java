package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.Chambre;
import com.example.projethsp.Entity.DemandeProduit;
import com.example.projethsp.Entity.Utilisateur;

import java.sql.*;
import java.util.ArrayList;

public class ChambreRepository {
    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();
    public ArrayList<Chambre> selectChambre(String dateDebut, String dateFin) {
        ArrayList<Chambre> liste = new ArrayList<>();
        String sql = "SELECT * FROM chambre WHERE id_chambre not in(SELECT DISTINCT ref_chambre FROM hospitalisation WHERE dateDebut BETWEEN ? AND ? OR dateFin BETWEEN ? AND ?);";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setDate(1, Date.valueOf(dateDebut));
            requetePrepare.setDate(2, Date.valueOf(dateFin));
            requetePrepare.setDate(3, Date.valueOf(dateDebut));
            requetePrepare.setDate(4, Date.valueOf(dateFin));
            ResultSet resultatRequette = requetePrepare.executeQuery();
           if (resultatRequette.next()) {
               liste.add(new Chambre(resultatRequette.getInt(1),resultatRequette.getString(2)));
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;

    }
}
