package com.example.main.datos;

import com.example.main.datos.excepciones.EntidadDuplicadaException;
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
        nuevo.setIdEmpleado(listaMecanicos.size()+1);
        this.listaMecanicos.add(nuevo);
        guardar();
    }

    @Override
    public void editar(Mecanico nuevo) {
        cargar();
        for (Mecanico mecanico : listaMecanicos) {
            if (mecanico.equals(nuevo)) {
                //Persona
                mecanico.setNombre(nuevo.getNombre());
                mecanico.setApellido(nuevo.getApellido());
                //Mecanico
                mecanico.setEspecialidad(nuevo.getEspecialidad());
                mecanico.setTipoVehiculo(nuevo.getTipoVehiculo());

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
    public void eliminar(int id) {
        for (Mecanico mecanico : this.listaMecanicos) {
            if (mecanico.getIdEmpleado() == id) {
                this.listaMecanicos.remove(mecanico);
                break;
            }
        }
        guardar();
    }

    @Override
    public List<Mecanico> listar() {
        cargar();
        return this.listaMecanicos;
    }
}
