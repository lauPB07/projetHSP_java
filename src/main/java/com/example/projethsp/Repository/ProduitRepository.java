package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.FicheProduit;
import com.example.projethsp.Entity.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProduitRepository {
    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();

    public ArrayList<FicheProduit> recupererProduit() {
        ArrayList<FicheProduit> liste = new ArrayList<>();
        String sql = "SELECT * FROM ficheproduit ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new FicheProduit(resultatRequette.getInt("id_ficheProduit"),resultatRequette.getString("libelle"),resultatRequette.getString("description"),resultatRequette.getFloat("prix"),resultatRequette.getInt("nbStocker"),resultatRequette.getInt("ref_niv"),resultatRequette.getInt("ref_fournisseur"),resultatRequette.getInt("ref_user")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;
    }
}
