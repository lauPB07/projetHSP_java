package com.example.projethsp.Repository;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.Role;
import com.example.projethsp.Entity.Utilisateur;
import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import javafx.scene.control.Label;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtilisateurRepository {

    static BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();

    public ArrayList<Utilisateur> recupererUtilisateur() {
        ArrayList<Utilisateur> liste = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur ";
        try {
            PreparedStatement requetePrepare = connection.prepareStatement(sql);
            ResultSet resultatRequette = requetePrepare.executeQuery();
            while (resultatRequette.next()) {
                liste.add(new Utilisateur(resultatRequette.getInt("id_utilisateur"),resultatRequette.getString("nom"),resultatRequette.getString("prenom"),resultatRequette.getString("email"),resultatRequette.getString("mdp"),resultatRequette.getInt("ref_role")));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return liste;
    }

    public Utilisateur getUserByEmail(String email){
        Bdd connexionBdd = new Bdd();
        Connection connection = connexionBdd.getBdd();
        String sql = "SELECT * FROM utilisateur WHERE email = ? ";
        try {
            PreparedStatement requete = connection.prepareStatement(sql);
            requete.setString(1,email);
            ResultSet resultatRequette = requete.executeQuery();
            if(resultatRequette.next()){
                int id = resultatRequette.getInt(1);
                String nom = resultatRequette.getString(2);
                String prenom = resultatRequette.getString(3);
                String email1 = resultatRequette.getString(4);
                String mdP = resultatRequette.getString(5);
                int role = resultatRequette.getInt(11);
                return new Utilisateur(id,nom, prenom,email1,mdP,role);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Utilisateur connexion(String identifiant, String mdp, Label label){
        //System.out.println("Id : " + identifiant);
        Utilisateur user = this.getUserByEmail(identifiant);
        //System.out.println("Hello : " + user);

        if (user == null){
            label.setText("erreur vous n'avez pas de compte");
        }else {
            System.out.println("Hello : " + user.getMdp());

            System.out.println(user.getMdp());
            System.out.println(bcrypt.matches(mdp,user.getMdp()));
            if (bcrypt.matches(mdp,user.getMdp())){
                String sql = "INSERT INTO `historiqueconnexion`( `ref_user`, `date`, `heure`) VALUES (?,DATE( NOW() ),TIME(NOW()))";
                try {
                    PreparedStatement requete = connection.prepareStatement(sql);
                    requete.setInt(1,user.getId());
                    requete.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return user;
            } else {
                label.setText("Mot de passe incorrect");
            }
        }
        return null;
    }

    public void inscription(String nom, String prenom, String email, String mdp,int role, Label label){
        if(this.getUserByEmail(email)!=null){
            label.setText("Erreur vous avez deja un compte");
        }else {
            String sql1 = "INSERT INTO utilisateur (nom,prenom,email,mdp,ref_role) VALUES (?,?,?,?,?) ";

            try {
                PreparedStatement requete = connection.prepareStatement(sql1);
                requete.setString(1,nom);
                requete.setString(2,prenom);
                requete.setString(3,email);
                requete.setString(4,bcrypt.encode(mdp));
                requete.setInt(5,role);
                requete.executeUpdate();
                label.setText("Nouvelle utilisateur enregistrer !");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String sql = "INSERT INTO `historiqueaction`(`ref_user`, `action`, `date`, `heure`) VALUES (?,'Ajout utilisateur',DATE( NOW() ),TIME(NOW()))";

            try {
                PreparedStatement requete = connection.prepareStatement(sql);
                requete.setInt(1,Utilisateurconnecte.getInstance().getId());
                requete.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
