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
import java.security.Principal;
import java.util.List;

@Service
public class MedicalAppointmentImpl implements MedicalAppointmentService{

    @Autowired
    private MedicalAppointmentRepository medicalAppointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;


   @Override
   public MedicalAppointment registerMedicalAppointment(Principal principal, MedicalAppointmentRequest request) {
       String username = principal.getName();
       User user = userRepository.findUserByEmail(username);

       if (user == null) {
           throw new RuntimeException("No se encontró un usuario con ese correo electrónico");
       }

       Doctor doctor = doctorRepository.findById(request.getDoctorId())
               .orElseThrow(() -> new RuntimeException("No se encontró un médico con ese ID"));

       MedicalAppointment medicalAppointment = new MedicalAppointment();
       medicalAppointment.setDoctor(doctor);
       medicalAppointment.setPatient(user); // Set the logged-in user as the patient
       medicalAppointment.setDateOfAppointment(request.getDateOfAppointment());
       medicalAppointment.setAppointmentTime(request.getAppointmentTime());
       medicalAppointment.setDate(request.getDate());

       System.out.println("Doctor ID: " + doctor.getDoctorId());
       System.out.println("Patient ID: " + user.getUserId());
       System.out.println("Date of Appointment: " + request.getDateOfAppointment());
       System.out.println("Appointment Time: " + request.getAppointmentTime());
       System.out.println("Appointment Date: " + request.getDate());

       return medicalAppointmentRepository.save(medicalAppointment);
   }

    @Override
    public List<MedicalAppointment> getAllAppointments() {
        return (List<MedicalAppointment>) medicalAppointmentRepository.findAll();
    }


}
