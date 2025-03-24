package com.example.projethsp.pagePatient;

import com.example.projethsp.Entity.Hospitalisation;
import com.example.projethsp.Entity.Ordonnance;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.HospitalisationRepository;
import com.example.projethsp.Repository.OrdonnanceRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HospitalisationController implements Initializable {

    @FXML
    private TableView<Hospitalisation> liste;

    @FXML
    private Button retour;

    @FXML
    void OnClickRetour(ActionEvent event) {
        HelloApplication.changeScene("pagePatient/menuView","Menu");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                {"ID","id"},
                {"Date de Debut", "dateDebut"},
                {"Date de Fin", "dateFin"},
                {"Description", "description"},
                {"Chambre", "chambre"}

        };
        for (int i = 0; i <colonnes.length; i++){
            TableColumn<Hospitalisation,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            liste.getColumns().add(maColonne);
        }
        HospitalisationRepository repo = new HospitalisationRepository();
        ArrayList<Hospitalisation> list = repo.selectPatient();
        liste.getItems().addAll(list);
    }
}
