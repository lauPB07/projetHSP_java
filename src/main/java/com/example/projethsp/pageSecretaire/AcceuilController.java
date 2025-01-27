package com.example.projethsp.pageSecretaire;

import com.example.projethsp.Entity.FicheProduit;
import com.example.projethsp.Entity.Patient;
import com.example.projethsp.Entity.Utilisateur;
import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.PatientRepository;
import com.example.projethsp.pageGestionStock.ModifierProduitController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AcceuilController implements Initializable {

    PatientRepository patientRepository = new PatientRepository();

    @FXML
    private Button supprimer;

    @FXML
    private Label label;

    @FXML
    private TableView<Patient> tableauPatient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[][] colonnes = {
                {"ID User", "id"},
                {"Nom", "nom"},
                {"Prenom", "prenom"},
                {"Email", "email"},
                {"Role", "role"},
                {"Telephone", "telephone"},
                {"Rue", "rue"},
                {"Code Postale", "cp"},
                {"Ville", "ville"},
                {"Numéro securiter sociale", "numSecu"}
        };

        for (int i = 0; i < colonnes.length; i++) {
            TableColumn<Patient, String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            tableauPatient.getColumns().add(maColonne);
        }
        ArrayList<Patient> list = patientRepository.recupererPatient();
        tableauPatient.getItems().addAll(list);

        supprimer.setVisible(false);
    }

    @FXML
    public void inscption(ActionEvent event) {
        HelloApplication.changeScene("pageSecretaire/inscriptionView", "Inscritpion Patient");
    }

    @FXML
    void supprimer(ActionEvent event) {

    }

    @FXML
    public void deconnexion(ActionEvent event) {
        if (Utilisateurconnecte.clearInstance()) {
            System.out.println("Session terminée.");
            HelloApplication.changeScene("Acceuil/loginView", "Connexion");
        } else {
            System.out.println("Aucune session active à terminer.");
        }
    }

    @FXML
    void onListeSelection(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            TablePosition cell = tableauPatient.getSelectionModel().getSelectedCells().get(0);
            int indexLigne = cell.getRow();
            TableColumn colone = cell.getTableColumn();
            Patient patient = tableauPatient.getItems().get(indexLigne);
            System.out.println("Double-clique ligne " + indexLigne + " , colone  " + colone.getText() + " : " + patient);
            HelloApplication.changeScene("pageSecretaire/editerPatientView", new EditerPatientController(patient));
        } else if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
            TablePosition cell = tableauPatient.getSelectionModel().getSelectedCells().get(0);
            int indexLigne = cell.getRow();
            TableColumn colone = cell.getTableColumn();
            Patient patient = tableauPatient.getItems().get(indexLigne);
            System.out.println("Simple-click ligne " + indexLigne + " colonne " + colone.getText() + " : " + patient);
            supprimer.setVisible(true);
            int id = patient.getId();
            supprimer.setOnAction(event1 -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                alert.setHeaderText("Êtes-vous sûr de vouloir supprimer la liste ?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    patientRepository.supprimerPatient(id, label);
                    supprimer.setVisible(false);
                } else {
                    label.setText("Supression annulée");
                    supprimer.setVisible(false);
                }
            });


        }
    }
}
