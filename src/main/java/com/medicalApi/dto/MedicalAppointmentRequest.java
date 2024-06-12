package com.medicalApi.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MedicalAppointmentRequest {
    private Long doctorId;
    private Long patientId;
    private LocalDateTime dateTime;
}
