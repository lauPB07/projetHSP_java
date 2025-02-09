package com.example.projethsp.pageMedecin;

import com.example.projethsp.Entity.Demande;
import com.example.projethsp.Entity.DemandeProduit;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.DemandeProduitRepository;
import com.example.projethsp.Repository.DemandeRepository;
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
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProduitDemandeController implements Initializable {
    DemandeProduitRepository demandeProduitRepository = new DemandeProduitRepository();
    @FXML
    private TableView<DemandeProduit> liste;

    @FXML
    private Button retour;

    @FXML
    private Button ajouter;

    @FXML
    private Text titre;

    private int id;

    private String nom;

    public ProduitDemandeController(int id) {
        this.id = id;
    }


    @FXML
    void OnClickRetour(ActionEvent event) {
        HelloApplication.changeScene("pageMedecin/mesDemandeView","Produit");
    }

    @FXML
    void onClickAjouter(ActionEvent event) {
        HelloApplication.changeScene("pageMedecin/ajouterProduitView",new AjouterProduitController(this.id,""));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                {"Demande","ref_demande"},
                {"Nom", "ref_demande"},
                {"Quantité","nb_produit"},
                {"Valider", "valider"},

        };
        for (int i = 0; i <colonnes.length; i++){
            TableColumn<DemandeProduit,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            liste.getColumns().add(maColonne);
        }

        ArrayList<DemandeProduit> list = demandeProduitRepository.selectProduit(this.id);
        liste.getItems().addAll(list);
    }

    @FXML
    void onClickList(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount() == 2) {
                TablePosition cell = liste.getSelectionModel().getSelectedCells().get(0);
                int indexLigne = cell.getRow();
                int red_d = liste.getItems().get(indexLigne).getRef_demande();
                int red_p = liste.getItems().get(indexLigne).getRef_produit();
                DemandeProduitRepository demandeProduitRepository = new DemandeProduitRepository();
                DemandeProduit demandeProduit = demandeProduitRepository.selectModif(red_d,red_p);
                HelloApplication.changeScene("pageMedecin/modifierProduitView",new ModifierProduitController(demandeProduit));
            } else if (event.getClickCount() == 1) {

            }
        }
    }

}
