package com.medicalApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medicalApi.model.Doctor;
import com.medicalApi.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import com.medicalApi.config.Documentation.ApiDocumentation;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/doctors/")
@Tag(name = "Doctor", description = "Controlador doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

     @Operation(summary = "Register a New Doctor", description = "Registro de un nuevo medico", tags = {"Doctor"})
    @ApiDocumentation.DoctorApiResponses
    @PostMapping("register")
    public ResponseEntity register(@RequestBody Doctor doctor) throws Exception {
         try {
             doctor.setRole("ROLE_DOCTOR"); // Asigna el rol aquí
             Doctor registeredDoctor = doctorService.register(doctor);
             return ResponseEntity.status(HttpStatus.CREATED).body(registeredDoctor);
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
         }
    }

    @Operation(summary = "Get Doctor by ID", description = "Obtiene la información de un doctor por su ID", tags = {"Doctor"})
    @ApiDocumentation.DoctorApiResponses
    @GetMapping("doctor/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable Long id) throws Exception {
        try {
            if(id == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Debes proporcionar un id valido");
            }
            Doctor doctor = doctorService.getDoctorById(id);
            return ResponseEntity.status(HttpStatus.OK).body(doctor);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error del servidor al procesar la solicitu");
        }
        
    }

    @Operation(summary = "Get  All Doctors", description = "Obtiene la información de todos los medicos registrados", tags = {"Doctor"})
    @ApiDocumentation.DoctorApiResponses
    @GetMapping("doctors")
    public ResponseEntity<?> getDoctors() throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(doctorService.getAllDoctors());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error del servidor al procesar la solicitu");
        }

    }

    @Operation(summary = "Update Doctor", description = "Actualiza la información de un medico por su ID", tags = {"Doctor"})
    @ApiDocumentation.DoctorApiResponses
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) throws Exception {
        try {
            Doctor updateDoctor = doctorService.updateDoctor(id, doctor);
            return ResponseEntity.status(HttpStatus.OK).body(updateDoctor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error del servidor al procesar la solicitu");
        }

    }

    @Operation(summary = "Delete Doctor", description = "elimina  la información de un medico por su ID", tags = {"Doctor"})
    @ApiDocumentation.DoctorApiResponses
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) throws Exception {
        try {
            doctorService.deleteDoctor(id);
            return ResponseEntity.status(HttpStatus.OK).body("Doctor eliminado correctamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error del servidor al procesar la solicitu");
        }
    }
}
