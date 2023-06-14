package com.example.main.servicios;

import com.example.main.datos.MecanicoRepoImpl;
import com.example.main.modelos.Arreglo;
import com.example.main.modelos.Mecanico;

import java.util.ArrayList;
import java.util.List;

public class MecanicoServiceImpl implements BaseService<Mecanico>{
    private MecanicoRepoImpl mecanicoRepo;

    public MecanicoServiceImpl(){
        this.mecanicoRepo = new MecanicoRepoImpl();
    }

    public void agregar(Mecanico mecanico){
        mecanicoRepo.agregar(mecanico);
    }

    public void editar(int id, Mecanico mecanico){
        mecanicoRepo.editar(mecanico);
    }

    public void eliminar(int id){
        mecanicoRepo.eliminar(id);
    }

    public void eliminadoLogico(int id){
        Mecanico mecanico = mecanicoRepo.buscarPorId(id);
        mecanico.setActivo(false);
        mecanicoRepo.editar(mecanico);
    }

    public List<Mecanico> listar(){
        return mecanicoRepo.listar();
    }

    public Mecanico buscarPorId(int id){
        return mecanicoRepo.buscarPorId(id);
    }
    public List<Arreglo> listarTareasAsignadas(){
        //Logica obtener lista de tareas asignadas
        return new ArrayList<>();
    }

    public void cargarDetalle(){
        //Logica cargar detalle y persistirlo en el Arreglo (editar arreglo).
    }
}
