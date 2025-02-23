package com.example.projethsp.pageAdmin;

import com.example.projethsp.Entity.HistoriqueConnexion;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.HistoriqueRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HistoriqueConnexionController implements Initializable {

    @FXML
    private TableView<HistoriqueConnexion> tableauHistorique;

    private HistoriqueRepository repository = new HistoriqueRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                {"Nom","nom"},
                {"Prenom", "prenom"},
                {"Date", "date"},
                {"Heure", "heure"}
        };
        for (int i = 0; i <colonnes.length; i++){
            TableColumn<HistoriqueConnexion,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            tableauHistorique.getColumns().add(maColonne);
        }

        ArrayList<HistoriqueConnexion> list = repository.recupererHistorique();
        tableauHistorique.getItems().addAll(list);
    }

    @FXML
    void retour(ActionEvent event){
        HelloApplication.changeScene("pageAdmin/menuView", "Acceuil Admin");
    }
}
