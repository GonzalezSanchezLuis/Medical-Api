package com.medicalApi.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicalApi.dto.MedicalAppointmentRequest;
import com.medicalApi.model.Doctor;
import com.medicalApi.model.MedicalAppointment;
import com.medicalApi.model.User;
import com.medicalApi.repository.DoctorRepository;
import com.medicalApi.repository.MedicalAppointmentRepository;
import com.medicalApi.repository.UserRepository;
import com.medicalApi.service.MedicalAppointmentService;

import jakarta.transaction.Transactional;

@Service
public class MedicalAppointmentImpl implements MedicalAppointmentService{

    @Autowired
    private MedicalAppointmentRepository medicalAppointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

   @Transactional
   @Override
    public MedicalAppointment registerMedicalAppointment(MedicalAppointmentRequest request) {
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
            .orElseThrow(() -> new RuntimeException("No se encontró un médico con ese ID"));
        User patient = userRepository.findById(request.getPatientId())
            .orElseThrow(() -> new RuntimeException("No se encontró un usuario con ese ID"));
        
        MedicalAppointment medicalAppointment = new MedicalAppointment();
        medicalAppointment.setDoctor(doctor);
        medicalAppointment.setPatient(patient);
        medicalAppointment.setDateTime(request.getDateTime());
        
        return medicalAppointmentRepository.save(medicalAppointment);
    }

}
