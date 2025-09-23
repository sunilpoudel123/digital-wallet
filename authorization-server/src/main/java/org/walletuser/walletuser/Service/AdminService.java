package org.walletuser.walletuser.Service;

import org.walletuser.walletuser.Model.User;
import org.walletuser.walletuser.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminService {

    UserDTO updateAdmin(Long userId, User userDetails);

    void updateUserEnabledStatus(Long userId, boolean enabled);

    List<User> getUsersByRole(String role);

    List<User> getUsersByRoleAndDateRange(String role, LocalDateTime startDate, LocalDateTime endDate);
}