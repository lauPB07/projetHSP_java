package com.example.projethsp.pagePatient;

import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    @FXML
    private Button deconnexion;

    @FXML
    private Button demande;

    @FXML
    private Button modif;

    @FXML
    private Button patient;

    @FXML
    void onClickDeconnexion(ActionEvent event) {
        if (Utilisateurconnecte.clearInstance()) {
            HelloApplication.changeScene("acceuil/loginView", "Connexion");
        } else {
            System.out.println("Aucune session active à fermer.");
        }
    }

    @FXML
    void onClickHospitalisation(ActionEvent event) {
        HelloApplication.changeScene("pagePatient/Hospitalisation", "Hospitalisation");
    }

    @FXML
    void onClickOrdonnance(ActionEvent event) {
        HelloApplication.changeScene("pagePatient/ordonnanceView", "Ordonnance");

    }


}
