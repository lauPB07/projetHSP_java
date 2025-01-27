package com.example.projethsp.Acceuil;

import com.example.projethsp.Entity.Utilisateur;
import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.UtilisateurRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private Label labelErreur;

    @FXML
    private PasswordField mdpField;


    @FXML
    void connexion(ActionEvent event) {
        System.out.println("L email est : "+emailField.getText());
        System.out.println("Le mot de passe est : "+mdpField.getText());
        if(emailField.getText().isBlank()  &&  mdpField.getText().isBlank()){
            labelErreur.setText("entrer l'email et le mot de passe ");
        }else {
            UtilisateurRepository userRepository = new UtilisateurRepository();
            Utilisateur user = userRepository.connexion(emailField.getText(),mdpField.getText(),labelErreur);
            if (user != null) {
                boolean isInitialized = Utilisateurconnecte.initInstance(user);
                int role = user.getRole();
                System.out.println(role);
                if (isInitialized && role==6) {
                    HelloApplication.changeScene("pageAdmin/menuView","Menu");
                } else if (isInitialized && role==3) {
                    HelloApplication.changeScene("pageSecretaire/acceuilView","Menu");
                } else if (isInitialized && role==4) {
                    HelloApplication.changeScene("pageMedecin/mesDemandeView","Menu");
                } else {
                    labelErreur.setText("Une session est déjà active.");
                }
            }

        }
    }

}