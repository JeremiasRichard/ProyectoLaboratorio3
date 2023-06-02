package com.example.main.modelos;

import com.example.main.enums.TipoVehiculo;

public abstract class Vehiculo {
    private int idVehiculo;
    private int anioFabricacion;
    private TipoVehiculo tipoVehiculo;
    private String marca;

    public Vehiculo(){}
    public Vehiculo(int idVehiculo, int anioFabricacion, com.example.main.enums.TipoVehiculo tipoVehiculo, String marca) {
        this.idVehiculo = idVehiculo;
        this.anioFabricacion = anioFabricacion;
        this.tipoVehiculo = tipoVehiculo;
        this.marca = marca;
    }

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
        anioFabricacion = anioFabricacion;
    }

    public com.example.main.enums.TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(com.example.main.enums.TipoVehiculo tipoVehiculo) {
        tipoVehiculo = tipoVehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        marca = marca;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "IdVehiculo=" + idVehiculo +
                ", AnioFabricacion=" + anioFabricacion +
                ", TipoVehiculo=" + tipoVehiculo +
                ", Marca='" + marca + '\'' +
                '}';
    }
}
