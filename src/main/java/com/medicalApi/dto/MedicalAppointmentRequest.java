package com.medicalApi.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.medicalApi.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MedicalAppointmentRequest {
    private Long doctorId;
    private Long patientId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime appointmentTime;
    private String dateOfAppointment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Schema(hidden = true)
    private User patient;
}
