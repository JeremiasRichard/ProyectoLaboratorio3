package com.example.main.modelos;

import com.example.main.enums.Especialidad;
import com.example.main.enums.TipoVehiculo;

import java.util.List;

public class Mecanico extends Personal{
    private List<Arreglo> ListaArreglos;
    private TipoVehiculo TipoVehiculo;
    private Especialidad Especialidad;

    public Mecanico(){}

    public Mecanico(List<Arreglo> listaArreglos, com.example.main.enums.TipoVehiculo tipoVehiculo, com.example.main.enums.Especialidad especialidad) {
        ListaArreglos = listaArreglos;
        TipoVehiculo = tipoVehiculo;
        Especialidad = especialidad;
    }

    public List<Arreglo> getListaArreglos() {
        return ListaArreglos;
    }

    public void setListaArreglos(List<Arreglo> listaArreglos) {
        ListaArreglos = listaArreglos;
    }

    public com.example.main.enums.TipoVehiculo getTipoVehiculo() {
        return TipoVehiculo;
    }

    public void setTipoVehiculo(com.example.main.enums.TipoVehiculo tipoVehiculo) {
        TipoVehiculo = tipoVehiculo;
    }

    public com.example.main.enums.Especialidad getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(com.example.main.enums.Especialidad especialidad) {
        Especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Mecanico{" +
                "ListaArreglos=" + ListaArreglos +
                ", TipoVehiculo=" + TipoVehiculo +
                ", Especialidad=" + Especialidad +
                '}';
    }
}
