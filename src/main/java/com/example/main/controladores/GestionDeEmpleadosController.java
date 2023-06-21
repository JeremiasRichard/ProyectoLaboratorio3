package com.example.main.controladores;

import com.example.main.DTOs.MecanicoDTO;
import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.datos.excepciones.EntidadNoEncontradaException;
import com.example.main.enums.Especialidad;
import com.example.main.enums.TipoVehiculo;
import com.example.main.modelos.*;
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
            List<Mecanico> aux = mecanicoService.listarActivos();
            mecanicos.addAll(mecanicoService.mecanicosToMecanicoDTO(aux));
            tblMecanicos.setItems(mecanicos);
        }

        this.columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columnaApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
        this.columnaDni.setCellValueFactory(new PropertyValueFactory("dni"));
        this.columnaTelefono.setCellValueFactory(new PropertyValueFactory("nroTelefono"));

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
            especialidadElectricidad.setSelected(false);
            especialidadGeneral.setSelected(false);
        });

        especialidadElectricidad.setOnAction(event -> {
            mecanicoGlobal.setEspecialidad(Especialidad.ELECTRICIDAD);
            especialidadEstetica.setSelected(false);
            especialidadGeneral.setSelected(false);
        });

        especialidadGeneral.setOnAction(event -> {
            mecanicoGlobal.setEspecialidad(Especialidad.MECANICA_GENERAL);
            especialidadEstetica.setSelected(false);
            especialidadElectricidad.setSelected(false);
        });
    }

    @FXML
    private void agregarEmpleado(ActionEvent event) {

        boolean a = true;

        if (a != false)
        {

            MecanicoDTO nuevo = new MecanicoDTO(this.nombreField.getText(), this.apellidoField.getText(), this.dniField.getText(), this.telefonoField.getText(), this.mecanicoGlobal.getTipoVehiculo(), this.mecanicoGlobal.getEspecialidad(),true);
            Mecanico aux = new Mecanico(nuevo.getNombre(),nuevo.getApellido(),nuevo.getDni(),nuevo.getNroTelefono(),nuevo.getListaArreglos(),nuevo.getTipoVehiculo(),nuevo.getEspecialidad());

            try
            {
                mecanicoService.agregar(aux);
                usuarioService.agregar(new Usuario(usuarioField.getText(), passwordField.getText(),false));

            }
            catch (EntidadDuplicadaException e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("El usuario ya existe");
                alert.showAndWait();
            }
            mecanicos.clear();
            List<Mecanico> aux2 = mecanicoService.listarActivos();
            mecanicos.addAll(mecanicoService.mecanicosToMecanicoDTO(aux2));
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
                limpiarInputsUsuario();
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
        this.dniField.setDisable(true);
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

        MecanicoDTO mecanicoDTO = this.tblMecanicos.getSelectionModel().getSelectedItem();

        if (mecanicoDTO == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(mecanicoDTO.toString());
            alert.showAndWait();
        }
        else {
            try {

                MecanicoDTO nuevo = new MecanicoDTO(mecanicoDTO.getId(),this.nombreField.getText(), this.apellidoField.getText(), this.dniField.getText(), this.telefonoField.getText(), this.mecanicoGlobal.getTipoVehiculo(), this.mecanicoGlobal.getEspecialidad());
                Mecanico aux = new Mecanico(this.nombreField.getText(), this.apellidoField.getText(), this.dniField.getText(), this.telefonoField.getText(),new ArrayList<>(),this.mecanicoGlobal.getTipoVehiculo(),this.mecanicoGlobal.getEspecialidad());

                if (!this.mecanicos.contains(nuevo))
                {
                    mecanicoDTO.setNombre(nuevo.getNombre());
                    mecanicoDTO.setApellido(nuevo.getApellido());
                    mecanicoDTO.setDni(nuevo.getDni());
                    mecanicoDTO.setNroTelefono(nuevo.getNroTelefono());
                    mecanicoDTO.setTipoVehiculo(nuevo.getTipoVehiculo());
                    mecanicoDTO.setEspecialidad(nuevo.getEspecialidad());
                    mecanicoService.editar(aux);
                    this.tblMecanicos.refresh();
                    limpiarInputsMecanico();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText(mecanicoDTO.toString()+"\n"+nuevo.toString());
                    alert.showAndWait();
                }
            } catch (RuntimeException e) {

            } catch (EntidadNoEncontradaException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void eliminarMecanico(ActionEvent event) throws EntidadNoEncontradaException {

        MecanicoDTO c = this.tblMecanicos.getSelectionModel().getSelectedItem();

        if (c == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un mecanico");
            alert.showAndWait();
        } else if (mecanicos.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("La lista esta vacia");
            alert.showAndWait();
        } else {
            mecanicoService.eliminadoLogico(c.getDni());
            mecanicos.clear();
            List<Mecanico>  mecanicoList = mecanicoService.listarActivos();
            mecanicos.addAll(mecanicoService.mecanicosToMecanicoDTO(mecanicoList));
            this.tblMecanicos.setItems(mecanicos);
            this.tblMecanicos.refresh();
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

        if (!mostrarTodos.isSelected())
        {
            this.tblMecanicos.setItems(mecanicos);
            this.tblMecanicos.refresh();
        }
        else
        {
            filtroMecanicos.clear();
            filtroMecanicos.setAll(mecanicoService.listarMecanicosDTO());
            this.tblMecanicos.setItems(filtroMecanicos);
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
