package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.Fournisseur;
import com.example.projethsp.Entity.Utilisateur;
import com.example.projethsp.Entity.Utilisateurconnecte;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FournisseurRepository {

    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();

    public ArrayList<Fournisseur> recupererFournisseur() {
        ArrayList<Fournisseur> liste = new ArrayList<>();
        String sql = "SELECT * FROM fournisseur ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new Fournisseur(resultatRequette.getInt("id_fournisseur"),resultatRequette.getString("nom")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;
    }

    public void modifierFournisseur(int id, String nom, Label label){
        String sql = "UPDATE fournisseur SET nom = ? WHERE id_fournisseur = ?";

        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setString(1,nom);
            requetePrepare.setInt(2,id);
            requetePrepare.executeUpdate();
            label.setText("Le fournisseur a bien été modifier");

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        String sql1 = "INSERT INTO `historiqueaction`(`ref_user`, `action`, `date`, `heure`) VALUES (?,'Modification Fournisseur',DATE( NOW() ),TIME(NOW()))";

        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setInt(1,Utilisateurconnecte.getInstance().getId());
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void supprimerFournisseur(int id, Label label){
        String sql = "DELETE FROM fournisseur WHERE id_fournisseur = ? ";

        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setInt(1, id);
            requete.executeUpdate();
            label.setText("Le fournisseur a bien été supprimer");
        } catch (SQLException e) {
            label.setText("erreur");
            throw new RuntimeException(e);
        }

        String sql1 = "INSERT INTO `historiqueaction`(`ref_user`, `action`, `date`, `heure`) VALUES (?,'Suppression Fournisseur',DATE( NOW() ),TIME(NOW()))";

        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setInt(1,Utilisateurconnecte.getInstance().getId());
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void ajouterFournisseur(String nom, Label label){
        String sql = "INSERT INTO fournisseur (nom) VALUE (?)";

        try{
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setString(1,nom);
            requete.executeUpdate();
            label.setText("Nouveau fournisseur ajouté");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String sql1 = "INSERT INTO `historiqueaction`(`ref_user`, `action`, `date`, `heure`) VALUES (?,'Ajout Fournisseur',DATE( NOW() ),TIME(NOW()))";

        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setInt(1, Utilisateurconnecte.getInstance().getId());
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
