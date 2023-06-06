package com.example.main.modelos;

import com.example.main.enums.TipoVehiculo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vehiculo {
    private int IdVehiculo;
    private int AnioFabricacion;
    private TipoVehiculo TipoVehiculo;
    private String Marca;

    public Vehiculo(){}
    public Vehiculo(int idVehiculo, int anioFabricacion, TipoVehiculo tipoVehiculo, String marca) {
        IdVehiculo = idVehiculo;
        AnioFabricacion =  anioFabricacion;
        TipoVehiculo = tipoVehiculo;
        Marca = marca;
    }
    //region Getters y Setters
    public int getIdVehiculo() {
        return IdVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        IdVehiculo = idVehiculo;
    }

    public int getAnioFabricacion() {
        return AnioFabricacion;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        this.AnioFabricacion = anioFabricacion;
    }

    public TipoVehiculo getTipoVehiculo()
    {
        return TipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo)
    {
        TipoVehiculo = tipoVehiculo;
    }

    public int anioFabricacionProperty() {
        return AnioFabricacion;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "AñoFrabricacion=" + IdVehiculo +
                ", AnioFabricacion=" + AnioFabricacion +
                ", TipoVehiculo=" + TipoVehiculo +
                ", Marca='" + Marca + '\'' +
                '}';
    }
}
