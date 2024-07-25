package com.medicalApi.model;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "doctor")
public class Doctor implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    private String doctorName;
    private String surnames;
    private  String specialty;
    private String email;
    private String password;
    private int  document;
    private String profesionalCard;
    private String addressConsultingRoom;
    private String phoneConsultingRoom;
    private boolean enabled = true;
    private String role;

    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<MedicalAppointment> appointments;


    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkSchedule> workSchedules;

    @Override
    @Schema(hidden = true)
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return null;
    }

    @Override
    @Schema(hidden = true)
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @Schema(hidden = true)
    @JsonIgnore
    public boolean isAccountNonExpired() {
       return true;
    }

    @Override
    @Schema(hidden = true)
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
        
    }

    @Override
    @Schema(hidden = true)
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Schema(hidden = true)
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }


}
