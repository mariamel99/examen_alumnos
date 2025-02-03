package com.examen.psp.servicios;

public interface CryptoService {

    String encriptarConPublica(String texto);
    String desencriptarConPrivada(String texto) throws Exception;

}
