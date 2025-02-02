package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.DemandeProduit;
import com.example.projethsp.Entity.FichePatient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FichePatientRepository {
    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();
    public ArrayList<FichePatient> selectPatient() {
        ArrayList<FichePatient> liste = new ArrayList<>();
        String sql = "SELECT * FROM fiche_patient ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new FichePatient(resultatRequette.getInt("id_fichePatient"),resultatRequette.getInt("ref_userPatient"),resultatRequette.getInt("ref_userCreer")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;

    }
}
