package com.example.main.modelos;

import com.example.main.enums.Especialidad;
import com.example.main.enums.TipoVehiculo;

import java.util.ArrayList;
import java.util.List;

public class Mecanico extends Empleado {
    private transient List<Arreglo> listaArreglos;
    private TipoVehiculo tipoVehiculo;
    private Especialidad especialidad;
    private boolean activo;

    public Mecanico() {
    }

    public Mecanico(String nombre, String apellido, String dni, String telefono, int idUsuario, List<Arreglo> listaArreglos, TipoVehiculo tipoVehiculo, Especialidad especialidad) {
        super(nombre, apellido, dni, telefono, idUsuario);
        this.listaArreglos = listaArreglos;
        this.tipoVehiculo = tipoVehiculo;
        this.especialidad = especialidad;
        this.activo = true;
    }

    public Mecanico(String nombre, String apellido, String dni, String telefono, List<Arreglo> listaArreglos, TipoVehiculo tipoVehiculo, Especialidad especialidad) {
        super(nombre, apellido, dni, telefono);
        this.listaArreglos = listaArreglos;
        this.tipoVehiculo = tipoVehiculo;
        this.especialidad = especialidad;
        this.activo = true;
    }

    public Mecanico(TipoVehiculo tipoVehiculo, Especialidad especialidad) {
        this.listaArreglos = new ArrayList<>();
        this.tipoVehiculo = tipoVehiculo;
        this.especialidad = especialidad;
        this.activo = true;
    }

    //region Getters y Setters
    public List<Arreglo> getListaArreglos() {
        return listaArreglos;
    }

    public void setListaArreglos(List<Arreglo> listaArreglos) {
        this.listaArreglos = listaArreglos;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /*
    public int getId() {
        return this.getIdEmpleado();
    }
    */

    //endregion
    @Override
    public String toString() {
        return "Mecanico{" + "ListaArreglos=" + listaArreglos + ", TipoVehiculo=" + tipoVehiculo + ", Especialidad=" + especialidad + ", activo=" + activo + '}';
    }
}
