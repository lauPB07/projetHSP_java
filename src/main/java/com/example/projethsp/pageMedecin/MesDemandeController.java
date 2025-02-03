package com.example.projethsp.pageMedecin;

import com.example.projethsp.Entity.Demande;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.DemandeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MesDemandeController implements Initializable {

    @FXML
    private TableView<Demande> liste;

    @FXML
    private Button retour;

    DemandeRepository demandeRepository = new DemandeRepository();

    @FXML
    void OnClickRetour(ActionEvent event) {

    }

    @FXML
    void onClickList(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount() == 2) {
                TablePosition cell = liste.getSelectionModel().getSelectedCells().get(0);
                int indexLigne = cell.getRow();
                int id = liste.getItems().get(indexLigne).getId();
                HelloApplication.changeScene("pageMedecin/produitDemandeView",new ProduitDemandeController(id));
            } else if (event.getClickCount() == 1) {
                TablePosition cell = liste.getSelectionModel().getSelectedCells().get(0);
                int indexLigne = cell.getRow();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                {"ID","id"},
                {"Titre","titre"},
                {"Description", "description"},
                {"Valider","valider"}

        };
        for (int i = 0; i <colonnes.length; i++){
            TableColumn<Demande,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            liste.getColumns().add(maColonne);
        }

        ArrayList<Demande> list = demandeRepository.select();
        liste.getItems().addAll(list);
    }
}
