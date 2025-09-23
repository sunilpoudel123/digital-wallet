package org.walletuser.walletuser.Service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.walletuser.walletuser.ApiResponse;
import org.walletuser.walletuser.Model.User;

public interface AuthService extends UserDetailsService {
    ApiResponse authenticateUser(User user) throws Exception;
}
