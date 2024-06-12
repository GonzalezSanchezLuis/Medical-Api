package com.medicalApi.repository;

import org.springframework.data.repository.CrudRepository;
import com.medicalApi.model.UserModerator;

public interface UserModeratorRepository extends CrudRepository<UserModerator, Long>{
    
}
