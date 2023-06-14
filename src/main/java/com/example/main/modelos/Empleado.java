package com.example.main.modelos;

public abstract class Empleado extends Persona{
    private int idEmpleado;
    private String datosContacto;//hay un objeto tipo DatosContacto?
    private Usuario usuario;

    public Empleado(){}

    public Empleado(String nombre, String apellido , String dni,int idEmpleado, String datosContacto, com.example.main.modelos.Usuario usuario) {
        super(nombre, apellido,dni);
        this.idEmpleado = idEmpleado;
        this.datosContacto = datosContacto;
        this.usuario = usuario;
    }
    //region Getters y Setters
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getDatosContacto() {
        return datosContacto;
    }

    public void setDatosContacto(String datosContacto) {
        this.datosContacto = datosContacto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    //endregion
    @Override
    public String toString() {
        return "Personal{" +
                "IdEmpleado=" + idEmpleado +
                ", DatosContacto='" + datosContacto + '\'' +
                ", Usuario=" + usuario +
                '}';
    }
}
