package com.example.main.datos;

import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.modelos.Arreglo;
import com.example.main.servicios.ArregloServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArregloRepositoryTest {
    private ArregloRepoImpl arregloRepo = new ArregloRepoImpl();
    private ArregloServiceImpl arregloService = new ArregloServiceImpl();
    @BeforeAll
    void cargarArchivo(){
        Arreglo arr1 = new Arreglo(1,"XD54","12332111",2,"No anda joder");
        Arreglo arr2 = new Arreglo(2,"XD55","12332222",1,"No anda tio");
        Arreglo arr3 = new Arreglo(3,"XD56","11332444",2,"No anda madre mia");
        Arreglo arr4 = new Arreglo(4,"XD57","123435777",1,"No anda conio");
        try{
            arregloRepo.agregar(arr1);
            arregloRepo.agregar(arr2);
            arregloRepo.agregar(arr3);
            arregloRepo.agregar(arr4);
        }catch (EntidadDuplicadaException e){
        }
    }
    @AfterAll
    void eliminarDatosArchivo(){
        File archivo = new File("src/main/resources/archivos/arreglos.json");

        try (FileWriter fw = new FileWriter(archivo)){
            fw.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void buscarXMecanicoTest(){
        arregloRepo.buscarTodosPorMecanico(2).forEach(System.out::println);
    }

    @Test
    void buscarXPatenteTest(){
        arregloRepo.buscarTodosPorPatente(Arrays.asList(new String[]{"XD57","XD55"})).forEach(System.out::println);
    }
    @Test
    void listar(){
        arregloService.listarArreglosDTO().forEach(System.out::println);
    }
}
