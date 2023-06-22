package com.example.main.modelos;

import com.example.main.enums.TipoVehiculo;

public class Vehiculo {
    private int anioFabricacion;
    private TipoVehiculo tipoVehiculo;
    private String marca;
    private String patente;
    private boolean activo;

    public Vehiculo(){}

    public Vehiculo(int anioFabricacion, TipoVehiculo tipoVehiculo, String marca, String patente)
    {
        this.anioFabricacion =  anioFabricacion;
        this.tipoVehiculo = tipoVehiculo;
        this.marca = marca;
        this.patente = patente;
        this.activo = true;
    }
    //region Getters y Setters

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
        this.tipoVehiculo = tipoVehiculo;
    }

    public int anioFabricacionProperty() {
        return anioFabricacion;
    }

    public String getMarca() { return marca; }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public boolean getActivo() { return activo; }

    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "Vehiculo{" +
                ", AnioFabricacion=" + anioFabricacion +
                ", TipoVehiculo=" + tipoVehiculo +
                ", Marca='" + marca + '\'' +
                ", Patente='" + patente +
                ", activo=" + activo +
                '}';
    }
}
