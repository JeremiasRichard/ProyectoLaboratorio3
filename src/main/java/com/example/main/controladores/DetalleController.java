package com.example.main.controladores;

import com.example.main.modelos.Arreglo;
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
    public void inicializar(Arreglo ListaTareas)
    {
        IdCliente.setText(String.valueOf(ListaTareas.getIdCliente()));
        IdVehiculo.setText(String.valueOf(ListaTareas.getVehiculo().getIdVehiculo()));
        DetallesDeFalla.setText(ListaTareas.getDetalleCliente());
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
