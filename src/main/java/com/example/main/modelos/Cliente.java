package com.example.main.modelos;

import java.util.List;

public class Cliente extends Persona{
    private int idCliente;
    private List<Arreglo> historialArreglos;
    private String nroTelefono;
    private String mail;
    private String direccion;
    private List<String> listaVehiculos;
    private boolean activo;
    public Cliente(){}

    public Cliente(String nombre, String apellido, String dni, int idCliente, List<Arreglo> historialArreglos, String nroTelefono, String mail, String direccion, List<String> listaVehiculos) {
        super(nombre, apellido, dni);
        this.idCliente = idCliente;
        this.historialArreglos = historialArreglos;
        this.nroTelefono = nroTelefono;
        this.mail = mail;
        this.direccion = direccion;
        this.listaVehiculos = listaVehiculos;
        this.activo = true;
    }

    public Cliente(int idCliente, List<Arreglo> historialArreglos, String nroTelefono, String mail, String direccion, List<String> listaVehiculos) {
        this.idCliente = idCliente;
        this.historialArreglos = historialArreglos;
        this.nroTelefono = nroTelefono;
        this.mail = mail;
        this.direccion = direccion;
        this.listaVehiculos = listaVehiculos;
        this.activo = true;
    }
    //region Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public List<Arreglo> getHistorialArreglos() {
        return historialArreglos;
    }

    public void setHistorialArreglos(List<Arreglo> historialArreglos) {
        this.historialArreglos = historialArreglos;
    }

    public String getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(String nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", historialArreglos=" + historialArreglos +
                ", nroTelefono=" + nroTelefono +
                ", mail='" + mail + '\'' +
                ", direccion='" + direccion + '\'' +
                ", listaVehiculos=" + listaVehiculos +
                ", activo=" + activo +
                "} " + super.toString();
    }
}
