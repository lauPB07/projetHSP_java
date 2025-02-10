package com.example.projethsp.pageMedecin;

import com.example.projethsp.Entity.Demande;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.DemandeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DemandeStockController {

    @FXML
    private TextArea description;

    @FXML
    private Button retour;

    @FXML
    private TextField titre;

    @FXML
    private Button valider;

    @FXML
    void OnActionRetour(ActionEvent event) {
        HelloApplication.changeScene("pageMedecin/menuView","Menu");
    }

    @FXML
    void OnActionValider(ActionEvent event) {
        DemandeRepository demandeRepository = new DemandeRepository();
        Demande demande = new Demande(titre.getText(), description.getText());
        demandeRepository.ajouter(demande);
        HelloApplication.changeScene("pageMedecin/mesDemandeView","Vos demande");
    }

}
