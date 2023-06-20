package com.example.main.DTOs;

import com.example.main.modelos.Vehiculo;

import java.util.List;
import java.util.Objects;

public class ClienteDTO
{
    private String nombre;
    private String apellido;
    private String dni;
    private String nroTelefono;
    private List<Integer> historialArreglos;
    private List<Vehiculo> listaVehiculos;
    private boolean activo;

    public ClienteDTO(String nombre,String apellido,String nroTelefono,String dni,List<Vehiculo> listaVehiculos,List<Integer>historialArreglos)
    {
        this.nombre = nombre;
        this.apellido=apellido;
        this.dni=dni;
        this.historialArreglos=historialArreglos;
        this.listaVehiculos = listaVehiculos;
        this.nroTelefono = nroTelefono;
        this.activo=true;
    }
    public ClienteDTO(){
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(String nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    public List<Integer> getHistorialArreglos() {
        return historialArreglos;
    }

    public void setHistorialArreglos(List<Integer> historialArreglos) {
        this.historialArreglos = historialArreglos;
    }

    public List<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClienteDTO that)) return false;
        return isActivo() == that.isActivo() && Objects.equals(getDni(), that.getDni());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getApellido(), getDni(), getNroTelefono(), isActivo());
    }
}

