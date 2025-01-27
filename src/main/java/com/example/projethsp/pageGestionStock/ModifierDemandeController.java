package com.example.projethsp.pageGestionStock;

import com.example.projethsp.Entity.DemandeStock;
import com.example.projethsp.Repository.DemandeStockRepository;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifierDemandeController implements Initializable {

    @FXML
    private CheckBox isValidate;

    @FXML
    private Label labelErreur;

    private DemandeStock demandeStock;
    private DemandeStockRepository demandeStockRepository = new DemandeStockRepository();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public ModifierDemandeController(DemandeStock demandeStock){
        this.demandeStock = demandeStock;
    }
}
