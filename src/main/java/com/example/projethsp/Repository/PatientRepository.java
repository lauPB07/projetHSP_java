package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.Patient;
import com.example.projethsp.Entity.Utilisateur;
import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientRepository {

    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();

    public ArrayList<Patient> recupererPatient() {
        ArrayList<Patient> liste = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur where ref_role = 7";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new Patient(resultatRequette.getInt("id_utilisateur"), resultatRequette.getString("nom"), resultatRequette.getString("prenom"), resultatRequette.getString("email"), resultatRequette.getInt("ref_role"), resultatRequette.getString("telephone"), resultatRequette.getString("rue"), resultatRequette.getString("cp"), resultatRequette.getString("ville"), resultatRequette.getString("numSecu")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;
    }

    public Utilisateur getUserByEmail(String email) {
        Bdd connexionBdd = new Bdd();
        String sql = "SELECT * FROM utilisateur WHERE email = ? ";
        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setString(1, email);
            ResultSet resultatRequette = requete.executeQuery();
            if (resultatRequette.next()) {
                int id = resultatRequette.getInt(1);
                String nom = resultatRequette.getString(2);
                String prenom = resultatRequette.getString(3);
                String email1 = resultatRequette.getString(4);
                String mdP = resultatRequette.getString(5);
                int role = resultatRequette.getInt(6);
                return new Utilisateur(id, nom, prenom, email1, mdP, role);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void inscription(String nom, String prenom, String email, String telephone, String rue, String cp, String ville, String numSecu, Label label) {
        if (this.getUserByEmail(email) != null) {
            label.setText("Erreur vous avez deja un compte");
        } else {
            String sql1 = "INSERT INTO utilisateur (nom,prenom,email,telephone,rue,cp,ville,numSecu,ref_role) VALUES (?,?,?,?,?,?,?,?,7) ";

            try {
                PreparedStatement requete = connection.prepareStatement(sql1,PreparedStatement.RETURN_GENERATED_KEYS);
                requete.setString(1, nom);
                requete.setString(2, prenom);
                requete.setString(3, email);
                requete.setString(4, telephone);
                requete.setString(5, rue);
                requete.setString(6, cp);
                requete.setString(7, ville);
                requete.setString(8, numSecu);

               if (requete.executeUpdate() == 1){

                   ResultSet resultSet = requete.getGeneratedKeys();
                   if (resultSet.next()){
                       int id_patient = resultSet.getInt(1);

                       String sql2 = "INSERT INTO fiche_patient (ref_userPatient,ref_userCreer) VALUES (?,?)";

                       requete = connection.prepareStatement(sql2);

                       requete.setInt(1,id_patient);
                       requete.setInt(2, Utilisateurconnecte.getInstance().getId());

                       requete.executeUpdate();

                       label.setText("Nouveaux patient enregistrer !");
                       HelloApplication.changeScene("pageSecretaire/acceuilView", "Acceuil");

                   }
               }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            String sql = "INSERT INTO `historiqueaction`(`ref_user`, `action`, `date`, `heure`) VALUES (?,'Ajout Patient',DATE( NOW() ),TIME(NOW()))";

            try {
                PreparedStatement requete = connection.prepareStatement(sql);
                requete.setInt(1,Utilisateurconnecte.getInstance().getId());
                requete.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void editerPatient(String nom, String prenom, String email, String telephone, String rue, String cp, String ville, String numSecu,int id, Label label) {

        Bdd bdd = new Bdd();

        String sql1 = "UPDATE utilisateur SET nom = ?,prenom = ? ,email = ?,telephone = ?,rue = ?,cp = ?,ville = ?,numSecu = ? WHERE id_utilisateur = ?";

        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setString(1, nom);
            requete.setString(2, prenom);
            requete.setString(3, email);
            requete.setString(4, telephone);
            requete.setString(5, rue);
            requete.setString(6, cp);
            requete.setString(7, ville);
            requete.setString(8, numSecu);
            requete.setInt(9, id);
            requete.executeUpdate();
            label.setText("Patient modifié !");
            HelloApplication.changeScene("pageSecretaire/acceuilView", "Acceuil");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO `historiqueaction`(`ref_user`, `action`, `date`, `heure`) VALUES (?,'Modification Patient',DATE( NOW() ),TIME(NOW()))";

        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setInt(1,Utilisateurconnecte.getInstance().getId());
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void supprimerPatient(int id, Label label) {

        String sql = "DELETE FROM utilisateur WHERE id_utilisateur = ?";

        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setInt(1, id);
            requete.executeUpdate();
            label.setText("L'utilisateur a bien été supprimer");
        } catch (SQLException e) {
            label.setText("erreur");
            throw new RuntimeException(e);
        }

        String sql1 = "INSERT INTO `historiqueaction`(`ref_user`, `action`, `date`, `heure`) VALUES (?,'Suppression Patient',DATE( NOW() ),TIME(NOW()))";

        try {
            PreparedStatement requete = connection.prepareStatement(sql1);
            requete.setInt(1,Utilisateurconnecte.getInstance().getId());
            requete.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
