package com.example.main.controladores;

import com.example.main.DTOs.ArregloDTO;
import com.example.main.datos.excepciones.EntidadNoEncontradaException;
import com.example.main.enums.EstadoReparacion;
import com.example.main.modelos.Arreglo;
import com.example.main.modelos.Mecanico;
import com.example.main.modelos.Usuario;
import com.example.main.servicios.ArregloServiceImpl;
import com.example.main.servicios.MecanicoServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class DetalleController
{
    ArregloServiceImpl arregloService = new ArregloServiceImpl();
    UserController userController = new UserController();
    MecanicoServiceImpl mecanicoService = new MecanicoServiceImpl();
    private ArregloDTO arregloDTO;
    @FXML
    private Button atrasButton;
    @FXML
    private Button guardarButton;
    @FXML
    private Stage stageAnterior;
    @FXML
    private  Label DniCliente = new Label();
    @FXML
    private Label Patente = new Label();
    @FXML
    private Label Marca = new Label();
    @FXML
    private Label DetallesDeFalla = new Label();
    @FXML
    private ChoiceBox<String> EstadoR = new ChoiceBox<>();
    private EstadoReparacion estadoAnterior;
    @FXML
    private Label idNombreUsuario = new Label();
    private Usuario logueado;
    @FXML
    private TextArea observacionesDelArregloField = new TextArea();

    public void initialize(ArregloDTO arreglo, Usuario logueado)
    {
        this.arregloDTO=arreglo;
        Mecanico actual = mecanicoService.buscarPorId(logueado.getIdUsuario());
        idNombreUsuario.setText(actual.getNombre()+" "+actual.getApellido());

        ObservableList<String> opciones = FXCollections.observableArrayList(
                "Stand By",
                "En proceso",
                "Finalizado"
        );
        EstadoR.setItems(opciones);

        estadoAnterior = this.arregloDTO.getEstadoReparacion();

        arregloDTO.setObservacionesMecanico(observacionesDelArregloField.getText());

        if(arregloDTO.getEstadoReparacion() == EstadoReparacion.STAND_BY)
            EstadoR.setValue("Stand by");
        else if(arregloDTO.getEstadoReparacion() == EstadoReparacion.EN_PROCESO)
        {
            EstadoR.setValue("En proceso");
        }
        else {
            EstadoR.setValue("Finalizado");
            arregloDTO.setObservacionesMecanico(observacionesDelArregloField.getText());
        }

        EstadoR.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {

            if (newValue.equals("En proceso"))
            {
                estadoAnterior = arregloDTO.getEstadoReparacion();
                arregloDTO.setEstadoReparacion(EstadoReparacion.EN_PROCESO);
            } else if (newValue.equals("Finalizado"))
            {
                estadoAnterior = arregloDTO.getEstadoReparacion();
                arregloDTO.setEstadoReparacion(EstadoReparacion.FINALIZADO);

            }
        });

    }
    public void inicializar(ArregloDTO ListaTareas, Usuario logueado)
    {
        initialize(ListaTareas,logueado);

        this.Patente.setText(ListaTareas.getPatente());

        this.DniCliente.setText(ListaTareas.getDniCliente());

        this.Marca.setText(ListaTareas.getMarca());

        this.DetallesDeFalla.setText(ListaTareas.getObservacionesCliente());

        observacionesDelArregloField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.TAB)
            {
                event.consume();
                EstadoR.requestFocus();
            }
        });

    }

    public void setStageAnterior(Stage stageAnterior) {
        this.stageAnterior = stageAnterior;
    }

    @FXML
    private void volverAtras()
    {
        Stage stageActual = (Stage) atrasButton.getScene().getWindow();
        stageActual.close();
    }

    @FXML
    private void guardarCambios(ActionEvent event) throws EntidadNoEncontradaException {

        if (this.arregloDTO.getEstadoReparacion() == EstadoReparacion.STAND_BY && this.arregloDTO.getObservacionesMecanico() == null)
        {
            mostrarAlerta("Error, estado inválido o campo observación vacío!");
        } else
        {
            arregloDTO.setObservacionesMecanico(observacionesDelArregloField.getText());
            Arreglo nuevo = new Arreglo(arregloDTO.getIdArreglo(),arregloDTO.getPatente(),arregloDTO.getDniCliente(),arregloDTO.getIdEmpleado(),arregloDTO.getObservacionesCliente(),arregloDTO.getObservacionesMecanico(),arregloDTO.getEstadoReparacion());
            arregloService.editar(nuevo);
            userController.actualizarLista();
            Stage stageActual = (Stage) atrasButton.getScene().getWindow();
            stageActual.close();
        }
    }

    private static void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void setUsuario(Usuario logueado)
    {
        this.logueado = logueado;
    }

    public void setVistaUsuariosController(UserController userController)
    {
        this.userController=userController;
    }


}
