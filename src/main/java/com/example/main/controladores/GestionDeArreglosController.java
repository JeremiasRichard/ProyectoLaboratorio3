package com.example.main.controladores;

import com.example.main.DTOs.ArregloDTO;
import com.example.main.DTOs.MecanicoDTO;
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
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GestionDeArreglosController
{
    private Usuario logueado;
    @FXML
    private Stage adminStage;
    @FXML
    private Button atrasButton;
    @FXML
    private Button modificarButton;
    @FXML
    private TextField txtBusquedaDNI;
    @FXML
    private TextField txtBusquedaPatente;
    @FXML
    private  Button crearButton;
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

    public void initialize()
    {
        arreglos = FXCollections.observableArrayList();
        filtroArreglos = FXCollections.observableArrayList();

        observacionesDelCliente.setWrapText(true);

        this.columnaId.setCellValueFactory(new PropertyValueFactory("idArreglo"));
        this.columnaPatente.setCellValueFactory(new PropertyValueFactory("patente"));
        this.columnaEstadoReparacion.setCellValueFactory(new PropertyValueFactory("estadoReparacion"));

        List<String> patentes = new ArrayList<>();

        patentes.add("AJB942");
        patentes.add("GGF082");
        patentes.add("EAG419");
        this.vehiculos = FXCollections.observableArrayList(patentes);
        listaVehiculos.setValue("");
        listaVehiculos.setItems(vehiculos);

        List<String> mecanicos2 = new ArrayList<>();

        mecanicos2.add("Jorge");
        mecanicos2.add("Pedro");
        mecanicos2.add("Raul");

        this.mecanicos = FXCollections.observableArrayList(mecanicos2);

        listaMecanicos.setValue("");

        listaMecanicos.setItems(mecanicos);

    }

   /* @FXML
    private void crearArreglo(ActionEvent event) {

        boolean a = true;

        if(a != false)
        {

            ArregloDTO arregloDTO = new ArregloDTO(1,listaVehiculos.getSelectionModel().getSelectedItem().toString(),"volkswagen",2000,txtBusquedaDNI.getText(),observacionesDelCliente.getText(), EstadoReparacion.STAND_BY);

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

    }*/



    public void setStageAnterior(Stage stageAdmin)
    {
        this.adminStage = stageAdmin;
    }
    public void volverAtras()
    {
        Stage stageActual = (Stage) atrasButton.getScene().getWindow();
        stageActual.close();
    }

    public void setUsuario(Usuario logueado)
    {
        this.logueado = logueado;
    }

}
