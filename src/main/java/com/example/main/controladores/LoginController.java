package com.example.main.controladores;

import com.example.main.controladores.validaciones.Validaciones;
import com.example.main.modelos.Usuario;
import com.example.main.servicios.LoginService;
import com.example.main.servicios.LoginServiceImpl;
import com.example.main.utils.GeneradorArchivos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class LoginController {
    private  Stage primaryStage;
    @FXML
    private TextField userTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button exitButton;
    private Usuario logueado;
    private Usuario actual;

    @FXML
    public void iniciarSesion() {
        LoginService loginService = new LoginServiceImpl();
        String usuario = userTextField.getText();
        String contraseña = passwordField.getText();

        if (Validaciones.isStringNull(usuario) || Validaciones.isStringNull(contraseña)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(mostrarErrorCamposVacios());
            alert.showAndWait();
        } else if (Validaciones.validarLogin(usuario, contraseña)) {
            Usuario deseado = loginService.autenticar(usuario, contraseña);
            this.logueado = deseado;

            if (logueado != null) {
                if (this.logueado.isEsAdmin()) {

                    abrirVistaAdmin(this.logueado);
                } else {

                    abrirVistaUsuario(this.logueado);
                }
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText(mostrarUsuarioOContraseñaInvalidos());
                alert.showAndWait();

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(mostrarErrorCamposInvalidos());
            alert.showAndWait();

        }

    }

    private void abrirVistaAdmin(Usuario actual) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/adminView.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);
            this.primaryStage = (Stage) userTextField.getScene().getWindow();
            AdminController adminController = loader.getController();
            adminController.setUsuario(actual);
            adminController.setStageAnterior(this.primaryStage);
            primaryStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void abrirVistaUsuario(Usuario actual) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/userView.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);
            this.primaryStage = (Stage) userTextField.getScene().getWindow();
            UserController userController = loader.getController();
            userController.inicializar(actual);
            userController.setUsuario(actual);
            userController.setStageAnterior(this.primaryStage);

            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String mostrarUsuarioOContraseñaInvalidos() {
        return "Credenciales inválidas" + "\n" + "Intenta nuevamente!";
    }

    private String mostrarErrorCamposInvalidos() {
        return "Solo se admiten caracteres alfanumericos!";
    }

    private String mostrarErrorCamposVacios() {
        return "El campo usuario o contraseña estan vacios!";
    }

    @FXML
    void closeApplication(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        this.logueado = null;
        stage.close();
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show(ActionEvent event) {
        primaryStage.show();
    }

}