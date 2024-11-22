package org.walletuser.walletuser;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private String message;
    private T data;
    private String token = "";

    public ApiResponse(String message, T data, String token) {
        this.message = message;
        this.data = data;
        this.token = token;
    }

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String message) {
        this.message = message;
    }
}