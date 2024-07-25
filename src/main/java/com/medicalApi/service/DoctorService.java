package com.medicalApi.service;

import java.util.List;

import com.medicalApi.model.Doctor;

public interface DoctorService {
    Doctor register(Doctor doctor) throws Exception;
    Doctor getDoctorById(Long id);
    List<Doctor> getAllDoctors();
    Doctor updateDoctor(Long id,Doctor doctor);
    public void deleteDoctor(Long id);
}
