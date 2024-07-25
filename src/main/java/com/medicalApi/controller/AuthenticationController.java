package com.medicalApi.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import com.medicalApi.controlExceptions.DisabledUserException;
import com.medicalApi.controlExceptions.InvalidCredentialsException;
import com.medicalApi.dto.DoctorDTO;
import com.medicalApi.dto.UserDTO;
import com.medicalApi.model.Doctor;
import com.medicalApi.model.User;
import com.medicalApi.service.BlacklistService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.medicalApi.model.jwt.JwtRequest;
import com.medicalApi.model.jwt.JwtResponse;
import com.medicalApi.model.jwt.JwtUtils;
import com.medicalApi.service.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.medicalApi.config.Documentation.ApiDocumentation;



@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth/")
@Tag(name = "Authentication", description = "Controlador de autenticación")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
   
     @Autowired
    private CustomUserDetailsService userDetailsService;
  
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private BlacklistService blacklistService;

    
    @Operation( summary = "Login user", description = "Obtiene el token de autorización")
    @ApiDocumentation.UserApiResponses
    @PostMapping("login")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            authenticate(jwtRequest.getUserEmail(), jwtRequest.getPassword());

            UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUserEmail());
            String token = jwtUtils.generateToken(userDetails);
            String role;
            Object userResponse;

            if (userDetails instanceof User user) {
                role = user.getRole();
                userResponse = new UserDTO(user);
            } else if (userDetails instanceof Doctor doctor) {
                role = doctor.getRole();
                userResponse = new DoctorDTO(doctor);
            } else {
                throw new Exception("Unknown user type");
            }

            // Incluir el rol en la respuesta
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("role", role);
            response.put("user",userResponse);

            return ResponseEntity.ok(response);
        }catch(DisabledUserException e){
            return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }catch (InvalidCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }

    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new DisabledUserException("USUARIO DESHABILITADO: ");
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("EMAIL O CONTRASEÑA  INCORRECTAS : ");
        }
    }

    @Operation( summary = "Cierra la sesión del usuario", description = "Cierra la sesión del usuario")
    @ApiDocumentation.UserApiResponses
    @PostMapping("logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        // Remover el prefijo "Bearer " del token
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        blacklistService.blacklistToken(token);
        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("current-user")
    public UserDetails currentUser(Principal principal) {
        return userDetailsService.loadUserByUsername(principal.getName());
    }
}

