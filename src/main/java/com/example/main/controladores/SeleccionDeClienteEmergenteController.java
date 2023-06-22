package com.example.main.controladores;

import com.example.main.modelos.Cliente;
import com.example.main.modelos.Usuario;
import com.example.main.servicios.ClienteServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SeleccionDeClienteEmergenteController {
    private ClienteServiceImpl clienteService = new ClienteServiceImpl();
    @FXML
    private Button atrasButton;
    private Usuario usuarioActual;
    private Cliente clienteSeleccionado;
    private Stage stageActual;
    private Stage stageAnterior;
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

    public void initialize() {
        inicializarTablaClientes();
    }

    private void inicializarTablaClientes() {
        List<Cliente> aux = clienteService.listar();
        List<String> patentes = new ArrayList<>();
        for (Cliente cliente : aux) {
            clientes.add(cliente);
            for (String s : cliente.getListaVehiculos()) {
                patentes.add(s);
            }
        }
        tblClientes.setItems(clientes);
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columnaApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
        this.columnaDni.setCellValueFactory(new PropertyValueFactory("dni"));
        this.columnaTelefono.setCellValueFactory(new PropertyValueFactory("nroTelefono"));
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

    public void setUsuario(Usuario logueado) {
        this.usuarioActual = logueado;
    }

    public void setStageAnterior(Stage adminStage) {
        this.stageAnterior = adminStage;
    }

    public void volverAtras() {
        if (this.clienteSeleccionado != null) {
            Stage stageActual = (Stage) atrasButton.getScene().getWindow();
            stageActual.close();
        }
        mostrarAlerta("Debe seleccionar un cliente");
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void seleccionarCliente(MouseEvent event) {
        this.clienteSeleccionado = tblClientes.getSelectionModel().getSelectedItem();
        Seleccionar();
    }

    public Cliente Seleccionar() {

        Cliente cliente = new Cliente();
        if (this.clienteSeleccionado != null) {
            cliente = this.clienteSeleccionado;
            this.stageActual = (Stage) atrasButton.getScene().getWindow();
            stageActual.close();
        }
        return cliente;
    }
}
