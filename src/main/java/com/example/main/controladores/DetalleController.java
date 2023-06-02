package com.example.main.controladores;

import com.example.main.modelos.Auto;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DetalleController
{
    @FXML
    private Button atrasButton;
    @FXML
    private Stage stageAnterior;
    @FXML
    private  Label IdCliente = new Label();

    @FXML
    private Label IdVehiculo = new Label();

    @FXML
    private Label Marca  = new Label();
    @FXML
    private Label DetallesDeFalla = new Label();

    @FXML
    private ChoiceBox<String> EstadoReparacion = new ChoiceBox<>();

    public void initialize()
    {
        ObservableList<String> opciones = FXCollections.observableArrayList(
                "Stand By",
                "En proceso",
                "Finalizado"
        );
        EstadoReparacion.setItems(opciones);
    }
    public void inicializar(Auto auto)
    {
        IdCliente.setText(String.valueOf(auto.getIdCliente()));
        IdVehiculo.setText(String.valueOf(auto.getIdVehiculo()));
        Marca.setText(auto.getMarca());
        DetallesDeFalla.setText(auto.getDetalleDeFalla());
        DetallesDeFalla.setWrapText(true);
    }

    public void setStageAnterior(Stage stageAnterior) {
        this.stageAnterior = stageAnterior;
    }

    public void volverAtras()
    {
        Stage stageActual = (Stage) atrasButton.getScene().getWindow();
        stageActual.close();
    }
}
