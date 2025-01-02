package com.example.projethsp.pageAdmin;

import com.example.projethsp.Entity.Role;
import com.example.projethsp.Repository.RoleRepository;
import com.example.projethsp.Repository.UtilisateurRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class InscriptionController implements Initializable {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField mdpField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private Label labelErreur;

    @FXML
    private ComboBox<Role> roleField;

    RoleRepository roleRepository = new RoleRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roleRepository.recupRole(roleField);
    }

    @FXML
    public void inscription(ActionEvent event){
        UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
        utilisateurRepository.inscription(nomField.getText(),prenomField.getText(),emailField.getText(),mdpField.getText(),roleField.getValue().getId(),labelErreur);

    }
}
