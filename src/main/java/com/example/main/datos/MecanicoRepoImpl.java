package com.example.main.datos;

import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.datos.excepciones.EntidadNoEncontradaException;
import com.example.main.modelos.Mecanico;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MecanicoRepoImpl implements Repositorio<Mecanico> {
    private final File archivo = new File("src/main/resources/archivos/mecanicos.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Mecanico> listaMecanicos;

    @Override
    public void cargar() {
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Mecanico.class);
            this.listaMecanicos = mapper.readValue(archivo, collectionType);
        } catch (IOException e) {
            this.listaMecanicos = new ArrayList<>();
        }
    }

    @Override
    public void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, this.listaMecanicos);
        } catch (IOException e) {
            throw new RuntimeException(); //Cambiar
        }
    }

    @Override
    public void agregar(Mecanico nuevo) throws EntidadDuplicadaException {
        cargar();
        if(buscarPorId(nuevo.getIdEmpleado()) != null) throw new EntidadDuplicadaException("El mecanico solicitado ya existe.");
        nuevo.setIdEmpleado(listaMecanicos.size()+1);
        nuevo.setUsuario(listaMecanicos.size()+1);
        this.listaMecanicos.add(nuevo);
        guardar();
    }

    @Override
    public void editar(Mecanico nuevo) throws EntidadNoEncontradaException {
        cargar();
        if(buscarPorId(nuevo.getIdEmpleado()) == null) throw new EntidadNoEncontradaException("El mecanico solicitado no existe.");
        for (int i = 0; i<this.listaMecanicos.size(); i++) {
            String dniActual = this.listaMecanicos.get(i).getDni();

            if (dniActual.equals(nuevo.getDni())) {
                listaMecanicos.set(i,nuevo);
                break;
            }
        }
        guardar();
    }

    @Override
    public Mecanico buscarPorId(int id) {
        cargar();
        Mecanico encontrado = null;
        for (Mecanico mecanico : listaMecanicos) {
            if (mecanico.getIdEmpleado() == id) {
                encontrado = mecanico;
                break;
            }
        }
        return encontrado;
    }

    @Override
    public void eliminar(Mecanico objeto) throws EntidadNoEncontradaException {
        if(buscarPorId(objeto.getIdEmpleado()) == null){
            throw new EntidadNoEncontradaException("El mecanico solicitado no existe");
        }
        this.listaMecanicos.removeIf(actual-> actual.getDni().equals(objeto.getDni()));
        guardar();
    }

    @Override
    public List<Mecanico> listar() {
        cargar();
        return this.listaMecanicos;
    }
    public Mecanico buscarPorIdUsuario(int idUsuario){
        Mecanico encontrado = null;
        for(Mecanico actual : this.listaMecanicos){
            if(actual.getUsuario() == idUsuario){
                encontrado = actual;
                break;
            }
        }
        return encontrado;
    }
}
