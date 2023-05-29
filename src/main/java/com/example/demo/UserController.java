package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserController
{
    private Stage stage;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label fecha;

    @FXML public void setearFecha(ActionEvent event)
    {
        String fechaString = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate fechaFormateada = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        datePicker.setValue(fechaFormateada);
        fecha.setText(fechaFormateada.toString());
    }

    @FXML
    void closeApplication(ActionEvent event) throws IOException
    {
        stage.close();
    }
}
