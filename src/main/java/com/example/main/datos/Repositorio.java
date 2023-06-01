package com.example.main.datos;

import java.util.List;

public interface Repositorio<T> {
    void persistir(T ... nuevos);
    void editar(int id, T nuevo);
    T buscarPorId(int id);
    void eliminar(int id);
    List<T> listar();
}