package com.example.main.controladores;

import com.example.main.enums.EstadoReparacion;
import com.example.main.enums.TipoVehiculo;
import com.example.main.modelos.Cliente;
import com.example.main.modelos.Usuario;
import com.example.main.modelos.Vehiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GestionDeClientesController {

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

    public void initialize()
    {
        clientes = FXCollections.observableArrayList();

        this.columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columnaApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
        this.columnaDni.setCellValueFactory(new PropertyValueFactory("dni"));
        this.columnaTelefono.setCellValueFactory(new PropertyValueFactory("nroTelefono"));

        this.opciones = FXCollections.observableArrayList(
                " Auto",
                " Moto",
                " Camion"
        );
        tipoVehiculo.setItems(opciones);

    }

    @FXML
    private void agregarCliente(ActionEvent event) {

        boolean a = true;
        if(a != false)
        {

            Cliente nuevo = new Cliente(this.nombreField.getText(),this.apellidoField.getText(),this.dniField.getText(),this.telefonoField.getText());

            Vehiculo nuevoVehiculo = new Vehiculo(Integer.parseInt(this.anioFabricacionField.getText()), TipoVehiculo.AUTO,this.marcaField.getText(),this.patenteField.getText());

            List<String> listaPatentes = new ArrayList<>();

            listaPatentes.add(nuevoVehiculo.getPatente());

            nuevo.setListaVehiculos(listaPatentes);

            this.clientes.add(nuevo);
            tblClientes.setItems(clientes);

            tipoVehiculo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
            {

                if (newValue.equals("Auto"))
                {
                    nuevoVehiculo.setTipoVehiculo(TipoVehiculo.AUTO);

                } else if (newValue.equals("Camion"))
                {
                    nuevoVehiculo.setTipoVehiculo(TipoVehiculo.CAMION);

                }
                else if(newValue.equals("Moto"))
                {
                    nuevoVehiculo.setTipoVehiculo(TipoVehiculo.MOTO);
                }
            });

        }
        else
        {
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
                this.telefonoField.setText(c.getNroTelefono());
            }

        }


    }
    @FXML
    private void modificarCliente(ActionEvent event)
    {
        Cliente c = this.tblClientes.getSelectionModel().getSelectedItem();

        if(c == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un cliente");
            alert.showAndWait();
        }
        else
        {
            try {

               String nombre = this.nombreField.getText();
               String apellido = this.apellidoField.getText();
               String dni = this.dniField.getText();
               String telefono = this.telefonoField.getText();

               Cliente aux = new Cliente(nombre,apellido,dni,telefono);

                if(!this.clientes.contains(aux))
                {
                    c.setNombre(aux.getNombre());
                    c.setApellido(aux.getApellido());
                    c.setDni(aux.getDni());
                    c.setNroTelefono(aux.getNroTelefono());

                    this.tblClientes.refresh();
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("Debe seleccionar un cliente");
                    alert.showAndWait();
                }
            }
            catch (RuntimeException e)
            {

            }
        }
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
            this.tblClientes.refresh();
        }

    }
}
