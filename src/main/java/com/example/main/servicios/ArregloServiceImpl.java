package com.example.main.servicios;

import com.example.main.datos.ArregloRepoImpl;
import com.example.main.modelos.Arreglo;

import java.util.List;

public class ArregloServiceImpl implements BaseService<Arreglo> {
    private ArregloRepoImpl arregloRepo;

    public ArregloServiceImpl(){
        this.arregloRepo = new ArregloRepoImpl();
    }

    public void agregar(Arreglo arreglo){
        //arregloRepo.agregar(arreglo);
    }

    public void editar(Arreglo arreglo){
        arregloRepo.editar(arreglo);
    }

    public void eliminar(Arreglo a){
        arregloRepo.eliminar(a);
    }

    public void eliminadoLogico(int id){
        Arreglo arreglo = arregloRepo.buscarPorId(id);
        arregloRepo.editar(arreglo);
    }

    public List<Arreglo> listar(){
        return arregloRepo.listar();
    }

    public Arreglo buscarPorId(int id){
        return arregloRepo.buscarPorId(id);
    }
}
