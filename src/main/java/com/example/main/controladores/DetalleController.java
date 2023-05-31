package com.example.main.controladores;

import com.example.main.modelos.Auto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DetalleController
{

    @FXML
    private Button atrasButton;
    @FXML
    private Stage stageAnterior;
    @FXML
    private Label IdCliente;
    @FXML
    private Label IdVehiculo;
    @FXML
    private Label Marca;
    @FXML
    private Label detallesDeFalla;

    @FXML
    private Auto auto;
    public void inicializar(Auto auto)
    {

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
