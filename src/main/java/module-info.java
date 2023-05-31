
module com.example.main {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    opens com.example.main to javafx.fxml;
    exports com.example.main;

    exports com.example.main.controladores;
    opens com.example.main.controladores to javafx.fxml;

}

