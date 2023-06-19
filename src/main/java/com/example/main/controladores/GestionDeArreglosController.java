package com.example.main.controladores;

import com.example.main.DTOs.ArregloDTO;
import com.example.main.enums.EstadoReparacion;
import com.example.main.enums.TipoVehiculo;
import com.example.main.modelos.Cliente;
import com.example.main.modelos.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestionDeArreglosController {
    private Usuario logueado;
    private Cliente seleccionado;
    @FXML
    private Stage adminStage;
    @FXML
    private TextField txtBusquedaPatente;
    @FXML
    private Button atrasButton;
    @FXML
    private Button modificarButton;
    @FXML
    private Button seleccionClienteButton;
    @FXML
    private Button crearButton;
    @FXML
    private ChoiceBox<String> listaVehiculos = new ChoiceBox<>();
    @FXML
    private ObservableList<String> vehiculos;
    @FXML
    private ChoiceBox<String> listaMecanicos = new ChoiceBox<>();
    @FXML
    private ObservableList<String> mecanicos;
    @FXML
    private ObservableList<ArregloDTO> arreglos;
    private ObservableList<ArregloDTO> filtroArreglos;
    @FXML
    private TableView<ArregloDTO> tblArreglos;
    @FXML
    private TableColumn columnaId;
    @FXML
    private TableColumn columnaPatente;
    @FXML
    private TableColumn columnaEstadoReparacion;
    @FXML
    private TextArea observacionesDelCliente = new TextArea();

    public void initialize() {
        arreglos = FXCollections.observableArrayList();
        filtroArreglos = FXCollections.observableArrayList();
        observacionesDelCliente.setWrapText(true);
        this.columnaId.setCellValueFactory(new PropertyValueFactory("idArreglo"));
        this.columnaPatente.setCellValueFactory(new PropertyValueFactory("patente"));
        this.columnaEstadoReparacion.setCellValueFactory(new PropertyValueFactory("estadoReparacion"));
        seleccionClienteButton.setOnAction(event -> {
            this.seleccionado = abrirSeleccionCliente();

            if (seleccionado != null) {

                List<String> listaAutos = seleccionado.getListaVehiculos();
                this.vehiculos = FXCollections.observableArrayList(listaAutos);
                listaVehiculos.setItems(vehiculos);
                List<String> mecanicos2 = new ArrayList<>();

                /*if(obtenerTipoPorPatente() == TipoVehiculo.AUTO)
                {
                    desplegar mecanicos de autos.
                    mecanicos2.add("Jorge");
                    mecanicos2.add("Pedro");
                    mecanicos2.add("Raul");
                    this.mecanicos = FXCollections.observableArrayList(mecanicos2);
                    listaMecanicos.setItems(mecanicos2)
                }
                if(obtenerTipoPorPatente() == TipoVehiculo.CAMION)
                {
                    desplegar mecanicos de autos.
                    mecanicos2.add("Jorge");
                    mecanicos2.add("Pedro");
                    mecanicos2.add("Raul");
                    this.mecanicos = FXCollections.observableArrayList(mecanicos2);
                    listaMecanicos.setItems(mecanicos2)
                }
                else (obtenerPorTipoPatente() == TipoVehiculo.MOTO)
                {
                     desplegar mecanicos de autos.
                    mecanicos2.add("Jorge");
                    mecanicos2.add("Pedro");
                    mecanicos2.add("Raul");
                    this.mecanicos = FXCollections.observableArrayList(mecanicos2);
                    listaMecanicos.setItems(mecanicos2)
                }
                */

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Cliente vacio!");
                alert.showAndWait();
            }

            listaMecanicos.setValue("");
        });
    }

    @FXML
    private Cliente abrirSeleccionCliente() {

        Cliente clienteSeleccionado = null;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/seleccionDeClienteEmergente.fxml"));
            Parent root = loader.load();
            SeleccionDeClienteEmergenteController seleccionDeClienteEmergenteController = loader.getController();
            seleccionDeClienteEmergenteController.setUsuario(logueado);
            seleccionDeClienteEmergenteController.setStageAnterior(this.adminStage);
            seleccionDeClienteEmergenteController.initialize();
            Stage detalleStage = new Stage();
            detalleStage.initModality(Modality.APPLICATION_MODAL);
            detalleStage.initOwner(adminStage);
            detalleStage.setScene(new Scene(root, 400, 300));
            detalleStage.showAndWait();
            clienteSeleccionado = seleccionDeClienteEmergenteController.Seleccionar();
            adminStage = (Stage) seleccionClienteButton.getScene().getWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clienteSeleccionado;
    }

    @FXML
    private void crearArreglo(ActionEvent event) {

        if (this.seleccionado != null) {
            ArregloDTO arregloDTO = new ArregloDTO(1, listaVehiculos.getSelectionModel().getSelectedItem(), "volkswagen", TipoVehiculo.AUTO, 2000, this.seleccionado.getDni(), observacionesDelCliente.getText(), 1);
            if (arregloDTO != null) {
                this.arreglos.add(arregloDTO);
                tblArreglos.setItems(arreglos);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Quedan campos por completar!");
            alert.showAndWait();
        }

    }

    public void setStageAnterior(Stage stageAdmin) {
        this.adminStage = stageAdmin;
    }

    public void volverAtras() {
        Stage stageActual = (Stage) atrasButton.getScene().getWindow();
        stageActual.close();
    }

    public void setUsuario(Usuario logueado) {
        this.logueado = logueado;
    }

}
