package org.walletuser.walletuser.Service;

import org.springframework.http.ResponseEntity;
import org.walletuser.walletuser.Model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminService {

    public ResponseEntity updateAdmin(User user) ;
    public void updateUserEnabled(Long userId);

    List<User> getUsersByRole(String role);

    List<User> getUsersByRoleAndDateRange(String role, LocalDateTime start, LocalDateTime end);
}
