package com.example.projethsp.pagePatient;

import com.example.projethsp.Entity.Utilisateur;
import com.example.projethsp.Entity.Utilisateurconnecte;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.example.projethsp.Entity.Ordonnance;
import com.example.projethsp.HelloApplication;
import com.example.projethsp.Repository.OrdonnanceRepository;
import com.example.projethsp.pageMedecin.ProduitDemandeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrdonnanceController implements Initializable {

    @FXML
    private TableView<Ordonnance> liste;

    @FXML
    private Button retour;

    @FXML
    private Button telecharger;

    private int id;

    private String ordonnance;

    @FXML
    void OnClickRetour(ActionEvent event) {
        HelloApplication.changeScene("pagePatient/menuView","Menu");
    }

    @FXML
    void onClickList(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount() == 2) {
                TablePosition cell = liste.getSelectionModel().getSelectedCells().get(0);
                int indexLigne = cell.getRow();
                int id = liste.getItems().get(indexLigne).getId();
            } else if (event.getClickCount() == 1) {
                TablePosition cell = liste.getSelectionModel().getSelectedCells().get(0);
                int indexLigne = cell.getRow();
                int id = liste.getItems().get(indexLigne).getRef_user();
                String contenue = liste.getItems().get(indexLigne).getContenue();
                this.id = id;
                this.ordonnance = contenue;
                this.telecharger.setVisible(true);
            }
        }
    }

    @FXML
    void onClickTelecharger(ActionEvent event) {
        Document doc = new Document();

        try {
            // S'assurer que le dossier existe
            File dossier = new File("C:\\Users\\djibr\\Cour\\projetHSP_java\\Pdf");
            if (!dossier.exists()) {
                dossier.mkdirs();
            }

            // Créer le PDF
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\djibr\\Cour\\projetHSP_java\\Pdf\\Ordonnance.pdf"));
            doc.open();

            // Infos patient
            Paragraph patientInfo = new Paragraph();
            patientInfo.setAlignment(Element.ALIGN_LEFT);
            patientInfo.add(new Phrase("Patient\n", FontFactory.getFont("Comic Sans MS", 14, Font.BOLD)));
            patientInfo.add(new Phrase(Utilisateurconnecte.getInstance().getNom() + "\n", FontFactory.getFont("Comic Sans MS", 12)));
            patientInfo.add(new Phrase(Utilisateurconnecte.getInstance().getPrenom() + "\n", FontFactory.getFont("Comic Sans MS", 12)));
            patientInfo.add(new Phrase(Utilisateurconnecte.getInstance().getEmail() + "\n", FontFactory.getFont("Comic Sans MS", 12)));
            doc.add(patientInfo);

            doc.add(new Paragraph(" ")); // espace

            // Infos médecin
            OrdonnanceRepository ordonnanceRepository = new OrdonnanceRepository();
            String[] medecin = ordonnanceRepository.selectMedecin(this.id);

            Paragraph medecinInfo = new Paragraph();
            medecinInfo.setAlignment(Element.ALIGN_LEFT);
            medecinInfo.add(new Phrase("Médecin\n", FontFactory.getFont("Comic Sans MS", 14, Font.BOLD)));
            medecinInfo.add(new Phrase(medecin[0] + "\n", FontFactory.getFont("Comic Sans MS", 12)));
            medecinInfo.add(new Phrase(medecin[1] + "\n", FontFactory.getFont("Comic Sans MS", 12)));
            medecinInfo.add(new Phrase(medecin[2] + "\n", FontFactory.getFont("Comic Sans MS", 12)));
            doc.add(medecinInfo);

            doc.add(new Paragraph(" ")); // espace

            // Contenu ordonnance centré
            Paragraph contenu = new Paragraph();
            contenu.setAlignment(Element.ALIGN_CENTER);
            contenu.add(new Phrase("ORDONNANCE\n\n", FontFactory.getFont("Comic Sans MS", 16, Font.BOLD)));
            contenu.add(new Phrase(this.ordonnance, FontFactory.getFont("Comic Sans MS", 13)));
            doc.add(contenu);

            // Confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("L'ordonnance a bien été téléchargée !");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Échec du téléchargement");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } finally {
            doc.close();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.telecharger.setVisible(false);
        String [][] colonnes = {
                {"ID","id"},
                {"Contenue", "contenue"},

        };
        for (int i = 0; i <colonnes.length; i++){
            TableColumn<Ordonnance,String> maColonne = new TableColumn<>(colonnes[i][0]);
            maColonne.setCellValueFactory(new PropertyValueFactory<>(colonnes[i][1]));
            liste.getColumns().add(maColonne);
        }
        OrdonnanceRepository ord = new OrdonnanceRepository();
        ArrayList<Ordonnance> list = ord.selectPatient();
        liste.getItems().addAll(list);
    }
}
