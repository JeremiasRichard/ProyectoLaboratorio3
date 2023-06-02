package com.example.main.controladores;

import com.example.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
    private Label errorLabel;


    @FXML
    public void iniciarSesion()
    {
        String user = userTextField.getText();
        String password = passwordField.getText();
        errorLabel.setVisible(false);

        if (user.equals("admin") && password.equals("admin")) {
            // Acceso de administrador
            abrirVistaAdmin();
        } else if (user.equals("usuario") && password.equals("usuario")) {
            // Acceso de usuario normal
            abrirVistaUsuario();
        } else {
            // Credenciales inválidas
            mostrarMensajeError(errorLabel);
        }
    }
    private void abrirVistaAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/adminView.fxml"));
            Scene scene = new Scene(loader.load(),800,600);
            this.primaryStage = (Stage) userTextField.getScene().getWindow();
            AdminController adminController = loader.getController();
            primaryStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVistaUsuario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/userView.fxml"));
            Scene scene = new Scene(loader.load(),800,600);
            this.primaryStage = (Stage) userTextField.getScene().getWindow();
            UserController userController = loader.getController();
            userController.setStageAnterior(this.primaryStage);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarMensajeError(Label errorLabel)
    {
       errorLabel.setText("Credenciales inválidas.Intenta nuevamente.");
       errorLabel.setTextFill(Color.RED);
       errorLabel.setVisible(true);
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