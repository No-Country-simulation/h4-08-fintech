package com.web.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Map;

@SpringBootApplication
@EnableFeignClients
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BackendApplication.class);

		// Establecer parámetros de configuración al inicio
		app.setDefaultProperties(Map.of(
				"server.address", "0.0.0.0", // Escuchar en todas las interfaces
				"server.port", "8080"       // Establece el puerto (opcional)
		));

		app.run(args);
	}
}

