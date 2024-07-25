package com.medicalApi.model;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "work_schedule")
public class WorkSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;
    private String dayOfWeek;
    private String date;

    @Schema(description = "Hora de inicio", type = "string", example = "09:00:00")
    private LocalTime startTime; 
    @Schema(description = "Hora de fin", type = "string", example = "14:00:00")   
    private LocalTime endTime;

    @ManyToOne
    @JsonIgnore
    private Doctor doctor;

}
