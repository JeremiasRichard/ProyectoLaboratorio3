package com.example.main.controladores;

import com.example.main.datos.ClienteRepoImpl;
import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.enums.EstadoReparacion;
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
    private Vehiculo nuevoVehiculo;
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
    private  TextField txtBusquedaDNI;
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
    private ObservableList<Cliente> clientes;
    private ObservableList<Cliente> filtroClientes;
    @FXML
    private ChoiceBox<String> tipoVehiculo = new ChoiceBox<>();
    @FXML
    private ObservableList<String> opciones;
    private Usuario logueado;

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

    public void initialize() {

        clientes = FXCollections.observableArrayList();
        filtroClientes = FXCollections.observableArrayList();

        if(clienteService.listar().size() !=0)
        {
            List<Cliente> aux = clienteService.listar();
            for (Cliente cl : aux) {
                clientes.add(cl);
            }
            tblClientes.setItems(clientes);
        }

        this.columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columnaApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
        this.columnaDni.setCellValueFactory(new PropertyValueFactory("dni"));
        this.columnaTelefono.setCellValueFactory(new PropertyValueFactory("nroTelefono"));

        this.opciones = FXCollections.observableArrayList(
                "",
                " Auto",
                " Moto",
                " Camion"
        );
        tipoVehiculo.setValue("");
        tipoVehiculo.setItems(opciones);

        tipoVehiculo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {

            if (newValue.equals("Auto"))
            {
                this.nuevoVehiculo.setTipoVehiculo(TipoVehiculo.AUTO);

            } else if (newValue.equals("Camion"))
            {
                this.nuevoVehiculo.setTipoVehiculo(TipoVehiculo.AUTO);
            }
            else if(newValue.equals("Moto"))
            {
                this.nuevoVehiculo.setTipoVehiculo(TipoVehiculo.AUTO);
            }
        });


    }

    @FXML
    private void agregarCliente(ActionEvent event) {

        boolean a = true;

        if (a != false)
        {
            Cliente nuevo2 = new Cliente(this.nombreField.getText(), this.apellidoField.getText(), this.dniField.getText(), new ArrayList<Integer>(), this.telefonoField.getText(), new ArrayList<>());

            this.nuevoVehiculo = new Vehiculo(Integer.parseInt(this.anioFabricacionField.getText()),TipoVehiculo.AUTO, this.marcaField.getText(), this.patenteField.getText());

            List<String> listaPatentes = new ArrayList<>();

            listaPatentes.add(nuevoVehiculo.getPatente());

            nuevo2.setListaVehiculos(listaPatentes);

            try {

                clienteService.agregar(nuevo2);
                this.clientes.add(nuevo2);
                tblClientes.setItems(clientes);

            } catch (EntidadDuplicadaException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("El cliente ya existe");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Quedan campos por completar!");
            alert.showAndWait();
        }

    }
    @FXML
    private void seleccionarCliente(MouseEvent event)
    {

        if(clientes.size() != 0)
        {
            Cliente c = this.tblClientes.getSelectionModel().getSelectedItem();

            if(c != null)
            {
                this.nombreField.setText(c.getNombre());
                this.apellidoField.setText(c.getApellido());
                this.dniField.setText(c.getDni());
                this.dniField.setDisable(true);
                this.telefonoField.setText(c.getNroTelefono());
                limpiarInputsVehiculo();
                bloquearInputsVehiculo();
            }

        } else if (clientes.isEmpty())
        {
            limpiarInputsUsuario();
            desbloquearInputsVehiculo();
        }


    }

    private void limpiarInputsVehiculo() {
        this.marcaField.setText("");
        this.anioFabricacionField.setText("");
        this.patenteField.setText("");
        this.tipoVehiculo.setValue("");
    }

    private void limpiarInputsUsuario() {
        this.apellidoField.setText("");
        this.nombreField.setText("");
        this.dniField.setDisable(true);
        this.telefonoField.setText("");
    }

    @FXML
    private void modificarCliente(ActionEvent event)
    {
        Cliente c = this.tblClientes.getSelectionModel().getSelectedItem();

        if (c == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un cliente");
            alert.showAndWait();
        } else {
            try {

                String nombre = this.nombreField.getText();
                String apellido = this.apellidoField.getText();
                String telefono = this.telefonoField.getText();
                limpiarInputsUsuario();
                desbloquearInputsVehiculo();
                Cliente aux = new Cliente(nombre, apellido, telefono,c.getDni(), true);

                if (!this.clientes.contains(aux)) {
                    c.setNombre(aux.getNombre());
                    c.setApellido(aux.getApellido());
                    c.setNroTelefono(aux.getNroTelefono());
                    this.tblClientes.refresh();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("Debe seleccionar un cliente");
                    alert.showAndWait();
                }
            } catch (RuntimeException e)
            {

            }
        }
    }

    private void bloquearInputsVehiculo() {
        this.marcaField.setDisable(true);
        this.anioFabricacionField.setDisable(true);
        this.patenteField.setDisable(true);
        this.tipoVehiculo.setDisable(true);
    }

    private void desbloquearInputsVehiculo()
    {
        this.marcaField.setDisable(false);
        this.anioFabricacionField.setDisable(false);
        this.patenteField.setDisable(false);
        this.tipoVehiculo.setDisable(false);
        this.dniField.setText("");
        this.dniField.setDisable(false);
    }

    @FXML
    private void eliminarCliente(ActionEvent event)
    {
        Cliente c = this.tblClientes.getSelectionModel().getSelectedItem();

        if(c == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un usuario");
            alert.showAndWait();
        }
        else if(clientes.size() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("La lista esta vacia");
            alert.showAndWait();
        }
        else
        {
            this.clientes.remove(c);
            this.filtroClientes.remove(c);
            this.tblClientes.refresh();
        }

    }

    @FXML
    private void filtrarPorNombre(KeyEvent event)
    {
        String filtroDni = this.txtBusquedaDNI.getText();

        if(filtroDni.isEmpty())
        {
            this.tblClientes.setItems(clientes);
        }
        else {
            this.filtroClientes.clear();

            for(Cliente cl : this.clientes)
            {
                if(cl.getDni().toLowerCase().contains(filtroDni.toLowerCase()))
                {
                    this.filtroClientes.add(cl);
                }
            }
            this.tblClientes.setItems(filtroClientes);
        }
    }

    @FXML
    private void filtrarActivoTodos(ActionEvent event)
    {
        if (!mostrarTodos.isSelected())
        {
            for(Cliente cl: this.clientes)
            {
                if(cl.isActivo() && !filtroClientes.contains(cl) && !clientes.isEmpty())
                {
                    this.filtroClientes.add(cl);
                }
            }
            this.tblClientes.setItems(filtroClientes);
            this.tblClientes.refresh();

        }
        else
        {
            this.tblClientes.setItems(clientes);
            this.tblClientes.refresh();
        }

    }
}
