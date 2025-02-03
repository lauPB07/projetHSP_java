package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.DemandeStock;
import com.example.projethsp.Entity.Utilisateurconnecte;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DemandeStockRepository {

    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();

    public ArrayList<DemandeStock> recupererDemande() {
        ArrayList<DemandeStock> liste = new ArrayList<>();
        String sql = "SELECT * FROM demande2 ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new DemandeStock(resultatRequette.getInt("id_demande"),resultatRequette.getInt("id_ficheProduit"),resultatRequette.getString("description"),resultatRequette.getString("nom"),resultatRequette.getString("prenom"),resultatRequette.getString("libelle"),resultatRequette.getInt("nb"),resultatRequette.getBoolean("valider"),resultatRequette.getInt("nbStocker")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;
    }


    public void modifierDemande(int iddemande,int idproduit, boolean isValidate, int nbDemander,Label label){
        String sql = "UPDATE demande2 SET valider = ? WHERE id_demande = ?";
        try{
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setBoolean(1,isValidate);
            requetePrepare.setInt(2,iddemande);
            requetePrepare.executeUpdate();
            label.setText("La demande à été valider");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String sql2 = "UPDATE ficheproduit SET nbStocker = nbStocker - ? WHERE id_ficheProduit = ?";
        try{
            PreparedStatement requetePrepare = connection.prepareStatement(sql2);
            requetePrepare.setInt(1,nbDemander);
            requetePrepare.setInt(2,idproduit);
            requetePrepare.executeUpdate();
            label.setText("La demande à été valider");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String sql1 = "INSERT INTO `historiqueaction`(`ref_user`, `action`, `date`, `heure`) VALUES (?,'Modification Demande',DATE( NOW() ),TIME(NOW()))";

        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setInt(1, Utilisateurconnecte.getInstance().getId());
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
