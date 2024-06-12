package com.medicalApi.config;

import org.springframework.http.HttpHeaders;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "Medical API",
        version = "1.0.0",
        description = "Api para servicios medicos",
        contact = @Contact(name = "Luis Gonzalez Sanchez",
                            email = "luis.gonzalez.sanchez.404@gmail.com"),
        license = @License(
            name = "Apache 2.0",
            url = "http://www.apache.org/licenses/LICENSE-2.0.html")),

    servers = {
        @Server(
            description = "DEV SERVER",
            url = "http://localhost:8080"
        )
    },
    security = @SecurityRequirement(name = "Access Token")
)
@SecurityScheme(
    name = "Access Token",
    description = "Token de acceso para la API",
    type = SecuritySchemeType.HTTP,
    in = SecuritySchemeIn.HEADER,
    scheme = "bearer",
    paramName = HttpHeaders.AUTHORIZATION,
    bearerFormat = "JWT"
)

public class SwaggerConfig {}

