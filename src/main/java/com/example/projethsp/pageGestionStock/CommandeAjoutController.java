package com.example.projethsp.pageGestionStock;
import com.example.projethsp.Entity.AjoutCommande;
import com.example.projethsp.Entity.FicheProduit;
import com.example.projethsp.Entity.Fournisseur;
import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.CommandeAjoutRepository;
import com.example.projethsp.Repository.FournisseurRepository;
import com.example.projethsp.Repository.ProduitRepository;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CommandeAjoutController implements Initializable {

    private FournisseurRepository fournisseurRepository = new FournisseurRepository();
    private ProduitRepository produitRepository = new ProduitRepository();
    private CommandeAjoutRepository commandeAjoutRepository = new CommandeAjoutRepository();

    @FXML
    private Button ajouterBoutton;

    @FXML
    private Button creerCommande;

    @FXML
    private TableColumn<AjoutCommande, String> fournisseur;

    @FXML
    private ComboBox<Fournisseur> fournisseurCombobox;

    @FXML
    private TableColumn<AjoutCommande, Double> prix;

    @FXML
    private TableColumn<AjoutCommande, Double> prixFinal;

    @FXML
    private TableColumn<AjoutCommande, String> produit;

    @FXML
    private ComboBox<FicheProduit> produitCombobox;

    @FXML
    private TableColumn<AjoutCommande, Integer> quantite;

    @FXML
    private TextField quantiteField;

    @FXML
    private TextField prixTotal;

    @FXML
    private TableView<AjoutCommande> tableProduit;

    private ObservableList<AjoutCommande> commandeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Fournisseur> list1 = fournisseurRepository.recupererFournisseur();
        fournisseurCombobox.getItems().addAll(list1);

        fournisseur.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomFournisseur()));
        produit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomProduit()));
        prix.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrixInitial()).asObject());
        quantite.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantite()).asObject());
        prixFinal.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrixFinal()).asObject());




        tableProduit.setItems(commandeList);
    }

    @FXML
    public void onClicked(ActionEvent event) {
        if (fournisseurCombobox.getValue() != null) {
            ArrayList<FicheProduit> list = produitRepository.selectProduitByFournisseur(fournisseurCombobox.getValue().getId());
            produitCombobox.getItems().clear();
            produitCombobox.getItems().addAll(list);
        }
    }

    @FXML
    public void ajouterButton(ActionEvent event) {
        if (fournisseurCombobox.getValue() == null || produitCombobox.getValue() == null || quantiteField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tous les champs.", ButtonType.OK);
            alert.show();
            return;
        }

        try {
            int quantiteValeur = Integer.parseInt(quantiteField.getText());
            double prixValeur = produitCombobox.getValue().getPrix();
            double total = prixValeur * quantiteValeur;

            AjoutCommande nouvelleCommande = new AjoutCommande(
                    produitCombobox.getValue().getId(),
                    fournisseurCombobox.getValue().getNom(),
                    produitCombobox.getValue().getLibelle(),
                    prixValeur,
                    total,
                    quantiteValeur
                        );

            commandeList.add(nouvelleCommande);
            tableProduit.refresh();
            produitCombobox.getSelectionModel().clearSelection();
            quantiteField.clear();
            float total2 = 0;

            for (AjoutCommande produit : tableProduit.getItems()) {
                total2 += (float) produit.getPrixFinal(); // Ajoute chaque prixFinal au total
            }

            prixTotal.setText(String.valueOf(total2)); // Affiche la somme totale


        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez entrer une quantité valide.", ButtonType.OK);
            alert.show();
        }
    }

    @FXML
    void retour(ActionEvent event){
        HelloApplication.changeScene("pageGestionStock/MenuView","Menu");
    }

    @FXML
    void ajouter(ActionEvent event) throws SQLException {
        commandeAjoutRepository.insererCommande(tableProduit.getItems().size(), Utilisateurconnecte.getInstance().getId(),
                Float.parseFloat(prixTotal.getText()),fournisseurCombobox.getValue().getId());
        for (AjoutCommande produit : tableProduit.getItems()) {
            commandeAjoutRepository.insererProduitsCommande(commandeAjoutRepository.getDernierIdCommande(), produit.getId());
        }

    }
}
