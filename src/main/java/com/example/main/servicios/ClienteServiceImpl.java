package com.example.main.servicios;

import com.example.main.datos.ClienteRepoImpl;
import com.example.main.modelos.Cliente;

import java.util.List;

public class ClienteServiceImpl implements BaseService<Cliente>{
    private ClienteRepoImpl clienteRepo;

    public ClienteServiceImpl(){
        this.clienteRepo = new ClienteRepoImpl();
    }

    public void agregar(Cliente cliente){
        clienteRepo.agregar(cliente);
    }

    public void editar(Cliente cliente){
        clienteRepo.editar(cliente);
    }

    public void eliminar(int id){
        clienteRepo.eliminar(id);
    }

    public void eliminadoLogico(int id){
        Cliente cliente = clienteRepo.buscarPorId(id);
        cliente.setActivo(false);
        clienteRepo.editar(cliente);
    }

    public List<Cliente> listar(){
        return clienteRepo.listar();
    }

    public Cliente buscarPorId(int id){
        return clienteRepo.buscarPorId(id);
    }
}//TODO: Agregar metodos para Vehiculos(lista patentes)
