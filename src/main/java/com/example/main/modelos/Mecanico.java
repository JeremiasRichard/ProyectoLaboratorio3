package com.example.main.modelos;

import com.example.main.enums.Especialidad;
import com.example.main.enums.TipoVehiculo;

import java.util.List;

public class Mecanico extends Personal{
    private List<Arreglo> listaArreglos;
    private TipoVehiculo tipoVehiculo;
    private Especialidad especialidad;
    private boolean activo;

    public Mecanico(){}

    public Mecanico(List<Arreglo> listaArreglos, com.example.main.enums.TipoVehiculo tipoVehiculo, com.example.main.enums.Especialidad especialidad) {
        this.listaArreglos = listaArreglos;
        this.tipoVehiculo = tipoVehiculo;
        this.especialidad = especialidad;
        this.activo = true;
    }
    //region Getters y Setters
    public List<Arreglo> getListaArreglos() {
        return listaArreglos;
    }

    public void setListaArreglos(List<Arreglo> listaArreglos) {
        listaArreglos = listaArreglos;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        tipoVehiculo = tipoVehiculo;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        especialidad = especialidad;
    }

    public boolean getActivo() { return activo; }

    public void setActivo(boolean activo) { this.activo = activo; }

    //endregion
    @Override
    public String toString() {
        return "Mecanico{" +
                "ListaArreglos=" + listaArreglos +
                ", TipoVehiculo=" + tipoVehiculo +
                ", Especialidad=" + especialidad +
                ", activo=" + activo +
                '}';
    }
}
