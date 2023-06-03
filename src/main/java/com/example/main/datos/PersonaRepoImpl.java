package com.example.main.datos;
import com.example.main.modelos.Persona;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class PersonaRepoImpl implements Repositorio<Persona> {

    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void persistir(Persona... nuevos) {
    }

    @Override
    public void editar(int id, Persona nuevo) {

    }

    @Override
    public Persona buscarPorId(int id) {
        return null;
    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public List<Persona> listar() {
        return null;
    }
}
