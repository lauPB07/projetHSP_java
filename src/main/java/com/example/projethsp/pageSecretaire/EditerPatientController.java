package com.example.projethsp.pageSecretaire;

import com.example.projethsp.Entity.Patient;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.PatientRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextFormatter;
import java.util.function.UnaryOperator;
import javafx.util.converter.IntegerStringConverter;


import java.net.URL;
import java.util.ResourceBundle;

public class EditerPatientController implements Initializable    {

    private Patient patient;

    public EditerPatientController(Patient patient) {

        this.patient = patient;

    }

    @FXML
    private TextField cpField;
    @FXML
    private TextField emailField;
    @FXML
    private Label labelErreur;
    @FXML
    private TextField nomField;
    @FXML
    private TextField numSecuField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField rueField;
    @FXML
    private TextField telField;
    @FXML
    private TextField villeField;
    @FXML
    private Button editer;
    @FXML
    private Button retour;
    @FXML
    void retour(ActionEvent event) {
        HelloApplication.changeScene("pageSecretaire/acceuilView", "Acceuil");
    }
    PatientRepository patientRepository = new PatientRepository();
    @FXML
    void editer(ActionEvent event) {
        patientRepository.editerPatient(nomField.getText(), prenomField.getText(), emailField.getText(), telField.getText(), rueField.getText(), cpField.getText(), villeField.getText(), numSecuField.getText(), patient.getId(), labelErreur);
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomField.setText(patient.getNom());
        prenomField.setText(patient.getPrenom());
        emailField.setText(patient.getEmail());
        villeField.setText(patient.getVille());
        rueField.setText(patient.getRue());
        cpField.setText(patient.getCp());
        numSecuField.setText(patient.getNumSecu());
        retour.setOnAction(event -> {
            retour(event);
        } );
        editer.setOnAction(event -> {
            editer(event);
        } );
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };

        TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), Integer.parseInt(patient.getTelephone()), filter);
        telField.setTextFormatter(textFormatter);
    }
}


