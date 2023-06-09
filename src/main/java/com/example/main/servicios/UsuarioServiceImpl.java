package com.example.main.servicios;

import com.example.main.datos.UsuarioRepoImpl;
import com.example.main.datos.excepciones.EntidadNoEncontradaException;
import com.example.main.datos.excepciones.EntidadDuplicadaException;
import com.example.main.modelos.Usuario;
import com.example.main.utils.Encriptador;

import java.util.List;

public class UsuarioServiceImpl implements BaseService<Usuario>{
    private UsuarioRepoImpl usuarioRepo;

    public UsuarioServiceImpl(){
        this.usuarioRepo = new UsuarioRepoImpl();
    }

    public void agregar(Usuario usuario) throws EntidadDuplicadaException
    {
        usuario.setPassword(Encriptador.obtenerMD5(usuario.getPassword()));
        usuarioRepo.agregar(usuario);
    }

    public void editar(Usuario usuario) throws EntidadNoEncontradaException {
        usuarioRepo.editar(usuario);
    }

    public void eliminar(Usuario usuario) throws EntidadNoEncontradaException {
        usuarioRepo.eliminar(usuario);
    }

    public void eliminadoLogico(int id) throws EntidadNoEncontradaException {
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

    public boolean buscarPorUsuario(String username)
    {
        return usuarioRepo.buscarPorUsuario(username) != null;
    }
}
