package com.example.main.modelos;


import com.example.main.enums.EstadoReparacion;

public class Arreglo {
    private int idArreglo;
    private String patente;
    private String dniCliente;
    private int idEmpleado;
    private String observacionesDelCliente;
    private String observacionesDelArreglo;
    private EstadoReparacion estadoReparacion;

    public Arreglo()
    {

    }

    public Arreglo(int idArreglo, String patente, String dniCliente, int idEmpleado, String observacionesDelCliente)
    {
        this.idArreglo = idArreglo;
        this.patente = patente;
        this.dniCliente = dniCliente;
        this.idEmpleado = idEmpleado;
        this.observacionesDelCliente = observacionesDelCliente;
        this.estadoReparacion = EstadoReparacion.STAND_BY;
    }


    //region Getters y Setters
    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
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

    public String getObservacionesDelCliente() {
        return observacionesDelCliente;
    }

    public void setObservacionesDelCliente(String observacionesDelCliente) {
        this.observacionesDelCliente = observacionesDelCliente;
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
                ", patente='" + patente + '\'' +
                ", dniCliente='" + dniCliente + '\'' +
                ", idEmpleado=" + idEmpleado +
                ", observacionesDelCliente='" + observacionesDelCliente + '\'' +
                ", observacionesDelArreglo='" + observacionesDelArreglo + '\'' +
                ", estadoReparacion=" + estadoReparacion +
                '}';
    }
}
