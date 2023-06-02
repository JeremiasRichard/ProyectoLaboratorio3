package com.example.main.modelos;

public class Usuario extends Persona{
    private int idUsuario;
    private String user;
    private String password;
    private boolean nivelDeAcceso;

    public Usuario(){}

    public Usuario(int idUsuario, String user, String password, boolean nivelDeAcceso) {
        this.idUsuario = idUsuario;
        this.user = user;
        this.password = password;
        this.nivelDeAcceso = nivelDeAcceso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        idUsuario = idUsuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = password;
    }

    public boolean isNivelDeAcceso() {
        return nivelDeAcceso;
    }

    public void setNivelDeAcceso(boolean nivelDeAcceso) {
        nivelDeAcceso = nivelDeAcceso;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "IdUsuario=" + idUsuario +
                ", User='" + user + '\'' +
                ", Password='" + password + '\'' +
                ", NivelDeAcceso=" + nivelDeAcceso +
                '}';
    }
}
