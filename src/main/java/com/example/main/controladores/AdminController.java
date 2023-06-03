package com.example.main.controladores;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdminController
{

    /*@FXML
    private DatePicker datePicker;
    @FXML
    private Label fecha;*/
    @FXML
    private Button exitButton;

    /*@FXML
    public void setearFecha(ActionEvent event) {
        String fechaString = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate fechaFormateada = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        datePicker.setValue(fechaFormateada);
        fecha.setText(fechaFormateada.toString());

    }*/
    @FXML
    void closeApplication(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/loginView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
