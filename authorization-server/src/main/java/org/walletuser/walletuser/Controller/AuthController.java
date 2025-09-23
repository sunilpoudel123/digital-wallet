package org.walletuser.walletuser.Controller;

import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.walletuser.walletuser.ApiResponse;
import org.walletuser.walletuser.Model.User;
import org.walletuser.walletuser.Service.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authenticate")
    @PermitAll
    public ResponseEntity<ApiResponse> createAuthenticationToken(@RequestBody User user) throws Exception {
        try {
            ApiResponse response = authService.authenticateUser(user);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }
    }
}