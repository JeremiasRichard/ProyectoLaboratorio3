package com.example.main.DTOs;

import com.example.main.enums.EstadoReparacion;
import com.example.main.modelos.Vehiculo;

public class ArregloDTO
{
   private int idArreglo;
   private Vehiculo vehiculo;
   private String dniCliente;
   private String observacionesCliente;
   private String observacionesMecanico;
   private EstadoReparacion estadoReparacion;

    public ArregloDTO(int idArreglo, Vehiculo vehiculo, String dniCliente, String observacionesCliente) {
        this.idArreglo = idArreglo;
        this.vehiculo = vehiculo;
        this.dniCliente = dniCliente;
        this.observacionesCliente = observacionesCliente;
    }

    public ArregloDTO() {
    }

    public int getIdArreglo() {
        return idArreglo;
    }

    public void setIdArreglo(int idArreglo) {
        this.idArreglo = idArreglo;
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

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public EstadoReparacion getEstadoReparacion() {
        return estadoReparacion;
    }

    public void setEstadoReparacion(EstadoReparacion estadoReparacion) {
        this.estadoReparacion = estadoReparacion;
    }
}