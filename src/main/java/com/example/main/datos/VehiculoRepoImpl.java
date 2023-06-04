package com.example.main.datos;

import com.example.main.modelos.Vehiculo;
import com.example.main.modelos.Vehiculo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class VehiculoRepoImpl implements  Repositorio<Vehiculo>{
    private final File archivo = new File("src/main/resources/archivos/vehiculos.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Vehiculo> listaVehiculos;

    @Override
    public void cargar() {
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Vehiculo.class);
            this.listaVehiculos = mapper.readValue(archivo, collectionType);
        } catch (IOException e) {
            this.listaVehiculos = new ArrayList<>();
        }
    }

    @Override
    public void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, this.listaVehiculos);
        } catch (IOException e) {
            throw new RuntimeException(); //Cambiar
        }
    }

    @Override
    public void agregar(Vehiculo... objeto) {
        cargar();
        this.listaVehiculos.addAll(List.of(objeto));
        guardar();
    }

    @Override
    public void editar(int id, Vehiculo nuevo) {
        cargar();
        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.getIdVehiculo() == id) {
                vehiculo = nuevo;
                break;
            }
        }
        guardar();
    }

    @Override
    public Vehiculo buscarPorId(int id) {
        cargar();
        Vehiculo encontrado = null;
        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.getIdVehiculo() == id) {
                encontrado = vehiculo;
                break;
            }
        }
        return encontrado;
    }

    @Override
    public void eliminar(int id) {
        for (Vehiculo vehiculo : this.listaVehiculos) {
            if (vehiculo.getIdVehiculo() == id) {
                this.listaVehiculos.remove(vehiculo);
                break;
            }
        }
        guardar();
    }

    @Override
    public List<Vehiculo> listar() {
        cargar();
        return this.listaVehiculos;
    }
}
