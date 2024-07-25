package com.medicalApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.medicalApi.model.WorkSchedule;


@Repository
public interface WorkScheduleRepository extends CrudRepository<WorkSchedule,Long>{
   @Query("SELECT ws FROM WorkSchedule ws WHERE ws.doctor.doctorId = :doctorId")
   List<WorkSchedule> findDoctorById(@Param("doctorId") Long doctorId);
}
