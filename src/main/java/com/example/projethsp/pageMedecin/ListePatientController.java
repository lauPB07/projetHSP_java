package com.example.projethsp.pageMedecin;

import com.example.projethsp.Entity.FichePatient;
import com.example.projethsp.Repository.FichePatientRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListePatient implements Initializable {

    private FichePatientRepository fichePatient=new FichePatientRepository();

    @FXML
    private Button hospitalisation;

    @FXML
    private TableView<FichePatient> liste;

    @FXML
    private Button ordonnance;

    @FXML
    private Button retour;

    @FXML
    void OnClickRetour(ActionEvent event) {

    }

    @FXML
    void onClickHospitalisation(ActionEvent event) {

    }

    @FXML
    void onClickList(MouseEvent event) {

    }

    @FXML
    void onClickOrdonnance(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.hospitalisation.setVisible(false);
        this.ordonnance.setVisible(false);
        ArrayList<FichePatient> list = fichePatient.selectPatient();
    }
}
