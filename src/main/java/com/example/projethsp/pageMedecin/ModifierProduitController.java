package com.example.projethsp.pageMedecin;

import com.example.projethsp.Entity.DemandeProduit;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.DemandeProduitRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import java.util.function.UnaryOperator;
import javafx.util.converter.IntegerStringConverter;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifierProduitController implements Initializable {

    @FXML
    private TextField nb;

    @FXML
    private Button retour;

    @FXML
    private Button valider;

    private DemandeProduit produit;

    public ModifierProduitController(DemandeProduit demandeProduit){
        this.produit=demandeProduit;
    }

    @FXML
    void onClickRetour(ActionEvent event) {
        HelloApplication.changeScene("pageMedecin/produitDemandeView",new ProduitDemandeController(produit.getRef_demande()));
    }

    @FXML
    void onClickValider(ActionEvent event) {
        produit.setNb_produit(Integer.parseInt(this.nb.getText()));
        DemandeProduitRepository demandeProduitRepository=new DemandeProduitRepository();
        demandeProduitRepository.modifier(produit);
        HelloApplication.changeScene("pageMedecin/produitDemandeView", new ProduitDemandeController(produit.getRef_demande()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) { // Accepte uniquement les chiffres
                return change;
            }
            return null; // Ignore la modification si ce n'est pas un nombre
        };

        TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), this.produit.getNb_produit(), filter);
        this.nb.setTextFormatter(textFormatter);

    }
}
