package com.example.projethsp.pageMedecin;

import com.example.projethsp.Entity.DemandeProduit;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.DemandeProduitRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifierProduitController implements Initializable {

    @FXML
    private TextField nb;

    @FXML
    private Button retour;

    @FXML
    private Button valider;

    private DemandeProduit produit;

    public ModifierProduitController(DemandeProduit demandeProduit){
        this.produit=demandeProduit;
    }

    @FXML
    void onClickRetour(ActionEvent event) {
        HelloApplication.changeScene("pageMedecin/produitDemandeView",new ProduitDemandeController(produit.getRef_demande()));
    }

    @FXML
    void onClickValider(ActionEvent event) {
        produit.setNb_produit(Integer.parseInt(this.nb.getText()));
        DemandeProduitRepository demandeProduitRepository=new DemandeProduitRepository();
        demandeProduitRepository.modifier(produit);
        HelloApplication.changeScene("pageMedecin/produitDemandeView", new ProduitDemandeController(produit.getRef_demande()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.nb.setText(String.valueOf(this.produit.getNb_produit()));
    }
}
