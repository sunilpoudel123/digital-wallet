package edu.miu.wallet.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private long id;

    private String username;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
