package com.example.main.servicios;

import com.example.main.datos.excepciones.EntidadDuplicadaException;

import java.util.List;

public interface BaseService<T> {
    void agregar(T nuevo) throws EntidadDuplicadaException;
    void editar(T nuevo);
    void eliminar(T objeto);
    void eliminadoLogico(int id);
    List<T> listar();
    T buscarPorId(int id);
}
