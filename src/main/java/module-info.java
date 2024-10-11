module com.example.farmacia {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.apache.commons.codec;

    opens com.example.farmacia to javafx.fxml;
    exports com.example.farmacia;
    exports com.example.farmacia.Controller;
    opens com.example.farmacia.Controller to javafx.fxml;
}