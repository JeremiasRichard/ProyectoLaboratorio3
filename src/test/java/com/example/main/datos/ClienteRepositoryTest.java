package com.example.main.datos;

import com.example.main.modelos.Arreglo;
import com.example.main.modelos.Cliente;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ClienteRepositoryTest {
    ClienteRepoImpl clienteRepo = new ClienteRepoImpl();

    @Test
    public void debePersistirEnArchivo(){
        Cliente cliente = new Cliente("Carlos","Bianco","23098789",1,new ArrayList<Arreglo>(),123,"carlos@gmail.com","Saavedra 1111", Arrays.asList("Patente1","Patente2"));
        Cliente cliente2 = new Cliente("Karla","Gomez","23098789",2,new ArrayList<Arreglo>(),321,"kgomez@gmail.com","Saavedra 2222", Arrays.asList("Patente3","Patente4"));
        clienteRepo.agregar(cliente);
        clienteRepo.agregar(cliente2);
    }

    @Test
    void debeRetornarListaDeClientes(){
        clienteRepo.listar().forEach(System.out::println);
    }
}
