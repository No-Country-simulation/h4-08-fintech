package com.web.backend.infrastructure.api.utils.auth;

import com.web.backend.config.AppConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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
            throw new IllegalStateException("La clave de cifrado (SECRET_ENCRYPT) no está configurada correctamente.");
        }

        if (key.length() != 16 && key.length() != 24 && key.length() != 32) {
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

        encryptedData = encryptedData.trim();

        // Verificación mejorada de Base64
        if (encryptedData.length() % 4 != 0 || !isBase64(encryptedData)) {
            throw new IllegalArgumentException("El texto cifrado no es válido Base64.");
        }

        SecretKey key = new SecretKeySpec(getSecretKey().getBytes(), ALGORITHM);

        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] decodedData = Base64.getDecoder().decode(encryptedData);
            return new String(cipher.doFinal(decodedData));
        } catch (Exception e) {
            System.err.println("Error al descifrar: " + e.getMessage());
            throw new IllegalArgumentException("No se pudo descifrar el texto cifrado. Verifica los datos proporcionados.");
        }
    }

    private boolean isBase64(String str) {
        try {
            Base64.getDecoder().decode(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
