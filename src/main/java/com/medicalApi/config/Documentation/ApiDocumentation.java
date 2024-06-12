package com.medicalApi.config.Documentation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.Data;

@Data
public class ApiDocumentation {

    // Define schemas reutilizables para las respuestas y solicitudes de JWT
    public interface Schemas {

        @Schema(name = "JwtRequest", description = "Autenticación con nombre de usuario y contraseña")
        class JwtRequestSchema extends com.medicalApi.model.jwt.JwtRequest {
        }

        @Schema(name = "JwtResponse", description = "Token de autenticación")
        class JwtResponseSchema extends com.medicalApi.model.jwt.JwtResponse {
        }


        @Schema(name = "User", description = "Detalles del usuario")
        class UserSchema extends com.medicalApi.model.User {
        }

        @Schema(name = "Doctor", description = "Detalles del doctor")
        class DoctorSchema extends com.medicalApi.model.Doctor {
        }

        @Schema(name = "Medical Appointment")
        class MedicalAppointmentSchema extends com.medicalApi.model.MedicalAppointment {
        }
    }

    // Define una anotación para la respuesta de autenticación
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Autenticación exitosa",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Schemas.JwtResponseSchema.class)
            )
        )
    })
    public @interface AuthApiResponse {}

    // Define una anotación para el cuerpo de la solicitud de autenticación
    @RequestBody(
        description = "Autenticación con email de usuario y contraseña",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = Schemas.JwtRequestSchema.class)
        )
    )
    public @interface AuthRequestBody {}

    // Define una anotación para el cuerpo de la solicitud de registro de usuario
    @RequestBody(
        description = "Registro de usuario",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = Schemas.UserSchema.class)
        )
    )
    public @interface RegisterRequestBody {}

    // Define una anotación para las respuestas de usuario
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Schemas.UserSchema.class)
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error del servidor",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class)
            )
        )
    })
    public @interface UserApiResponses {}

    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Schemas.DoctorSchema.class)
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error del servidor",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class)
            )
        )
    })

    public @interface DoctorApiResponses {}
   
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Schemas.DoctorSchema.class)
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error del servidor",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class)
            )
        )
    })

    public @interface MedicalAppointmentApiResponses {}

}

