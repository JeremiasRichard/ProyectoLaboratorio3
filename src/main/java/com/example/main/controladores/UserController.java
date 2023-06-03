package com.example.main.controladores;

import com.example.main.enums.TipoVehiculo;
import com.example.main.modelos.Arreglo;
import com.example.main.modelos.Auto;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class UserController {

    @FXML
    private TableView<Arreglo> ListaTareas = new TableView<>();
    @FXML
    private TableColumn<Arreglo,Integer> IdArregloColumna;
    @FXML
    private TableColumn<Arreglo,Integer> anioFabricacionColumna;
    @FXML
    private TableColumn<Arreglo,String> MarcaColumna;
    @FXML
    private ObservableList<Auto> itemsList;
    private Stage userStage;
    @FXML
    private Button exitButton;
    @FXML
    private Button detalleButton;
    @FXML
    private Stage stageAnterior;

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

    @FXML
    public void initialize() {
        // Configurar las columnas
        IdArregloColumna.setCellValueFactory(cellData -> cellData.getValue().getIdArreglo().asObject());
        anioFabricacionColumna.setCellValueFactory(cellData -> cellData.getValue().getVehiculo().getAnioFabricacion().asObject());
        MarcaColumna.setCellValueFactory(cellData -> cellData.getValue().getVehiculo().getMarca());

        // Obtener los com.example.main.datos de los autos (puedes reemplazar esto con tus propios com.example.main.datos)
        ObservableList<Arreglo> autosList =  FXCollections.observableArrayList();
        Vehiculo vehiculoUno = new Vehiculo(1,2005, TipoVehiculo.AUTO,"VolksWagen GOL POWER 1.6");
        Vehiculo vehiculoDos = new Vehiculo(2,1995, TipoVehiculo.AUTO,"VolksWagen GOL G1 1.8");
        Vehiculo vehiculoTres = new Vehiculo(3,2012, TipoVehiculo.AUTO,"VolksWagen GOL TREND");
        Vehiculo vehiculoCuatro = new Vehiculo(4,2008, TipoVehiculo.AUTO,"VolksWagen GOLF MK2");


        Arreglo arreglo1 = new Arreglo(1,vehiculoUno,1,1,"Detalla falla motor",false);
        Arreglo arreglo2 = new Arreglo(2,vehiculoDos,2,1,"Detalla falla calefaccion",false);
        Arreglo arreglo3 = new Arreglo(3,vehiculoTres,3,1,"Detalla falla direccion",false);
        Arreglo arreglo4 = new Arreglo(4,vehiculoCuatro,4,1,"Detalla falla direccion",false);

        autosList.add(arreglo1);
        autosList.add(arreglo2);
        autosList.add(arreglo3);
        autosList.add(arreglo4);

        ListaTareas.setItems(autosList);
    }

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/loginView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void verDetalle() {
        Arreglo elementoSeleccionado = ListaTareas.getSelectionModel().getSelectedItem();
        if(elementoSeleccionado != null)
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/detalleView.fxml"));
                Parent root = loader.load();
                DetalleController detalleController = loader.getController();
                detalleController.setStageAnterior(this.userStage);
                detalleController.inicializar(elementoSeleccionado);
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
