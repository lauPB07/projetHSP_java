package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.FicheProduit;
import com.example.projethsp.Entity.Utilisateurconnecte;
import javafx.scene.control.Label;

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

    public ArrayList<FicheProduit> selectProduitByFournisseur(int fournisseur){
        ArrayList<FicheProduit> liste = new ArrayList<>();
        String sql = "SELECT * FROM ficheproduit where ref_fournisseur = ? ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setInt(1,fournisseur);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new FicheProduit(resultatRequette.getInt("id_ficheProduit"),resultatRequette.getString("libelle"),resultatRequette.getString("description"),resultatRequette.getFloat("prix"),resultatRequette.getInt("nbStocker"),resultatRequette.getInt("ref_niv"),resultatRequette.getInt("ref_fournisseur"),resultatRequette.getInt("ref_user")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;
    }

    public void ajoutProduit(String libelle, String description, float prix, int nb,int niv, int fournisseur,int user, Label label){

            String sql1 = "INSERT INTO  ficheproduit (libelle,description,prix,nbStocker,ref_niv,ref_fournisseur,ref_user) VALUES (?,?,?,?,?,?,?) ";

            try {
                PreparedStatement requete = connection.prepareStatement(sql1);
                requete.setString(1,libelle);
                requete.setString(2,description);
                requete.setFloat(3,prix);
                requete.setInt(4,nb);
                requete.setInt(5,niv);
                requete.setInt(6,fournisseur);
                requete.setInt(7,user);
                requete.executeUpdate();
                label.setText("Nouveau produit enregistrer !");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        String sql = "INSERT INTO `historiqueaction`(`ref_user`, `action`, `date`, `heure`) VALUES (?,'Ajout Produit',DATE( NOW() ),TIME(NOW()))";

        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setInt(1, Utilisateurconnecte.getInstance().getId());
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    public void modifierProduit(int id, String libelle, String description, float prix, int nb,int niv, int fournisseur, Label label){

        String sql = "UPDATE ficheproduit SET libelle=?,description=?,prix=?,nbStocker=?,ref_niv=?,ref_fournisseur= ? WHERE id_ficheProduit = ?";

        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setString(1,libelle);
            requete.setString(2,description);
            requete.setFloat(3,prix);
            requete.setInt(4,nb);
            requete.setInt(5,niv);
            requete.setInt(6,fournisseur);
            requete.setInt(7,id);
            requete.executeUpdate();
            label.setText("Le produit a bien été modifier");
        } catch (SQLException e) {
            label.setText("erreur");
            throw new RuntimeException(e);
        }

        String sql1 = "INSERT INTO `historiqueaction`(`ref_user`, `action`, `date`, `heure`) VALUES (?,'Modifier Produit',DATE( NOW() ),TIME(NOW()))";

        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setInt(1,Utilisateurconnecte.getInstance().getId());
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void supprimerProduit(int id, Label label){

        String sql = "DELETE FROM ficheproduit WHERE id_ficheProduit = ?";

        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setInt(1,id);
            requete.executeUpdate();
            label.setText("Le produit a bien été suprimer");
        } catch (Exception e) {
            label.setText("erreur");
            throw new RuntimeException(e);
        }

        String sql1 = "INSERT INTO `historiqueaction`(`ref_user`, `action`, `date`, `heure`) VALUES (?,'Suppression Produit',DATE( NOW() ),TIME(NOW()))";

        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setInt(1,Utilisateurconnecte.getInstance().getId());
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<FicheProduit> selectProduit() {
        ArrayList<FicheProduit> liste = new ArrayList<>();
        String sql = "SELECT id_ficheProduit,libelle,nbStocker FROM ficheproduit ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new FicheProduit(resultatRequette.getInt("id_ficheProduit"),resultatRequette.getString("libelle"),resultatRequette.getInt("nbStocker")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;
    }
}
