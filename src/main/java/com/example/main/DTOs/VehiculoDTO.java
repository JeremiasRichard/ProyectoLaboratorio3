package com.example.main.DTOs;

import com.example.main.enums.EstadoReparacion;
import com.example.main.enums.TipoVehiculo;
import com.example.main.modelos.Vehiculo;
import com.fasterxml.jackson.databind.util.ISO8601Utils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VehiculoDTO
{
    private int idVehiculo;
    private IntegerProperty AñoFrabricacion;
    private StringProperty Marca;
    private TipoVehiculo TipoVehiculo;

    public VehiculoDTO()
    {
        this.AñoFrabricacion = new SimpleIntegerProperty();
        this.Marca = new SimpleStringProperty();
    }

    public void pasarVehiculoATabla(Vehiculo vehiculo)
    {
        this.idVehiculo = vehiculo.getIdVehiculo();
        this.setAñoFrabricacion(vehiculo.getAnioFabricacion());
        this.setMarca(vehiculo.getMarca());
        this.setTipoVehiculo(vehiculo.getTipoVehiculo());
    }


    public int getIdVehiculo() {

        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public com.example.main.enums.TipoVehiculo getTipoVehiculo() {
        return TipoVehiculo;
    }

    public void setTipoVehiculo(com.example.main.enums.TipoVehiculo tipoVehiculo) {
        TipoVehiculo = tipoVehiculo;
    }

    public int getAñoFrabricacion() {
        return AñoFrabricacion.get();
    }

    public IntegerProperty añoFrabricacionProperty() {
        return AñoFrabricacion;
    }

    public void setAñoFrabricacion(int añoFrabricacion) {
        this.AñoFrabricacion.set(añoFrabricacion);
    }

    public String getMarca() {
        return Marca.get();
    }

    public StringProperty marcaProperty() {
        return Marca;
    }

    public void setMarca(String marca) {
        this.Marca.set(marca);
    }
}