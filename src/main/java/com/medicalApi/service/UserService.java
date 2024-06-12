package com.medicalApi.service;

import java.util.Set;
import com.medicalApi.model.User;
import com.medicalApi.model.UserModerator;


public interface UserService {
    public User register(User user, Set<UserModerator> userModerator) throws Exception;
    public User getUserById(Long id); 
    public User updateUser(Long id, User user);
    public void deleteUser(Long id);  
}
