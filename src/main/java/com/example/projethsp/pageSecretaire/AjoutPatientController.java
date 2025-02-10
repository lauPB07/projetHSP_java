package com.example.projethsp.pageSecretaire;

import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.PatientRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import java.util.function.UnaryOperator;
import javafx.util.converter.IntegerStringConverter;
import java.net.URL;
import java.util.ResourceBundle;

public class AjoutPatientController implements Initializable {
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

    @FXML
    public void retour(ActionEvent event){
        HelloApplication.changeScene("pageSecretaire/acceuilView","Acceuil");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };

        TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), null, filter);
        telField.setTextFormatter(textFormatter);
    }
}

