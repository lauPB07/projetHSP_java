package com.example.projethsp.pageGestionStock;

import com.example.projethsp.Entity.FicheProduit;
import com.example.projethsp.Entity.Utilisateur;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.ProduitRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProduitController implements Initializable {

    @FXML
    private Label labelErreur;

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

        supprimerButton.setVisible(false);
    }

    @FXML
    public void retour(ActionEvent event){
        HelloApplication.changeScene("pageGestionStock/menuView","Menu");
    }

    @FXML
    public void ajoutProduit(ActionEvent event){
        HelloApplication.changeScene("pageGestionStock/ajoutProduitView", "Ajout de produit");
    }


    @FXML
    void onListeSelection(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY && event.getClickCount()==2){
            TablePosition cell = produitTable.getSelectionModel().getSelectedCells().get(0);
            int indexLigne = cell.getRow();
            TableColumn colone = cell.getTableColumn();
            FicheProduit produitsel = produitTable.getItems().get(indexLigne);
            System.out.println("Double-clique ligne "+indexLigne+" , colone  "+colone.getText()+ " : "+ produitsel);
            HelloApplication.changeScene("pageGestionStock/editerProduitView",new ModifierProduitController(produitsel));
        } else if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() ==1) {
            TablePosition cell = produitTable.getSelectionModel().getSelectedCells().get(0);
            int indexLigne = cell.getRow();
            TableColumn colone = cell.getTableColumn();
            FicheProduit produitsel = produitTable.getItems().get(indexLigne);
            System.out.println("Simple-click ligne "+indexLigne+" colonne "+colone.getText()+ " : "+produitsel);
            supprimerButton.setVisible(true);
            int id = produitsel.getId();
            supprimerButton.setOnAction(event1 -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                alert.setHeaderText("Êtes-vous sûr de vouloir supprimer la liste ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    produitRepository.supprimerProduit(id,labelErreur);
                    supprimerButton.setVisible(false);
                }else {
                    labelErreur.setText("Supression annulée");
                    supprimerButton.setVisible(false);
                }
            });


        }
    }
}
