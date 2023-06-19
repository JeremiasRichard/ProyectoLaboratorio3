package com.example.main.servicios;

import com.example.main.DTOs.ArregloDTO;
import com.example.main.DTOs.MecanicoDTO;
import com.example.main.datos.ArregloRepoImpl;
import com.example.main.datos.MecanicoRepoImpl;
import com.example.main.datos.VehiculoRepoImpl;
import com.example.main.datos.excepciones.EntidadNoEncontradaException;
import com.example.main.enums.TipoVehiculo;
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

    public void editar(Mecanico mecanico) throws EntidadNoEncontradaException {
        mecanicoRepo.editar(mecanico);
    }

    public void eliminar(Mecanico mecanico) throws EntidadNoEncontradaException {
        mecanicoRepo.eliminar(mecanico);
    }

    public void eliminadoLogico(int id) throws EntidadNoEncontradaException {
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

    public MecanicoDTO convertAMecanicoDTO(Mecanico mecanico){
        MecanicoDTO mecanicoDTO = new MecanicoDTO();
        mecanicoDTO.setNombre(mecanico.getNombre());
        mecanicoDTO.setApellido(mecanico.getApellido());
        mecanicoDTO.setDni(mecanico.getDni());
        mecanicoDTO.setNroTelefono(mecanico.getTelefono());
        mecanicoDTO.setTipoVehiculo(mecanico.getTipoVehiculo());
        mecanicoDTO.setEspecialidad(mecanico.getEspecialidad());
        mecanicoDTO.setActivo(mecanico.getActivo());
        return mecanicoDTO;
    }
    public List<MecanicoDTO> listarMecanicosDTO() {
        return listar().stream().map(mecanico -> {
            MecanicoDTO mecanicoDTO = new MecanicoDTO();
            mecanicoDTO.setNombre(mecanico.getNombre());
            mecanicoDTO.setApellido(mecanico.getApellido());
            mecanicoDTO.setDni(mecanico.getDni());
            mecanicoDTO.setNroTelefono(mecanico.getTelefono());
            mecanicoDTO.setTipoVehiculo(mecanico.getTipoVehiculo());
            mecanicoDTO.setEspecialidad(mecanico.getEspecialidad());
            mecanicoDTO.setActivo(mecanico.getActivo());
            return mecanicoDTO;
        }).toList();
    }

    public List<MecanicoDTO> listarMecanicoPorTipoVehiculo(TipoVehiculo tipo){
        List<Mecanico> mecanicos = mecanicoRepo.listarPorTipoVehiculo(tipo);
        return mecanicos.stream().map(mecanico -> {
            MecanicoDTO mecanicoDTO = new MecanicoDTO();
            mecanicoDTO.setNombre(mecanico.getNombre());
            mecanicoDTO.setApellido(mecanico.getApellido());
            mecanicoDTO.setDni(mecanico.getDni());
            mecanicoDTO.setNroTelefono(mecanico.getTelefono());
            mecanicoDTO.setTipoVehiculo(mecanico.getTipoVehiculo());
            mecanicoDTO.setEspecialidad(mecanico.getEspecialidad());
            mecanicoDTO.setActivo(mecanico.getActivo());
            return mecanicoDTO;
        }).toList();
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
