package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.Demande;
import com.example.projethsp.Entity.DemandeProduit;
import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.pageMedecin.AjouterProduitController;
import com.example.projethsp.pageMedecin.ProduitDemandeController;

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
                liste.add(new DemandeProduit(resultatRequette.getInt("d.ref_demande"),resultatRequette.getInt("d.ref_produit"),resultatRequette.getInt("d.nb"),resultatRequette.getBoolean("d.valider"),resultatRequette.getString("p.libelle")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;
    }
    public void ajouter(DemandeProduit demandeProduit) {
        String sql1 = "SELECT * FROM demandeProduit WHERE d.ref_demande = ? and d.ref_produit = ?";
        String sql = "INSERT INTO demandeproduit (ref_demande,ref_produit,nb,valider) VALUES (?,?,?,?)";
        try {
            PreparedStatement requeteSelect = connection.prepareStatement(sql1);
            requeteSelect.setInt(1, demandeProduit.getRef_demande());
            requeteSelect.setInt(2, demandeProduit.getRef_produit());
            ResultSet resultatRequete = requeteSelect.executeQuery();
            if (resultatRequete.next()) {
                HelloApplication.changeScene("pageMedecin/ajouterProduitView",new AjouterProduitController(demandeProduit.getRef_demande(),"Le produit est déjà dans la liste"));
            }else {
                PreparedStatement requetePrepare = connection.prepareStatement(sql);
                requetePrepare.setInt(1,demandeProduit.getRef_demande());
                requetePrepare.setInt(2, demandeProduit.getRef_produit());
                requetePrepare.setBoolean(4, false);
                requetePrepare.setInt(3, demandeProduit.getNb_produit());
                requetePrepare.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        String sql2 = "INSERT INTO `historiqueaction`(`ref_user`, `action`, `date`, `heure`) VALUES (?,'Ajout Demande',DATE( NOW() ),TIME(NOW()))";

        try {
            PreparedStatement requete = connection.prepareStatement(sql2);
            requete.setInt(1,Utilisateurconnecte.getInstance().getId());
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public DemandeProduit selectModif(int demande, int produit) {
        DemandeProduit demandeProduit = null;
        String sql = "SELECT d.*, p.libelle FROM demandeProduit as d INNER JOIN ficheproduit as p on d.ref_produit = p.id_ficheProduit WHERE d.ref_demande = ? and d.ref_produit = ?";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setInt(1, demande);
            requetePrepare.setInt(2, produit);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                demandeProduit = new DemandeProduit(resultatRequette.getInt("d.ref_demande"),resultatRequette.getInt("d.ref_produit"),resultatRequette.getInt("d.nb"),resultatRequette.getBoolean("d.valider"),resultatRequette.getString("p.libelle"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return demandeProduit;
    }

    public void modifier(DemandeProduit demandeProduit) {
        String sql = "UPDATE demandeproduit SET nb=? WHERE ref_demande = ? and ref_produit=?";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setInt(1, demandeProduit.getNb_produit());
            requetePrepare.setInt(2, demandeProduit.getRef_demande());
            requetePrepare.setInt(3, demandeProduit.getRef_produit());
            requetePrepare.executeUpdate();
            HelloApplication.changeScene("pageMedecin/produitDemandeView",new ProduitDemandeController(demandeProduit.getRef_demande()));
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
