package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.Commande;
import com.example.projethsp.Entity.Demande;
import com.example.projethsp.Entity.FicheProduit;
import com.example.projethsp.Entity.Utilisateurconnecte;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommandeRepository {

    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();



    public ArrayList<Commande> getAllCommande(){
        ArrayList<Commande> liste = new ArrayList<>();
        String sql = "SELECT * FROM commande2 ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new Commande(resultatRequette.getInt("idCommande"),resultatRequette.getInt("nbProduit"),resultatRequette.getInt("idUser"),resultatRequette.getString("nomUser"),resultatRequette.getString("prenomUser"),resultatRequette.getInt("idProduit"),resultatRequette.getFloat("prixProduit"),resultatRequette.getFloat("prixFinal"),resultatRequette.getInt("idStatus"),resultatRequette.getInt("idFournisseur"),resultatRequette.getBoolean("isValidate")));            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;

    }

    public void ajoutCommande(int nbProduit, int ref_user, int ref_produit, float prixFinal, int ref_fournisseur, Label label){
        String sql  = "INSERT INTO commande (`nbProduit`, `ref_user`, `ref_produit`, `prixFinal`, `ref_status`, `ref_fournisseur`, `isValidate`) " +
                "VALUES (?,?,?,?,3,?,false)";

        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setInt(1,nbProduit);
            requete.setInt(2,ref_user);
            requete.setInt(3,ref_produit);
            requete.setFloat(4,prixFinal);
            requete.setInt(5,ref_fournisseur);
            requete.executeUpdate();
            label.setText("Nouvelle commande enregistrer !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql1 = "INSERT INTO `historiqueaction`(`ref_user`, `action`, `date`, `heure`) VALUES (?,'Création d une commande ',DATE( NOW() ),TIME(NOW()))";

        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setInt(1, Utilisateurconnecte.getInstance().getId());
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modifierCommande(){

    }

    public void suppressionCommande(){

    }


}
