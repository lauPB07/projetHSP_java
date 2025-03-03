package com.example.projethsp.pageGestionStock;

import com.example.projethsp.Entity.FicheProduit;
import com.example.projethsp.Entity.Fournisseur;
import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.CommandeRepository;
import com.example.projethsp.Repository.FournisseurRepository;
import com.example.projethsp.Repository.ProduitRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.action.Action;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CommandeController implements Initializable {
    private FournisseurRepository fournisseurRepository = new FournisseurRepository() ;
    private ProduitRepository produitRepository = new ProduitRepository();
    private CommandeRepository commandeRepository = new CommandeRepository();

    @FXML
    private ComboBox<Fournisseur> founisseur;

    @FXML
    private TextField prixFinalField;

    @FXML
    private Label labelErreur;

    @FXML
    private ComboBox<FicheProduit> produit;

    @FXML
    private TextField quantiteField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Fournisseur> list1 = fournisseurRepository.recupererFournisseur();
        founisseur.getItems().addAll(list1);

    }

    @FXML
    public void retour(ActionEvent event){
        HelloApplication.changeScene("pageGestionStock/AllCommandeView","Toutes les commandes commande");
    }

    @FXML
    public void onClicked(ActionEvent event){
        if (null != founisseur.getValue()){
            ArrayList<FicheProduit> list = produitRepository.selectProduitByFournisseur(founisseur.getValue().getId());
            produit.getItems().addAll(list);
        }
    }

    @FXML
    public void produitSelected(ActionEvent event){
        if (null != produit.getValue()){
            prixFinalField.setText(String.valueOf(produit.getValue().getPrix()*Float.parseFloat(quantiteField.getText())));
        }
    }

    @FXML
    public void commander(ActionEvent event){
        commandeRepository.ajoutCommande(Integer.parseInt(quantiteField.getText()), Utilisateurconnecte.getInstance().getId(),produit.getValue().getId(),Float.parseFloat(prixFinalField.getText()),founisseur.getValue().getId(),labelErreur);
        founisseur.setValue(null);
        produit.setValue(null);
        quantiteField.setText(null);
        prixFinalField.setText(null);
    }


}
