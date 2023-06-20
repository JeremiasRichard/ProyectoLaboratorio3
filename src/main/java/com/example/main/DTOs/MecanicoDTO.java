package com.example.main.DTOs;

import com.example.main.enums.Especialidad;
import com.example.main.enums.TipoVehiculo;
import com.example.main.modelos.Arreglo;

import java.util.List;
import java.util.Objects;

public class MecanicoDTO
{
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String nroTelefono;
    private transient List<Arreglo> listaArreglos;
    private TipoVehiculo tipoVehiculo;
    private Especialidad especialidad;
    private boolean activo;

    public MecanicoDTO(int id, String nombre, String apellido, String dni, String nroTelefono, TipoVehiculo tipoVehiculo, Especialidad especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.nroTelefono = nroTelefono;
        this.tipoVehiculo = tipoVehiculo;
        this.especialidad = especialidad;
        this.activo=true;
    }

    public MecanicoDTO(String nombre, String apellido, String dni, String nroTelefono, TipoVehiculo tipoVehiculo, Especialidad especialidad, boolean activo) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.nroTelefono = nroTelefono;
        this.tipoVehiculo = tipoVehiculo;
        this.especialidad = especialidad;
        this.activo = activo;
    }

    public MecanicoDTO() {
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(String nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    public List<Arreglo> getListaArreglos() {
        return listaArreglos;
    }

    public void setListaArreglos(List<Arreglo> listaArreglos) {
        this.listaArreglos = listaArreglos;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isEstado()
    {
        if(activo == true)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MecanicoDTO that)) return false;
        return isActivo() == that.isActivo() && Objects.equals(getDni(), that.getDni()) && Objects.equals(getNroTelefono(), that.getNroTelefono()) && getTipoVehiculo() == that.getTipoVehiculo() && getEspecialidad() == that.getEspecialidad();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDni(), getNroTelefono(), getTipoVehiculo(), getEspecialidad(), isActivo());
    }

    @Override
    public String toString() {
        return "MecanicoDTO{" +
                "id='" + id + '\'' +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", nroTelefono='" + nroTelefono + '\'' +
                ", listaArreglos=" + listaArreglos +
                ", tipoVehiculo=" + tipoVehiculo +
                ", especialidad=" + especialidad +
                ", activo=" + activo +
                '}';
    }
}