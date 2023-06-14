 package com.example.main.modelos;

public class Usuario {
    private int idUsuario; //TODO: conexion con ID? o agregar un objeto Persona (Cliente/Mecanico)? Conexion por DNI?
    private String user;
    private String password;
    private boolean nivelDeAcceso;
    private boolean activo;
    public Usuario() {
    }

    public Usuario(int idUsuario, String user, String password, boolean nivelDeAcceso) {
        this.idUsuario = idUsuario;
        this.user = user;
        this.password = password;
        this.nivelDeAcceso = nivelDeAcceso;
        this.activo = true;
    }

    //region Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isNivelDeAcceso() {
        return nivelDeAcceso;
    }

    public void setNivelDeAcceso(boolean nivelDeAcceso) {
        this.nivelDeAcceso = nivelDeAcceso;
    }

    public boolean getActivo() { return activo; }

    public void setActivo(boolean activo) { this.activo = activo; }
    //endregion
}
