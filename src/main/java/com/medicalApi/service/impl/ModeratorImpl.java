package com.medicalApi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.medicalApi.model.Moderator;
import com.medicalApi.repository.ModeratorRepository;
import com.medicalApi.service.ModeratorService;

@Service
public class ModeratorImpl implements ModeratorService{
    @Autowired
    private ModeratorRepository moderatorRepository;

    @Override
    public Moderator findByModeratorName(String moderatorName) {
       return moderatorRepository.findModeratorByModeratorName(moderatorName);
    }

    
    
}
