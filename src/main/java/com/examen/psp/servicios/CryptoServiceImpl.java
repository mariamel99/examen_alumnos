package com.examen.psp.servicios;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

@Service
public class CryptoServiceImpl implements CryptoService {
    private final KeyPair keyPair;

    public CryptoServiceImpl() {
        this.keyPair = generarParClaves();
    }

    private KeyPair generarParClaves() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generando par de claves", e);
        }
    }

    @Override
    public String encriptarConPublica(String texto) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
            byte[] encriptado = cipher.doFinal(texto.getBytes());
            return Base64.getEncoder().encodeToString(encriptado);
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar", e);
        }
    }

    public  String desencriptarConPrivada(String clave) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        return new String(cipher.doFinal(Base64.getDecoder().decode(clave)));
       
    }

}
