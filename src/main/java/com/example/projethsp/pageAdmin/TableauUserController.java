package com.example.projethsp.pageAdmin;

import com.example.projethsp.Entity.Utilisateur;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.UtilisateurRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TableauUserController implements Initializable {

    @FXML
    private TableView<Utilisateur> tableauUser;


    @FXML
    private Button supprimer;

    UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                {"ID User","id"},
                {"Nom", "nom"},
                {"Prenom", "prenom"},
                {"Email", "email"},
                {"Role", "role"}
        };

        for (int i = 0; i <colonnes.length; i++){
            TableColumn<Utilisateur,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            tableauUser.getColumns().add(maColonne);
        }
        ArrayList<Utilisateur> list = utilisateurRepository.recupererUtilisateur();
        tableauUser.getItems().addAll(list);

        supprimer.setVisible(false);
    }

    @FXML
    public void inscription(ActionEvent event){
        HelloApplication.changeScene("pageAdmin/inscriptionView","Inscription");
    }

    @FXML
    public void retour(ActionEvent event){
        HelloApplication.changeScene("pageAdmin/menuView","Menu");
    }

}
