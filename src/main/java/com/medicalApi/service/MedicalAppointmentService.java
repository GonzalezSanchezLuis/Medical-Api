package com.medicalApi.service;

import com.medicalApi.dto.MedicalAppointmentRequest;
import com.medicalApi.model.MedicalAppointment;

import java.security.Principal;
import java.util.List;

public interface MedicalAppointmentService {
    MedicalAppointment registerMedicalAppointment(Principal principal,MedicalAppointmentRequest medicalAppointmentRequest);
    List<MedicalAppointment> getAllAppointments();
}
