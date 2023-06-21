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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    @FXML
    private Label errorLabel;
    private Usuario logueado;
    private Usuario actual;

    @FXML
    public void iniciarSesion()
    {
        String rutaArchivos = "src/main/resources/archivos/";
        String archivoMecanicos = rutaArchivos + "mecanicos.json";
        String archivoClientes = rutaArchivos + "clientes.json";
        String archivoVehiculos = rutaArchivos + "vehiculos.json";
        String archivoArreglos = rutaArchivos + "arreglos.json";

        // Verificar si los archivos ya existen
        boolean archivosExisten = Files.exists(Paths.get(archivoMecanicos))
                && Files.exists(Paths.get(archivoClientes))
                && Files.exists(Paths.get(archivoVehiculos))
                && Files.exists(Paths.get(archivoArreglos));

        if (!archivosExisten) {
            GeneradorArchivos.generarArchivos();
        }
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
            this.logueado = deseado;

            if(logueado !=  null)
            {
                // solo se va a verificar el nivel de acceso.
                if (this.logueado.isEsAdmin())
                {
                    // Acceso de administrador
                    abrirVistaAdmin(this.logueado);
                } else
                {
                    // Acceso de usuario normal
                    abrirVistaUsuario(this.logueado);
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
    private void abrirVistaAdmin(Usuario actual) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/vista/adminView.fxml"));
            Scene scene = new Scene(loader.load(),800,600);
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
            Scene scene = new Scene(loader.load(),800,600);
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
        this.logueado=null;
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