package com.example.main.controladores;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdminController
{



    @FXML
    private DatePicker datePicker;
    @FXML
    private Label fecha;
    @FXML
    public void setearFecha(ActionEvent event) {
        String fechaString = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate fechaFormateada = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        datePicker.setValue(fechaFormateada);
        fecha.setText(fechaFormateada.toString());
    }



}
