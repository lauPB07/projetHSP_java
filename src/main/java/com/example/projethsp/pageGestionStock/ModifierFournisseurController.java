package com.example.projethsp.pageGestionStock;

import com.example.projethsp.Entity.Fournisseur;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.FournisseurRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifierFournisseurController implements Initializable {

    @FXML
    private Button retourBoutton;

    @FXML
    private Button validerBoutton;

    @FXML
    private TextField nomField;

    @FXML
    private Label erreurLabel;

    private Fournisseur fournisseur;
    private FournisseurRepository repository = new FournisseurRepository();

    public ModifierFournisseurController(Fournisseur fournisseur){
        this.fournisseur = fournisseur;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomField.setText(fournisseur.getNom());

        retourBoutton.setOnAction(this::retour);
        validerBoutton.setOnAction(this::modifierFournisseur);

    }

    @FXML
    void retour(ActionEvent event){
        HelloApplication.changeScene("pageGestionStock/fournisseurView","Fournisseurs");
    }

    @FXML
    void modifierFournisseur(ActionEvent event){
        repository.modifierFournisseur(fournisseur.getId(), fournisseur.getNom(), erreurLabel);
    }
}
