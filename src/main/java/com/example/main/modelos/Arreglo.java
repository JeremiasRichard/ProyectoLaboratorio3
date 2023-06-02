package com.example.main.modelos;

import java.util.Date;

public class Arreglo {
    private int idArreglo;
    private int idVehiculo;
    private int idCliente;
    private int idEmpleado;
    private String detalleCliente;
    private Date fechaDeEntrada;
    private String detalleArreglo;
    private double costo;
    private boolean estaListo;

    public Arreglo(){

    }
    public Arreglo(int idArreglo, int idVehiculo, int idCliente, int idEmpleado, String detalleCliente, Date fechaDeEntrada, String detalleArreglo, double costo, boolean estaListo) {
        this.idArreglo = idArreglo;
        this.idVehiculo = idVehiculo;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.detalleCliente = detalleCliente;
        this.fechaDeEntrada = fechaDeEntrada;
        this.detalleArreglo = detalleArreglo;
        this.costo = costo;
        this.estaListo = estaListo;
    }

    public int getIdArreglo() {
        return idArreglo;
    }

    public void setIdArreglo(int idArreglo) {
        idArreglo = idArreglo;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        idVehiculo = idVehiculo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        idEmpleado = idEmpleado;
    }

    public String getDetalleCliente() {
        return detalleCliente;
    }

    public void setDetalleCliente(String detalleCliente) {
        detalleCliente = detalleCliente;
    }

    public Date getFechaDeEntrada() {
        return fechaDeEntrada;
    }

    public void setFechaDeEntrada(Date fechaDeEntrada) {
        fechaDeEntrada = fechaDeEntrada;
    }

    public String getDetalleArreglo() {
        return detalleArreglo;
    }

    public void setDetalleArreglo(String detalleArreglo) {
        detalleArreglo = detalleArreglo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        costo = costo;
    }

    public boolean isEstaListo() {
        return estaListo;
    }

    public void setEstaListo(boolean estaListo) {
        estaListo = estaListo;
    }

    @Override
    public String toString() {
        return "Arreglo{" +
                "IdArreglo=" + idArreglo +
                ", IdVehiculo=" + idVehiculo +
                ", IdCliente=" + idCliente +
                ", IdEmpleado=" + idEmpleado +
                ", DetalleCliente='" + detalleCliente + '\'' +
                ", FechaDeEntrada=" + fechaDeEntrada +
                ", DetalleArreglo='" + detalleArreglo + '\'' +
                ", Costo=" + costo +
                ", EstaListo=" + estaListo +
                '}';
    }
}
