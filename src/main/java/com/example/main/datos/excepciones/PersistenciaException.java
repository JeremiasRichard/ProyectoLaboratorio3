package com.example.main.datos.excepciones;

public class PersistenciaException extends Exception {
    public PersistenciaException(){
        super("Ocurrio un error al guardar.");
    }
}
