package com.example.main.DTOs;

import com.example.main.modelos.Vehiculo;

import java.util.List;

public class ClienteDTO
{
    private String nombre;
    private String apellido;
    private String dni;
    private String nroTelefono;
    private List<Integer> historialArreglos;
    private List<Vehiculo> listaVehiculos;
    private boolean activo;

    public ClienteDTO(String nombre,String apellido,String nroTelefono,String dni,List<Vehiculo> listaVehiculos,List<Integer>historialArreglos)
    {
        this.nombre = nombre;
        this.apellido=apellido;
        this.dni=dni;
        this.historialArreglos=historialArreglos;
        this.listaVehiculos = listaVehiculos;
        this.nroTelefono = nroTelefono;
        this.activo=true;
    }
}
