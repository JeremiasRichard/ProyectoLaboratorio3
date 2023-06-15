package com.example.main.modelos;


import com.example.main.enums.EstadoReparacion;

import java.util.Objects;

public class Arreglo {
    private int idArreglo;
    private String patente;
    private int idCliente;
    private int idEmpleado;
    private String detalleCliente;
    private String observacionesDelArreglo;
    private EstadoReparacion estadoReparacion;

    public Arreglo()
    {

    }

    public Arreglo(int idArreglo, String patente, int idCliente, int idEmpleado, String detalleCliente)
    {
        this.idArreglo = idArreglo;
        this.patente = patente;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.detalleCliente = detalleCliente;
        this.estadoReparacion = EstadoReparacion.STAND_BY;
    }

    //region Getters y Setters
    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public int getIdArreglo() {
        return this.idArreglo;
    }
    public void setIdArreglo(int idArreglo) {
        this.idArreglo = idArreglo;
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

    public String getObservacionesDelArreglo() {
        return observacionesDelArreglo;
    }

    public void setObservacionesDelArreglo(String observacionesDelArreglo) {
        this.observacionesDelArreglo = observacionesDelArreglo;
    }
    public EstadoReparacion getEstadoReparacion() {
        return estadoReparacion;
    }
    public void setEstadoReparacion(EstadoReparacion estadoReparacion) {
        this.estadoReparacion = estadoReparacion;
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
                ", estadoReparacion=" + estadoReparacion +
                '}';
    }

}
