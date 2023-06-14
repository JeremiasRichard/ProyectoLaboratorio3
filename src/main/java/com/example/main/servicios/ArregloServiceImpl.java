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
        arregloRepo.agregar(arreglo);
    }

    public void editar(int id, Arreglo arreglo){
        arregloRepo.editar(id, arreglo);
    }

    public void eliminar(int id){
        arregloRepo.eliminar(id);
    }

    public void eliminadoLogico(int id){
        Arreglo arreglo = arregloRepo.buscarPorId(id);
        arreglo.setActivo(false);
        arregloRepo.editar(arreglo.getIdArreglo(),arreglo);
    }

    public List<Arreglo> listar(){
        return arregloRepo.listar();
    }

    public Arreglo buscarPorId(int id){
        return arregloRepo.buscarPorId(id);
    }
}
