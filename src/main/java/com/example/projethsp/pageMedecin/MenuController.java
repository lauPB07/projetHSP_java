package com.example.projethsp.pageMedecin;

import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    @FXML
    private Button modif;

    @FXML
    private Button demande;

    @FXML
    private Button patient;

    @FXML
    private Button deconnexion;

    @FXML
    void onClickModif(ActionEvent event) {
        HelloApplication.changeScene("Acceuil/modifMdpView","Modifier Mot De Passe");
    }

    @FXML
    void onClickDemande(ActionEvent event) {
        HelloApplication.changeScene("pageMedecin/mesDemandeView","Vos demandes");
    }

    @FXML
    void onClickPatient(ActionEvent event) {
        HelloApplication.changeScene("pageMedecin/listePatientView","Liste D'attente");
    }

    public void onClickDeconnexion(ActionEvent actionEvent) {
        if (Utilisateurconnecte.clearInstance()) {
            HelloApplication.changeScene("acceuil/loginView", "Connexion");
        } else {
            System.out.println("Aucune session active à fermer.");
        }
    }
}
