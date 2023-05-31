package com.example.main.controladores;

import com.example.main.modelos.Auto;
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
    private TableView<Auto> arreglos = new TableView<>();
    @FXML
    private TableColumn<Auto,Integer> IdClienteColumna;
    @FXML
    private TableColumn<Auto,Integer> IdVehiculoColumna;
    @FXML
    private TableColumn<Auto,String> MarcaColumna;
    @FXML
    private ObservableList<Auto> itemsList;
    private Stage userStage;
    @FXML
    private Button exitButton;
    @FXML
    private Button detalleButton;
    @FXML
    private Button cerrarSesion;
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
        IdClienteColumna.setCellValueFactory(cellData -> cellData.getValue().idClienteProperty().asObject());
        IdVehiculoColumna.setCellValueFactory(cellData -> cellData.getValue().idVehiculoProperty().asObject());
        MarcaColumna.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());

        // Obtener los com.example.main.datos de los autos (puedes reemplazar esto con tus propios com.example.main.datos)
        ObservableList<Auto> autosList =  FXCollections.observableArrayList();

        Auto auto1 = new Auto(1,1,"Volkswagen Gol GTI");
        Auto auto2 = new Auto(2,2,"Volkswagen Gol Power");
        Auto auto3 = new Auto(3,3,"Volkswagen Gol AB9");
        Auto auto4 = new Auto(4,4,"Volkswagen Gol Trend");

        autosList.add(auto1);
        autosList.add(auto2);
        autosList.add(auto3);
        autosList.add(auto4);

        arreglos.setItems(autosList);
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
        Auto elementoSeleccionado = arreglos.getSelectionModel().getSelectedItem();

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
                detalleStage.setScene(new Scene(root,640,480));
                detalleStage.show();
                userStage = (Stage) detalleButton.getScene().getWindow();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }
}
