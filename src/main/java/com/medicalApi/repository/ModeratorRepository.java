package com.medicalApi.repository;

import org.springframework.data.repository.CrudRepository;
import com.medicalApi.model.Moderator;

public interface ModeratorRepository extends CrudRepository<Moderator,Long> {
    public Moderator findModeratorByModeratorName(String moderatorName);
}
