package com.example.main.datos;

import com.example.main.modelos.Persona;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class PersonaRepoImpl implements Repositorio<Persona> {
    private static final String RUTA_ARCHIVO = "src/main/resources/archivos/personas.json";
    private final Gson gson = new Gson();
    private Type tipo = new TypeToken<List<Persona>>() {
    }.getType();
    private List<Persona> objetos;

    @Override
    public void persistir(Persona... nuevos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
            this.objetos = listar();
            this.objetos.addAll(Arrays.asList(nuevos));
            String json = gson.toJson(objetos, tipo);
            bw.write(json);
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo para escribir.");
        }
    }

    @Override
    public void editar(int id, Persona nuevo) {
        //Resta implementar
    }

    @Override
    public Persona buscarPorId(int id) {
        //Resta implementar
        return null;
    }

    @Override
    public void eliminar(int id) {
        //Resta implementar
    }

    @Override
    public List<Persona> listar() {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            StringBuilder sb = new StringBuilder();
            String linea = "";

            while ((linea = br.readLine()) != null) {
                sb.append(linea);
            }
            objetos = gson.fromJson(sb.toString(), tipo);
            if (objetos != null) return objetos;
        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo.");
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo.");
        }
        return new ArrayList<>();
    }
}
