package com.medicalApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.medicalApi.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findUserByEmail(String email);
    
}
