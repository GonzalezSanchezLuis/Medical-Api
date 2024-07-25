package com.medicalApi.controller;

import java.security.Principal;
import java.util.List;
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
import com.medicalApi.model.WorkSchedule;
import com.medicalApi.service.WorkScheduleService;
import com.medicalApi.config.Documentation.ApiDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/work-schedules/")
@Tag(name = "Schedule", description = "Controlador para registrar el calendario de trabajo para el doctor")
public class WorkScheduleController {

    @Autowired
    private WorkScheduleService workScheduleService;
    

    @Operation(summary = "Register a new Work Schedule", description = "Registra un calendario de trabajo" ,tags = {"Schedule"})
    @ApiDocumentation.ScheduleApiResponses
    @PostMapping("register")
    public ResponseEntity<WorkSchedule> createWorkSchedule(Principal principal,@RequestBody WorkSchedule workSchedule) {
        WorkSchedule savedSchedule = workScheduleService.registWorkSchedule(principal,workSchedule);
        return ResponseEntity.ok(savedSchedule);
    }

    @Operation(summary = "Get the work schedule", description = "Obtiene el calendario de trabajo" ,tags = {"Schedule"})
    @ApiDocumentation.ScheduleApiResponses
    @GetMapping("work-schedule/{doctorId}")
    public ResponseEntity<?>getWorkSchedulesByDoctorId(@PathVariable Long doctorId) {
        List<WorkSchedule> schedules = workScheduleService.getWorkScheduleByDoctorId(doctorId);
        return ResponseEntity.ok(schedules);

    }

    @Operation(summary = "Update the work schedule", description = "Actualiza el calendario de trabajo" ,tags = {"Schedule"})
    @ApiDocumentation.ScheduleApiResponses
    @PutMapping("update/{id}")
    public ResponseEntity<WorkSchedule> updateWorkSchedule(@PathVariable Long id, @RequestBody WorkSchedule workSchedule) {
        WorkSchedule updatedSchedule = workScheduleService.updateWorkSchedule(id, workSchedule);
        return ResponseEntity.ok(updatedSchedule);
    }


    @Operation(summary = "Delete the work schedule", description = "Elimina el calendario de trabajo" ,tags = {"Schedule"})
    @ApiDocumentation.ScheduleApiResponses
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteWorkSchedule(@PathVariable Long id) {
        workScheduleService.deleteWorkSchedule(id);
        try{
            return ResponseEntity.ok("{\"message\": \"Information deleted successfully\"}");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error deleting information\"}");
        }

    }
}
