package com.example.main.DTOs;

import com.example.main.enums.EstadoReparacion;
import com.example.main.modelos.Arreglo;
import com.example.main.modelos.Vehiculo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ArregloDTO
{
    public IntegerProperty idArreglo;
    public  VehiculoDTO vehiculoDTO;
    private int idCliente;
    private int idEmpleado;
    private String detalleCliente;
    private String observacionesDelArreglo;
    private double costo;
    private EstadoReparacion estadoReparacion;

    public ArregloDTO()
    {
        this.vehiculoDTO = new VehiculoDTO();
    }

    public void MapearATabla(Arreglo arreglo)
    {
        this.vehiculoDTO = new VehiculoDTO();
        this.idArreglo = new SimpleIntegerProperty(arreglo.getIdArreglo());
        this.vehiculoDTO.pasarVehiculoATabla(arreglo.getVehiculo());
        this.idCliente = arreglo.getIdCliente();
        this.idEmpleado = arreglo.getIdEmpleado();
        this.detalleCliente = arreglo.getDetalleCliente();
        this.estadoReparacion = arreglo.getEstadoReparacion();
        this.observacionesDelArreglo = arreglo.getObservacionesDelArreglo();
    }

    public int getIdArreglo() {
        return idArreglo.get();
    }

    public IntegerProperty idArregloProperty() {
        return idArreglo;
    }

    public void setIdArreglo(int idArreglo) {
        this.idArreglo.set(idArreglo);
    }

    public VehiculoDTO getVehiculoDTO() {
        return vehiculoDTO;
    }

    public void setVehiculoDTO(VehiculoDTO vehiculoDTO) {
        this.vehiculoDTO = vehiculoDTO;
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

    public String getObservacionesDelArreglo() {
        return observacionesDelArreglo;
    }

    public void setObservacionesDelArreglo(String observacionesDelArreglo) {
        this.observacionesDelArreglo = observacionesDelArreglo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public EstadoReparacion getEstadoReparacion() {
        return estadoReparacion;
    }

    public void setEstadoReparacion(EstadoReparacion estadoReparacion) {
        this.estadoReparacion = estadoReparacion;
    }
}
