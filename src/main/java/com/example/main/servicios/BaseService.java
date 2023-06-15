package com.example.main.servicios;

import java.util.List;

public interface BaseService<T> {
    void agregar(T nuevo);
    void editar(T nuevo);
    void eliminar(T objeto);
    void eliminadoLogico(int id);
    List<T> listar();
    T buscarPorId(int id);
}
