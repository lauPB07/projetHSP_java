package com.example.projethsp.pageGestionStock;

import com.example.projethsp.Entity.FicheProduit;
import com.example.projethsp.Entity.Fournisseur;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.FournisseurRepository;
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

public class FournisseurController implements Initializable {

    private FournisseurRepository fournisseurRepository = new FournisseurRepository();

    @FXML
    private Label erreurLabel;

    @FXML
    private TableView<Fournisseur> fournisseurTable;

    @FXML
    private Button supprimerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                {"ID ","id"},
                {"Nom", "nom"},
        };

        for (int i = 0; i <colonnes.length; i++){
            TableColumn<Fournisseur,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            fournisseurTable.getColumns().add(maColonne);
        }
        ArrayList<Fournisseur> list = fournisseurRepository.recupererFournisseur();
        fournisseurTable.getItems().addAll(list);

        supprimerButton.setVisible(false);

    }


    @FXML
    void onListeSelection(MouseEvent event){
        if(event.getButton() == MouseButton.PRIMARY && event.getClickCount()==2){
            TablePosition cell = fournisseurTable.getSelectionModel().getSelectedCells().get(0);
            int indexLigne = cell.getRow();
            TableColumn colone = cell.getTableColumn();
            Fournisseur fournisseur = fournisseurTable.getItems().get(indexLigne);
            System.out.println("Double-clique ligne "+indexLigne+" , colone  "+colone.getText()+ " : "+ fournisseur);
            //HelloApplication.changeScene("pageGestionStock/editerProduitView",new ModifierProduitController(produitsel));
        } else if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() ==1) {
            TablePosition cell = fournisseurTable.getSelectionModel().getSelectedCells().get(0);
            int indexLigne = cell.getRow();
            TableColumn colone = cell.getTableColumn();
            Fournisseur fournisseur = fournisseurTable.getItems().get(indexLigne);
            System.out.println("Simple-click ligne "+indexLigne+" colonne "+colone.getText()+ " : "+fournisseur);
            supprimerButton.setVisible(true);
            int id = fournisseur.getId();
            supprimerButton.setOnAction(event1 -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                alert.setHeaderText("Êtes-vous sûr de vouloir supprimer la liste ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    //produitRepository.supprimerProduit(id,labelErreur);
                    supprimerButton.setVisible(false);
                }else {
                    //labelErreur.setText("Supression annulée");
                    supprimerButton.setVisible(false);
                }
            });


        }
    }

    @FXML
    void retour(ActionEvent event){
        HelloApplication.changeScene("pageGestionStock/produitView","Produits");
    }
}
