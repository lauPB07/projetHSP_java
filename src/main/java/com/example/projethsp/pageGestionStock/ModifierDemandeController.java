package com.example.projethsp.pageGestionStock;

import com.example.projethsp.Entity.DemandeStock;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.DemandeStockRepository;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifierDemandeController implements Initializable {

    @FXML
    private CheckBox isValidate;

    @FXML
    private Label labelErreur;
    @FXML
    private Button retourButon;

    @FXML
    private Button validerButon;


    private boolean valider = true;

    private DemandeStock demandeStock;
    private DemandeStockRepository demandeStockRepository = new DemandeStockRepository();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        validerButon.setOnAction(this::modifierDemande);

        retourButon.setOnAction(this::retour);
    }

    public ModifierDemandeController(DemandeStock demandeStock){
        this.demandeStock = demandeStock;
    }

    @FXML
    void modifierDemande(ActionEvent event){
        if(isValidate.isSelected()){
            demandeStockRepository.modifierDemande(demandeStock.getId(),demandeStock.getIdProduit(),valider,demandeStock.getNb(),labelErreur);
        }else {
            labelErreur.setText("La demande n'a pas été valider");
        }
    }

    @FXML
    void retour(ActionEvent event){
        HelloApplication.changeScene("pageGestionStock/demandeView","Gérer les demandes");
    }
}
