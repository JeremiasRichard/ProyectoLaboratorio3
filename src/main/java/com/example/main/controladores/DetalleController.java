package com.example.main.controladores;

import com.example.main.DTOs.ArregloDTO;
import com.example.main.enums.EstadoReparacion;
import com.example.main.modelos.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DetalleController
{
    @FXML
    private Button atrasButton;
    @FXML
    private Button guardarButton;
    @FXML
    private Stage stageAnterior;
    @FXML
    private  Label IdArreglo = new Label();
    @FXML
    private  Label IdCliente = new Label();
    @FXML
    private Label IdVehiculo = new Label();
    @FXML
    private Label Marca = new Label();
    @FXML
    private Label DetallesDeFalla = new Label();
    @FXML
    private ChoiceBox<String> EstadoR = new ChoiceBox<>();
    private EstadoReparacion estadoAnterior;
    @FXML
    private Label idNombreUsuario = new Label();
    private Usuario logueado;
    @FXML
    private TextArea observacionesDelArregloField = new TextArea();

    public void initialize(ArregloDTO arregloDTO,Usuario logueado)
    {
        idNombreUsuario.setText(logueado.getUser().toString());

        ObservableList<String> opciones = FXCollections.observableArrayList(
                "Stand By",
                "En proceso",
                "Finalizado"
        );
        EstadoR.setItems(opciones);

        estadoAnterior = arregloDTO.getEstadoReparacion();
        arregloDTO.setObservacionesDelArreglo(observacionesDelArregloField.getText());

        if(arregloDTO.getEstadoReparacion() == EstadoReparacion.STAND_BY)
            EstadoR.setValue("Stand by");
        else if(arregloDTO.getEstadoReparacion() == EstadoReparacion.EN_PROCESO)
        {
            EstadoR.setValue("En proceso");
        }
        else {
            EstadoR.setValue("Finalizado");
            arregloDTO.setObservacionesDelArreglo(observacionesDelArregloField.getText());
        }

        EstadoR.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {

            if (newValue.equals("En proceso"))
            {
                estadoAnterior = arregloDTO.getEstadoReparacion();
                arregloDTO.setEstadoReparacion(EstadoReparacion.EN_PROCESO);
            } else if (newValue.equals("Finalizado"))
            {
                estadoAnterior = arregloDTO.getEstadoReparacion();
                arregloDTO.setEstadoReparacion(EstadoReparacion.FINALIZADO);

            }
        });

        //guardarCambios(arregloDTO);
    }
    public void inicializar(ArregloDTO ListaTareas,Usuario logueado)
    {   initialize(ListaTareas,logueado);
        IdCliente.setText(String.valueOf(ListaTareas.getIdCliente()));
        IdArreglo.setText(String.valueOf(ListaTareas.idArregloProperty().asObject()));
        IdVehiculo.setText(String.valueOf(ListaTareas.getVehiculoDTO().getIdVehiculo()));
        Marca.setText(String.valueOf(ListaTareas.getVehiculoDTO().getMarca()));
        DetallesDeFalla.setText(ListaTareas.getDetalleCliente().toString());
        DetallesDeFalla.setWrapText(true);
    }

    public void setStageAnterior(Stage stageAnterior) {
        this.stageAnterior = stageAnterior;
    }

    @FXML
    private void volverAtras()
    {
        Stage stageActual = (Stage) atrasButton.getScene().getWindow();
        stageActual.close();
    }

    private void guardarCambios(ArregloDTO arregloDTO) {
        if (arregloDTO.getEstadoReparacion() != EstadoReparacion.FINALIZADO || arregloDTO.getObservacionesDelArreglo() == null) {
            System.out.println("Error, estado inválido o campo observación vacío!");
        } else {
            Stage stageActual = (Stage) atrasButton.getScene().getWindow();
            stageActual.close();

        }
    }

    public void setUsuario(Usuario logueado)
    {
        this.logueado = logueado;
    }
}
