package com.example.main.DTOs;

import com.example.main.enums.Especialidad;
import com.example.main.enums.EstadoReparacion;
import com.example.main.enums.TipoVehiculo;

public class ArregloDTO
{
    private int idArreglo;
    private String patente;
    private String marca;
    private TipoVehiculo tipoVehiculo;
    private int idEmpleado;
    private Especialidad especialidad;
    private int anioFabricacion;
    private String dniCliente;

    @Override
    public String toString() {
        return "ArregloDTO{" +
                "idArreglo=" + idArreglo +
                ", patente='" + patente + '\'' +
                ", marca='" + marca + '\'' +
                ", anioFabricacion=" + anioFabricacion +
                ", dniCliente='" + dniCliente + '\'' +
                ", observacionesCliente='" + observacionesCliente + '\'' +
                ", observacionesMecanico='" + observacionesMecanico + '\'' +
                ", estadoReparacion=" + estadoReparacion +
                '}';
    }

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

    public ArregloDTO(int idArreglo, String patente, String marca, TipoVehiculo tipoVehiculo, int anioFabricacion, String dniCliente, String observacionesCliente, int idEmpleado,Especialidad especialidad) {
        this.idArreglo = idArreglo;
        this.patente = patente;
        this.marca = marca;
        this.tipoVehiculo = tipoVehiculo;
        this.anioFabricacion = anioFabricacion;
        this.dniCliente = dniCliente;
        this.observacionesCliente = observacionesCliente;
        this.idEmpleado=idEmpleado;
        this.estadoReparacion = EstadoReparacion.STAND_BY;
        this.especialidad=especialidad;
    }

    public ArregloDTO() {
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
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
