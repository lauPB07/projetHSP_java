module com.example.projethsp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires spring.security.crypto;

    opens com.example.projethsp to javafx.fxml;
    exports com.example.projethsp;
    exports com.example.projethsp.Acceuil;
    opens com.example.projethsp.Acceuil to javafx.fxml;
    exports com.example.projethsp.pageAdmin;
    opens com.example.projethsp.pageAdmin to javafx.fxml;
    exports com.example.projethsp.Entity;
    opens com.example.projethsp.Entity to javafx.fxml;
    exports com.example.projethsp.pageSecretaire;
    opens com.example.projethsp.pageSecretaire to javafx.fxml;
    exports com.example.projethsp.pageGestionStock;
    opens com.example.projethsp.pageGestionStock to javafx.fxml;
    exports com.example.projethsp.pageMedecin;
    opens com.example.projethsp.pageMedecin to javafx.fxml;
    exports com.example.projethsp.pagePatient;
    opens com.example.projethsp.pagePatient to javafx.fxml;

}