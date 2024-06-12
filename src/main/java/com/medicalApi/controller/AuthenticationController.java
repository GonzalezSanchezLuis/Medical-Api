package com.medicalApi.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medicalApi.model.jwt.JwtRequest;
import com.medicalApi.model.jwt.JwtResponse;
import com.medicalApi.model.jwt.JwtUtils;
import com.medicalApi.service.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth/")
@Tag(name = "Authetication", description = "Controlador de autenticación")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
   
     @Autowired
    private CustomUserDetailsService userDetailsService;
  
    @Autowired
    private JwtUtils jwtUtils;

    
    @PostMapping("login")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        authenticate(jwtRequest.getUserEmail(), jwtRequest.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUserEmail());
        String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USUARIO DESHABILITADO: " + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("CREDENCIALES INCORRECTAS: " + e.getMessage());
        }
    }

    @GetMapping("current-user")
    public UserDetails currentUser(Principal principal) {
        return userDetailsService.loadUserByUsername(principal.getName());
    }
}

