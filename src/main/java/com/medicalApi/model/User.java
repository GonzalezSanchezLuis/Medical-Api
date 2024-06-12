package com.medicalApi.model;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medicalApi.model.jwt.Authority;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "user")
@Entity
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String surnames;
    private String document;
    private String email;
    private String password;
    private String address;
    private String phone;
    private boolean enabled = true;
    private String perfil;
 
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserModerator> userModerators = new HashSet<>();

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,orphanRemoval = true)
    @Schema(hidden = true)
    @JsonIgnore
    private List<MedicalAppointment> appointments;

    @Override
    @Schema(hidden = true)
    public String getUsername() {
        return this.email;
    }

    @Override
    @Schema(hidden = true)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Schema(hidden = true)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Schema(hidden = true)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Schema(hidden = true)
    public Collection<? extends GrantedAuthority> getAuthorities() {
       Set<Authority> authorities =  new HashSet<>();
       if (userModerators != null) {
           userModerators.forEach(usModerator -> {
               if (usModerator.getModerator() != null) {
                   authorities.add(new Authority(usModerator.getModerator().getModeratorName()));
               }
           });
       }
       return authorities;
    }

    @Override
    @Schema(hidden = true)
    public boolean isEnabled() {
        return true;
    }

    @Schema(hidden = true)
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(userId, user.userId) && Objects.equals(email, user.email);
    }
    
}
