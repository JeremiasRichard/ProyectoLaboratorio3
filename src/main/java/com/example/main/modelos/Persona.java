package com.example.main.modelos;

import java.util.Objects;

public abstract class Persona {
    private String nombre;
    private String apellido;
    private String dni;

    public Persona(){}

    public Persona(String nombre, String apellido,String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }
    //region Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    //endregion
    @Override
    public String toString() {
        return "Persona{" +
                "Nombre='" + nombre + '\'' +
                ", Apellido='" + apellido + '\'' +
                ", Dni='" + dni +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(getNombre(), persona.getNombre()) && Objects.equals(getApellido(), persona.getApellido()) && Objects.equals(getDni(), persona.getDni());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDni());
    }
}
