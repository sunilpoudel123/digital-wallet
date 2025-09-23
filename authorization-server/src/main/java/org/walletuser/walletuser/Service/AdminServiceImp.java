package org.walletuser.walletuser.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.walletuser.walletuser.Model.User;
import org.walletuser.walletuser.Repository.AdminRepository;
import org.walletuser.walletuser.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdminServiceImp implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImp(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Transactional
    public UserDTO updateAdmin(Long userId, User updatedAdminDetails) {
        User existingAdmin = adminRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Admin with ID " + userId + " not found."));
        updateUserFields(existingAdmin, updatedAdminDetails);
        existingAdmin.setUpdatedAt(LocalDateTime.now());
        User savedUser = adminRepository.save(existingAdmin);
        return toUserDTO(savedUser);
    }

    private void updateUserFields(User existingUser, User updatedDetails) {
        if (updatedDetails.getFirstName() != null) {
            existingUser.setFirstName(updatedDetails.getFirstName());
        }
        if (updatedDetails.getLastName() != null) {
            existingUser.setLastName(updatedDetails.getLastName());
        }
        if (updatedDetails.getEmail() != null) {
            existingUser.setEmail(updatedDetails.getEmail());
        }
        if (updatedDetails.getPhone() != null) {
            existingUser.setPhone(updatedDetails.getPhone());
        }
        if (updatedDetails.getAddress() != null) {
            existingUser.setAddress(updatedDetails.getAddress());
        }
    }

    @Override
    @Transactional
    public void updateUserEnabledStatus(Long userId, boolean enabled) {
        User user = adminRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + userId + " not found."));
        user.setEnabled(enabled);
        adminRepository.save(user);
    }

    @Override
    public List<User> getUsersByRole(String role) {
        return adminRepository.findByRole(role);
    }

    @Override
    public List<User> getUsersByRoleAndDateRange(String role, LocalDateTime startDate, LocalDateTime endDate) {
        return adminRepository.findUsersByRoleAndCreatedAtBetween(role, startDate, endDate);
    }

    private UserDTO toUserDTO(User user) {
        return new UserDTO(user.getUsername(), user.getEmail());
    }
}