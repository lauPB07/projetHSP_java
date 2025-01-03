package com.example.projethsp.pageSecretaire;

import com.example.projethsp.Entity.Patient;
import com.example.projethsp.Entity.Utilisateur;
import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.PatientRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
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
}
