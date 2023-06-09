package com.example.main.controladores;

import com.example.main.DTOs.ArregloDTO;
import com.example.main.modelos.Mecanico;
import com.example.main.modelos.Usuario;
import com.example.main.servicios.MecanicoServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;

public class UserController {
    public MecanicoServiceImpl mecanicoService = new MecanicoServiceImpl();
    @FXML
    private Stage stageAnterior;
    private Stage userStage;
    @FXML
    private Button exitButton;
    @FXML
    private Button detalleButton;
    @FXML
    private TableView<ArregloDTO> listaTareas = new TableView<>();
    @FXML
    private TableColumn idArregloColumna;
    @FXML
    private TableColumn anioColumna;
    @FXML
    private TableColumn patenteColumna;
    @FXML
    private TableColumn marcaColumna;
    private  ObservableList<ArregloDTO> tareas = FXCollections.observableArrayList();
    private Usuario logueado;
    @FXML
    private Label idNombreUsuario = new Label();
    
    public void inicializar(Usuario actual)
    {

        initialize(actual);
    }
    @FXML
    public void initialize(Usuario actual)
    {
        inicializarListaDeTareas(actual);
    }
    public void verDetalle(ActionEvent event)
    {
        ArregloDTO elementoSeleccionado = listaTareas.getSelectionModel().getSelectedItem();
        if(elementoSeleccionado != null)
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/detalleView.fxml"));
                Parent root = loader.load();
                DetalleController detalleController = loader.getController();
                detalleController.setUsuario(logueado);
                detalleController.setVistaUsuariosController(this);
                detalleController.setStageAnterior(this.userStage);
                detalleController.inicializar(elementoSeleccionado,logueado);
                Stage detalleStage = new Stage();
                detalleStage.initStyle(StageStyle.UNDECORATED);
                detalleStage.initOwner(userStage);
                detalleStage.setScene(new Scene(root,800,600));
                detalleStage.showAndWait();
                userStage = (Stage) detalleButton.getScene().getWindow();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            mostrarAlerta("Debe seleccionar una tarea!");
        }
    }

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/loginView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        this.logueado = null;
        stage.show();
    }
    @FXML
    void closeCurrentWindow(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private void inicializarListaDeTareas(Usuario actual) {
        List<ArregloDTO> listTareas = mecanicoService.obtenerTareas(actual.getIdUsuario());
        tareas.addAll(listTareas);
        this.idArregloColumna.setCellValueFactory(new PropertyValueFactory<>("idArreglo"));
        this.marcaColumna.setCellValueFactory(new PropertyValueFactory<>("marca"));
        this.patenteColumna.setCellValueFactory(new PropertyValueFactory<>("patente"));
        this.anioColumna.setCellValueFactory(new PropertyValueFactory<>("anioFabricacion"));
        Mecanico mecanico = mecanicoService.buscarPorId(actual.getIdUsuario());
        idNombreUsuario.setText(mecanico.getNombre()+" "+mecanico.getApellido());
        listaTareas.setItems(tareas);
    }
    public void setUsuario(Usuario actual)
    {
        this.logueado = actual;
    }

    public void setStageAnterior(Stage stageAnterior)
    {
        this.stageAnterior = stageAnterior;
    }

    public void actualizarLista()
    {
        tareas.clear();
        List<ArregloDTO> listTareas = mecanicoService.obtenerTareas(logueado.getIdUsuario());
        tareas.addAll(listTareas);
        listaTareas.refresh();
    }
    private static void mostrarAlerta(String s)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(s);
        alert.showAndWait();
    }
    
}
