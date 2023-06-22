package com.example.main.controladores;

import com.example.main.controladores.validaciones.Validaciones;
import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.datos.excepciones.EntidadNoEncontradaException;
import com.example.main.enums.TipoVehiculo;
import com.example.main.modelos.Cliente;
import com.example.main.modelos.Usuario;
import com.example.main.modelos.Vehiculo;
import com.example.main.servicios.ClienteServiceImpl;
import com.example.main.servicios.VehiculoServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GestionDeClientesController {
    private ClienteServiceImpl clienteService = new ClienteServiceImpl();
    private VehiculoServiceImpl vehiculoService = new VehiculoServiceImpl();
    private Vehiculo nuevoVehiculo = new Vehiculo();
    @FXML
    private Stage adminStage;
    @FXML
    private Button modificarButton;
    @FXML
    private Button crearButton;
    @FXML
    private Button eliminarButton;
    @FXML
    private Button atrasButton;
    @FXML
    private CheckBox mostrarTodos;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField dniField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField marcaField;
    @FXML
    private TextField anioFabricacionField;
    @FXML
    private TextField patenteField;
    @FXML
    private TextField txtBusquedaDNI;
    @FXML
    private TableView<Cliente> tblClientes;
    @FXML
    private TableColumn columnaNombre;
    @FXML
    private TableColumn columnaApellido;
    @FXML
    private TableColumn columnaDni;
    @FXML
    private TableColumn columnaTelefono;
    @FXML
    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();
    private ObservableList<Cliente> filtroClientes = FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<String> tipoVehiculo = new ChoiceBox<>();
    @FXML
    private ObservableList<String> opciones;
    private Usuario logueado;

    public void initialize() {
        inicializarTablaClientes();
        seleccionarTipoVehiculo();
    }

    public void setStageAnterior(Stage adminStage) {
        this.adminStage = adminStage;
    }

    public void volverAtras() {
        Stage stageActual = (Stage) atrasButton.getScene().getWindow();
        stageActual.close();
    }

    public void setUsuario(Usuario logueado) {
        this.logueado = logueado;
    }

    private void seleccionarTipoVehiculo() {
        this.opciones = FXCollections.observableArrayList(
                "",
                "Auto",
                "Moto",
                "Camion"
        );
        tipoVehiculo.setValue("");
        tipoVehiculo.setItems(opciones);
        tipoVehiculo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue.equals("Auto")) {
                this.nuevoVehiculo.setTipoVehiculo(TipoVehiculo.AUTO);

            } else if (newValue.equals("Camion")) {
                this.nuevoVehiculo.setTipoVehiculo(TipoVehiculo.CAMION);
            } else if (newValue.equals("Moto")) {
                this.nuevoVehiculo.setTipoVehiculo(TipoVehiculo.MOTO);
            }
        });
    }

    private void inicializarTablaClientes() {
        if (clienteService.listarActivos().size() != 0) {
            List<Cliente> aux = clienteService.listarActivos();
            for (Cliente cl : aux) {
                clientes.add(cl);
            }
            tblClientes.setItems(clientes);
        }
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columnaApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
        this.columnaDni.setCellValueFactory(new PropertyValueFactory("dni"));
        this.columnaTelefono.setCellValueFactory(new PropertyValueFactory("nroTelefono"));
    }

    @FXML
    private void agregarCliente(ActionEvent event) {
        if (verificarCampos()) {
            if (verificarNumeros())
            {
                Cliente nuevo2 = new Cliente(this.nombreField.getText(), this.apellidoField.getText(), this.dniField.getText(), this.telefonoField.getText(), new ArrayList<>());
                this.nuevoVehiculo = new Vehiculo(Integer.parseInt(this.anioFabricacionField.getText()), nuevoVehiculo.getTipoVehiculo(), this.marcaField.getText(), this.patenteField.getText());
                List<String> listaPatentes = new ArrayList<>();
                listaPatentes.add(nuevoVehiculo.getPatente());
                nuevo2.setListaVehiculos(listaPatentes);
                try {
                    if (!clientes.contains(nuevo2) && vehiculoService.buscarPorPatente(nuevoVehiculo.getPatente())) {
                        vehiculoService.agregar(nuevoVehiculo);
                        clienteService.agregar(nuevo2);
                        this.clientes.add(nuevo2);
                        tblClientes.setItems(clientes);
                    } else {
                        throw new EntidadDuplicadaException("El cliente o vehículo ya existen");
                    }
                } catch (EntidadDuplicadaException e) {
                    mostrarAlerta(e.getMessage());
                }
            } else {
                mostrarAlerta("Los campos DNI y Año solo admiten numeros!");
            }
        } else {
            mostrarAlerta("Quedan campos por completar");
        }
    }

    @FXML
    private void seleccionarCliente(MouseEvent event) {
        if (clientes.size() != 0) {
            Cliente cliente = this.tblClientes.getSelectionModel().getSelectedItem();
            if (cliente != null) {
                traerDeTabla(cliente);
                limpiarInputsVehiculo();
                bloquearInputsVehiculo();
            } else {
                mostrarAlerta("Debe seleccionar un cliente!");
            }
        } else if (clientes.isEmpty()) {
            limpiarInputsCliente();
            desbloquearInputsVehiculo();
        }
    }

    private void traerDeTabla(Cliente cliente) {
        this.nombreField.setText(cliente.getNombre());
        this.apellidoField.setText(cliente.getApellido());
        this.dniField.setText(cliente.getDni());
        this.dniField.setDisable(true);
        this.telefonoField.setText(cliente.getNroTelefono());
    }

    @FXML
    private void modificarCliente(ActionEvent event) {
        Cliente cliente = this.tblClientes.getSelectionModel().getSelectedItem();
        if (cliente == null) {
            mostrarAlerta("Debe seleccionar un cliente");
        } else {
            if (verificarCamposModificacion()) {
                try {
                    Cliente nuevo = new Cliente(this.nombreField.getText(), this.apellidoField.getText(), cliente.getDni(), this.telefonoField.getText(), cliente.getListaVehiculos());
                    if (this.clientes.contains(nuevo)) {
                        editarCliente(cliente, nuevo);
                        this.tblClientes.refresh();
                        resetearCampos();
                    } else {
                        mostrarAlerta("Debe hacer cambios");
                    }
                } catch (RuntimeException e) {

                } catch (EntidadNoEncontradaException e) {
                    throw new RuntimeException(e);
                }
            } else {
                mostrarAlerta("Todos los campos son requeridos");
            }
        }
    }

    @FXML
    private void eliminarCliente(ActionEvent event) throws EntidadNoEncontradaException {

        Cliente cliente = this.tblClientes.getSelectionModel().getSelectedItem();
        if (cliente == null) {
            mostrarAlerta("Debe seleccionar un cliente");
        } else if (clientes.size() == 0) {
            mostrarAlerta("La lista esta vacia");
        } else {
            bajaLogica(cliente);
            resetearCampos();
        }
    }

    @FXML
    private void filtrarPorNombre(KeyEvent event) {
        String filtroDni = this.txtBusquedaDNI.getText();
        if (filtroDni.isEmpty()) {
            this.tblClientes.setItems(clientes);
        } else {
            this.filtroClientes.clear();
            for (Cliente cl : this.clientes) {
                if (cl.getDni().toLowerCase().contains(filtroDni.toLowerCase())) {
                    this.filtroClientes.add(cl);
                }
            }
            this.tblClientes.setItems(filtroClientes);
        }
    }

    @FXML
    private void filtrarActivoTodos(ActionEvent event) {
        if (!mostrarTodos.isSelected()) {
            this.tblClientes.setItems(clientes);
            this.tblClientes.refresh();
        } else {
            filtroClientes.clear();
            filtroClientes.setAll(clienteService.listar());
            this.tblClientes.setItems(filtroClientes);
        }

    }

    private void bajaLogica(Cliente cliente) throws EntidadNoEncontradaException {
        clienteService.eliminadoLogico(cliente.getDni());
        clientes.clear();
        clientes.addAll(clienteService.listarActivos());
        this.tblClientes.setItems(clientes);
        this.tblClientes.refresh();
    }

    private void editarCliente(Cliente cliente, Cliente nuevo) throws EntidadNoEncontradaException {
        cliente.setNombre(nuevo.getNombre());
        cliente.setApellido(nuevo.getApellido());
        cliente.setDni(nuevo.getDni());
        cliente.setNroTelefono(nuevo.getNroTelefono());
        cliente.setListaVehiculos(nuevo.getListaVehiculos());
        cliente.setActivo(nuevo.isActivo());
        clienteService.editar(cliente);
    }

    private void resetearCampos() {
        limpiarInputsCliente();
        limpiarInputsVehiculo();
        desbloquearInputsVehiculo();
        desbloquearInputsCliente();
    }

    private void bloquearInputsVehiculo() {
        this.marcaField.setDisable(true);
        this.anioFabricacionField.setDisable(true);
        this.patenteField.setDisable(true);
        this.tipoVehiculo.setDisable(true);
    }

    private void desbloquearInputsVehiculo() {
        this.marcaField.setDisable(false);
        this.anioFabricacionField.setDisable(false);
        this.patenteField.setDisable(false);
        this.tipoVehiculo.setDisable(false);
        this.dniField.setText("");
        this.dniField.setDisable(false);
    }

    private void desbloquearInputsCliente() {
        this.apellidoField.setDisable(false);
        this.nombreField.setDisable(false);
        this.dniField.setDisable(false);
        this.telefonoField.setDisable(false);
        this.dniField.setDisable(false);
    }

    private void limpiarInputsVehiculo() {
        this.marcaField.setText("");
        this.anioFabricacionField.setText("");
        this.patenteField.setText("");
        this.tipoVehiculo.setValue("");
    }

    private void limpiarInputsCliente() {
        this.apellidoField.setText("");
        this.nombreField.setText("");
        this.dniField.setDisable(false);
        this.telefonoField.setText("");
        this.dniField.setText("");
    }

    private static void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean verificarNumeros() {
        return Validaciones.isNumero(this.dniField.getText()) && Validaciones.isNumero(this.anioFabricacionField.getText());
    }

    private boolean verificarCampos() {

        return Validaciones.isStringValido(this.nombreField.getText()) &&
                Validaciones.isStringValido(this.dniField.getText()) &&
                Validaciones.isStringValido(this.telefonoField.getText()) &&
                Validaciones.isStringValido(this.apellidoField.getText()) &&
                Validaciones.isStringValido(this.marcaField.getText()) &&
                Validaciones.isStringValido(this.anioFabricacionField.getText()) &&
                Validaciones.isStringValido(this.patenteField.getText()) &&
                this.nuevoVehiculo.getTipoVehiculo() != null;
    }

    private boolean verificarCamposModificacion() {

        return Validaciones.isStringValido(this.nombreField.getText()) &&
                Validaciones.isStringValido(this.dniField.getText()) &&
                Validaciones.isStringValido(this.telefonoField.getText()) &&
                Validaciones.isStringValido(this.apellidoField.getText());
    }
}
