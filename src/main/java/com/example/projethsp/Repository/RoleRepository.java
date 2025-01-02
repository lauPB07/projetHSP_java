package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.Role;
import javafx.scene.control.ComboBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoleRepository {

    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();
    private int id;

    public void recupRole(ComboBox<Role> comboBox){

        String sql = "SELECT * FROM role ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);;
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                int id = resultatRequette.getInt("id_role");
                String nom = resultatRequette.getString("nom");
                comboBox.getItems().add(new Role(id, nom));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public ArrayList<Role> recupererRole() {
        ArrayList<Role> liste = new ArrayList<>();
        Bdd connexionBdd = new Bdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT * FROM role ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new Role(resultatRequette.getInt("id_role"),resultatRequette.getString("nom")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;
    }
}
