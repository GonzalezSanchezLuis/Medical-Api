package com.medicalApi.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.medicalApi.model.Doctor;
import com.medicalApi.repository.DoctorRepository;
import com.medicalApi.service.DoctorService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DoctorImpl implements DoctorService{

    @Autowired 
    private DoctorRepository doctorRepository;

    @Autowired 
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Doctor register(Doctor doctor) throws Exception {
        Doctor localDoctor =  doctorRepository.findDoctorByEmail(doctor.getEmail());
        if (localDoctor!= null) {
            throw new Exception("El doctor ya existe");
        }else{
            doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
            return doctorRepository.save(doctor);
        }
    }

    @Override
    public Doctor getDoctorById(Long id) {
       Optional<Doctor> doctorId = doctorRepository.findById(id);
       if(doctorId.isPresent()){
        return doctorId.get();
       }
       throw new EntityNotFoundException("No se encontro un medico registrado con ese ID");
    }

    @Override
    public List<Doctor> getAllDoctors() {
       return (List<Doctor>) doctorRepository.findAll();
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {
        Doctor thereIsDoctor = doctorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No hay un medico registrado con ese ID"));
        
            thereIsDoctor.setDoctorName(doctor.getDoctorName());
            thereIsDoctor.setEmail(doctor.getEmail());
            thereIsDoctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
            thereIsDoctor.setSurnames(doctor.getSurnames());
            thereIsDoctor.setSpecialty(doctor.getSpecialty());
            thereIsDoctor.setSpecialty(doctor.getSpecialty());
            thereIsDoctor.setAddressConsultingRoom(doctor.getAddressConsultingRoom());
            thereIsDoctor.setPhoneConsultingRoom(doctor.getPhoneConsultingRoom());         
            return doctorRepository.save(thereIsDoctor);
    }

    public void deleteDoctor(Long id){
       Doctor doctorToDelete = doctorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No hay un medico registrado con ese ID")); 
       doctorRepository.delete(doctorToDelete);
    }
    
}
