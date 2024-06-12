package com.medicalApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.medicalApi.model.Doctor;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor,Long>{
    public Doctor findDoctorByEmail(String email);
}
