package com.medicalApi.service.impl;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.medicalApi.model.Doctor;
import com.medicalApi.model.WorkSchedule;
import com.medicalApi.repository.DoctorRepository;
import com.medicalApi.repository.WorkScheduleRepository;
import com.medicalApi.service.WorkScheduleService;

@Service
public class WorkScheduleImpl implements WorkScheduleService{

    @Autowired
    private WorkScheduleRepository workScheduleRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public WorkSchedule registWorkSchedule(Principal principal, WorkSchedule workSchedule) {
        if (workSchedule == null) {
            throw new IllegalArgumentException("El horario de trabajo es obligatorio");           
        }else{
            String username = principal.getName();
            Doctor id = doctorRepository.findDoctorByEmail(username);
            workSchedule.setDoctor(id);
            return workScheduleRepository.save(workSchedule);

        }
    }
    
    @Override
    public List<WorkSchedule> getWorkScheduleByDoctorId(Long doctorId) {
      return workScheduleRepository.findDoctorById(doctorId);
    }


    @Override
    public WorkSchedule updateWorkSchedule(Long id, WorkSchedule workSchedule) {
       if (!workScheduleRepository.existsById(id)) {
        throw new RuntimeException("WorkSchedule not found");
       }else{
        workSchedule.setId(id);
        return workScheduleRepository.save(workSchedule);
       }
    }
    @Override
    public void deleteWorkSchedule(Long id) {
        workScheduleRepository.deleteById(id);
    }
    
}
