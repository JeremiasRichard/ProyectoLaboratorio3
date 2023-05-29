package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class LoginController {
    private  Stage stage;
    @FXML
    private TextField userTextField;
    @FXML
    private PasswordField passwordField;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.example.demo/vista/adminView.fxml"));
            Scene scene = new Scene(loader.load(),640,480);
            stage.setTitle("Admin");
            this.stage = (Stage) userTextField.getScene().getWindow();
            AdminController adminController = loader.getController();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVistaUsuario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.example.demo/vista/userView.fxml"));
            Scene scene = new Scene(loader.load(),640,480);
            stage.setTitle("Usuario");
            this.stage = (Stage) userTextField.getScene().getWindow();
            UserController userController = loader.getController();
            stage.setScene(scene);
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
        stage.close();
    }
    public void setStage(Stage primaryStage)
    {
        stage = primaryStage;
    }

    public void show(ActionEvent event)
    {
        stage.show();
    }
}