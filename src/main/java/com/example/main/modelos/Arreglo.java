package com.example.main.modelos;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Arreglo {
    private int idArreglo;
    private Vehiculo vehiculo;
    private int idCliente;
    private int idEmpleado;
    private String detalleCliente;
    private String observacionesDelArreglo;
    private double costo;
    private boolean estaListo;

    public Arreglo(){

    }
    public Arreglo(int idArreglo,Vehiculo vehiculo, int idCliente, int idEmpleado, String detalleCliente, boolean estaListo) {
        this.idArreglo = idArreglo;
        this.vehiculo = vehiculo;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.detalleCliente = detalleCliente;
        this.estaListo = estaListo;
    }
    //region Getters y Setters
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public int getIdArreglo() {
        return this.idArreglo;
    }
    public void setIdArreglo(int idArreglo) {
        this.idArreglo = idArreglo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getDetalleCliente() {
        return detalleCliente;
    }

    public void setDetalleCliente(String detalleCliente) {
        this.detalleCliente = detalleCliente;
    }

    public String getDetalleArreglo() {
        return observacionesDelArreglo;
    }

    public void setDetalleArreglo(String detalleArreglo) {
        observacionesDelArreglo = detalleArreglo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public boolean isEstaListo() {
        return estaListo;
    }

    public void setEstaListo(boolean estaListo) {
        this.estaListo = estaListo;
    }
    //endregion
    @Override
    public String toString() {
        return "Arreglo{" +
                "idArreglo=" + idArreglo +
                ", idCliente=" + idCliente +
                ", idEmpleado=" + idEmpleado +
                ", detalleCliente='" + detalleCliente + '\'' +
                ", observacionesDelArreglo='" + observacionesDelArreglo + '\'' +
                ", costo=" + costo +
                ", estaListo=" + estaListo +
                '}';
    }
}
