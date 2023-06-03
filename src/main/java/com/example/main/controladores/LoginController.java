package com.example.main.controladores;

import com.example.main.Main;
import com.example.main.controladores.validaciones.Validaciones;
import com.example.main.modelos.Usuario;
import com.example.main.servicios.LoginService;
import com.example.main.servicios.LoginServiceImpl;
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
        LoginService loginService = new LoginServiceImpl();

        String usuario = userTextField.getText();
        String contraseña = passwordField.getText();

        errorLabel.setVisible(false);

        if(Validaciones.isStringNull(usuario) || Validaciones.isStringNull(contraseña))
        {
            mostrarErrorCamposVacios(errorLabel);
        }
        else if(Validaciones.validarLogin(usuario,contraseña))
        {
            Usuario deseado = loginService.autenticar(usuario,contraseña);

            if(deseado !=  null)
            {
                // solo se va a verificar el nivel de acceso.
                if (deseado.isNivelDeAcceso())
                {
                    // Acceso de administrador
                    abrirVistaAdmin();
                } else
                {
                    // Acceso de usuario normal
                    abrirVistaUsuario();
                }
            }
            else {
                // Credenciales inválidas
                mostrarUsuarioOContraseñaInvalidos(errorLabel);
            }
        }
        else
        {   //Campos no alfanumericos detectados!
            mostrarErrorCamposInvalidos(errorLabel);
        }

    }
    private void abrirVistaAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/adminView.fxml"));
            Scene scene = new Scene(loader.load(),800,600);
            this.primaryStage = (Stage) userTextField.getScene().getWindow();
            AdminController adminController = loader.getController();
            //adminController.setStageAnterior(this.primaryStage);
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

    private void mostrarUsuarioOContraseñaInvalidos(Label errorLabel)
    {
       errorLabel.setText("Credenciales inválidas.Intenta nuevamente!");
       errorLabel.setTextFill(Color.RED);
       errorLabel.setVisible(true);
    }

    private void mostrarErrorCamposInvalidos(Label errorLabel)
    {
        errorLabel.setText("Solo se admiten caracteres alfanumericos!");
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(true);
    }

    private void mostrarErrorCamposVacios(Label errorLabel)
    {
        errorLabel.setText("El campo usuario o contraseña estan vacios!");
        errorLabel.setTextFill(Color.RED);
        errorLabel.setVisible(true);
    }

    @FXML
    void closeApplication(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void setStage(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

    public void show(ActionEvent event)
    {
        primaryStage.show();
    }



}