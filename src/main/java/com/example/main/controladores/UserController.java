package com.example.main.controladores;

import com.example.main.DTOs.ArregloDTO;
import com.example.main.enums.EstadoReparacion;
import com.example.main.enums.TipoVehiculo;
import com.example.main.modelos.Usuario;
import com.example.main.modelos.Vehiculo;
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

public class UserController {
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
    private  ObservableList<ArregloDTO> tareas;
    private Usuario logueado;
    @FXML
    private Label idNombreUsuario = new Label();

    public void setUsuario(Usuario actual)
    {
        this.logueado = actual;
    }

    public void setStageAnterior(Stage stageAnterior)
    {
        this.stageAnterior = stageAnterior;
    }

    @FXML
    void closeCurrentWindow(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }


    public void inicializar(Usuario actual)
    {
        initialize(actual);
    }
    @FXML
    public void initialize(Usuario actual)
    {

        //= mecanicoServiceImpl.obtenerTareas(actual.getIdUsuario());

        this.tareas = FXCollections.observableArrayList();

        this.idArregloColumna.setCellValueFactory(new PropertyValueFactory<>("idArreglo"));
        this.marcaColumna.setCellValueFactory(new PropertyValueFactory<>("marca"));
        this.patenteColumna.setCellValueFactory(new PropertyValueFactory<>("patente"));
        this.anioColumna.setCellValueFactory(new PropertyValueFactory<>("anioFabricacion"));

        Vehiculo vehiculo = new Vehiculo(2000, TipoVehiculo.AUTO,"VOLKSWAGEN GOL POWER","AJB942");

        ArregloDTO arreglo1 = new ArregloDTO(1, vehiculo.getPatente(), vehiculo.getMarca(),vehiculo.getAnioFabricacion(),"38829033","El cliente solicita un service completo", EstadoReparacion.STAND_BY);

        ArregloDTO arreglo2 = new ArregloDTO(2,"GGF082","FIAT IVECO DAYLI",2007,"38829033","El cliente solicita cambio de diferencial",EstadoReparacion.STAND_BY);

        tareas.add(arreglo1);

        tareas.add(arreglo2);

        idNombreUsuario.setText(actual.getUser().toString());

        listaTareas.setItems(tareas);
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

    public void verDetalle()
    {
        ArregloDTO elementoSeleccionado = listaTareas.getSelectionModel().getSelectedItem();

        if(elementoSeleccionado != null)
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/detalleView.fxml"));
                Parent root = loader.load();
                DetalleController detalleController = loader.getController();
                detalleController.setUsuario(logueado);
                detalleController.setStageAnterior(this.userStage);
                detalleController.inicializar(elementoSeleccionado,logueado);
                Stage detalleStage = new Stage();
                detalleStage.initStyle(StageStyle.UNDECORATED);
                detalleStage.initOwner(userStage);
                detalleStage.setScene(new Scene(root,800,600));
                detalleStage.show();
                userStage = (Stage) detalleButton.getScene().getWindow();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }
}
