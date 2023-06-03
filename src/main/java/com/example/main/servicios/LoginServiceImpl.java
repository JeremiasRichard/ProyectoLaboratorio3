package com.example.main.servicios;

import com.example.main.modelos.Usuario;

public class LoginServiceImpl implements LoginService {
    @Override
    public Usuario autenticar(String usuario, String contraseña)
    {
        return new Usuario(1,"asd","asd",false);
    }
}
