package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.Demande;
import com.example.projethsp.Entity.Utilisateur;
import com.example.projethsp.Entity.Utilisateurconnecte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DemandeRepository {
    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();

    public ArrayList<Demande> select(){
        ArrayList<Demande> liste = new ArrayList<>();
        String sql = "SELECT * FROM demande WHERE ref_userDemandeur = ?";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setInt(1, Utilisateurconnecte.getInstance().getId());
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new Demande(resultatRequette.getInt("id_demande"),resultatRequette.getString("description")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;
    }
}
