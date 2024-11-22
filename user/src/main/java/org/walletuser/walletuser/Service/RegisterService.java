package org.walletuser.walletuser.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.walletuser.walletuser.Model.User;

public interface RegisterService{
    public ResponseEntity registerUser(User user);
    public ResponseEntity registerAdmin(User user);
}
