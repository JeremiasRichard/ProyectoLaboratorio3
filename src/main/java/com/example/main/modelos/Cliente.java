package com.example.main.modelos;

import java.util.List;

public class Cliente extends Persona{
    private int IdCliente;
    private List<Arreglo> HistorialArreglos;
    private int NroTelefono;
    private String Mail;
    private String Direccion;
    private List<Vehiculo> ListaVehiculos;

    public Cliente(){}

    public Cliente(int idCliente, List<Arreglo> historialArreglos, int nroTelefono, String mail, String direccion, List<Vehiculo> listaVehiculos) {
        IdCliente = idCliente;
        HistorialArreglos = historialArreglos;
        NroTelefono = nroTelefono;
        Mail = mail;
        Direccion = direccion;
        ListaVehiculos = listaVehiculos;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public List<Arreglo> getHistorialArreglos() {
        return HistorialArreglos;
    }

    public void setHistorialArreglos(List<Arreglo> historialArreglos) {
        HistorialArreglos = historialArreglos;
    }

    public int getNroTelefono() {
        return NroTelefono;
    }

    public void setNroTelefono(int nroTelefono) {
        NroTelefono = nroTelefono;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public List<Vehiculo> getListaVehiculos() {
        return ListaVehiculos;
    }

    public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
        ListaVehiculos = listaVehiculos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "IdCliente=" + IdCliente +
                ", HistorialArreglos=" + HistorialArreglos +
                ", NroTelefono=" + NroTelefono +
                ", Mail='" + Mail + '\'' +
                ", Direccion='" + Direccion + '\'' +
                ", ListaVehiculos=" + ListaVehiculos +
                '}';
    }
}
