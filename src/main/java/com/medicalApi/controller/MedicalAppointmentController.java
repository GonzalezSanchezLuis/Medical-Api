package com.medicalApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medicalApi.model.MedicalAppointment;
import com.medicalApi.service.MedicalAppointmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.medicalApi.config.Documentation.ApiDocumentation;
import com.medicalApi.dto.MedicalAppointmentRequest;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/appointment/")
@Tag(name = "Medical Appointment", description = "Controlador de registro para una cita medica")
public class MedicalAppointmentController {
    @Autowired
    private MedicalAppointmentService medicalAppointmentService;

     @PostMapping("register")
     @Operation(
        summary = "Register a New Medical Appointment",
        description = "Registro de una cita medica",
        tags = {"Medical Appointment"})
        @ApiDocumentation.MedicalAppointmentApiResponses
     
    public MedicalAppointment register(@RequestBody MedicalAppointmentRequest request) {
        return medicalAppointmentService.registerMedicalAppointment(request);
    }
}
