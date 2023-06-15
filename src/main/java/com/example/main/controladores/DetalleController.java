package com.example.main.controladores;

import com.example.main.enums.EstadoReparacion;
import com.example.main.modelos.Arreglo;
import com.example.main.modelos.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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

    public void initialize(Arreglo arreglo,Usuario logueado)
    {
        idNombreUsuario.setText(logueado.getUser().toString());

        ObservableList<String> opciones = FXCollections.observableArrayList(
                "Stand By",
                "En proceso",
                "Finalizado"
        );
        EstadoR.setItems(opciones);

        estadoAnterior = arreglo.getEstadoReparacion();

        arreglo.setObservacionesDelArreglo(observacionesDelArregloField.getText());

        if(arreglo.getEstadoReparacion() == EstadoReparacion.STAND_BY)
            EstadoR.setValue("Stand by");
        else if(arreglo.getEstadoReparacion() == EstadoReparacion.EN_PROCESO)
        {
            EstadoR.setValue("En proceso");
        }
        else {
            EstadoR.setValue("Finalizado");
            arreglo.setObservacionesDelArreglo(observacionesDelArregloField.getText());
        }

        EstadoR.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {

            if (newValue.equals("En proceso"))
            {
                estadoAnterior = arreglo.getEstadoReparacion();
                arreglo.setEstadoReparacion(EstadoReparacion.EN_PROCESO);
            } else if (newValue.equals("Finalizado"))
            {
                estadoAnterior = arreglo.getEstadoReparacion();
                arreglo.setEstadoReparacion(EstadoReparacion.FINALIZADO);

            }
        });

    }
    public void inicializar(Arreglo ListaTareas, Usuario logueado)
    {   initialize(ListaTareas,logueado);
        IdCliente.setText(String.valueOf(new PropertyValueFactory("idCliente")));
        IdArreglo.setText(String.valueOf(new PropertyValueFactory("idArreglo")));
        IdVehiculo.setText(String.valueOf(new PropertyValueFactory("idVehiculo")));
        Marca.setText(String.valueOf(new PropertyValueFactory("marca")));
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

    private void guardarCambios(Arreglo arreglo) {
        if (arreglo.getEstadoReparacion() != EstadoReparacion.FINALIZADO || arreglo.getObservacionesDelArreglo() == null) {
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
