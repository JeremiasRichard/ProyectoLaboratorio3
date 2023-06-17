package com.example.main.servicios;

import com.example.main.DTOs.ArregloDTO;
import com.example.main.datos.ArregloRepoImpl;
import com.example.main.datos.MecanicoRepoImpl;
import com.example.main.datos.VehiculoRepoImpl;
import com.example.main.modelos.Arreglo;
import com.example.main.modelos.Mecanico;
import com.example.main.modelos.Vehiculo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MecanicoServiceImpl implements BaseService<Mecanico> {
    private MecanicoRepoImpl mecanicoRepo;
    private ArregloRepoImpl arregloRepo;
    private VehiculoRepoImpl vehiculoRepo;


    public MecanicoServiceImpl() {
        this.vehiculoRepo = new VehiculoRepoImpl();
        this.mecanicoRepo = new MecanicoRepoImpl();
        this.arregloRepo = new ArregloRepoImpl();
    }

    public void agregar(Mecanico mecanico) {
        //mecanicoRepo.agregar(mecanico);
    }

    public void editar(Mecanico mecanico) {
        mecanicoRepo.editar(mecanico);
    }

    public void eliminar(Mecanico mecanico) {
        mecanicoRepo.eliminar(mecanico);
    }

    public void eliminadoLogico(int id) {
        Mecanico mecanico = mecanicoRepo.buscarPorId(id);
        mecanico.setActivo(false);
        mecanicoRepo.editar(mecanico);
    }

    public List<Mecanico> listar() {
        return mecanicoRepo.listar();
    }

    public Mecanico buscarPorId(int id) {
        return mecanicoRepo.buscarPorId(id);
    }

    public List<Arreglo> listarTareasAsignadas() {
        //Logica obtener lista de tareas asignadas
        return new ArrayList<>();
    }

    public void cargarDetalle() {
        //Logica cargar detalle y persistirlo en el Arreglo (editar arreglo).
    }

    public List<ArregloDTO> obtenerTareas(int idUsuario) {

        Mecanico mecanico = mecanicoRepo.buscarPorId(idUsuario);
        List<Arreglo> tareas = arregloRepo.buscarTodosPorMecanico(mecanico.getIdEmpleado());

        return tareas.stream()
                .map(arreglo -> {
                            Vehiculo vehiculoActual = vehiculoRepo.buscarPorPatente(arreglo.getPatente());

                            return new ArregloDTO(
                                    arreglo.getIdArreglo(),
                                    arreglo.getPatente(),
                                    vehiculoActual.getMarca(),
                                    vehiculoActual.getAnioFabricacion(),
                                    arreglo.getDniCliente(),
                                    arreglo.getObservacionesDelCliente(),
                                    arreglo.getEstadoReparacion());
                        }
                )
                .toList();
    }

}
