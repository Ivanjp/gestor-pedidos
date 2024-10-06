package com.liverpool.gestion_pedidos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Pedidos")
                        .version("1.0.0")
                        .description("Esta es la documentación de la API de gestión de pedidos para el sistema de ventas."));
    }
}