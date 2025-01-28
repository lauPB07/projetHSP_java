package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.Demande;
import com.example.projethsp.Entity.DemandeProduit;
import com.example.projethsp.Entity.Utilisateurconnecte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DemandeProduitRepository {
    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();
    public ArrayList<DemandeProduit> selectProduit(int id) {
        ArrayList<DemandeProduit> liste = new ArrayList<>();
        String sql = "SELECT d.*, p.libelle FROM demandeProduit as d INNER JOIN ficheproduit as p on d.ref_produit = p.id_ficheProduit WHERE d.ref_demande = ?";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setInt(1, id);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new DemandeProduit(resultatRequette.getInt("d.ref_demande"),resultatRequette.getInt("p.libelle"),resultatRequette.getInt("d.nb"),resultatRequette.getBoolean("d.valider")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;

    }
}
