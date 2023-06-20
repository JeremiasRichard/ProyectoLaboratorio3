package com.example.main.datos;

import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.datos.excepciones.EntidadNoEncontradaException;
import com.example.main.modelos.Arreglo;
import com.example.main.modelos.Cliente;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ClienteRepositoryTest {
    ClienteRepoImpl clienteRepo = new ClienteRepoImpl();

    @Test
    public void debePersistirEnArchivo() throws EntidadDuplicadaException {
        Cliente cliente = new Cliente("Carlos","Bianco","23098789",new ArrayList<Integer>(),"223045648", Arrays.asList("Patente1","Patente2"));
        Cliente cliente2 = new Cliente("Carlos","Perez","21313123131",new ArrayList<Integer>(),"223045648", Arrays.asList("Patente3","Patente4"));

        clienteRepo.agregar(cliente);
        clienteRepo.agregar(cliente2);
    }

    @Test
    void debeRetornarListaDeClientes(){
        clienteRepo.listar().forEach(System.out::println);
    }

    @Test
    public void debeModificarEnArchivo() throws EntidadNoEncontradaException{
        Cliente jose = new Cliente("Jose","Bergues","32795875",new ArrayList<>(),"491293",Arrays.asList("XD999"));
        jose.setActivo(false);
        clienteRepo.editar(jose);
    }
}
