package com.example.main.controladores;

import com.example.main.DTOs.MecanicoDTO;
import com.example.main.controladores.validaciones.Validaciones;
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
    private TableColumn columnaTipoDeMecanica;
    @FXML
    private TableColumn columnaEspecialidad;
    @FXML
    private TableColumn columnaDni;
    @FXML
    private ObservableList<MecanicoDTO> mecanicos = FXCollections.observableArrayList();;
    private ObservableList<MecanicoDTO> filtroMecanicos = FXCollections.observableArrayList();;
    @FXML
    private ChoiceBox<String> tipoVehiculo = new ChoiceBox<>();
    @FXML
    private ObservableList<String> opciones;

    public void initialize() {

        if(mecanicoService.listar().size() !=0)
        {
            mecanicos.addAll(mecanicoService.listarActivos());
            tblMecanicos.setItems(mecanicos);
        }
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columnaApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
        this.columnaTipoDeMecanica.setCellValueFactory(new PropertyValueFactory("tipoVehiculo"));
        this.columnaEspecialidad.setCellValueFactory(new PropertyValueFactory("especialidad"));
        this.columnaDni.setCellValueFactory(new PropertyValueFactory("dni"));
        
        cargarTablaEspecialidades();
        seleccionarRamaReparacion();
    }

    private void seleccionarRamaReparacion() {

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

    private void cargarTablaEspecialidades() {
        this.opciones = FXCollections.observableArrayList(
                "",
                "AUTO",
                "MOTO",
                "CAMION"
        );
        tipoVehiculo.setValue("");
        tipoVehiculo.setItems(opciones);

        tipoVehiculo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {

            if (newValue.equals("AUTO"))
            {
                    this.mecanicoGlobal.setTipoVehiculo(TipoVehiculo.AUTO);

            } else if (newValue.equals("CAMION"))
            {
                this.mecanicoGlobal.setTipoVehiculo(TipoVehiculo.CAMION);
            }
            else if(newValue.equals("MOTO"))
            {
                this.mecanicoGlobal.setTipoVehiculo(TipoVehiculo.MOTO);
            }
        });
    }

    @FXML
    private void agregarEmpleado(ActionEvent event) {

        if (verificarCampos()) {
            MecanicoDTO nuevo = new MecanicoDTO(this.nombreField.getText(), this.apellidoField.getText(), this.dniField.getText(), this.telefonoField.getText(), this.mecanicoGlobal.getTipoVehiculo(), this.mecanicoGlobal.getEspecialidad(), true);
            Mecanico aux = new Mecanico(nuevo.getNombre(), nuevo.getApellido(), nuevo.getDni(), nuevo.getNroTelefono(), nuevo.getListaArreglos(), nuevo.getTipoVehiculo(), nuevo.getEspecialidad());

            try {
                mecanicoService.agregar(aux);
                usuarioService.agregar(new Usuario(usuarioField.getText(), passwordField.getText(), false));

            } catch (EntidadDuplicadaException e) {
                mostrarAlerta("El usuario ya existe");
            }
            mecanicos.clear();
            mecanicos.addAll(mecanicoService.listarActivos());
            tblMecanicos.setItems(mecanicos);

            cargarTablaEspecialidades();

        } else {
            mostrarAlerta("Quedan campos por completar");
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
                this.tipoVehiculo.setValue(mecanicoDTO.getTipoVehiculo().toString());
                mecanicoGlobal.setTipoVehiculo(mecanicoDTO.getTipoVehiculo());

                if(mecanicoDTO.getEspecialidad() == Especialidad.ELECTRICIDAD)
                {
                    mecanicoGlobal.setEspecialidad(Especialidad.ELECTRICIDAD);
                    especialidadElectricidad.setSelected(true);
                    especialidadEstetica.setSelected(false);
                    especialidadGeneral.setSelected(false);
                }
                else if (mecanicoDTO.getEspecialidad() == Especialidad.ESTETICA)
                {
                    mecanicoGlobal.setEspecialidad(Especialidad.ESTETICA);
                    especialidadEstetica.setSelected(true);
                    especialidadElectricidad.setSelected(false);
                    especialidadGeneral.setSelected(false);
                }
                else if(mecanicoDTO.getEspecialidad() == Especialidad.MECANICA_GENERAL)
                {
                    mecanicoGlobal.setEspecialidad(Especialidad.MECANICA_GENERAL);
                    especialidadGeneral.setSelected(true);
                    especialidadElectricidad.setSelected(false);
                    especialidadEstetica.setSelected(false);
                }

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
        this.dniField.setDisable(false);
        this.telefonoField.setText("");
        this.tipoVehiculo.setValue("");
        this.especialidadEstetica.setSelected(false);
        this.especialidadElectricidad.setSelected(false);
        this.especialidadGeneral.setSelected(false);

    }

    private void bloquearInputsUsuario() {
        this.passwordField.setDisable(true);
        this.usuarioField.setDisable(true);
        this.dniField.setDisable(true);
        this.especialidadGeneral.setDisable(true);
        this.especialidadElectricidad.setDisable(true);
        this.especialidadEstetica.setDisable(true);
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

        MecanicoDTO mecanicoSeleccionado = this.tblMecanicos.getSelectionModel().getSelectedItem();

        if (mecanicoSeleccionado == null)
        {
            mostrarAlerta("Debe seleccionar un mecanico");
        }
        else {
            if(verificarCampos())
            {
                bloquearInputsUsuario();
                try {

                    MecanicoDTO auxiliar = new MecanicoDTO(mecanicoSeleccionado.getId(),this.nombreField.getText(), this.apellidoField.getText(), this.dniField.getText(), this.telefonoField.getText(), this.mecanicoGlobal.getTipoVehiculo(), this.mecanicoGlobal.getEspecialidad());
                    Mecanico paraPersistir = new Mecanico(this.nombreField.getText(), this.apellidoField.getText(), this.dniField.getText(), this.telefonoField.getText(),new ArrayList<>(),this.mecanicoGlobal.getTipoVehiculo(),this.mecanicoGlobal.getEspecialidad());

                    if (!this.mecanicos.contains(auxiliar))
                    {
                        mecanicoSeleccionado.setId(mecanicoSeleccionado.getId());
                        mecanicoSeleccionado.setNombre(auxiliar.getNombre());
                        mecanicoSeleccionado.setApellido(auxiliar.getApellido());
                        mecanicoSeleccionado.setDni(auxiliar.getDni());
                        mecanicoSeleccionado.setNroTelefono(auxiliar.getNroTelefono());
                        mecanicoSeleccionado.setTipoVehiculo(auxiliar.getTipoVehiculo());
                        paraPersistir.setIdEmpleado(mecanicoSeleccionado.getId());
                        paraPersistir.setIdUsuario(mecanicoSeleccionado.getId());
                        mecanicoService.editar(paraPersistir);
                        this.tblMecanicos.refresh();
                        limpiarInputsMecanico();
                    }
                    else
                    {

                        mostrarAlerta("Debe hacer algun cambio!");
                    }
                } catch (RuntimeException e) {

                } catch (EntidadNoEncontradaException e) {
                    throw new RuntimeException(e);
                }
            }
            else
            {
                mostrarAlerta("Todos los campos son requeridos");
            }

        }
    }

    @FXML
    private void eliminarMecanico(ActionEvent event) throws EntidadNoEncontradaException {

        MecanicoDTO c = this.tblMecanicos.getSelectionModel().getSelectedItem();
        if (c == null) {
            mostrarAlerta("Debe seleccionar un mecanico");
        } else if (mecanicos.size() == 0) {
            mostrarAlerta("La lista esta vacia");
        } else {
            mecanicoService.eliminadoLogico(c.getDni());
            mecanicos.clear();
            mecanicos.addAll(mecanicoService.listarActivos());
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


    private static void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean verificarCampos()
    {
        return Validaciones.isStringValido(this.nombreField.getText()) &&
                Validaciones.isStringValido(this.dniField.getText()) &&
                Validaciones.isStringValido(this.telefonoField.getText()) &&
                Validaciones.isStringValido(this.apellidoField.getText()) &&
                this.mecanicoGlobal.getTipoVehiculo() != null;
    }
}
