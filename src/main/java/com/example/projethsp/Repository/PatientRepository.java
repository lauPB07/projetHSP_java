package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;

import java.sql.Connection;

public class PatientRepository {

    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();
}
