package com.example.main.DTOs;

import com.example.main.enums.EstadoReparacion;

public class ArregloDTO
{
    private int idArreglo;
    private String patente;
    private String marca;
    private int anioFabricacion;
    private String dniCliente;
    private String observacionesCliente;
    private String observacionesMecanico;
    private EstadoReparacion estadoReparacion;

    public ArregloDTO(int idArreglo, String patente, String marca, int anioFabricacion, String dniCliente, String observacionesCliente, EstadoReparacion estadoReparacion) {
        this.idArreglo = idArreglo;
        this.patente = patente;
        this.marca = marca;
        this.anioFabricacion = anioFabricacion;
        this.dniCliente = dniCliente;
        this.observacionesCliente = observacionesCliente;
        this.estadoReparacion = estadoReparacion;
    }

    public ArregloDTO() {
    }

    public int getIdArreglo() {
        return idArreglo;
    }

    public void setIdArreglo(int idArreglo) {
        this.idArreglo = idArreglo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getObservacionesCliente() {
        return observacionesCliente;
    }

    public void setObservacionesCliente(String observacionesCliente) {
        this.observacionesCliente = observacionesCliente;
    }

    public String getObservacionesMecanico() {
        return observacionesMecanico;
    }

    public void setObservacionesMecanico(String observacionesMecanico) {
        this.observacionesMecanico = observacionesMecanico;
    }

    public EstadoReparacion getEstadoReparacion() {
        return estadoReparacion;
    }

    public void setEstadoReparacion(EstadoReparacion estadoReparacion) {
        this.estadoReparacion = estadoReparacion;
    }
}
