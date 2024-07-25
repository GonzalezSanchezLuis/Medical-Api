package com.medicalApi.dto;

import com.medicalApi.model.User;
import lombok.Data;


@Data
public class UserDTO {
    private Long userId;
    private String name;
    private String surnames;
    private String email;
    private String address;
    private String phone;
    private String role;

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.surnames = user.getSurnames();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.role = user.getRole();
    }
}
