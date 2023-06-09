package com.example.main.servicios;

import com.example.main.datos.ClienteRepoImpl;
import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.datos.excepciones.EntidadNoEncontradaException;
import com.example.main.modelos.Cliente;

import java.util.List;

public class ClienteServiceImpl implements BaseService<Cliente>{
    private ClienteRepoImpl clienteRepo;

    public ClienteServiceImpl() {
        this.clienteRepo = new ClienteRepoImpl();
    }

    public void agregar(Cliente cliente) throws EntidadDuplicadaException {
        clienteRepo.agregar(cliente);
    }

    public void editar(Cliente cliente) throws EntidadNoEncontradaException {
        clienteRepo.editar(cliente);
    }

    public void eliminar(Cliente cliente) throws EntidadNoEncontradaException {
        clienteRepo.eliminar(cliente);
    }

    @Override
    public void eliminadoLogico(int id) throws EntidadNoEncontradaException {

    }

    public void eliminadoLogico(String  dni) throws EntidadNoEncontradaException {
        Cliente cliente = clienteRepo.buscarPorDNI(dni);
        cliente.setActivo(false);
        clienteRepo.editar(cliente);
    }

    public List<Cliente> listar()
    {return clienteRepo.listar();}

    public Cliente buscarPorId(int id) {
        return clienteRepo.buscarPorId(id);
    }


    public boolean buscarPorDni(String dni) {
        return clienteRepo.buscarPorDNI(dni) != null;
    }

    public List<Cliente> listarActivos(){
       return clienteRepo.listarActivos();
    }

}//TODO: Agregar metodos para Vehiculos(lista patentes)
