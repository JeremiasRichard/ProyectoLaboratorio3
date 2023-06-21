package com.example.main.servicios;

import com.example.main.DTOs.ArregloDTO;
import com.example.main.datos.ArregloRepoImpl;
import com.example.main.datos.MecanicoRepoImpl;
import com.example.main.datos.VehiculoRepoImpl;
import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.datos.excepciones.EntidadNoEncontradaException;
import com.example.main.enums.EstadoReparacion;
import com.example.main.modelos.Arreglo;

import java.util.List;
import java.util.stream.Collectors;

public class ArregloServiceImpl implements BaseService<Arreglo> {
    private ArregloRepoImpl arregloRepo;
    private MecanicoRepoImpl mecanicoRepo = new MecanicoRepoImpl();
    private VehiculoRepoImpl vehiculoRepo = new VehiculoRepoImpl();

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
        arreglo.setEstadoReparacion(EstadoReparacion.FINALIZADO);
        arregloRepo.editar(arreglo);
    }

    public List<Arreglo> listar(){
        return arregloRepo.listar();
    }
    public List<Arreglo> listarActivos(){
        return arregloRepo.listar().stream().filter(arreglo -> arreglo.getEstadoReparacion() != EstadoReparacion.FINALIZADO).toList();
    }

    public Arreglo buscarPorId(int id){
        return arregloRepo.buscarPorId(id);
    }

    public List<ArregloDTO> listarArreglosDTO(){
        return arreglosToArreglosDTO(this.listarActivos());
    }
    public List<ArregloDTO> arreglosToArreglosDTO(List<Arreglo> arreglos){
        return arreglos.stream().map(this::convertirAArregloDTO).toList();
    }
    public ArregloDTO convertirAArregloDTO(Arreglo arreglo) {
        ArregloDTO arregloDTO = new ArregloDTO();
        arregloDTO.setIdEmpleado(arreglo.getIdEmpleado());
        arregloDTO.setIdArreglo(arreglo.getIdArreglo());
        arregloDTO.setPatente(arreglo.getPatente());
        arregloDTO.setMarca(vehiculoRepo.buscarPorPatente(arreglo.getPatente()).getMarca());
        arregloDTO.setTipoVehiculo(vehiculoRepo.buscarPorPatente(arreglo.getPatente()).getTipoVehiculo());
        arregloDTO.setIdEmpleado(arreglo.getIdEmpleado());
        arregloDTO.setEspecialidad(mecanicoRepo.buscarPorId(arreglo.getIdEmpleado()).getEspecialidad());
        arregloDTO.setAnioFabricacion(vehiculoRepo.buscarPorPatente(arreglo.getPatente()).getAnioFabricacion());
        arregloDTO.setDniCliente(arreglo.getDniCliente());
        arregloDTO.setObservacionesCliente(arreglo.getObservacionesDelCliente());
        arregloDTO.setObservacionesMecanico(arreglo.getObservacionesDelMecanico());
        arregloDTO.setEstadoReparacion(arreglo.getEstadoReparacion());
        return arregloDTO;
    }

}
