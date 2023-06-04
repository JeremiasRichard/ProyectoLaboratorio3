package com.example.main.datos;

import com.example.main.modelos.Usuario;
import org.junit.jupiter.api.Test;

class UsuarioRepoImplTest {
    UsuarioRepoImpl usuarioRepo = new UsuarioRepoImpl();

    @Test
    void debeGuardarUnNuevoUsuario(){
        Usuario user = new Usuario(1,"carlos","carlos",true);
        Usuario[] arregloUsuario = new Usuario[]{user};
        usuarioRepo.agregar(arregloUsuario);

        usuarioRepo.listar().forEach(System.out::println);
    }
}
