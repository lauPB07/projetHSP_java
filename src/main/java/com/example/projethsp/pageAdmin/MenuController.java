package com.example.projethsp.pageAdmin;

import com.example.projethsp.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {

    @FXML
    public void user(ActionEvent event){
        HelloApplication.changeScene("pageAdmin/utilisateurView","Tableau Utilisateur");
    }

    @FXML
    public void deconnexion(ActionEvent event){
        HelloApplication.changeScene("Acceuil/loginView","Projet HSP");
    }
}
