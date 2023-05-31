package com.example.main.modelos;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Auto
{
    public IntegerProperty IdCliente;
    public IntegerProperty IdVehiculo;
    public StringProperty Marca;

    public Auto(int idCliente, int idVehiculo, String marca) {
        this.IdCliente = new SimpleIntegerProperty(idCliente);
        this.IdVehiculo = new SimpleIntegerProperty(idVehiculo);
        this.Marca = new SimpleStringProperty(marca);
    }
    // Getter y setter para IdCliente
    public int getIdCliente() {
        return IdCliente.get();
    }

    public void setIdCliente(int idCliente) {
        this.IdCliente.set(idCliente);
    }

    public IntegerProperty idClienteProperty() {
        return IdCliente;
    }

    // Getter y setter para idVehiculo
    public int getIdVehiculo() {
        return IdVehiculo.get();
    }

    public void setIdVehiculo(int idVehiculo) {
        this.IdVehiculo.set(idVehiculo);
    }

    public IntegerProperty idVehiculoProperty() {
        return IdVehiculo;
    }

    // Getter y setter para marca
    public String getMarca() {
        return Marca.get();
    }

    public void setMarca(String marca) {
        this.Marca.set(marca);
    }

    public StringProperty marcaProperty() {
        return Marca;
    }

    // Otros getters y setters para los demás campos


    // ToString

    @Override
    public String toString() {
        return "Autos{" +
                "IdCliente=" + IdCliente +
                ", IdVehiculo=" + IdVehiculo +
                ", Marca=" + Marca +
                '}';
    }
}