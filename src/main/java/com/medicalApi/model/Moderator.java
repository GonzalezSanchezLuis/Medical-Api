package com.medicalApi.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "moderator")
public class Moderator {

    @Id
    private Long moderatorId;
    private String moderatorName;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "moderator")
    private Set<UserModerator> userModerators = new HashSet<>();

    @Override
    public int hashCode(){
        return Objects.hash(moderatorId);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Moderator moderator = (Moderator) obj;
        return moderatorId.equals(moderator.moderatorId);
    }
}
