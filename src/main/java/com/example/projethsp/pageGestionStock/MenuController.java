package com.example.projethsp.pageGestionStock;

import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController {

    @FXML
    public void produit(ActionEvent event){
        HelloApplication.changeScene("pageGestionStock/produitView","Produits");
    }

    @FXML
    public void deconnexion(ActionEvent event){
        if (Utilisateurconnecte.clearInstance()) {
            HelloApplication.changeScene("acceuil/loginView", "Connexion");
        } else {
            System.out.println("Aucune session active à fermer.");
        }
    }
}
