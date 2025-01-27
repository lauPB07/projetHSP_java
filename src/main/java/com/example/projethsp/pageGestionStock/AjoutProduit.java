package com.example.projethsp.pageGestionStock;

import com.example.projethsp.Entity.FicheProduit;
import com.example.projethsp.Entity.Fournisseur;
import com.example.projethsp.Entity.Niveau;
import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.FournisseurRepository;
import com.example.projethsp.Repository.NiveauRepository;
import com.example.projethsp.Repository.ProduitRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class AjoutProduit implements Initializable {

    @FXML
    private TextField descField;

    @FXML
    private ComboBox<Fournisseur> fournisseurBox;

    @FXML
    private Label labelErreur;

    @FXML
    private TextField libelleField;

    @FXML
    private TextField nbField;

    @FXML
    private ComboBox<Niveau> nivBox;

    @FXML
    private TextField prixField;

    private FournisseurRepository fournisseurRepository = new FournisseurRepository();
    private NiveauRepository niveauRepository = new NiveauRepository();
    private ProduitRepository produitRepository = new ProduitRepository();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Niveau> list = niveauRepository.recupererNiv();
        nivBox.getItems().addAll(list);

        ArrayList<Fournisseur> list1 = fournisseurRepository.recupererFournisseur();
        fournisseurBox.getItems().addAll(list1);
    }

    @FXML
    public void retour(ActionEvent event){
        HelloApplication.changeScene("pageGestionStock/produitView","Produits");
    }

    @FXML
    public void ajout(ActionEvent event){

        produitRepository.ajoutProduit(libelleField.getText(),descField.getText(),parseFloat(prixField.getText()),parseInt(nbField.getText()),nivBox.getValue().getId(),fournisseurBox.getValue().getId(),
                Utilisateurconnecte.getInstance().getId(), labelErreur);
    }
}
