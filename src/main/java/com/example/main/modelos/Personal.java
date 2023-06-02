package com.example.main.modelos;

public abstract class Personal extends Persona{
    private int IdEmpleado;
    private String DatosContacto;//hay un objeto tipo DatosContacto?
    private Usuario Usuario;

    public Personal(){}

    public Personal(String nombre, String apellido, int edad, int idEmpleado, String datosContacto, com.example.main.modelos.Usuario usuario) {
        super(nombre, apellido, edad);
        IdEmpleado = idEmpleado;
        DatosContacto = datosContacto;
        Usuario = usuario;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        IdEmpleado = idEmpleado;
    }

    public String getDatosContacto() {
        return DatosContacto;
    }

    public void setDatosContacto(String datosContacto) {
        DatosContacto = datosContacto;
    }

    public com.example.main.modelos.Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(com.example.main.modelos.Usuario usuario) {
        Usuario = usuario;
    }

    @Override
    public String toString() {
        return "Personal{" +
                "IdEmpleado=" + IdEmpleado +
                ", DatosContacto='" + DatosContacto + '\'' +
                ", Usuario=" + Usuario +
                '}';
    }
}
