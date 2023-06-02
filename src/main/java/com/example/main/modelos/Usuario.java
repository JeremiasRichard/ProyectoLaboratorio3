package com.example.main.modelos;

public class Usuario extends Persona{
    private int IdUsuario;
    private String User;
    private String Password;
    private boolean NivelDeAcceso;

    public Usuario(){}

    public Usuario(int idUsuario, String user, String password, boolean nivelDeAcceso) {
        IdUsuario = idUsuario;
        User = user;
        Password = password;
        NivelDeAcceso = nivelDeAcceso;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isNivelDeAcceso() {
        return NivelDeAcceso;
    }

    public void setNivelDeAcceso(boolean nivelDeAcceso) {
        NivelDeAcceso = nivelDeAcceso;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "IdUsuario=" + IdUsuario +
                ", User='" + User + '\'' +
                ", Password='" + Password + '\'' +
                ", NivelDeAcceso=" + NivelDeAcceso +
                '}';
    }
}
