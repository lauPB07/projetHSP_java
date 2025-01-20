package com.example.projethsp.pageSecretaire;

import com.example.projethsp.Entity.Patient;
import com.example.projethsp.Entity.Utilisateur;
import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.PatientRepository;
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
import java.util.ResourceBundle;

public class AcceuilController implements Initializable {

    PatientRepository patientRepository = new PatientRepository();

    @FXML
    private TableView<Patient> tableauPatient;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                {"ID User","id"},
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

        for (int i = 0; i <colonnes.length; i++){
            TableColumn<Patient,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            tableauPatient.getColumns().add(maColonne);
        }
        ArrayList<Patient> list = patientRepository.recupererPatient();
        tableauPatient.getItems().addAll(list);
    }

    @FXML
    public void inscption(ActionEvent event){
        HelloApplication.changeScene("pageSecretaire/inscriptionView","Inscritpion Patient");
    }

    @FXML
    public void deconnexion(ActionEvent event){
        if (Utilisateurconnecte.clearInstance()) {
            System.out.println("Session terminée.");
            HelloApplication.changeScene("Acceuil/loginView", "Connexion");
        } else {
            System.out.println("Aucune session active à terminer.");
        }
    }

    @FXML void onListeSelection(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            TablePosition cell = tableauPatient.getSelectionModel().getSelectedCells().get(0);
                 int indexLigne = cell.getRow();
                 Patient patientSelect = tableauPatient.getItems().get(indexLigne);

            if (event.getClickCount() == 2) {
                // Double clic pour édition
                HelloApplication.changeScene("liste/EditerPatientView", new EditerPatientController(patientSelect));

            } else if (event.getClickCount() == 1) {
                // Simple clic pour suppression avec confirmation
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation de suppression");
                //alert.setHeaderText("Suppression de la liste : " + nom);
                alert.setContentText("Êtes-vous sûr de vouloir supprimer cette liste ?");

                // Attente de la réponse de l'utilisateur
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        // Suppression de la liste
                        //ListeRepository listeRepository = new ListeRepository();
                        try {
                           // listeRepository.supprimerListe(id); // Suppression dans la base de données
                        //} catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        //tableauListe.getItems().remove(indexLigne); // Suppression dans l'affichage
                        //System.out.println("Liste supprimée : " + nom);
                    }
                });
            }
        }
    }

}
