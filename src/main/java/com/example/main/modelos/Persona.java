package com.example.main.modelos;

public abstract class Persona {
    private String nombre;
    private String apellido;
    private int edad;
    private String dni;

    public Persona(){}

    public Persona(String nombre, String apellido, int edad, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
    }
    //region Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        edad = edad;
    }

    public String getDni() { return dni; }

    public void setDni(String dni) { this.dni = dni; }

    //endregion
    @Override
    public String toString() {
        return "Persona{" +
                "Nombre='" + nombre + '\'' +
                ", Apellido='" + apellido + '\'' +
                ", Edad=" + edad +
                ", Dni='" + dni +
                '}';
    }
}
