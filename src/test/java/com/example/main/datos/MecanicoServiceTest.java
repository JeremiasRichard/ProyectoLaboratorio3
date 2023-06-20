package com.example.main.datos;

import com.example.main.DTOs.ArregloDTO;
import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.enums.Especialidad;
import com.example.main.enums.TipoVehiculo;
import com.example.main.modelos.*;
import com.example.main.servicios.MecanicoServiceImpl;
import com.example.main.servicios.UsuarioServiceImpl;
import com.example.main.utils.Encriptador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MecanicoServiceTest {
    MecanicoServiceImpl mecanicoService = new MecanicoServiceImpl();
    ArregloRepoImpl arregloRepo = new ArregloRepoImpl();
    VehiculoRepoImpl vehiculoRepo = new VehiculoRepoImpl();
    ClienteRepoImpl clienteRepo = new ClienteRepoImpl();
    MecanicoRepoImpl mecanicoRepo = new MecanicoRepoImpl();
    UsuarioRepoImpl usuarioRepo = new UsuarioRepoImpl();

    @Test
    void obtenerTareasTest() {
        // region datos
        List<Cliente> listaClientes = Arrays.asList(
                new Cliente("Carlos", "Bianco", "23654456", new ArrayList<>(), "123321", new ArrayList<>()),
                new Cliente("Karla", "Gomez", "77777777", new ArrayList<>(), "3212123", new ArrayList<>()));
        List<Vehiculo> listaVehiculos = Arrays.asList(
                new Vehiculo(2010, TipoVehiculo.AUTO, "Mercedes", "XDXD1"),
                new Vehiculo(2011, TipoVehiculo.MOTO, "Kawazaki", "XDXD2"),
                new Vehiculo(2012, TipoVehiculo.AUTO, "Alfa Romeo", "XDXD3"));
        List<Arreglo> listaArreglos = Arrays.asList(new Arreglo(1, "XDXD1", "23654456", 1, "KEAAAAAAACE"),
                new Arreglo(2, "XDXD2", "77777777", 2, "AGUANTIAAAAAAAAAA"),
                new Arreglo(3, "XDXD3", "77777777", 1, "AESEDE"));

        List<Mecanico> listaMecanicos = Arrays.asList(
                new Mecanico("Jeremias", "Richard", "40123321", "223666666", 1, new ArrayList<>(), TipoVehiculo.AUTO, Especialidad.MECANICA_GENERAL),
                new Mecanico("Pablo", "Morales", "38321332", "22377777", 2, new ArrayList<>(), TipoVehiculo.MOTO, Especialidad.ESTETICA),
                new Mecanico("Pablo", "Baldor", "35888888", "22377777", 3, new ArrayList<>(), TipoVehiculo.MOTO, Especialidad.ELECTRICIDAD)
        );
        //endregion

        listaClientes.forEach(c -> {
            try {
                clienteRepo.agregar(c);
            } catch (EntidadDuplicadaException e) {
                throw new RuntimeException(e);
            }
        });
        listaMecanicos.forEach(m -> {
            try {
                mecanicoRepo.agregar(m);
            } catch (EntidadDuplicadaException e) {
                throw new RuntimeException(e);
            }
        });
        listaArreglos.forEach(a -> {
            try {
                arregloRepo.agregar(a);
            } catch (EntidadDuplicadaException e) {
                throw new RuntimeException(e);
            }
        });
        listaVehiculos.forEach(v -> {
            try {
                vehiculoRepo.agregar(v);
            } catch (EntidadDuplicadaException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            usuarioRepo.agregar(new Usuario("jereRichard", Encriptador.obtenerMD5("123"), false));
        } catch (EntidadDuplicadaException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Listando tareas de Jeremias:");
        List<ArregloDTO> lista = mecanicoService.obtenerTareas(1);
        lista.forEach(System.out::println);
    }

    @Test
    void Test3()
    {
        mecanicoService.listarMecanicoPorEspecialidadYTipo(TipoVehiculo.AUTO,Especialidad.ELECTRICIDAD).forEach(System.out::println);
    }
}
