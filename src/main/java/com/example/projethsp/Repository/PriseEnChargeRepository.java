package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import javafx.scene.control.Label;
import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class PriseEnChargeRepository {

    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();
    public void ajouterPriseEnCharge(LocalDate dateArriver, String heureArriver, String descriptionSymptomes, String nivGraviter, int ref_fichePatient, int ref_user){

        String sql = "INSERT INTO dossier_priseencharge (dateArriver,heureArriver,descriptionSymptomes,nivGraviter,ref_fichePatient,ref_user) VALUE (?,?,?,?,?,?)";

        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setDate(1, Date.valueOf(dateArriver));
            requete.setString(2,heureArriver);
            requete.setString(3,descriptionSymptomes);
            requete.setString(4,nivGraviter);
            requete.setInt(5,ref_fichePatient);
            requete.setInt(6,ref_user);
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void editerPriseEnCharge(Date dateArriver, String heureArriver, String descriptionSymptomes, String nivGraviter, Label label){

        String sql = "UPDATE dossier_priseencharge SET (dateArriver,heureArriver,descriptionSymptomes,nivGraviter) WHERE id_priseEnCharge = ? ";

        try{
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setDate(1,dateArriver);
            requete.setString(2,heureArriver);
            requete.setString(3,descriptionSymptomes);
            requete.setString(4,nivGraviter);
            requete.executeUpdate();
            label.setText("La demande à été valider");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void supprimerPriseEnCharge(int id, Label label){

        String sql = "DELETE FROM dossier_priseencharge WHERE id_priseEnCharge = ? ";

        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setInt(1, id);
            requete.executeUpdate();
            label.setText("La fiche de prise en charge a bien été supprimer");
        } catch (SQLException e) {
            label.setText("erreur");
            throw new RuntimeException(e);
        }

    }

}
