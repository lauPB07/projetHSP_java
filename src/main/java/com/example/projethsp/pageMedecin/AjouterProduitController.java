package com.example.projethsp.pageMedecin;

import com.example.projethsp.Entity.DemandeProduit;
import com.example.projethsp.Entity.FicheProduit;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.DemandeProduitRepository;
import com.example.projethsp.Repository.ProduitRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class AjouterProduitController implements Initializable {

    @FXML
    private TextField nb;

    @FXML
    private ComboBox<FicheProduit> produit;

    @FXML
    private Button retour;

    @FXML
    private Label status;

    @FXML
    private Button valider;

    private int id;

    private String label;

    public AjouterProduitController(int id,String label) {
        this.id = id;
        this.label = label;
    }

    @FXML
    void onClickRetour(ActionEvent event) {
        HelloApplication.changeScene("pageMedecin/mesDemandeView","Vos demandes");
    }

    @FXML
    void onClickValider(ActionEvent event) {
        int idProduit = produit.getSelectionModel().getSelectedItem().getId();
        int nb = Integer.parseInt(this.nb.getText());
        DemandeProduit demandeProduit = new DemandeProduit(id,idProduit,nb,false);
        System.out.println(id);
        DemandeProduitRepository repo = new DemandeProduitRepository();
        System.out.println(demandeProduit.toString());
        repo.ajouter(demandeProduit);
        HelloApplication.changeScene("pageMedecin/mesDemandeView","Vos demandes");


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.status.setText(label);
        ProduitRepository repo = new ProduitRepository();
        ArrayList<FicheProduit> list = repo.selectProduit();
        this.produit.getItems().addAll(list);
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        };

        TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), null, filter);
        this.nb.setTextFormatter(textFormatter);
    }
}
