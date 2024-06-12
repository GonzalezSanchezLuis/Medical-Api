package com.medicalApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.medicalApi.model.MedicalAppointment;

@Repository
public interface MedicalAppointmentRepository extends CrudRepository<MedicalAppointment, Long> {
    
}
