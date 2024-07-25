package com.medicalApi.service;

import java.security.Principal;
import com.medicalApi.model.WorkSchedule;
import java.util.List;

public interface WorkScheduleService {
    WorkSchedule registWorkSchedule(Principal principal, WorkSchedule workSchedule);
    List<WorkSchedule> getWorkScheduleByDoctorId(Long doctorId);
    WorkSchedule updateWorkSchedule(Long id, WorkSchedule workSchedule);
    void deleteWorkSchedule(Long id);
}
