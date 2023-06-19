package com.example.main.datos;

import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.datos.excepciones.EntidadNoEncontradaException;
import com.example.main.datos.excepciones.PersistenciaException;

import java.util.List;

public interface Repositorio<T> {

    void cargar();
    void agregar(T nuevo) throws EntidadDuplicadaException;
    void editar(T nuevo) throws EntidadNoEncontradaException;
    T buscarPorId(int id);
    void eliminar(T objeto) throws EntidadNoEncontradaException;
    List<T> listar();
    void guardar() throws PersistenciaException;
}