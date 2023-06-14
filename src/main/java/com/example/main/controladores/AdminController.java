package com.example.main.controladores;

import com.example.main.modelos.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AdminController
{

    /*@FXML
    private DatePicker datePicker;
    @FXML
    private Label fecha;*/
    @FXML
    private Button exitButton;
    @FXML
    private Stage stageLogin;
    @FXML
    private Button vistaSeleccionadaButton;
    private Usuario logueado;



    /*@FXML
    public void setearFecha(ActionEvent event) {
        String fechaString = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate fechaFormateada = LocalDate.parse(fechaString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        datePicker.setValue(fechaFormateada);
        fecha.setText(fechaFormateada.toString());

    }*/

    @FXML
    void closeApplication(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
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

    public void setStageAnterior(Stage stageLogin)
    {
        this.stageLogin = stageLogin;
    }

    public void cargarGestionDeEmpleados() {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/gestionDeEmpleadosView.fxml"));
                Parent root = loader.load();
                GestionDeEmpleadosController gestionDeEmpleadosController = loader.getController();
                gestionDeEmpleadosController.setUsuario(logueado);
                gestionDeEmpleadosController.setStageAnterior(this.stageLogin);
                Stage gestionDeEmpleadosStage = new Stage();
                gestionDeEmpleadosStage.initStyle(StageStyle.UNDECORATED);
                gestionDeEmpleadosStage.initOwner(stageLogin);
                gestionDeEmpleadosStage.setScene(new Scene(root,800,600));
                gestionDeEmpleadosStage.show();
                vistaSeleccionadaButton.getScene().getWindow();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
    }

    public void cargarGestionDeClientes() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/gestionDeClientesView.fxml"));
            Parent root = loader.load();
            GestionDeClientesController gestionDeClientesController = loader.getController();
            gestionDeClientesController.setUsuario(logueado);
            gestionDeClientesController.setStageAnterior(this.stageLogin);
            Stage gestionDeClientesStage = new Stage();
            gestionDeClientesStage.initStyle(StageStyle.UNDECORATED);
            gestionDeClientesStage.initOwner(stageLogin);
            gestionDeClientesStage.setScene(new Scene(root,800,600));
            gestionDeClientesStage.show();
            vistaSeleccionadaButton.getScene().getWindow();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void cargarGestionDeArreglos() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/gestionDeArreglosView.fxml"));
            Parent root = loader.load();
            GestionDeArreglosController gestionDeArreglosController = loader.getController();
            gestionDeArreglosController.setUsuario(logueado);
            gestionDeArreglosController.setStageAnterior(this.stageLogin);
            Stage gestionDeArreglosStage = new Stage();
            gestionDeArreglosStage.initStyle(StageStyle.UNDECORATED);
            gestionDeArreglosStage.initOwner(stageLogin);
            gestionDeArreglosStage.setScene(new Scene(root,800,600));
            gestionDeArreglosStage.show();
            vistaSeleccionadaButton.getScene().getWindow();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void cargarGestionDeAlgo() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/gestionDeAlgoView.fxml"));
            Parent root = loader.load();
            GestionDeAlgoController gestionDeAlgoController = loader.getController();
            gestionDeAlgoController.setUsuario(logueado);
            gestionDeAlgoController.setStageAnterior(this.stageLogin);
            Stage gestionDeAlgoStage = new Stage();
            gestionDeAlgoStage.initStyle(StageStyle.UNDECORATED);
            gestionDeAlgoStage.initOwner(stageLogin);
            gestionDeAlgoStage.setScene(new Scene(root,800,600));
            gestionDeAlgoStage.show();
            vistaSeleccionadaButton.getScene().getWindow();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setUsuario(Usuario logueado)
    {
        this.logueado = logueado;
    }
}
