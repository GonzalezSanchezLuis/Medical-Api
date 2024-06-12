package com.medicalApi.service;

import com.medicalApi.model.Moderator;

public interface ModeratorService {
    public Moderator findByModeratorName(String moderatorName);
}
