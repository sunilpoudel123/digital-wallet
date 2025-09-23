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

        User existingUser = userRepository.findByUsername(updatedUser.getUsername()).get();

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
}
