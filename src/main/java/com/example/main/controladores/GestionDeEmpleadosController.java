package com.example.main.controladores;

import com.example.main.DTOs.MecanicoDTO;
import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.enums.Especialidad;
import com.example.main.enums.TipoVehiculo;
import com.example.main.modelos.Cliente;
import com.example.main.modelos.Mecanico;
import com.example.main.modelos.Usuario;
import com.example.main.modelos.Vehiculo;
import com.example.main.servicios.MecanicoServiceImpl;
import com.example.main.servicios.UsuarioServiceImpl;
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

public class GestionDeEmpleadosController {
    private Mecanico mecanicoGlobal = new Mecanico();
    private UsuarioServiceImpl usuarioService = new UsuarioServiceImpl();
    private MecanicoServiceImpl mecanicoService = new MecanicoServiceImpl();
    @FXML
    private Stage stageAdmin;
    @FXML
    private Button atrasButton;
    private Usuario logueado;
    @FXML
    private Button modificarButton;
    @FXML
    private Button crearButton;
    @FXML
    private Button eliminarButton;
    @FXML
    private CheckBox mostrarTodos;
    @FXML
    private CheckBox especialidadElectricidad;
    @FXML
    private CheckBox especialidadEstetica;
    @FXML
    private CheckBox especialidadGeneral;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField dniField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField usuarioField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField txtBusquedaDNI;
    @FXML
    private TableView<MecanicoDTO> tblMecanicos;
    @FXML
    private TableColumn columnaNombre;
    @FXML
    private TableColumn columnaApellido;
    @FXML
    private TableColumn columnaDni;
    @FXML
    private TableColumn columnaTelefono;
    @FXML
    private ObservableList<MecanicoDTO> mecanicos;
    private ObservableList<MecanicoDTO> filtroMecanicos;
    @FXML
    private ChoiceBox<String> tipoVehiculo = new ChoiceBox<>();
    @FXML
    private ObservableList<String> opciones;

    public void initialize() {
        mecanicos = FXCollections.observableArrayList();
        filtroMecanicos = FXCollections.observableArrayList();

        if(mecanicoService.listar().size() !=0)
        {
            List<Mecanico> aux = mecanicoService.listar();
            mecanicos.addAll(mecanicoService.mecanicosToMecanicoDTO(aux));
            tblMecanicos.setItems(mecanicos);
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
                    this.mecanicoGlobal.setTipoVehiculo(TipoVehiculo.AUTO);

            } else if (newValue.equals("Camion"))
            {
                this.mecanicoGlobal.setTipoVehiculo(TipoVehiculo.CAMION);
            }
            else if(newValue.equals("Moto"))
            {
                this.mecanicoGlobal.setTipoVehiculo(TipoVehiculo.MOTO);
            }
        });


        especialidadEstetica.setOnAction(event -> {
            mecanicoGlobal.setEspecialidad(Especialidad.ESTETICA);
        });

        especialidadElectricidad.setOnAction(event -> {
            mecanicoGlobal.setEspecialidad(Especialidad.ELECTRICIDAD);
        });

        especialidadGeneral.setOnAction(event -> {
            mecanicoGlobal.setEspecialidad(Especialidad.MECANICA_GENERAL);
        });
    }

    @FXML
    private void agregarEmpleado(ActionEvent event) {

        boolean a = true;

        if (a != false)
        {

            MecanicoDTO nuevo = new MecanicoDTO(this.nombreField.getText(), this.apellidoField.getText(), this.dniField.getText(), this.telefonoField.getText(), TipoVehiculo.AUTO, Especialidad.ELECTRICIDAD,true);
            Mecanico aux = new Mecanico(nuevo.getNombre(),nuevo.getApellido(),nuevo.getDni(),nuevo.getNroTelefono(),nuevo.getListaArreglos(),nuevo.getTipoVehiculo(),nuevo.getEspecialidad());

            try
            {
                mecanicoService.agregar(aux);
                usuarioService.agregar(new Usuario(usuarioField.getText(), passwordField.getText(),false));
                this.mecanicos.add(nuevo);

            }
            catch (EntidadDuplicadaException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("El usuario ya existe");
                alert.showAndWait();
            }

            tblMecanicos.setItems(mecanicos);

            tipoVehiculo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
            {

                if (newValue.equals("Auto")) {
                    nuevo.setTipoVehiculo(TipoVehiculo.AUTO);

                } else if (newValue.equals("Camion")) {
                    nuevo.setTipoVehiculo(TipoVehiculo.CAMION);

                } else if (newValue.equals("Moto")) {
                    nuevo.setTipoVehiculo(TipoVehiculo.MOTO);
                }
            });

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Quedan campos por completar!");
            alert.showAndWait();
        }
    }

    @FXML
    private void seleccionarEmpleado(MouseEvent event) {

        if (mecanicos.size() != 0) {
            MecanicoDTO mecanicoDTO = this.tblMecanicos.getSelectionModel().getSelectedItem();

            if (mecanicoDTO != null) {
                this.nombreField.setText(mecanicoDTO.getNombre());
                this.apellidoField.setText(mecanicoDTO.getApellido());
                this.dniField.setText(mecanicoDTO.getDni());
                this.telefonoField.setText(mecanicoDTO.getNroTelefono());
                limpiarInputsMecanico();
                desbloquearInputsMecanico();
                bloquearInputsUsuario();
            }

        } else if (mecanicos.isEmpty()) {
            limpiarInputsMecanico();
            desbloquearInputsUsuario();
        }

    }

    private void limpiarInputsUsuario() {
        this.passwordField.setText("");
        this.usuarioField.setText("");
    }

    private void limpiarInputsMecanico() {

        this.apellidoField.setText("");
        this.nombreField.setText("");
        this.dniField.setText("");
        this.telefonoField.setText("");
    }

    private void bloquearInputsUsuario() {
        this.passwordField.setDisable(true);
        this.usuarioField.setDisable(true);
    }

    private void desbloquearInputsUsuario() {
        this.passwordField.setDisable(false);
        this.usuarioField.setDisable(false);
    }

    private void desbloquearInputsMecanico() {
        this.nombreField.setDisable(false);
        this.apellidoField.setDisable(false);
        this.dniField.setDisable(false);
        this.telefonoField.setDisable(false);
        this.tipoVehiculo.setDisable(false);
    }


    @FXML
    private void modificarEmpleado(ActionEvent event) {
        MecanicoDTO c = this.tblMecanicos.getSelectionModel().getSelectedItem();

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
                String dni = this.dniField.getText();
                String telefono = this.telefonoField.getText();
                limpiarInputsUsuario();
                limpiarInputsMecanico();
                desbloquearInputsUsuario();
                Cliente aux = new Cliente(nombre, apellido, dni, telefono, true);

                if (!this.mecanicos.contains(aux)) {
                    c.setNombre(aux.getNombre());
                    c.setApellido(aux.getApellido());
                    c.setDni(aux.getDni());
                    c.setNroTelefono(aux.getNroTelefono());
                    this.tblMecanicos.refresh();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("Debe seleccionar un cliente");
                    alert.showAndWait();
                }
            } catch (RuntimeException e) {

            }
        }
    }

    @FXML
    private void eliminarMecanico(ActionEvent event) {
        MecanicoDTO c = this.tblMecanicos.getSelectionModel().getSelectedItem();

        if (c == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un usuario");
            alert.showAndWait();
        } else if (mecanicos.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("La lista esta vacia");
            alert.showAndWait();
        } else {
            this.mecanicos.remove(c);
            this.filtroMecanicos.remove(c);
            this.tblMecanicos.refresh();
            desbloquearInputsUsuario();
        }

    }

    @FXML
    private void filtrarPorDNI(KeyEvent event) {
        String filtroDni = this.txtBusquedaDNI.getText();

        if (filtroDni.isEmpty()) {
            this.tblMecanicos.setItems(mecanicos);
        } else {
            this.filtroMecanicos.clear();

            for (MecanicoDTO cl : this.mecanicos) {
                if (cl.getDni().toLowerCase().contains(filtroDni.toLowerCase())) {
                    this.filtroMecanicos.add(cl);
                }
            }
            this.tblMecanicos.setItems(filtroMecanicos);
        }
    }

    @FXML
    private void filtrarActivoTodos(ActionEvent event) {
        if (!mostrarTodos.isSelected()) {
            for (MecanicoDTO cl : this.mecanicos) {
                if (cl.isEstado() && !filtroMecanicos.contains(cl) && !mecanicos.isEmpty()) {
                    this.filtroMecanicos.add(cl);
                }
            }
            this.tblMecanicos.setItems(filtroMecanicos);
            this.tblMecanicos.refresh();

        } else {
            this.tblMecanicos.setItems(mecanicos);
            this.tblMecanicos.refresh();
        }

    }


    public void setStageAnterior(Stage stageAdmin) {
        this.stageAdmin = stageAdmin;
    }

    public void volverAtras() {
        Stage stageActual = (Stage) atrasButton.getScene().getWindow();
        stageActual.close();
    }

    public void setUsuario(Usuario logueado) {
        this.logueado = logueado;
    }
}
