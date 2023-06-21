package com.example.main.modelos;

import java.util.Objects;

public abstract class Empleado extends Persona {
    private int idEmpleado;
    private String telefono;
    private int idUsuario;

    public Empleado() {
    }

    public Empleado(String nombre, String apellido, String dni, String telefono, int idUsuario) {
        super(nombre, apellido, dni);
        this.telefono = telefono;
        this.idUsuario = idUsuario;
    }

    public Empleado(String nombre, String apellido, String dni, String telefono) {
        super(nombre, apellido, dni);
        this.telefono = telefono;
    }

    //region Getters y Setters
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getUsuario() {
        return idUsuario;
    }

    public void setUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTelefono() { return telefono; }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setTelefono(String telefono) { this.telefono = telefono; }
    //endregion

}
