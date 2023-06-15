package com.example.main.datos;


import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.modelos.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepoImpl implements Repositorio<Cliente>{
    private final File archivo = new File("src/main/resources/archivos/clientes.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Cliente> listaClientes;

    @Override
    public void cargar() {
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Cliente.class);
            this.listaClientes = mapper.readValue(archivo, collectionType);
        } catch (IOException e) {
            this.listaClientes = new ArrayList<>();
        }
    }

    @Override
    public void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, this.listaClientes);
        } catch (IOException e) {
            throw new RuntimeException(); //Cambiar
        }
    }

    @Override
    public void agregar(Cliente cliente) throws EntidadDuplicadaException {
        cargar();
        cliente.setIdCliente(listaClientes.size()+1);
        this.listaClientes.add(cliente);
        guardar();
    }

    @Override
    public void editar(Cliente nuevo) {
        cargar();
        for (int i =0;i<this.listaClientes.size();i++) {
                if(listaClientes.get(i).getIdCliente() == nuevo.getIdCliente()){
                    listaClientes.set(i,nuevo);
            }
        }
        guardar();
    }

    @Override
    public Cliente buscarPorId(int id) {
        cargar();
        Cliente encontrado = null;
        for (Cliente cliente : listaClientes) {
            if (cliente.getIdCliente() == id) {
                encontrado = cliente;
                break;
            }
        }
        return encontrado;
    }

    @Override
    public void eliminar(Cliente objeto) {
        cargar();
        this.listaClientes.removeIf(cliente->cliente.equals(objeto));
        guardar();
    }

    @Override
    public List<Cliente> listar() {
        cargar();
        return this.listaClientes;
    }
}
