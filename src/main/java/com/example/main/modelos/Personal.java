package com.example.main.modelos;

public abstract class Personal extends Persona{
    private int idEmpleado;
    private String datosContacto;//hay un objeto tipo DatosContacto?
    private Usuario usuario;

    public Personal(){}

    public Personal(String nombre, String apellido, int edad, int idEmpleado, String datosContacto, com.example.main.modelos.Usuario usuario) {
        super(nombre, apellido, edad);
        this.idEmpleado = idEmpleado;
        this.datosContacto = datosContacto;
        this.usuario = usuario;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        idEmpleado = idEmpleado;
    }

    public String getDatosContacto() {
        return datosContacto;
    }

    public void setDatosContacto(String datosContacto) {
        datosContacto = datosContacto;
    }

    public com.example.main.modelos.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(com.example.main.modelos.Usuario usuario) {
        usuario = usuario;
    }

    @Override
    public String toString() {
        return "Personal{" +
                "IdEmpleado=" + idEmpleado +
                ", DatosContacto='" + datosContacto + '\'' +
                ", Usuario=" + usuario +
                '}';
    }
}
