
module com.example.main {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;

    opens com.example.main to javafx.fxml;
    exports com.example.main;

    exports com.example.main.controladores;
    opens com.example.main.controladores to javafx.fxml;

    exports com.example.main.enums to com.fasterxml.jackson.databind;
    exports com.example.main.modelos to com.fasterxml.jackson.databind;
    exports com.example.main.DTOs to com.fasterxml.jackson.databind, javafx.base;
    opens com.example.main.DTOs to javafx.base;
    opens com.example.main.modelos to javafx.base;
    exports com.example.main.utils;
    opens com.example.main.utils to javafx.fxml;

}

