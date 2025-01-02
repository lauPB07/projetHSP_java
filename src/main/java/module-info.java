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
}