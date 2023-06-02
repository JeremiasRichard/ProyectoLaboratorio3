package com.example.main.modelos;

import java.util.Date;

public class Arreglo {
    private int IdArreglo;
    private int IdVehiculo;
    private int IdCliente;
    private int IdEmpleado;
    private String DetalleCliente;
    private Date FechaDeEntrada;
    private String DetalleArreglo;
    private double Costo;
    private boolean EstaListo;

    public Arreglo(){

    }
    public Arreglo(int idArreglo, int idVehiculo, int idCliente, int idEmpleado, String detalleCliente, Date fechaDeEntrada, String detalleArreglo, double costo, boolean estaListo) {
        IdArreglo = idArreglo;
        IdVehiculo = idVehiculo;
        IdCliente = idCliente;
        IdEmpleado = idEmpleado;
        DetalleCliente = detalleCliente;
        FechaDeEntrada = fechaDeEntrada;
        DetalleArreglo = detalleArreglo;
        Costo = costo;
        EstaListo = estaListo;
    }

    public int getIdArreglo() {
        return IdArreglo;
    }

    public void setIdArreglo(int idArreglo) {
        IdArreglo = idArreglo;
    }

    public int getIdVehiculo() {
        return IdVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        IdVehiculo = idVehiculo;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) {
        IdCliente = idCliente;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        IdEmpleado = idEmpleado;
    }

    public String getDetalleCliente() {
        return DetalleCliente;
    }

    public void setDetalleCliente(String detalleCliente) {
        DetalleCliente = detalleCliente;
    }

    public Date getFechaDeEntrada() {
        return FechaDeEntrada;
    }

    public void setFechaDeEntrada(Date fechaDeEntrada) {
        FechaDeEntrada = fechaDeEntrada;
    }

    public String getDetalleArreglo() {
        return DetalleArreglo;
    }

    public void setDetalleArreglo(String detalleArreglo) {
        DetalleArreglo = detalleArreglo;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double costo) {
        Costo = costo;
    }

    public boolean isEstaListo() {
        return EstaListo;
    }

    public void setEstaListo(boolean estaListo) {
        EstaListo = estaListo;
    }

    @Override
    public String toString() {
        return "Arreglo{" +
                "IdArreglo=" + IdArreglo +
                ", IdVehiculo=" + IdVehiculo +
                ", IdCliente=" + IdCliente +
                ", IdEmpleado=" + IdEmpleado +
                ", DetalleCliente='" + DetalleCliente + '\'' +
                ", FechaDeEntrada=" + FechaDeEntrada +
                ", DetalleArreglo='" + DetalleArreglo + '\'' +
                ", Costo=" + Costo +
                ", EstaListo=" + EstaListo +
                '}';
    }
}
