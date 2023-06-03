package com.example.main.servicios;

import com.example.main.modelos.Usuario;

public interface LoginService
{
     Usuario autenticar(String usuario, String contraseña);
}
