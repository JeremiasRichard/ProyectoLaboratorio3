package com.example.main.servicios;

import java.util.List;

public interface BaseService<T> {
    void agregar(T nuevo);
    void editar(int id, T nuevo);
    void eliminar(int id);
    void eliminadoLogico(int id);
    List<T> listar();
    T buscarPorId(int id);
}
