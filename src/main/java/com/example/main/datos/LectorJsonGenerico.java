package com.example.main.datos;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class LectorJsonGenerico<T> {
    public List<T> leerDatos(String rutaArchivo, Type tipoObjeto) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            StringBuilder sb = new StringBuilder();
            String linea;

            while ((linea = br.readLine()) != null) {
                sb.append(linea);
            }
            Gson gson = new Gson();

            return gson.fromJson(sb.toString(), tipoObjeto);
        } catch (IOException e) {
            System.out.println("Ocurrio un error al abrir el archivo.");
            return Collections.emptyList();
        }
    }
}
