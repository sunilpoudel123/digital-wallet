package org.walletuser.walletuser.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.walletuser.walletuser.ApiResponse;
import org.walletuser.walletuser.Model.User;
import org.walletuser.walletuser.Repository.UserRepository;
import org.walletuser.walletuser.dto.UserDTO;

import java.time.LocalDateTime;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity updateUser(User updatedUser) {

        User existingUser = userRepository.findByUsername(updatedUser.getUsername());

        // Update only modifiable fields
        if (updatedUser.getFirstName() != null) {
            existingUser.setFirstName(updatedUser.getFirstName());
        }

        if (updatedUser.getLastName() != null) {
            existingUser.setLastName(updatedUser.getLastName());
        }

        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }

        if (updatedUser.getPhone() != null) {
            existingUser.setPhone(updatedUser.getPhone());
        }

        if (updatedUser.getAddress() != null) {
            existingUser.setAddress(updatedUser.getAddress());
        }

        // Update the `updatedAt` timestamp
        existingUser.setUpdatedAt(LocalDateTime.now());

        userRepository.save(existingUser);

        // Map the User object to UserDTO
        UserDTO userDTO = toUserDTO(existingUser);
        // Create a custom response
        ApiResponse<UserDTO> response = new ApiResponse<>("User updated successfully", userDTO);

        return ResponseEntity.ok(response);
    }

    private UserDTO toUserDTO(User user) {
        return new UserDTO(user.getUsername());
    }

//    public ResponseEntity updateAdmin(User updatedAdmin) {
//
//        User existingAdmin = userRepository.findByUsername(updatedAdmin.getUsername());
//
//        // Update only modifiable fields
//        if (updatedAdmin.getFirstName() != null) {
//            existingAdmin.setFirstName(updatedAdmin.getFirstName());
//        }
//
//        if (updatedAdmin.getLastName() != null) {
//            existingAdmin.setLastName(updatedAdmin.getLastName());
//        }
//
//        if (updatedAdmin.getEmail() != null) {
//            existingAdmin.setEmail(updatedAdmin.getEmail());
//        }
//
//        if (updatedAdmin.getPhone() != null) {
//            existingAdmin.setPhone(updatedAdmin.getPhone());
//        }
//
//        if (updatedAdmin.getAddress() != null) {
//            existingAdmin.setAddress(updatedAdmin.getAddress());
//        }
//
//        // Update the `updatedAt` timestamp
//        existingAdmin.setUpdatedAt(LocalDateTime.now());
//
//        userRepository.save(existingAdmin);
//
//        // Map the User object to UserDTO
//        UserDTO userDTO = toUserDTO(existingAdmin);
//        // Create a custom response
//        ApiResponse<UserDTO> response = new ApiResponse<>("Admin updated successfully", userDTO);
//
//        return ResponseEntity.ok(response);
//    }
}
