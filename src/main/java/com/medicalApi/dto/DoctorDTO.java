package com.medicalApi.dto;

import com.medicalApi.model.Doctor;
import lombok.Data;

@Data
public class DoctorDTO {
    private Long doctorId;
    private String name;
    private String email;
    private String specialization;
    private String role;

    public DoctorDTO(Doctor doctor) {
        this.doctorId = doctor.getDoctorId();
        this.name = doctor.getDoctorName();
        this.email = doctor.getEmail();
        this.specialization = doctor.getSpecialty();
        this.role = doctor.getRole();
    }
}
