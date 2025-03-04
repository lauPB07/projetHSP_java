package com.example.projethsp.pageAdmin;

import com.example.projethsp.BDD.Bdd;
import com.example.projethsp.Entity.Commande;
import com.example.projethsp.Repository.CommandeRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CommandeController implements Initializable {
    @FXML
    private PieChart statCommande;
    @FXML
    private Button valider;

    @FXML
    private TableView<Commande> commandeTable;

    private CommandeRepository commandeRepository = new CommandeRepository();

    static Bdd connexionBdd = new Bdd();
    static Connection connection = connexionBdd.getBdd();

    public void localData(){
        String sql ="SELECT COUNT(c.id_commande), s.nom FROM commande as c inner join status as s on s.id_status = c.ref_status ORDER BY ref_status; ";
        ObservableList< PieChart.Data > piechartdata;
        piechartdata = FXCollections.observableArrayList();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                String statusName = resultSet.getString(2);

                PieChart.Data data = new PieChart.Data(statusName, count);
                data.setName(statusName + " (" + count + ")");
                piechartdata.add(data);
            }

            if (statCommande != null) {
                statCommande.setData(piechartdata);
            } else {
                System.err.println("Erreur : Le PieChart statCommande est null !");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        localData();
        valider.setVisible(false);

        String [][] colonnes = {
                {"NB Produit", "nbProduit"},
                {"Nom", "nomUser"},
                {"Prenom", "prenomUser"},
                {"Prix Final", "prixFinal"},
                {"Produit", "libelleProduit"},
                {"Valider ? ","isValidate"},
        };

        for (int i = 0; i <colonnes.length; i++){
            TableColumn<Commande,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            commandeTable.getColumns().add(maColonne);
        }
        ArrayList<Commande> list = commandeRepository.getAllCommande();
        commandeTable.getItems().addAll(list);
    }
}

