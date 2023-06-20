package com.example.main.servicios;

import com.example.main.datos.UsuarioRepoImpl;
import com.example.main.modelos.Usuario;
import com.example.main.utils.Encriptador;


public class LoginServiceImpl implements LoginService {
    UsuarioRepoImpl usuarioRepo = new UsuarioRepoImpl();

    /**
     * Metodo para autenticar un usuario mediante su nombre de usuario y password
     * @param usuario Nombre de usuario
     * @param password Password
     * @return Una entidad usuario en caso de ser encontrada o nulo si no lo encuentra
     */
    @Override
    public Usuario autenticar(String usuario, String password)
    {
        /*String passwordEncriptado = Encriptador.obtenerMD5(password);
        return usuarioRepo.buscarPorUsuarioYPassword(usuario,passwordEncriptado);*/
        return new Usuario("Jeremias Richard","asd",false);
    }
}
