package com.example.projethsp.pageGestionStock;

import com.example.projethsp.Entity.Commande;
import com.example.projethsp.Entity.DemandeStock;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.CommandeRepository;
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

public class AllCommandeController implements Initializable {

    private CommandeRepository commandeRepository = new CommandeRepository();

    @FXML
    private TableView<Commande> commandeTable;

    @FXML
    private Button supprimer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String [][] colonnes = {
                {"Numéro commande ", "idCommande"},
                {"NB Produit", "nbProduit"},
                {"Nom", "nomUser"},
                {"Prenom", "prenomUser"},
                {"Prix Final", "prixFinal"},
                {"Statut ","idStatus"},
        };

        for (int i = 0; i <colonnes.length; i++){
            TableColumn<Commande,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            commandeTable.getColumns().add(maColonne);
        }
        ArrayList<Commande> list = commandeRepository.getAllCommande();
        commandeTable.getItems().addAll(list);

        supprimer.setVisible(false);
    }

    @FXML
    void ajouterCommande(ActionEvent event){
        HelloApplication.changeScene("pageGestionStock/commandeAjoutView","Passer une commande");
    }

    @FXML
    void retour(ActionEvent event){
        HelloApplication.changeScene("pageGestionStock/menuView","Menu ");
    }
}
