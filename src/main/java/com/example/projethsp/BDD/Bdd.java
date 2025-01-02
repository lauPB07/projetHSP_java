package com.example.projethsp.BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bdd {

    public Connection bdd;

    public Connection getBdd() {
        String bddNom = "projethspjava";
        String user ="projetHSP";
        String usermdp ="3caw8U2BZ6";
        String url ="jdbc:mysql://localhost:3306/" + bddNom;

        try {
            bdd = DriverManager.getConnection(url,user,usermdp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bdd;
    }
}
