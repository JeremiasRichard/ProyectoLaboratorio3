package com.example.main.servicios;

import com.example.main.datos.VehiculoRepoImpl;
import com.example.main.modelos.Vehiculo;

import java.util.List;

public class VehiculoServiceImpl implements BaseService<Vehiculo>{
    private VehiculoRepoImpl vehiculoRepo;

    public VehiculoServiceImpl(){
        this.vehiculoRepo = new VehiculoRepoImpl();
    }

    public void agregar(Vehiculo vehiculo){
        vehiculoRepo.agregar(vehiculo);
    }

    public void editar(int id, Vehiculo vehiculo){
        vehiculoRepo.editar(id, vehiculo);
    }

    public void eliminar(int id){
        vehiculoRepo.eliminar(id);
    }

    public void eliminadoLogico(int id){
        Vehiculo vehiculo = vehiculoRepo.buscarPorId(id);
        vehiculo.setActivo(false);
        vehiculoRepo.editar(vehiculo.getIdVehiculo(), vehiculo);
    }

    public List<Vehiculo> listar(){
        return vehiculoRepo.listar();
    }

    public Vehiculo buscarPorId(int id){
        return vehiculoRepo.buscarPorId(id);
    }
}
