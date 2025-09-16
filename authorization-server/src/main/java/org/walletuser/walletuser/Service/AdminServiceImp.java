package org.walletuser.walletuser.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.walletuser.walletuser.ApiResponse;
import org.walletuser.walletuser.Model.User;
import org.walletuser.walletuser.Repository.AdminRepository;
import org.walletuser.walletuser.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    private UserDTO toUserDTO(User user) {

        return new UserDTO(user.getUsername());
    }

    public ResponseEntity updateAdmin(User updatedAdmin) {

        User existingAdmin = adminRepository.findByUsername(updatedAdmin.getUsername());

        // Update only modifiable fields
        if (updatedAdmin.getFirstName() != null) {
            existingAdmin.setFirstName(updatedAdmin.getFirstName());
        }

        if (updatedAdmin.getLastName() != null) {
            existingAdmin.setLastName(updatedAdmin.getLastName());
        }

        if (updatedAdmin.getEmail() != null) {
            existingAdmin.setEmail(updatedAdmin.getEmail());
        }

        if (updatedAdmin.getPhone() != null) {
            existingAdmin.setPhone(updatedAdmin.getPhone());
        }

        if (updatedAdmin.getAddress() != null) {
            existingAdmin.setAddress(updatedAdmin.getAddress());
        }

        // Update the `updatedAt` timestamp
        existingAdmin.setUpdatedAt(LocalDateTime.now());

        adminRepository.save(existingAdmin);

        // Map the User object to UserDTO
        UserDTO userDTO = toUserDTO(existingAdmin);
        // Create a custom response
        ApiResponse<UserDTO> response = new ApiResponse<>("Admin updated successfully", userDTO);

        return ResponseEntity.ok(response);
    }

    @Override
    public void updateUserEnabled(Long userId) {

        Optional<User> optionalUser = adminRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(!user.getEnabled()); // Set the 'enabled' field
            adminRepository.save(user); // Save the updated user
        } else {
            throw new IllegalArgumentException("User with ID " + userId + " not found.");
        }
    }

    @Override
    public List<User> getUsersByRole(String role) {
        return adminRepository.findByRole(role);
    }

    public List<User> getUsersByRoleAndDateRange(String role, LocalDateTime startDate, LocalDateTime endDate) {
        return adminRepository.findUsersByRoleAndCreatedAtBetween(role, startDate, endDate);
    }
}
