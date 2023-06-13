package com.example.main.modelos;


import com.example.main.enums.EstadoReparacion;

public class Arreglo {
    private int idArreglo;
    private Vehiculo vehiculo;
    private int idCliente;
    private int idEmpleado;
    private String detalleCliente;
    private String observacionesDelArreglo;
    private double costo;
    private EstadoReparacion estadoReparacion;
    private boolean activo;

    public Arreglo(){

    }
    public Arreglo(int idArreglo,Vehiculo vehiculo, int idCliente, int idEmpleado, String detalleCliente, EstadoReparacion estadoReparacion) {
        this.idArreglo = idArreglo;
        this.vehiculo = vehiculo;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.detalleCliente = detalleCliente;
        this.estadoReparacion = estadoReparacion;
        this.activo = true;
    }
    //region Getters y Setters
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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
    public boolean getActivo() { return activo; }

    public void setActivo(boolean activo) { this.activo = activo; }
    //endregion
    @Override
    public String toString() {
        return "Arreglo{" +
                "idArreglo=" + idArreglo +
                ", vehiculo=" + vehiculo +
                ", idCliente=" + idCliente +
                ", idEmpleado=" + idEmpleado +
                ", detalleCliente='" + detalleCliente + '\'' +
                ", observacionesDelArreglo='" + observacionesDelArreglo + '\'' +
                ", costo=" + costo +
                ", estadoReparacion=" + estadoReparacion +
                ", activo=" + activo +
                '}';
    }
}
