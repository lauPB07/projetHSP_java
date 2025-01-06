package com.example.projethsp.pageGestionStock;

import com.example.projethsp.Entity.FicheProduit;
import com.example.projethsp.Entity.Utilisateur;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.ProduitRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProduitController implements Initializable {

    @FXML
    private Button modifierButton;

    @FXML
    private TableView<FicheProduit> produitTable;

    @FXML
    private Button supprimerButton;

    private ProduitRepository produitRepository = new ProduitRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                {"ID ","id"},
                {"Libelle", "libelle"},
                {"Description", "description"},
                {"Email", "prix"},
                {"Stock", "nbStocker"},
                {"Niveau", "niv"},
                {"Fournisseur", "fournisseur"}
        };

        for (int i = 0; i <colonnes.length; i++){
            TableColumn<FicheProduit,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            produitTable.getColumns().add(maColonne);
        }
        ArrayList<FicheProduit> list = produitRepository.recupererProduit();
        produitTable.getItems().addAll(list);

        modifierButton.setVisible(false);
        supprimerButton.setVisible(false);
    }

    @FXML
    public void retour(ActionEvent event){
        HelloApplication.changeScene("pageGestionStock/menuView","Menu");
    }
}
