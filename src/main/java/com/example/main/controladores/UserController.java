package com.example.main.controladores;

import com.example.main.DTOs.ArregloDTO;
import com.example.main.enums.Especialidad;
import com.example.main.enums.EstadoReparacion;
import com.example.main.enums.TipoVehiculo;
import com.example.main.modelos.Arreglo;
import com.example.main.DTOs.VehiculoDTO;
import com.example.main.modelos.Mecanico;
import com.example.main.modelos.Usuario;
import com.example.main.modelos.Vehiculo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class UserController {

    @FXML
    private TableView<ArregloDTO> ListaTareas = new TableView<>();
    @FXML
    private TableColumn<ArregloDTO,Integer> IdArregloColumna;
    @FXML
    private TableColumn<ArregloDTO, Integer> anioFabricacionColumna;
    @FXML
    private TableColumn<ArregloDTO,String> MarcaColumna;
    private Stage userStage;
    @FXML
    private Button exitButton;
    @FXML
    private Button detalleButton;
    @FXML
    private Stage stageAnterior;
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
    public void initialize(Usuario actual) {

        // Configurar las columnas

        IdArregloColumna.setCellValueFactory(cellData -> cellData.getValue().idArregloProperty().asObject());
        anioFabricacionColumna.setCellValueFactory(cellData -> cellData.getValue().getVehiculoDTO().añoFrabricacionProperty().asObject());
        MarcaColumna.setCellValueFactory(cellData -> cellData.getValue().getVehiculoDTO().marcaProperty());

        // Obtener los com.example.main.datos de los autos (puedes reemplazar esto con tus propios com.example.main.datos)
        ObservableList<ArregloDTO> ListaParaTabla = FXCollections.observableArrayList();
        Vehiculo vehiculoUno = new Vehiculo(1, 2005, TipoVehiculo.AUTO, "VolksWagen GOL POWER 1.6");
        Vehiculo vehiculoDos = new Vehiculo(2, 1995, TipoVehiculo.AUTO, "VolksWagen GOL G1 1.8");
        Vehiculo vehiculoTres = new Vehiculo(3, 2012, TipoVehiculo.AUTO, "VolksWagen GOL TREND");

        Arreglo arreglo1 = new Arreglo(1, vehiculoUno, 1, 1, "Detalla falla motor", EstadoReparacion.STAND_BY);
        Arreglo arreglo2 = new Arreglo(2, vehiculoDos, 2, 1, "Detalla falla calefaccion", EstadoReparacion.STAND_BY);
        Arreglo arreglo3 = new Arreglo(3, vehiculoTres, 3, 1, "Detalla falla direccion", EstadoReparacion.STAND_BY);

        ArregloDTO arregloDTO1 = new ArregloDTO();
        ArregloDTO arregloDTO2 = new ArregloDTO();
        ArregloDTO arregloDTO3 = new ArregloDTO();

        arregloDTO1.MapearATabla(arreglo1);
        arregloDTO2.MapearATabla(arreglo2);
        arregloDTO3.MapearATabla(arreglo3);

        ListaParaTabla.add(arregloDTO1);
        ListaParaTabla.add(arregloDTO2);
        ListaParaTabla.add(arregloDTO3);

        ObservableList<ArregloDTO> ListaParaTablaFinal = FXCollections.observableArrayList();

        idNombreUsuario.setText(actual.getUser().toString());

        for (ArregloDTO arreglo :ListaParaTabla)
        {
            if(arreglo.getEstadoReparacion() != EstadoReparacion.FINALIZADO)
            {
                ListaParaTablaFinal.add(arreglo);
            }
        }

        ListaTareas.setItems(ListaParaTablaFinal);
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
        ArregloDTO elementoSeleccionado = ListaTareas.getSelectionModel().getSelectedItem();

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
