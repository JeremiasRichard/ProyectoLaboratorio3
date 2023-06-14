package com.example.main.datos.excepciones;

public class EntidadDuplicadaException extends Exception {
    public EntidadDuplicadaException(){
        super("Entidad duplicada.");
    }
}
