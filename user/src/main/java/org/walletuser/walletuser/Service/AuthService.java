package org.walletuser.walletuser.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.walletuser.walletuser.Model.User;

public interface AuthService extends UserDetailsService {
    public ResponseEntity createAuthenticationToken(User user) throws Exception;
}
