package com.example.main.controladores;

import com.example.main.modelos.Cliente;
import com.example.main.modelos.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SeleccionDeClienteEmergenteController
{
    @FXML
    private Button atrasButton;
    @FXML
    private Button seleccionarButton;
    private Usuario actual;
    private Cliente seleccionado;
    private Stage stageActual;
    private Stage anterior;
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
    private ObservableList<Cliente> clientes;
    private ObservableList<Cliente> filtroClientes;

    public void initialize()
    {
        clientes = FXCollections.observableArrayList();
        filtroClientes = FXCollections.observableArrayList();

        Cliente nuevo2 = new Cliente("jorge","la piedra","3213","4790789",true);
        List<String> patentes =  new ArrayList<>();
        patentes.add("AJB942");
        patentes.add("AJB943");
        patentes.add("AJB944");
        nuevo2.setListaVehiculos(patentes);
        clientes.add(nuevo2);
        tblClientes.setItems(clientes);
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columnaApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
        this.columnaDni.setCellValueFactory(new PropertyValueFactory("dni"));
        this.columnaTelefono.setCellValueFactory(new PropertyValueFactory("nroTelefono"));
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
    public void setUsuario(Usuario logueado)
    {
        this.actual = logueado;
    }

    public void setStageAnterior(Stage adminStage)
    {
        this.anterior=adminStage;
    }
    public void volverAtras() {
         this.stageActual = (Stage) atrasButton.getScene().getWindow();
         stageActual.close();
    }

    @FXML
    private void seleccionarCliente(MouseEvent event) {
        this.seleccionado = tblClientes.getSelectionModel().getSelectedItem();
        Seleccionar();
    }

    public Cliente Seleccionar() {

        Cliente a = new Cliente();
        if (this.seleccionado != null) {
            a = this.seleccionado;
            this.stageActual = (Stage) atrasButton.getScene().getWindow();
            stageActual.close();
        }
        return a;
    }
}
