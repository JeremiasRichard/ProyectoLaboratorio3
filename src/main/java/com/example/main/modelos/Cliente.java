package com.example.main.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente extends Persona {
    private List<Integer> historialArreglos;
    private String nroTelefono;
    private List<String> listaVehiculos;
    private boolean activo;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String dni, List<Integer> historialArreglos, String nroTelefono, List<String> listaVehiculos) {
        super(nombre, apellido, dni);
        this.historialArreglos = new ArrayList<>();
        this.nroTelefono = nroTelefono;
        this.listaVehiculos = listaVehiculos;
        this.activo = true;
    }

    public Cliente(String nombre, String apellido, String dni, String nroTelefono, boolean activo) {
        super(nombre, apellido, dni);
        this.nroTelefono = nroTelefono;
        this.historialArreglos = new ArrayList<>();
        this.activo = activo;
    }

    public Cliente(List<Integer> historialArreglos, String nroTelefono, String mail, String direccion, List<String> listaVehiculos) {
        this.historialArreglos = historialArreglos;
        this.nroTelefono = nroTelefono;
        this.listaVehiculos = listaVehiculos;
        this.activo = true;
    }


    public List<Integer> getHistorialArreglos() {
        return historialArreglos;
    }

    public void setHistorialArreglos(List<Integer> historialArreglos) {
        this.historialArreglos = historialArreglos;
    }

    public String getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(String nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    public List<String> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(List<String> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDni());
    }

    @Override
    public String toString() {
        return "Cliente{" +
                ", historialArreglos=" + historialArreglos +
                ", listaVehiculos=" + listaVehiculos +
                ", activo=" + activo +
                "} " + super.toString();
    }

    public boolean isActivo() {
        return activo;
    }
}
