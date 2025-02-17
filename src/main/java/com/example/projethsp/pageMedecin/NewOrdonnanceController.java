package com.example.projethsp.pageMedecin;

import com.example.projethsp.Entity.Ordonnance;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.OrdonnanceRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class NewOrdonnanceController implements Initializable {

    @FXML
    private TextArea contenue;

    @FXML
    private Button retour;

    @FXML
    private Button valider;

    private int id;

    public NewOrdonnanceController(int id) {
        this.id = id;
    }

    @FXML
    void onClickRetour(ActionEvent event) {
        HelloApplication.changeScene("pageMedecin/listePatientView","Liste D'attente");
    }

    @FXML
    void onClickValider(ActionEvent event) {
        Ordonnance ordonnance = new Ordonnance(contenue.getText(), id);
        OrdonnanceRepository ordonnanceRepository = new OrdonnanceRepository();
        ordonnanceRepository.ajouter(ordonnance);
        HelloApplication.changeScene("pageMedecin/listePatientView","Liste D'attente");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
