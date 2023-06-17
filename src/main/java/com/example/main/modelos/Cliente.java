package com.example.main.modelos;

import java.util.List;

public class Cliente extends Persona
{
    private List<Integer> historialArreglos;
    private String nroTelefono;
    private List<String> listaVehiculos;
    private boolean activo;
    public Cliente(){}

    public Cliente(String nombre, String apellido, String dni, List<Integer> historialArreglos, String nroTelefono, List<String> listaVehiculos)
    {
        super(nombre, apellido, dni);
        this.historialArreglos = historialArreglos;
        this.nroTelefono = nroTelefono;
        this.listaVehiculos = listaVehiculos;
        this.activo = true;
    }

    public Cliente(String nombre,String apellido,String dni,String nroTelefono,boolean estado)
    {
        super(nombre, apellido, dni);
        this.nroTelefono=nroTelefono;
        this.activo=estado;
    }

    public Cliente(List<Integer> historialArreglos, String nroTelefono, String mail, String direccion, List<String> listaVehiculos) {
        this.historialArreglos = historialArreglos;
        this.nroTelefono = nroTelefono;
        this.listaVehiculos = listaVehiculos;
        this.activo = true;
    }
    //region Getters y Setters


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

    public boolean getActivo() { return activo; }

    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public boolean equals(Object o)
    {
        if(o instanceof  Cliente && o != null)
        {
            return this.getDni() == ((Cliente) o).getDni();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                ", historialArreglos=" + historialArreglos +
                ", listaVehiculos=" + listaVehiculos +
                ", activo=" + activo +
                "} " + super.toString();
    }

    public boolean isEstado()
    {
        if(this.activo==true)
        {
            return true;
        }
        return  false;
    }
}
