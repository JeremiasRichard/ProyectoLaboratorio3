package com.example.main.datos;

import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.modelos.Vehiculo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
    public void agregar(Vehiculo nuevo) throws EntidadDuplicadaException {
        cargar();
        nuevo.setIdVehiculo(listaVehiculos.size()+1);
        this.listaVehiculos.add(nuevo);
        guardar();
    }

    @Override
    public void editar(Vehiculo nuevo) {
        cargar();
        for (int i = 0; i < this.listaVehiculos.size(); i++) {
            Vehiculo actual = this.listaVehiculos.get(i);
            if(actual.getPatente().equals(nuevo.getPatente())){
                this.listaVehiculos.set(i,nuevo);
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
    public void eliminar(Vehiculo objeto) {
        cargar();
        this.listaVehiculos.removeIf(actual->actual.getPatente().equals(objeto.getPatente()));
        guardar();
    }

    @Override
    public List<Vehiculo> listar() {
        cargar();
        return this.listaVehiculos;
    }

    public List<Vehiculo> buscarTodosPorPatente(List<String> patentes){
        cargar();
        return this.listaVehiculos
                .stream()
                .filter(patentes::contains)
                .collect(Collectors.toList());
    }
}
