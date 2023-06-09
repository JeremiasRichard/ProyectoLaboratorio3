package com.example.main.datos;

import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.datos.excepciones.EntidadNoEncontradaException;
import com.example.main.enums.EstadoReparacion;
import com.example.main.modelos.Arreglo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArregloRepoImpl implements Repositorio<Arreglo> {

    private final File archivo = new File("src/main/resources/archivos/arreglos.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Arreglo> listaArreglos;

    @Override
    public void cargar() {
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Arreglo.class);
            this.listaArreglos = mapper.readValue(archivo, collectionType);
        } catch (IOException e) {
            this.listaArreglos = new ArrayList<>();
        }
    }

    @Override
    public void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, this.listaArreglos);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void agregar(Arreglo nuevo) throws EntidadDuplicadaException {
        cargar();
        nuevo.setIdArreglo(listaArreglos.size() + 1);
        this.listaArreglos.add(nuevo);
        guardar();
    }

    @Override
    public void editar(Arreglo nuevo) throws EntidadNoEncontradaException {
        cargar();
        if(buscarPorId(nuevo.getIdArreglo()) == null) throw new EntidadNoEncontradaException("El arreglo solicitado no existe.");
        for (int i = 0; i < this.listaArreglos.size(); i++) {
            if (listaArreglos.get(i).getIdArreglo() == nuevo.getIdArreglo()) {
                listaArreglos.set(i, nuevo);
                break;
            }
        }
        guardar();
    }

    @Override
    public Arreglo buscarPorId(int id) {
        cargar();
        Arreglo encontrado = null;
        for (Arreglo arr : listaArreglos) {
            if (arr.getIdArreglo() == id) {
                encontrado = arr;
                break;
            }
        }
        return encontrado;
    }

    @Override
    public void eliminar(Arreglo objeto) throws EntidadNoEncontradaException {
        cargar();
        if(buscarPorId(objeto.getIdArreglo()) == null){
            throw new EntidadNoEncontradaException("El arreglo solicitado no existe");
        }
        this.listaArreglos.removeIf(arr-> arr.getIdArreglo() == objeto.getIdArreglo());
        guardar();
    }

    @Override
    public List<Arreglo> listar() {
        cargar();
        return this.listaArreglos;
    }
    public List<Arreglo> buscarTodosPorPatente(List<String> patentes){
        cargar();
        return this.listaArreglos
                .stream()
                .filter(actual -> patentes.contains(actual.getPatente()))
                .toList();
    }
    public List<Arreglo> buscarTodosPorMecanico(int idMecanico){
        cargar();
        return this.listaArreglos
                .stream()
                .filter(actual -> actual.getIdEmpleado() == idMecanico && actual.getEstadoReparacion() != EstadoReparacion.FINALIZADO)
                .toList();
    }

}
