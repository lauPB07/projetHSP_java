package com.example.projethsp.pageSecretaire;

import com.example.projethsp.Repository.PatientRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AjoutPatientController {
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

    PatientRepository patientRepository = new PatientRepository();

    @FXML
    public void inscription(ActionEvent event){
        patientRepository.inscription(nomField.getText(),prenomField.getText(),emailField.getText(),telField.getText(),rueField.getText(),cpField.getText(),villeField.getText(),numSecuField.getText(),labelErreur);
    }
}
