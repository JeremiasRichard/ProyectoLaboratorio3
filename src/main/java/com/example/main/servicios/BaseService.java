package com.example.main.servicios;

import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.datos.excepciones.EntidadNoEncontradaException;

import java.util.List;

public interface BaseService<T> {
    void agregar(T nuevo) throws EntidadDuplicadaException;
    void editar(T nuevo) throws EntidadNoEncontradaException;
    void eliminar(T objeto) throws EntidadNoEncontradaException;
    void eliminadoLogico(int id) throws EntidadNoEncontradaException;
    List<T> listar();
    T buscarPorId(int id);
}
