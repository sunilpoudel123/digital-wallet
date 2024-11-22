package org.walletuser.walletuser.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
//    private String password;

    // No-argument constructor (required by frameworks like Spring)
    public UserDTO() {
    }

    // Constructor with username field
    public UserDTO(String username) {
        this.username = username;
    }
}
