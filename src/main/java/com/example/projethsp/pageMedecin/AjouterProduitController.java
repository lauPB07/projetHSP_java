package com.example.projethsp.pageMedecin;

import com.example.projethsp.Entity.DemandeProduit;
import com.example.projethsp.Entity.FicheProduit;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.DemandeProduitRepository;
import com.example.projethsp.Repository.ProduitRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AjouterProduitController implements Initializable {

    @FXML
    private TextField nb;

    @FXML
    private ComboBox<FicheProduit> produit;

    @FXML
    private Button retour;

    @FXML
    private Button valider;

    private int id;

    public AjouterProduitController(int id) {
        this.id = id;
    }

    @FXML
    void onClickRetour(ActionEvent event) {
        HelloApplication.changeScene("pageMedecin/mesDemandeView","Vos demandes");
    }

    @FXML
    void onClickValider(ActionEvent event) {
        int idProduit = produit.getSelectionModel().getSelectedItem().getId();
        int nb = Integer.parseInt(this.nb.getText());
        DemandeProduit demandeProduit = new DemandeProduit(id,idProduit,nb,false);
        DemandeProduitRepository repo = new DemandeProduitRepository();
        repo.ajouter(demandeProduit);
        HelloApplication.changeScene("pageMedecin/mesDemandeView","Vos demandes");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProduitRepository repo = new ProduitRepository();
        ArrayList<FicheProduit> list = repo.selectProduit();
        this.produit.getItems().addAll(list);
    }
}
