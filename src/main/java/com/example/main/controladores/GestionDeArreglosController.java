package com.example.main.controladores;

import com.example.main.DTOs.ArregloDTO;
import com.example.main.DTOs.MecanicoDTO;
import com.example.main.controladores.validaciones.Validaciones;
import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.datos.excepciones.EntidadNoEncontradaException;
import com.example.main.enums.Especialidad;
import com.example.main.enums.EstadoReparacion;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GestionDeArreglosController {

    private VehiculoServiceImpl vehiculoService = new VehiculoServiceImpl();
    private ArregloServiceImpl arregloService = new ArregloServiceImpl();
    private MecanicoServiceImpl mecanicoService = new MecanicoServiceImpl();
    private Usuario logueado;
    private Vehiculo elegido = new Vehiculo();
    private Cliente seleccionado;
    private Integer idEmpleado;
    private Especialidad especialidad;
    @FXML
    private Stage adminStage;
    @FXML
    private TextField txtBusquedaPatente;
    @FXML
    private Button atrasButton;
    @FXML
    private Button seleccionClienteButton;
    @FXML
    private Button crearButton;
    @FXML
    private CheckBox mostrarTodos;
    @FXML
    private ChoiceBox<String> listaVehiculos = new ChoiceBox<>();
    @FXML
    private ObservableList<String> vehiculos;
    @FXML
    private ChoiceBox<Integer> listaMecanicos = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> listaEspecialidades = new ChoiceBox<>();
    @FXML
    private ObservableList<Integer> mecanicos;
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

        if (arregloService.listar().size() != 0) {
            arreglos.addAll(arregloService.listarArreglosDTO());
            tblArreglos.setItems(arreglos);
        }

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

            if (elegido != null) {
                List<MecanicoDTO> listita = mecanicoService.listarMecanicoPorEspecialidadYTipo(elegido.getTipoVehiculo(), especialidad);
                mecanicos = FXCollections.observableArrayList();
                for (MecanicoDTO m : listita) {
                    mecanicos.add(Integer.valueOf(m.getId()));
                }
                listaMecanicos.setItems(mecanicos);
            }
        });

        listaVehiculos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String seleccion = newValue;
            elegido = vehiculoService.buscarPorPatenteDos(seleccion);
            if (elegido != null) {
                if (especialidad != null) {
                    List<MecanicoDTO> listita = mecanicoService.listarMecanicoPorEspecialidadYTipo(elegido.getTipoVehiculo(), especialidad);

                    mecanicos = FXCollections.observableArrayList();
                    for (MecanicoDTO m : listita) {
                        mecanicos.add(Integer.valueOf(m.getId()));
                    }
                    listaMecanicos.setItems(mecanicos);
                }
            }
        });

        listaMecanicos.setOnAction(event -> {
            this.idEmpleado = listaMecanicos.getValue();
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
    private void crearArreglo(ActionEvent event) throws EntidadDuplicadaException {

        if (this.seleccionado != null) {
            if (verificarCampos()) {
                Vehiculo aux = vehiculoService.buscarPorPatenteDos(listaVehiculos.getSelectionModel().getSelectedItem());
                ArregloDTO arregloDTO = new ArregloDTO(listaVehiculos.getSelectionModel().getSelectedItem(), aux.getMarca(), aux.getTipoVehiculo(), aux.getAnioFabricacion(), this.seleccionado.getDni(), observacionesDelCliente.getText(), this.idEmpleado, this.especialidad);
                Arreglo arreglo = new Arreglo(aux.getPatente(), this.seleccionado.getDni(), this.idEmpleado, observacionesDelCliente.getText());

                if (arregloDTO != null) {
                    this.arreglos.add(arregloDTO);
                    this.arreglos.clear();
                    arregloService.agregar(arreglo);
                    if (arregloService.listar().size() != 0) {
                        List<Arreglo> aux2 = arregloService.listar();
                        for (Arreglo arreglo2 : aux2) {
                            if (!arreglos.contains(arreglo2)) {
                                arreglos.add(arregloService.convertirAArregloDTO(arreglo2));
                            }
                        }
                        tblArreglos.setItems(arreglos);
                    }
                }
            } else {
                mostrarAlerta("Todos los campos son requeridos");
            }
        } else {
            mostrarAlerta("Debe seleccionar un cliente");
        }
    }

    private static void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean verificarCampos() {
        return Validaciones.isStringValido(listaVehiculos.getSelectionModel().getSelectedItem()) &&
                Validaciones.isStringValido(listaEspecialidades.getSelectionModel().getSelectedItem()) &&
                Validaciones.isStringValido(observacionesDelCliente.getText()) &&
                Validaciones.isStringValido(this.idEmpleado.toString());
    }

    @FXML
    private void filtrarPorPatente(KeyEvent event) {
        String filtroPatente = this.txtBusquedaPatente.getText();

        if (filtroPatente.isEmpty()) {
            this.tblArreglos.setItems(arreglos);
        } else {
            this.filtroArreglos.clear();

            for (ArregloDTO cl : this.arreglos) {
                if (cl.getPatente().toLowerCase().contains(filtroPatente.toLowerCase())) {
                    this.filtroArreglos.add(cl);
                }
            }
            this.tblArreglos.setItems(filtroArreglos);
        }
    }

    @FXML
    private void filtrarActivoTodos(ActionEvent event) {

        if (!mostrarTodos.isSelected())
        {
            arreglos.clear();
            List<ArregloDTO> aux = arregloService.arreglosToArreglosDTO(arregloService.listarActivos());
            arreglos.addAll(aux);
            this.tblArreglos.setItems(arreglos);
            this.tblArreglos.refresh();

        } else
        {
            arreglos.clear();
            List<ArregloDTO> aux = arregloService.arreglosToArreglosDTO(arregloService.listar());
            arreglos.addAll(aux);
            this.tblArreglos.setItems(arreglos);
            this.tblArreglos.refresh();
        }
    }

    @FXML
    private void eliminarArreglo(ActionEvent event) throws EntidadNoEncontradaException {
        ArregloDTO c = this.tblArreglos.getSelectionModel().getSelectedItem();

        if (c == null) {
            mostrarAlerta("Debe seleccionar un arreglo");

        } else if (arreglos.size() == 0) {
            mostrarAlerta("La lista está vacia");
        } else
        {
            arregloService.eliminadoLogico(c.getIdArreglo());
            actualizarLista();
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

    public void actualizarLista()
    {
        arreglos.clear();
        arreglos.addAll(arregloService.listarArreglosDTO());
        tblArreglos.refresh();
    }

}
