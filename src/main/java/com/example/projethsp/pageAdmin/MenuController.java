package com.example.projethsp.pageAdmin;

import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {

    private Utilisateurconnecte UtilisateurConnecte;

    @FXML
    public void user(ActionEvent event){
        HelloApplication.changeScene("pageAdmin/utilisateurView","Tableau Utilisateur");
    }

    @FXML
    public void historiqueCo(ActionEvent event){
        HelloApplication.changeScene("pageAdmin/historiqueCoView","Historique de connexion");
    }

    @FXML
    public void historiqueAction(ActionEvent event){
        HelloApplication.changeScene("pageAdmin/historiqueActionView","Historique des actions ");
    }

    @FXML
    public void deconnexion(ActionEvent event){
        if (Utilisateurconnecte.clearInstance()) {
            HelloApplication.changeScene("acceuil/loginView", "Connexion");
        } else {
            System.out.println("Aucune session active à fermer.");
        }

    }
}
