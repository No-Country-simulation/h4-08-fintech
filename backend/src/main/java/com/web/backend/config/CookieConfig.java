package com.web.backend.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookieConfig {

    private final Dotenv dotenv;

    public CookieConfig() {
        this.dotenv = Dotenv.configure().ignoreIfMissing().load();
    }

    public boolean isHttpOnly() {
        return Boolean.parseBoolean(dotenv.get("COOKIE_HTTP_ONLY", "true"));
    }

    public boolean isSecure() {
        return Boolean.parseBoolean(dotenv.get("COOKIE_SECURE", "false"));
    }

    public String getSameSite() {
        return dotenv.get("COOKIE_SAME_SITE", "Lax");
    }
}
