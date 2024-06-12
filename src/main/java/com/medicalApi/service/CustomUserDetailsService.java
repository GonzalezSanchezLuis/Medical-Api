package com.medicalApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.medicalApi.model.Doctor;
import com.medicalApi.model.User;
import com.medicalApi.repository.DoctorRepository;
import com.medicalApi.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user != null) {
            return user;
        }
        
        Doctor doctor = doctorRepository.findDoctorByEmail(email);
        if (doctor != null) {
            return doctor;
        }

        throw new UsernameNotFoundException("Usuario no encontrado");
    }  
}
