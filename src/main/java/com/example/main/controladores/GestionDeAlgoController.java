package com.example.main.controladores;

import com.example.main.modelos.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GestionDeAlgoController
{
    @FXML
    private Stage adminStage;
    @FXML
    private Button atrasButton;
    private Usuario logueado;

    public void setStageAnterior(Stage adminStage)
    {
        this.adminStage = adminStage;
    }
    public void volverAtras()
    {
        //cambiarAEstadoAnterior();
        Stage stageActual = (Stage) atrasButton.getScene().getWindow();
        stageActual.close();
    }
    public void setUsuario(Usuario logueado)
    {
        this.logueado = logueado;
    }
}
