package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;

import java.sql.*;
import java.util.List;

public class CommandeAjoutRepository {

    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();

    public void insererCommande(int nbProduit, int ref_user, float prixFinal, int ref_fournisseur) throws SQLException {
        String sql = "INSERT INTO commande (`nbProduit`, `ref_user`, `prixFinal`, `ref_status`, `ref_fournisseur`, `isValidate`) " +
                "VALUES (?, ?, ?, 3, ?, false)";
        PreparedStatement requete = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        requete.setInt(1, nbProduit);
        requete.setInt(2, ref_user);
        requete.setFloat(3, prixFinal);
        requete.setInt(4, ref_fournisseur);
        requete.executeUpdate();

    }


    public void insererProduitsCommande(int ref_commande, int ref_produit) throws SQLException {
        String sql = "INSERT INTO commandeproduit (ref_commande, ref_produit) VALUES (?, ?)";
        try (PreparedStatement requete = connection.prepareStatement(sql)) {
            requete.setInt(1, ref_commande);
            requete.setInt(2, ref_produit);
            requete.executeUpdate();
        }
    }

    public int getDernierIdCommande() throws SQLException {
        String sql = "SELECT MAX(id_commande) FROM commande"; // Récupère le dernier ID inséré
        try (PreparedStatement requete = connection.prepareStatement(sql);
             ResultSet rs = requete.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1); // Retourne l'ID le plus grand
            }
        }
        return -1; // Retourne -1 si aucun ID trouvé
    }

}
