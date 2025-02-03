package com.example.projethsp.pageGestionStock;

import com.example.projethsp.Entity.DemandeStock;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.DemandeStockRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DemandeController implements Initializable {

    @FXML
    private TableView<DemandeStock> demandeTable;

    @FXML
    private Button validerButon;

    private DemandeStockRepository demandeRepository = new DemandeStockRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                {"ID ","id"},
                {"ID Produit", "idProduit"},
                {"Descritption", "description"},
                {"Nom", "nom"},
                {"Prenom", "prenom"},
                {"Produit", "libelle"},
                {"Nombre demandé", "nb"},
                {"Valider", "isValidate"}
        };

        for (int i = 0; i <colonnes.length; i++){
            TableColumn<DemandeStock,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            demandeTable.getColumns().add(maColonne);
        }
        ArrayList<DemandeStock> list = demandeRepository.recupererDemande();
        demandeTable.getItems().addAll(list);

        validerButon.setVisible(false);

    }

    @FXML
    void retour(ActionEvent event){
        HelloApplication.changeScene("pageGestionStock/menuView","Menu Gestion de Stock");
    }

    @FXML
    void onListeSelection(MouseEvent event){
         if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() ==1) {
            TablePosition cell = demandeTable.getSelectionModel().getSelectedCells().get(0);
            int indexLigne = cell.getRow();
            TableColumn colone = cell.getTableColumn();
            DemandeStock demande = demandeTable.getItems().get(indexLigne);
            System.out.println("Simple-click ligne "+indexLigne+" colonne "+colone.getText()+ " : "+demande);
            validerButon.setVisible(true);
            int id = demande.getId();
            validerButon.setOnAction(event1 -> {
                HelloApplication.changeScene("pageGestionStock/modifierDemandeView",new ModifierDemandeController(demande));
                validerButon.setVisible(false);
            });


        }
    }
}
