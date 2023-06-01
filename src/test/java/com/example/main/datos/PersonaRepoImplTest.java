package com.example.main.datos;

import com.example.main.modelos.Persona;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class PersonaRepoImplTest {
    PersonaRepoImpl personaRepo = new PersonaRepoImpl();

    @Test
    @DisplayName("Persistencia de objetos")
    void debenPersitirseYListarseLosObjetos(){
        Persona[] personas = new Persona[]{
                new Persona("Carlos","Bianco"),
                new Persona("Carlos","Biancusco"),
                new Persona("Karla","Gomez"),
        };
        personaRepo.persistir(personas);

        List<Persona> listaPersonas = personaRepo.listar();
        Assertions.assertNotNull(listaPersonas,"La lista no debe ser nula.");
        listaPersonas.forEach(System.out::println);
    }
}
