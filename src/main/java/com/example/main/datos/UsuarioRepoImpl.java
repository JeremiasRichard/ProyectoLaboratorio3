package com.example.main.datos;

import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.modelos.Usuario;
import com.example.main.utils.Encriptador;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepoImpl implements Repositorio<Usuario> {
    private final File archivo = new File("src/main/resources/archivos/usuarios.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Usuario> listaUsuarios;

    @Override
    public void cargar() {
        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Usuario.class);
            this.listaUsuarios = mapper.readValue(archivo, collectionType);
        } catch (IOException e) {
            this.listaUsuarios = new ArrayList<>();
        }
    }

    @Override
    public void guardar() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, this.listaUsuarios);
        } catch (IOException e) {
            throw new RuntimeException(); //Cambiar
        }
    }

    @Override
    public void agregar(Usuario nuevo) throws EntidadDuplicadaException {
        cargar();
        nuevo.setIdUsuario(listaUsuarios.size()+1);
        this.listaUsuarios.add(nuevo);
        guardar();
    }

    @Override
    public void editar(Usuario nuevo) {
        cargar();
        for (int i = 0; i < this.listaUsuarios.size(); i++) {
            Usuario actual = this.listaUsuarios.get(i);

            if(actual.getIdUsuario() == nuevo.getIdUsuario()){
                listaUsuarios.set(i,nuevo);
            }
        }
        guardar();
    }

    @Override
    public Usuario buscarPorId(int id) {
        cargar();
        Usuario encontrado = null;
        for (Usuario usr : listaUsuarios) {
            if (usr.getIdUsuario() == id) {
                encontrado = usr;
                break;
            }
        }
        return encontrado;
    }

    @Override
    public void eliminar(Usuario objeto) {
        cargar();
        this.listaUsuarios.removeIf(actual-> actual.getIdUsuario() == objeto.getIdUsuario());
        guardar();
    }

    @Override
    public List<Usuario> listar() {
        cargar();
        return this.listaUsuarios;
    }

    public Usuario buscarPorUsuarioYPassword(String usuario,String password){
        cargar();
        Usuario encontrado = null;
        for(Usuario usr : listaUsuarios){
            if(isUsuarioBuscado(usuario, password, usr)){
                encontrado = usr;
                break;
            }
        }
       return encontrado;
    }

    private static boolean isUsuarioBuscado(String usuario, String password, Usuario usr) {
        return usr.getUser().equals(usuario) && usr.getPassword().equals(password);
    }
}
