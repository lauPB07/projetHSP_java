package com.example.projethsp.pageGestionStock;

import com.example.projethsp.Entity.DemandeStock;
import com.example.projethsp.Entity.FicheProduit;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.DemandeStockRepository;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    @FXML
    private TextField nbDuProduit;


    private boolean valider = true;

    private DemandeStock demandeStock;
    private DemandeStockRepository demandeStockRepository = new DemandeStockRepository();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nbDuProduit.setText(String.valueOf(demandeStock.getNbStocker()));
        validerButon.setOnAction(this::modifierDemande);

        retourButon.setOnAction(this::retour);
    }

    public ModifierDemandeController(DemandeStock demandeStock){
        this.demandeStock = demandeStock;
    }

    @FXML
    void modifierDemande(ActionEvent event){
        if (demandeStock.getNbStocker() > demandeStock.getNb() && !demandeStock.isValidate()){
            if(isValidate.isSelected()){
                demandeStockRepository.modifierDemande(demandeStock.getId(),demandeStock.getIdProduit(),valider,demandeStock.getNb(),labelErreur);
            }else {
                labelErreur.setText("La demande n'a pas été valider");
            }
        }else {
            labelErreur.setText("Erreur le nombre de produit du stock est inferieur au nombre souhaiter");
        }

    }

    @FXML
    void retour(ActionEvent event){
        HelloApplication.changeScene("pageGestionStock/demandeView","Gérer les demandes");
    }
}
