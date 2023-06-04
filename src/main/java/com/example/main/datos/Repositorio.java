package com.example.main.datos;

import com.example.main.datos.excepciones.PersistenciaException;

import java.util.List;

public interface Repositorio<T> {

    void cargar();
    void agregar(T ... objeto);
    void editar(int id, T nuevo);
    T buscarPorId(int id);
    void eliminar(int id);
    List<T> listar();
    void guardar() throws PersistenciaException;
}