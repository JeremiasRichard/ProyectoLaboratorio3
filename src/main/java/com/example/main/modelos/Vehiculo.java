package com.example.main.modelos;

import com.example.main.enums.TipoVehiculo;

public abstract class Vehiculo {
    private int IdVehiculo;
    private int AnioFabricacion;
    private TipoVehiculo TipoVehiculo;
    private String Marca;

    public Vehiculo(){}
    public Vehiculo(int idVehiculo, int anioFabricacion, com.example.main.enums.TipoVehiculo tipoVehiculo, String marca) {
        IdVehiculo = idVehiculo;
        AnioFabricacion = anioFabricacion;
        TipoVehiculo = tipoVehiculo;
        Marca = marca;
    }

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
        AnioFabricacion = anioFabricacion;
    }

    public com.example.main.enums.TipoVehiculo getTipoVehiculo() {
        return TipoVehiculo;
    }

    public void setTipoVehiculo(com.example.main.enums.TipoVehiculo tipoVehiculo) {
        TipoVehiculo = tipoVehiculo;
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
                "IdVehiculo=" + IdVehiculo +
                ", AnioFabricacion=" + AnioFabricacion +
                ", TipoVehiculo=" + TipoVehiculo +
                ", Marca='" + Marca + '\'' +
                '}';
    }
}
