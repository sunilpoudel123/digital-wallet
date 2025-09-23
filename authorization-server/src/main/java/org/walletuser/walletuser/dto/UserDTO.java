package org.walletuser.walletuser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;

    public UserDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserDTO(String username) {
        this.username = username;
    }
}