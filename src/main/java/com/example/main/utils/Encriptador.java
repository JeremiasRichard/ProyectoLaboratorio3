package com.example.main.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Encriptador {
    /**
     * Metodo que encripta una cadena dada en algoritmo MD5
     * @param password Cadena de texto
     * @return Una cadena de texto encriptada con MD5
     */
    public static String obtenerMD5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            StringBuilder hashtext = new StringBuilder(number.toString(16));

            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }
            return hashtext.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

