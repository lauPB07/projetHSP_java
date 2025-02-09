package com.example.projethsp.pageMedecin;

import com.example.projethsp.Entity.FichePatient;
import com.example.projethsp.Entity.Utilisateur;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.FichePatientRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListePatientController implements Initializable {

    private FichePatientRepository fichePatient=new FichePatientRepository();

    @FXML
    private Button hospitalisation;

    @FXML
    private TableView<Utilisateur> liste;

    @FXML
    private Button ordonnance;

    @FXML
    private Button retour;

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    @FXML
    void OnClickRetour(ActionEvent event) {
        HelloApplication.changeScene("pageMedecin/menuView","Menu");
    }

    @FXML
    void onClickHospitalisation(ActionEvent event) {

    }

    @FXML
    void onClickList(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount() == 2) {
                TablePosition cell = liste.getSelectionModel().getSelectedCells().get(0);
                int indexLigne = cell.getRow();
                int id = liste.getItems().get(indexLigne).getId();
                HelloApplication.changeScene("pageMedecin/produitDemandeView",new ProduitDemandeController(id));
            } else if (event.getClickCount() == 1) {
                TablePosition cell = liste.getSelectionModel().getSelectedCells().get(0);
                int indexLigne = cell.getRow();
                int id = liste.getItems().get(indexLigne).getId();
                setId(id);
                ordonnance.setVisible(true);
                hospitalisation.setVisible(true);
            }
        }
    }

    @FXML
    void onClickOrdonnance(ActionEvent event) {
        HelloApplication.changeScene("pageMedecin/newOrdonnanceView",new NewOrdonnanceController(id));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.hospitalisation.setVisible(false);
        this.ordonnance.setVisible(false);
        String [][] colonnes = {
                {"ID","id"},
                {"Nom","nom"},
                {"Prenom", "prenom"},
                {"Email","email"}

        };
        for (int i = 0; i <colonnes.length; i++){
            TableColumn<Utilisateur,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            liste.getColumns().add(maColonne);
        }
        ArrayList<Utilisateur> list = fichePatient.selectPatient();
        liste.getItems().addAll(list);

    }
}
