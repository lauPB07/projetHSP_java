package com.example.projethsp.Acceuil;

import com.example.projethsp.Entity.Utilisateur;
import com.example.projethsp.Entity.Utilisateurconnecte;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.UtilisateurRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;


public class ModifMdpController {


    @FXML
    private PasswordField ancien;

    @FXML
    private Label label;

    @FXML
    private PasswordField confirmer;

    @FXML
    private PasswordField nouveau;

    @FXML
    private Button retour;

    @FXML
    private Button valider;

    @FXML
    void onClickRetour(ActionEvent event) {
        int role = Utilisateurconnecte.getInstance().getRole();
        if (role==6) {
            HelloApplication.changeScene("pageAdmin/menuView","Menu");
        } else if (role==3) {
            HelloApplication.changeScene("pageSecretaire/acceuilView","Menu");
        } else if (role == 5 ) {
            HelloApplication.changeScene("pageGestionStock/menuView","Menu");
        } else if (role==4) {
            HelloApplication.changeScene("pageMedecin/menuView","Menu");
        } else if (role==7) {
            HelloApplication.changeScene("pagePatient/menuView","Menu");
        }
    }

    @FXML
    void onClickValider(ActionEvent event) {
        UtilisateurRepository userRepository = new UtilisateurRepository();
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        if(userRepository.isValidPassword(nouveau.getText())){
            if(Objects.equals(nouveau.getText(), confirmer.getText())) {

                if (bcrypt.matches(ancien.getText(),Utilisateurconnecte.getInstance().getMdp())){
                    userRepository.modifmdp(nouveau.getText());
                    int role = Utilisateurconnecte.getInstance().getRole();
                    if (role==6) {
                        HelloApplication.changeScene("pageAdmin/menuView","Menu");
                    } else if (role==3) {
                        HelloApplication.changeScene("pageSecretaire/acceuilView","Menu");
                    } else if (role == 5 ) {
                        HelloApplication.changeScene("pageGestionStock/menuView","Menu");
                    } else if (role==4) {
                        HelloApplication.changeScene("pageMedecin/menuView","Menu");
                    }
                }else {
                    label.setText("Mot de passe incorrect");
                }
            }
            else{
                label.setText("Erreur de saisie");
            }
        }else {
            label.setText("Nouveau : 12 Caractére, 1 Majuscule, 1 Miniscule, 1 Caractére Spéciale");
        }
    }

}
