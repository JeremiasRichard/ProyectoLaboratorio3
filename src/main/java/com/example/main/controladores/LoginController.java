package com.example.main.controladores;

import com.example.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginController {
    private  Stage primaryStage;
    @FXML
    private TextField userTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button exitButton;
    @FXML
    public void iniciarSesion()
    {
        String user = userTextField.getText();
        String password = passwordField.getText();

        if (user.equals("admin") && password.equals("admin")) {
            // Acceso de administrador
            abrirVistaAdmin();
        } else if (user.equals("usuario") && password.equals("usuario")) {
            // Acceso de usuario normal
            abrirVistaUsuario();
        } else {
            // Credenciales inválidas
            mostrarMensajeError("Credenciales inválidas");
        }
    }
    private void abrirVistaAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/adminView.fxml"));
            Scene scene = new Scene(loader.load(),640,480);
            this.primaryStage = (Stage) userTextField.getScene().getWindow();
            AdminController adminController = loader.getController();
            primaryStage.setScene(scene);
            //primaryStage.initStyle(StageStyle.UNDECORATED);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVistaUsuario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/userView.fxml"));
            Scene scene = new Scene(loader.load(),640,480);
            this.primaryStage = (Stage) userTextField.getScene().getWindow();
            UserController userController = loader.getController();
            userController.setStageAnterior(this.primaryStage);
            //primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarMensajeError(String mensaje) {
        System.out.println(mensaje);
    }
    @FXML
    void closeApplication(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public void show(ActionEvent event)
    {
        primaryStage.show();
    }



}