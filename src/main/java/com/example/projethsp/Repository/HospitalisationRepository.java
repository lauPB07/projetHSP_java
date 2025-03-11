package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.DemandeProduit;
import com.example.projethsp.Entity.Hospitalisation;
import com.example.projethsp.Entity.Utilisateurconnecte;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class HospitalisationRepository {
    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();
    public void ajouter(LocalDate dateDebut, LocalDate dateFin, String description, int ref_c, int ref_u) {
        String sql = "INSERT INTO `hospitalisation` (`dateDebut`, `dateFin`, `description`, `ref_chambre`, `ref_user`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            System.out.println(dateDebut);

            requetePrepare.setDate(1, Date.valueOf(dateDebut));
            requetePrepare.setDate(2, Date.valueOf(dateFin));
            requetePrepare.setString(3,description);
            requetePrepare.setInt(4,ref_c);
            requetePrepare.setInt(5,ref_u);
            requetePrepare.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        sql = "INSERT INTO `historiqueaction`(`ref_user`, `action`, `date`, `heure`) VALUES (?,'Ajouter Hospitalisation',DATE( NOW() ),TIME(NOW()))";

        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setInt(1, Utilisateurconnecte.getInstance().getId());
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
