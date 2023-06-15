package com.example.main.datos;

import com.example.main.datos.excepciones.EntidadDuplicadaException;
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
            throw new RuntimeException(); //Cambiar
        }
    }

    @Override
    public void agregar(Arreglo nuevo) throws EntidadDuplicadaException {
        cargar();
        nuevo.setIdArreglo(listaArreglos.size()+1);
        this.listaArreglos.add(nuevo);
        guardar();
    }

    @Override
    public void editar(Arreglo nuevo) {
        cargar();
        for (Arreglo arreglo : listaArreglos) {
            if (arreglo.equals(nuevo)) {
                arreglo.setIdEmpleado(nuevo.getIdEmpleado());
                arreglo.setIdEmpleado(nuevo.getIdEmpleado());
                arreglo.setEstadoReparacion(nuevo.getEstadoReparacion());
                arreglo.setDetalleArreglo(nuevo.getDetalleArreglo());
                arreglo.setObservacionesDelCliente(nuevo.getObservacionesDelCliente());
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
    public void eliminar(int id) {
        for (Arreglo arr : this.listaArreglos) {
            if (arr.getIdArreglo() == id) {
                this.listaArreglos.remove(arr);
                break;
            }
        }
        guardar();
    }

    @Override
    public List<Arreglo> listar() {
        cargar();
        return this.listaArreglos;
    }
}
