package com.example.projethsp.pagePatient;

import com.example.projethsp.Entity.Demande;
import com.example.projethsp.Entity.Ordonnance;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.OrdonnanceRepository;
import com.example.projethsp.pageMedecin.ProduitDemandeController;
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

public class OrdonnanceController implements Initializable {

    @FXML
    private TableView<Ordonnance> liste;

    @FXML
    private Button retour;

    @FXML
    private Button telecharger;

    private int idPatient;

    @FXML
    void OnClickRetour(ActionEvent event) {
        HelloApplication.changeScene("pagePatient/menuView","Menu");
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
                int id = liste.getItems().get(indexLigne).getId();
                idPatient = id;
                this.telecharger.setVisible(true);
            }
        }
    }

    @FXML
    void onClickTelecharger(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.telecharger.setVisible(false);
        String [][] colonnes = {
                {"ID","id"},
                {"Contenue", "contenue"},

        };
        for (int i = 0; i <colonnes.length; i++){
            TableColumn<Ordonnance,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            liste.getColumns().add(maColonne);
        }
        OrdonnanceRepository ord = new OrdonnanceRepository();
        ArrayList<Ordonnance> list = ord.selectPatient();
        liste.getItems().addAll(list);
    }
}
