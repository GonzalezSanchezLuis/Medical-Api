package com.medicalApi.service.impl;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.medicalApi.model.Moderator;
import com.medicalApi.model.User;
import com.medicalApi.model.UserModerator;
import com.medicalApi.repository.ModeratorRepository;
import com.medicalApi.repository.UserModeratorRepository;
import com.medicalApi.repository.UserRepository;
import com.medicalApi.service.UserService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
   
    @Autowired
    private ModeratorRepository moderatorRepository;

    @Autowired
    private UserModeratorRepository userModeratorRepository;

    @Autowired 
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User register(User user, Set<UserModerator> userModerators) throws Exception {
    User localUser = userRepository.findUserByEmail(user.getEmail());
    if (localUser != null) {
        throw new Exception("Ya existe un usuario registrado con ese email.");
    }

    // Encriptar la contraseña del usuario
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

    // Guardar el usuario primero
    localUser = userRepository.save(user);

    // Configurar y guardar los UserModerators
    for (UserModerator userModerator : userModerators) {
        // Verificar si el moderador ya existe en la base de datos
        Moderator existingModerator = moderatorRepository.findById(userModerator.getModerator().getModeratorId()).orElse(null);
        if (existingModerator == null) {
            // Guardar el moderador si no existe
            existingModerator = moderatorRepository.save(userModerator.getModerator());
        }
        userModerator.setModerator(existingModerator);
        userModerator.setUser(localUser);
        userModeratorRepository.save(userModerator);
    }

    localUser.getUserModerators().addAll(userModerators);
    return userRepository.save(localUser);
}

    @Override
    public User getUserById(Long id) {
       Optional<User> getUserById = userRepository.findById(id);
        if(getUserById.isPresent()){
            return getUserById.get();
        }
        throw new EntityNotFoundException("No se encontró un usuario con ese ID");
    }

    @Override
    public User updateUser(Long id, User user) {
        User userUpdate = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No hay un usuario con este ID"));
    
        userUpdate.setName(user.getName());
        userUpdate.setSurnames(user.getSurnames());   
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userUpdate.setAddress(user.getAddress());
        userUpdate.setPhone(user.getPhone());
        userUpdate.setPerfil(user.getPerfil());
    return userRepository.save(userUpdate);
    }

    @Override
    public void deleteUser(Long id) {
        User userDelete = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No hay un usuario con este ID"));
        userRepository.delete(userDelete);
    }
}
