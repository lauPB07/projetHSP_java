package com.example.projethsp.pageSecretaire;

import com.example.projethsp.Entity.Niveau;
import com.example.projethsp.Entity.Patient;
import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.NiveauRepository;
import com.example.projethsp.Repository.PatientRepository;
import com.example.projethsp.Repository.PriseEnChargeRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PriseEnChargeController implements Initializable {

    @FXML
    void retour(ActionEvent event) {
        HelloApplication.changeScene("pageSecretaire/acceuilView","Acceuil");
    }

    PriseEnChargeRepository priseEnChargeRepository = new PriseEnChargeRepository();
    @FXML
    void valider(ActionEvent event) {
        System.out.println("test");
        priseEnChargeRepository.ajouterPriseEnCharge(dateField.getValue(),comboBox.getValue(),symptomesField.getText(), String.valueOf(nivBox.getValue().getId()),new PatientRepository().rechercheFichePatient(patient.getId()),Utilisateurconnecte.getInstance().getId());
        HelloApplication.changeScene("pageSecretaire/acceuilView","Acceuil");
    }

    @FXML
    private ComboBox<?> heuresField;

    @FXML
    private ComboBox<?> minutesField;

    @FXML
    private Button valider;

    @FXML
    private TextField adresseField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField numSecuField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextArea symptomesField;

    @FXML
    private TextField telephoneField;

    @FXML
    private ChoiceBox<Niveau> nivBox;

    @FXML
    private Text label;

    @FXML
    private ComboBox<String> comboBox;

    private Patient patient;

    public PriseEnChargeController(Patient patient) {

        this.patient = patient;

    }

    private NiveauRepository niveauRepository = new NiveauRepository();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nomField.setText(patient.getNom());
        prenomField.setText(patient.getPrenom());
        numSecuField.setText(patient.getNumSecu());
        emailField.setText(patient.getEmail());
        telephoneField.setText(patient.getTelephone());
        adresseField.setText(patient.getRue()+patient.getVille()+patient.getCp());
        ArrayList<Niveau> list = niveauRepository.recupererNiv();
        nivBox.getItems().addAll(list);

        comboBox.getValue();

        valider.setOnAction(this::valider);


    }
}
