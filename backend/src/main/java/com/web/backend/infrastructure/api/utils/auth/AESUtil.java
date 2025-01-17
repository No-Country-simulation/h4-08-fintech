package com.web.backend.infrastructure.api.utils.auth;

import com.web.backend.config.AppConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
@AllArgsConstructor
public class AESUtil {
    private static final String ALGORITHM = "AES";
    private final AppConfig appConfig;

    private String getSecretKey() {
        String key = appConfig.getProperty("SECRET_ENCRYPT");
        if (key == null || key.isEmpty()) {
            System.out.println(key);
            throw new IllegalStateException("La clave de cifrado (SERIAL_KEY) no está configurada correctamente.");
        }

        if (key.length() != 16 && key.length() != 24 && key.length() != 32) {
            System.out.println(key);
            throw new IllegalArgumentException("La clave debe tener una longitud de 16, 24 o 32 caracteres.");
        }
        return key;
    }

    public String encrypt(String data) throws Exception {
        SecretKey key = new SecretKeySpec(getSecretKey().getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public String decrypt(String encryptedData) throws Exception {
        if (encryptedData == null || encryptedData.trim().isEmpty()) {
            throw new IllegalArgumentException("El texto cifrado no puede ser nulo o vacío.");
        }

        // Validar si es Base64
        encryptedData = encryptedData.trim();
        if (!isBase64(encryptedData)) {
            System.out.println(encryptedData.trim());
            throw new IllegalArgumentException("El texto cifrado no es válido Base64.");
        }

        // Obtener la clave secreta
        SecretKey key = new SecretKeySpec(getSecretKey().getBytes(), ALGORITHM);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        return new String(cipher.doFinal(decodedData));
    }

    private boolean isBase64(String str) {
        try {
            Base64.getDecoder().decode(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}
