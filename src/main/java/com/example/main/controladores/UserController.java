package com.example.main.controladores;

import com.example.main.enums.EstadoReparacion;
import com.example.main.modelos.Arreglo;
import com.example.main.modelos.Usuario;
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
    private TableView<Arreglo> ListaTareas = new TableView<>();
    @FXML
    private TableColumn IdArregloColumna;
    @FXML
    private TableColumn patenteColumna;
    @FXML
    private TableColumn MarcaColumna;
    private Stage userStage;
    @FXML
    private Button exitButton;
    @FXML
    private Button detalleButton;
    private  ObservableList<Arreglo> tareas;
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
    public void initialize(Usuario actual)
    {

        // Configurar las columnas
        this.IdArregloColumna.setCellValueFactory(new PropertyValueFactory("idArreglo"));
        this.patenteColumna.setCellValueFactory(new PropertyValueFactory("patente"));
        this.MarcaColumna.setCellValueFactory(new PropertyValueFactory("marca"));
        this.tareas = FXCollections.observableArrayList();

        Arreglo arreglo1 = new Arreglo(1, "vehiculoUno", 1, 1, "Detalla falla motor");
        Arreglo arreglo2 = new Arreglo(2, "vehiculoDos", 2, 1, "Detalla falla calefaccion");
        Arreglo arreglo3 = new Arreglo(3, "vehiculoTres", 3, 1, "Detalla falla direccion");

        tareas.add(arreglo1);
        tareas.add(arreglo2);
        tareas.add(arreglo3);

        idNombreUsuario.setText(actual.getUser().toString());

        ListaTareas.setItems(tareas);
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
        Arreglo elementoSeleccionado = ListaTareas.getSelectionModel().getSelectedItem();

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
