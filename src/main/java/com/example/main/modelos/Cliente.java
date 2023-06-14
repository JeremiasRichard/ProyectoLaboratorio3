package com.example.main.modelos;

import java.util.List;

public class Cliente extends Persona{
    private int idCliente;
    private List<Arreglo> historialArreglos;
    private int nroTelefono;
    private String mail;
    private String direccion;
    private List<String> listaVehiculos;
    private boolean activo;
    public Cliente(){}

    public Cliente(int idCliente, List<Arreglo> historialArreglos, int nroTelefono, String mail, String direccion, List<String> listaVehiculos) {
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
        idCliente = idCliente;
    }

    public List<Arreglo> getHistorialArreglos() {
        return historialArreglos;
    }

    public void setHistorialArreglos(List<Arreglo> historialArreglos) {
        historialArreglos = historialArreglos;
    }

    public int getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(int nroTelefono) {
        nroTelefono = nroTelefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        mail = mail;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        direccion = direccion;
    }

    public List<String> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(List<String> listaVehiculos) {
        listaVehiculos = listaVehiculos;
    }

    public boolean getActivo() { return activo; }

    public void setActivo(boolean activo) { this.activo = activo; }

    //endregion
    @Override
    public String toString() {
        return "Cliente{" +
                "IdCliente=" + idCliente +
                ", HistorialArreglos=" + historialArreglos +
                ", NroTelefono=" + nroTelefono +
                ", Mail='" + mail + '\'' +
                ", Direccion='" + direccion + '\'' +
                ", ListaVehiculos=" + listaVehiculos +
                ", activo=" + activo +
                '}';
    }
}
