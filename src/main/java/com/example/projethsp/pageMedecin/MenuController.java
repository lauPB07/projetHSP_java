package com.example.projethsp.pageMedecin;

import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    @FXML
    private Button ajouter;

    @FXML
    private Button demande;

    @FXML
    private Button deconnexion;

    @FXML
    void onClickAjouter(ActionEvent event) {
        HelloApplication.changeScene("pageMedecin/demandeStockView","Ajouter une demande");
    }

    @FXML
    void onClickDemande(ActionEvent event) {
        HelloApplication.changeScene("pageMedecin/mesDemandeView","Vos demandes");
    }

    public void onClickDeconnexion(ActionEvent actionEvent) {
        if (Utilisateurconnecte.clearInstance()) {
            HelloApplication.changeScene("acceuil/loginView", "Connexion");
        } else {
            System.out.println("Aucune session active à fermer.");
        }
    }
}
