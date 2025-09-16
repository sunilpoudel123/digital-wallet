package org.walletuser.walletuser.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.walletuser.walletuser.ApiResponse;
import org.walletuser.walletuser.Model.User;
import org.walletuser.walletuser.Repository.UserRepository;
import org.walletuser.walletuser.dto.UserDTO;

@Service
public class RegisterServiceImp implements RegisterService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Override
    public ResponseEntity registerUser(User user) {

        String res = "";

        if (userRepository.findByUsername(user.getUsername()) != null) {
            res = "User already exists!";
        }else {
            res =  "User registered successfully!";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER"); // Assign a default role
        userRepository.save(user);

        /////  Kafka messaging calling ///////

        kafkaProducerService.sendWalletCreationMessage("New Wallet Created: 3432 " + user);

        ///////////////  End //////////

        // Map the User object to UserDTO
        UserDTO userDTO = toUserDTO(user);

        // Create a custom response
        ApiResponse<UserDTO> response = new ApiResponse<>(res, userDTO, "");

        // Return the response with an HTTP status
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    private UserDTO toUserDTO(User user) {
        return new UserDTO(user.getUsername());
    }

    @Override
    public ResponseEntity registerAdmin(User user) {

        String res = "";

        if (userRepository.findByUsername(user.getUsername()) != null) {
            res = "Admin already exists!";
        }else{
            res = "Admin registered successfully!";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ADMIN"); // Assign a default role
        user.setEnabled(true);
        userRepository.save(user);

        // Map the User object to UserDTO
        UserDTO userDTO = toUserDTO(user);
        // Create a custom response
        ApiResponse<UserDTO> response = new ApiResponse<>(res, userDTO, "");

        // Return the response with an HTTP status
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
