package com.example.main.servicios;

import com.example.main.datos.ArregloRepoImpl;
import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.datos.excepciones.EntidadNoEncontradaException;
import com.example.main.modelos.Arreglo;

import java.util.List;

public class ArregloServiceImpl implements BaseService<Arreglo> {
    private ArregloRepoImpl arregloRepo;

    public ArregloServiceImpl(){
        this.arregloRepo = new ArregloRepoImpl();
    }

    public void agregar(Arreglo arreglo) throws EntidadDuplicadaException {
        arregloRepo.agregar(arreglo);
    }

    public void editar(Arreglo arreglo) throws EntidadNoEncontradaException {
        arregloRepo.editar(arreglo);
    }

    public void eliminar(Arreglo a) throws EntidadNoEncontradaException {
        arregloRepo.eliminar(a);
    }

    public void eliminadoLogico(int id) throws EntidadNoEncontradaException {
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
