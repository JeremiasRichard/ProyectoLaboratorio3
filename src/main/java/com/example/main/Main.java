package com.example.main;

import com.example.main.controladores.LoginController;
import com.example.main.utils.GeneradorArchivos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/main/vista/loginView.fxml"));
        Scene scene = new Scene(loader.load(), 800, 600);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        LoginController controller = loader.getController();
        controller.setStage(primaryStage);
        primaryStage.show();
        generarArchivos();
    }

    private static void generarArchivos() {
        String rutaArchivos = "src/main/resources/archivos/";
        String archivoMecanicos = rutaArchivos + "mecanicos.json";
        String archivoClientes = rutaArchivos + "clientes.json";
        String archivoVehiculos = rutaArchivos + "vehiculos.json";
        String archivoArreglos = rutaArchivos + "arreglos.json";

        boolean archivosExisten = Files.exists(Paths.get(archivoMecanicos))
                && Files.exists(Paths.get(archivoClientes))
                && Files.exists(Paths.get(archivoVehiculos))
                && Files.exists(Paths.get(archivoArreglos));

        if (!archivosExisten) {
            GeneradorArchivos.generarArchivos();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}