package com.example.projethsp.pageGestionStock;

import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.FournisseurRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AjoutFournisseurController {

    @FXML
    private Label labelErreur;

    @FXML
    private TextField nomField;

    private FournisseurRepository repository = new FournisseurRepository();

    @FXML
    void ajoutFournisseur(ActionEvent event){
        repository.ajouterFournisseur(nomField.getText(),labelErreur);
    }

    @FXML
    void retour(ActionEvent event){
        HelloApplication.changeScene("pageGestionStock/fournisseurView","Fournisseur");
    }
}
