package com.example.main.servicios;

import com.example.main.datos.UsuarioRepoImpl;
import com.example.main.modelos.Usuario;

import java.util.List;

public class UsuarioServiceImpl implements BaseService<Usuario>{
    private UsuarioRepoImpl usuarioRepo;

    public UsuarioServiceImpl(){
        this.usuarioRepo = new UsuarioRepoImpl();
    }

    public void agregar(Usuario usuario){
        //usuarioRepo.agregar(usuario);
    }

    public void editar(Usuario usuario){
        usuarioRepo.editar(usuario);
    }

    public void eliminar(Usuario usuario){
        usuarioRepo.eliminar(usuario);
    }

    public void eliminadoLogico(int id){
        Usuario usuario = usuarioRepo.buscarPorId(id);
        usuario.setActivo(false);
        usuarioRepo.editar(usuario);
    }

    public List<Usuario> listar(){
        return usuarioRepo.listar();
    }

    public Usuario buscarPorId(int id){
        return usuarioRepo.buscarPorId(id);
    }
}
