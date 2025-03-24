package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.DemandeProduit;
import com.example.projethsp.Entity.Hospitalisation;
import com.example.projethsp.Entity.Ordonnance;
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
    public ArrayList<Hospitalisation> selectPatient() {
        ArrayList<Hospitalisation> liste = new ArrayList<>();
        String sql = "SELECT h.*,c.num FROM hospitalisation as h INNER JOIN chambre as c on h.ref_chambre=c.id_chambre WHERE h.ref_user = ? and h.dateFin = DATE(NOW());";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            requetePrepare.setInt(1, Utilisateurconnecte.getInstance().getId());
            ResultSet resultatRequette = requetePrepare.executeQuery();
            if (resultatRequette.next()) {
                liste.add(new Hospitalisation(resultatRequette.getInt("h.id_hospitalisation"),resultatRequette.getString("h.dateDebut"),resultatRequette.getString("h.dateFin"),resultatRequette.getString("h.description"),resultatRequette.getString("c.num"),resultatRequette.getInt("ref_user")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;

    }
}
