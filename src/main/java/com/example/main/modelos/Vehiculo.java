package com.example.main.modelos;

import com.example.main.enums.TipoVehiculo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vehiculo {
    private int idVehiculo;
    private int anioFabricacion;
    private TipoVehiculo tipoVehiculo;
    private String marca;
    private boolean activo;

    public Vehiculo(){}
    public Vehiculo(int idVehiculo, int anioFabricacion, TipoVehiculo tipoVehiculo, String marca) {
        this.idVehiculo = idVehiculo;
        this.anioFabricacion =  anioFabricacion;
        this.tipoVehiculo = tipoVehiculo;
        this.marca = marca;
        this.activo = true;
    }
    //region Getters y Setters
    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        idVehiculo = idVehiculo;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }

    public TipoVehiculo getTipoVehiculo()
    {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo)
    {
        tipoVehiculo = tipoVehiculo;
    }

    public int anioFabricacionProperty() {
        return anioFabricacion;
    }

    public String getMarca() { return marca; }

    public void setMarca(String marca) {
        marca = marca;
    }

    public boolean getActivo() { return activo; }

    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "AñoFrabricacion=" + idVehiculo +
                ", AnioFabricacion=" + anioFabricacion +
                ", TipoVehiculo=" + tipoVehiculo +
                ", Marca='" + marca + '\'' +
                ", activo=" + activo +
                '}';
    }
}
