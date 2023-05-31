package datos;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EscritorJsonGenerico<T> {
    public void escribirDatos(List<T> listaInput, String nombreArchivoJSON){
        Gson gson = new Gson();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivoJSON))) {
            bw.write(gson.toJson(listaInput));
        } catch (IOException e) {
            System.out.println("Ocurrio un error al abrir el archivo.");
        }
    }
}
