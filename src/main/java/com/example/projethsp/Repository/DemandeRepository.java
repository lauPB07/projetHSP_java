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
                liste.add(new Demande(resultatRequette.getInt("id_demande"),resultatRequette.getString("titre"),resultatRequette.getString("description"),resultatRequette.getBoolean("valider"),resultatRequette.getInt("ref_userDemandeur")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;
    }
    public void ajouter(Demande demande){
        String sql = "INSERT INTO demande (titre,description,valider,ref_userDemandeur) VALUES (?,?,?,?)";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setString(1,demande.getTitre());
            requetePrepare.setString(2, demande.getDescription());
            requetePrepare.setBoolean(3, false);
            requetePrepare.setInt(4, Utilisateurconnecte.getInstance().getId());
            requetePrepare.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        String sql1 = "INSERT INTO `historiqueaction`(`ref_user`, `action`, `date`, `heure`) VALUES (?,'Ajout Demande',DATE( NOW() ),TIME(NOW()))";

        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setInt(1,Utilisateurconnecte.getInstance().getId());
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
