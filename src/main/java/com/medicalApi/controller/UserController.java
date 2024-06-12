package com.medicalApi.controller;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medicalApi.config.Documentation.ApiDocumentation;
import com.medicalApi.model.Moderator;
import com.medicalApi.model.User;
import com.medicalApi.model.UserModerator;
import com.medicalApi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/user/")
@Tag(name = "User", description = "Controlador de usuario")
public class UserController {

    @Autowired
    private UserService userService;
 

    @PostMapping("register")
    @Operation(
        summary = "Register a New User",
        description = "Registra un nuevo usuario",
        tags = {"User"})
    @ApiDocumentation.UserApiResponses
    public ResponseEntity<?> registerUser(@RequestBody User user) throws Exception{
       
        try {
            user.setPerfil("default.png");
            Set<UserModerator> moderators = new HashSet<>();

            
            Moderator moderator = new Moderator();
            moderator.setModeratorId(2L); // ID del moderador existente
            moderator.setModeratorName("NORMAL");
    
            UserModerator userModerator = new UserModerator();
            userModerator.setUser(user);
            userModerator.setModerator(moderator);
            moderators.add(userModerator);
    
            User register = userService.register(user, moderators);
            return ResponseEntity.status(HttpStatus.CREATED).body(register);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("user/{id}")
    @Operation(
        summary = "Get User by ID",
        description = "Obtiene la información de un usuario por su ID",
        tags = {"User"})
    @ApiDocumentation.UserApiResponses
    public ResponseEntity<?> getUserById(@PathVariable Long id) throws Exception{
       try {
         User user = userService.getUserById(id);
         return ResponseEntity.status(HttpStatus.OK).body(user);
       } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("se produjo un error del servidor al procesar la solicitud");
       }
    }

    @PutMapping("update/{id}")
    @Operation(
        summary = "Update User",
        description = "Actualiza la información de un usuario por su ID",
        tags = {"User"})
    @ApiDocumentation.UserApiResponses
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) throws Exception {
        try {
            User updateUser = userService.updateUser(id, user);
            return ResponseEntity.status(HttpStatus.OK).body(updateUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error del servidor al procesar la solicitu");
        }
    }

    @DeleteMapping("delete/{id}")
    @Operation(
        summary = "Delete User",
        description = "Elimina un usuario por su ID",
        tags = {"User"})
    @ApiDocumentation.UserApiResponses
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) throws Exception{
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Usuario eliminado");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("se produjo un error del servidor al procesar la solicitud");
        }
    }
}
