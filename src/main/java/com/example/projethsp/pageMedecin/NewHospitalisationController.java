package com.example.projethsp.pageMedecin;

import com.example.projethsp.Entity.Chambre;
import com.example.projethsp.Entity.Hospitalisation;
import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.ChambreRepository;
import com.example.projethsp.Repository.HospitalisationRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class NewHospitalisationController {

    @FXML
    private ComboBox<Chambre> chambre;

    @FXML
    private DatePicker dateDebut;

    @FXML
    private DatePicker dateFin;

    @FXML
    private TextArea description;

    @FXML
    private Button retour;

    @FXML
    private Button valider;

    private int id;

    public NewHospitalisationController(int id) {
        this.id = id;
    }

    @FXML
    void onChangeDateFin(ActionEvent event) {
        System.out.println(dateDebut.getValue().toString());
        if(dateDebut.getValue()==null){
            this.chambre.getItems().clear();
        }else {
            ChambreRepository chambreRepository = new ChambreRepository();
            ArrayList<Chambre> list = chambreRepository.selectChambre(this.dateDebut.getValue().toString(), this.dateFin.getValue().toString());
            for (Chambre chambre : list) {
                this.chambre.getItems().add(chambre);
            }
        }
    }
    @FXML
    void onChangeDateDebut(ActionEvent event) {

        if(dateFin.getValue()==null){
            this.chambre.getItems().clear();
        }else {
            ChambreRepository chambreRepository = new ChambreRepository();
            ArrayList<Chambre> list = chambreRepository.selectChambre(this.dateDebut.getValue().toString(), this.dateFin.getValue().toString());
            for (Chambre chambre : list) {
                this.chambre.getItems().add(chambre);
            }
        }
    }

    @FXML
    void onClickRetour(ActionEvent event) {
        HelloApplication.changeScene("pageMedecin/menuView","Menu");
    }

    @FXML
    void onClickValider(ActionEvent event) {
        HospitalisationRepository hospitalisationRepository = new HospitalisationRepository();
        hospitalisationRepository.ajouter(this.dateDebut.getValue(),this.dateFin.getValue(),this.description.getText(),this.chambre.getValue().getId(),id);
        HelloApplication.changeScene("pageMedecin/listePatientView","Liste Patient");
    }

}
