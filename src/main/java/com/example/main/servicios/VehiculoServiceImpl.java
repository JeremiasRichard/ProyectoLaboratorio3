package com.example.main.servicios;

import com.example.main.datos.VehiculoRepoImpl;
import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.datos.excepciones.EntidadNoEncontradaException;
import com.example.main.modelos.Vehiculo;

import java.util.List;

public class VehiculoServiceImpl implements BaseService<Vehiculo>{
    private VehiculoRepoImpl vehiculoRepo;

    public VehiculoServiceImpl(){
        this.vehiculoRepo = new VehiculoRepoImpl();
    }

    public void agregar(Vehiculo vehiculo) throws EntidadDuplicadaException {
        vehiculoRepo.agregar(vehiculo);
    }

    public void editar(Vehiculo vehiculo) throws EntidadNoEncontradaException {
        vehiculoRepo.editar(vehiculo);
    }

    public void eliminar(Vehiculo vehiculo) throws EntidadNoEncontradaException {
        vehiculoRepo.eliminar(vehiculo);
    }

    public void eliminadoLogico(int id) throws EntidadNoEncontradaException {
        Vehiculo vehiculo = vehiculoRepo.buscarPorId(id);
        vehiculo.setActivo(false);
        vehiculoRepo.editar(vehiculo);
    }

    public List<Vehiculo> listar(){
        return vehiculoRepo.listar();
    }

    public Vehiculo buscarPorId(int id){
        return vehiculoRepo.buscarPorId(id);
    }

    public boolean buscarPorPatente(String patente)
    {
        return vehiculoRepo.buscarPorPatente(patente) == null;
    }

    public Vehiculo buscarPorPatenteDos(String patente)
    {
       return vehiculoRepo.buscarPorPatente(patente);
    }
}
