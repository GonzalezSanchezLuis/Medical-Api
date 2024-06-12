package com.medicalApi.service;

import com.medicalApi.dto.MedicalAppointmentRequest;
import com.medicalApi.model.MedicalAppointment;

public interface MedicalAppointmentService {
    public MedicalAppointment registerMedicalAppointment(MedicalAppointmentRequest medicalAppointmentRequest);
}
