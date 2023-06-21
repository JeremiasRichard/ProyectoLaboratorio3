package com.example.main.controladores.validaciones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones
{

    public static boolean validarLogin(String usuario,String contraseña)
    {
             if(verificarUsuarioYContraseña(usuario) || verificarUsuarioYContraseña(contraseña))
             {
                 return false;
             }
             else
             {
                 return true;
             }
    }

    public static boolean isStringNull(String string)
    {
        return string == null || string.equals("");
    }

    public static boolean isStringValido(String string)
    {
        return string != null && !string.equals("");
    }

    public static boolean verificarUsuarioYContraseña(String string)
    {
        String regex = "[^a-zA-Z0-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);

        if(matcher.find())
        {
            return  true;
        }
        else
        {
            return false;
        }
    }
}
