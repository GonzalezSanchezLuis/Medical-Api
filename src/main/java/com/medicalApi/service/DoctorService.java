package com.medicalApi.service;

import java.util.List;

import com.medicalApi.model.Doctor;

public interface DoctorService {
    public Doctor register(Doctor doctor) throws Exception;
    public Doctor getDoctorById(Long id);
    public List<Doctor> getAllDoctors();
    public Doctor updateDoctor(Long id,Doctor doctor);
    public void deleteDoctor(Long id);
}
