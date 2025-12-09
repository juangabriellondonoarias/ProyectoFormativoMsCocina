package com.example.demo;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	  @Bean
	    public OpenAPI customOpenAPI() {
	        return new OpenAPI()
	                .info(new Info()
	                        .title("API de Ingredientes")
	                        .description("Documentación de la API del módulo de cocina")
	                        .version("1.0.0")
	                        .contact(new Contact().name("Tu Nombre").email("correo@example.com"))
	                );
	    }
}
