package com.example.main.utils;

import com.example.main.datos.ArregloRepoImpl;
import com.example.main.datos.ClienteRepoImpl;
import com.example.main.datos.MecanicoRepoImpl;
import com.example.main.datos.VehiculoRepoImpl;
import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.enums.Especialidad;
import com.example.main.enums.TipoVehiculo;
import com.example.main.modelos.Arreglo;
import com.example.main.modelos.Cliente;
import com.example.main.modelos.Mecanico;
import com.example.main.modelos.Vehiculo;

import java.util.ArrayList;
import java.util.Arrays;

public class GeneradorArchivos {
    private static ArregloRepoImpl  arregloRepo = new ArregloRepoImpl();
    private static VehiculoRepoImpl vehiculoRepo = new VehiculoRepoImpl();
    private static ClienteRepoImpl clienteRepo = new ClienteRepoImpl();
    private static MecanicoRepoImpl mecanicoRepo = new MecanicoRepoImpl();

    public static void generarArchivos() {
        Arrays.asList(
                new Mecanico("Carlos", "Bianco", "55848233", "4751131", new ArrayList<>(), TipoVehiculo.AUTO, Especialidad.MECANICA_GENERAL),
                new Mecanico("Karla", "Gomez", "37888439", "4751113", new ArrayList<>(), TipoVehiculo.AUTO, Especialidad.ELECTRICIDAD),
                new Mecanico("Angel", "Mendez", "83888353", "4751311", new ArrayList<>(), TipoVehiculo.AUTO, Especialidad.ESTETICA),
                new Mecanico("Monica", "Gonzales", "25848343", "4751121", new ArrayList<>(), TipoVehiculo.MOTO, Especialidad.MECANICA_GENERAL),
                new Mecanico("Esteban", "Dietrich", "75556453158749", "4721112", new ArrayList<>(), TipoVehiculo.MOTO, Especialidad.ELECTRICIDAD),
                new Mecanico("Agustin", "Erpen", "35888333", "4761122", new ArrayList<>(), TipoVehiculo.MOTO, Especialidad.ESTETICA),
                new Mecanico("Fernanda", "Orizuela", "55885335", "4951212", new ArrayList<>(), TipoVehiculo.CAMION, Especialidad.MECANICA_GENERAL),
                new Mecanico("Wanda", "Arana", "37858338", "4851212", new ArrayList<>(), TipoVehiculo.CAMION, Especialidad.ELECTRICIDAD),
                new Mecanico("Elsa", "Bandija", "65884332", "4621212", new ArrayList<>(), TipoVehiculo.CAMION, Especialidad.ESTETICA)
        ).forEach(nuevo -> {
            try {
                mecanicoRepo.agregar(nuevo);
            } catch (EntidadDuplicadaException e) {
                throw new RuntimeException(e);
            }
        });

        Arrays.asList(
                new Cliente("Daniel","Marzoli","55929334",new ArrayList<>(),"4645814",Arrays.asList("RTX5459")),
                new Cliente("Flavia","Gorriti","46332343",new ArrayList<>(),"4435844",Arrays.asList("RXT5248")),
                new Cliente("Ernesto","Jurado","37943234",new ArrayList<>(),"4715814",Arrays.asList("FXX5517")),
                new Cliente("Cristian","Etchber","28694314",new ArrayList<>(),"4975244",Arrays.asList("TTX5726")),
                new Cliente("Nahuel","Ganso","59795334",new ArrayList<>(),"4895244",Arrays.asList("NTX5835"))
        ).forEach(cliente -> {
            try {
                clienteRepo.agregar(cliente);
            } catch (EntidadDuplicadaException e) {
                throw new RuntimeException(e);
            }
        });

        Arrays.asList(
                new Vehiculo(1995,TipoVehiculo.AUTO,"Mercedes Benz","RTX5459"),
                new Vehiculo(1974,TipoVehiculo.MOTO,"Kawazaki 600","RXT5248"),
                new Vehiculo(2021,TipoVehiculo.CAMION,"Ford F350","FXX5517"),
                new Vehiculo(1984,TipoVehiculo.CAMION,"Renault","TTX5726"),
                new Vehiculo(1976,TipoVehiculo.AUTO,"Volkswagen","NTX5835")
        ).forEach(vehiculo -> {
            try {
                vehiculoRepo.agregar(vehiculo);
            } catch (EntidadDuplicadaException e) {
                throw new RuntimeException(e);
            }
        });

        Arrays.asList(
          new Arreglo(1,"RTX5459","55929334",1,"Se quedo sin frenos."),
          new Arreglo(2,"RTX5459","55929334",1,"Se quedo sin Luces."),
          new Arreglo(3,"RXT5248","46332343",1,"Para pintar."),
          new Arreglo(4,"FXX5517","37943234",1,"Caja de camios rota."),
          new Arreglo(5,"TTX5726","28694314",1,"Rayon en la caja."),
          new Arreglo(6,"NTX5835","59795334",1,"Rayon en la puerta.")
        ).forEach(
                arreglo -> {
                    try {
                        arregloRepo.agregar(arreglo);
                    } catch (EntidadDuplicadaException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}