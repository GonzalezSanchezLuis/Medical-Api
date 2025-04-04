package com.medicalApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.medicalApi.model.MedicalAppointment;
import com.medicalApi.service.MedicalAppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.medicalApi.config.Documentation.ApiDocumentation;
import com.medicalApi.dto.MedicalAppointmentRequest;
import java.security.Principal;



@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/appointments/")
@Tag(name = "Medical Appointment", description = "Controlador de manejo de  citas medicas")
public class MedicalAppointmentController {
    @Autowired
    private MedicalAppointmentService medicalAppointmentService;

     @Operation(summary = "Register a New Medical Appointment", description = "Registro de una cita medica", tags = {"Medical Appointment"})
     @ApiDocumentation.MedicalAppointmentApiResponses
     @PostMapping("register")
    public MedicalAppointment register(Principal principal, @RequestBody MedicalAppointmentRequest request) {
        return medicalAppointmentService.registerMedicalAppointment(principal,request);
    }

    @Operation(summary = "Get all  Medicals Appointments", description = "Obtener todas las citas medicas", tags = {"Medical Appointment"})
    @ApiDocumentation.MedicalAppointmentApiResponses
    @GetMapping("all")
    public ResponseEntity<?> getAllAppointments() throws Exception{
        try {
            return ResponseEntity.status(HttpStatus.OK).body(medicalAppointmentService.getAllAppointments());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error del servidor al procesar la solicitu");
        }
    }
}
