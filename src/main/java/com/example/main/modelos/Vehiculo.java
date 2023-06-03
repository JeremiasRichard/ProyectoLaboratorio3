package com.example.main.modelos;

import com.example.main.enums.TipoVehiculo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Vehiculo {
    private int IdVehiculo;
    private IntegerProperty AnioFabricacion;
    private TipoVehiculo TipoVehiculo;
    private StringProperty Marca;

    public Vehiculo(){}
    public Vehiculo(int idVehiculo, int anioFabricacion, com.example.main.enums.TipoVehiculo tipoVehiculo, String marca) {
        IdVehiculo = idVehiculo;
        AnioFabricacion =  new SimpleIntegerProperty(anioFabricacion);
        TipoVehiculo = tipoVehiculo;
        Marca = new SimpleStringProperty(marca);
    }

    public int getIdVehiculo() {
        return IdVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        IdVehiculo = idVehiculo;
    }

    public IntegerProperty getAnioFabricacion() {
        return AnioFabricacion;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        this.AnioFabricacion.set(anioFabricacion);
    }

    public com.example.main.enums.TipoVehiculo getTipoVehiculo()
    {
        return TipoVehiculo;
    }

    public void setTipoVehiculo(com.example.main.enums.TipoVehiculo tipoVehiculo)
    {
        TipoVehiculo = tipoVehiculo;
    }

    public StringProperty getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        this.Marca.set(marca);
    }
    @Override
    public String toString() {
        return "Vehiculo{" +
                "IdVehiculo=" + IdVehiculo +
                ", AnioFabricacion=" + AnioFabricacion +
                ", TipoVehiculo=" + TipoVehiculo +
                ", Marca='" + Marca + '\'' +
                '}';
    }
}
