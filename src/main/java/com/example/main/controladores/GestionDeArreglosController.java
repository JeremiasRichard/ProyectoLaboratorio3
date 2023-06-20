package com.example.main.controladores;

import com.example.main.DTOs.ArregloDTO;
import com.example.main.DTOs.MecanicoDTO;
import com.example.main.enums.Especialidad;
import com.example.main.enums.EstadoReparacion;
import com.example.main.enums.TipoVehiculo;
import com.example.main.modelos.*;
import com.example.main.servicios.ArregloServiceImpl;
import com.example.main.servicios.MecanicoServiceImpl;
import com.example.main.servicios.VehiculoServiceImpl;
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

    private VehiculoServiceImpl vehiculoService = new VehiculoServiceImpl();
    private ArregloServiceImpl arregloService = new ArregloServiceImpl();
    private MecanicoServiceImpl mecanicoService = new MecanicoServiceImpl();
    private Usuario logueado;
    private Vehiculo elegido = new Vehiculo();
    private Cliente seleccionado;
    private Especialidad especialidad;

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
    private ChoiceBox<String> listaEspecialidades = new ChoiceBox<>();
    @FXML
    private ObservableList<String> mecanicos;
    @FXML
    private ObservableList<ArregloDTO> arreglos = FXCollections.observableArrayList();
    private ObservableList<ArregloDTO> filtroArreglos = FXCollections.observableArrayList();
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
    private ObservableList<String> opciones2;

    public void initialize() {

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
            }
        });

        this.opciones2 = FXCollections.observableArrayList(
                "",
                "Estetica",
                "General",
                "Electricidad"
        );

        listaEspecialidades.setValue("");
        listaEspecialidades.setItems(opciones2);
        listaEspecialidades.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Electricidad")) {
                this.especialidad = Especialidad.ELECTRICIDAD;
                System.out.println(especialidad);
            } else if (newValue.equals("Estetica")) {
                this.especialidad = Especialidad.ESTETICA;
                System.out.println(especialidad);
            } else if (newValue.equals("General")) {
                this.especialidad = Especialidad.MECANICA_GENERAL;
                System.out.println(especialidad);
            }

            // Actualizar la lista de mecánicos
            if (elegido != null) {
                List<MecanicoDTO> listita = mecanicoService.listarMecanicoPorEspecialidadYTipo(elegido.getTipoVehiculo(), especialidad);
                mecanicos = FXCollections.observableArrayList();
                for (MecanicoDTO m : listita) {
                    mecanicos.add(m.getNombre());
                    System.out.println("ASD");
                }
                listaMecanicos.setItems(mecanicos);
            }
        });

        listaVehiculos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String seleccion = newValue;

            elegido = vehiculoService.buscarPorPatenteDos(seleccion);

            if (elegido != null) {
                // Comprobar si ya se ha seleccionado una especialidad y actualizar la lista de mecánicos
                if (especialidad != null) {
                    List<MecanicoDTO> listita = mecanicoService.listarMecanicoPorEspecialidadYTipo(elegido.getTipoVehiculo(), especialidad);
                    mecanicos = FXCollections.observableArrayList();
                    for (MecanicoDTO m : listita) {
                        mecanicos.add(m.getNombre());
                        System.out.println("ASD");
                    }
                    listaMecanicos.setItems(mecanicos);
                }
            }
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
            Vehiculo aux = vehiculoService.buscarPorPatenteDos(listaVehiculos.getSelectionModel().getSelectedItem());
            ArregloDTO arregloDTO = new ArregloDTO(1, listaVehiculos.getSelectionModel().getSelectedItem(), aux.getMarca(), aux.getTipoVehiculo(), 2000, this.seleccionado.getDni(), observacionesDelCliente.getText(), 1, this.especialidad);

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
