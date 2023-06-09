 package com.example.main.modelos;

public class Usuario {
    private int idUsuario;
    private String user;
    private String password;
    private boolean esAdmin;
    private boolean activo;
    public Usuario() {
    }

    public Usuario(String userName, String password, boolean esAdmin) {
        this.user = userName;
        this.password = password;
        this.esAdmin = esAdmin;
        this.activo = true;
    }
    public Usuario(int idUsuario, String userName, String password, boolean esAdmin) {//TODO: revisar this para evitar repetir parametros
        this.idUsuario = idUsuario;
        this.user = userName;
        this.password = password;
        this.esAdmin = esAdmin;
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

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean nivelDeAcceso) {
        this.esAdmin = nivelDeAcceso;
    }

    public boolean getActivo() { return activo; }

    public void setActivo(boolean activo) { this.activo = activo; }
    //endregion


    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", esAdmin=" + esAdmin +
                ", activo=" + activo +
                '}';
    }
}
