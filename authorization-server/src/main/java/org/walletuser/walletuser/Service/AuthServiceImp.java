package org.walletuser.walletuser.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.walletuser.walletuser.ApiResponse;
import org.walletuser.walletuser.Model.User;
import org.walletuser.walletuser.Repository.UserRepository;
import org.walletuser.walletuser.Util.JwtUtil;
import org.walletuser.walletuser.dto.UserDTO;

import java.util.ArrayList;

@Service
public class AuthServiceImp implements AuthService{

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public AuthServiceImp(JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.authenticationProvider = authenticationProvider;
//        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByUsername(username);
        //.orElseThrow(()-> new UsernameNotFoundException("User Not Found !!!"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>() // Here, you could also add roles/authorities
        );

    }

    private UserDTO toUserDTO(User user) {
        return new UserDTO(user.getUsername());
    }

    @Override
    public ResponseEntity createAuthenticationToken(User user) throws Exception {

        String res = "LogIn Successful";

        try {
            UserDetails userDetails = loadUserByUsername(user.getUsername());
// Check if the provided password matches the stored password
            if (!passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
                throw new Exception("Incorrect username or password");
            }
        } catch (AuthenticationException e) {
            res = "Incorrect username or password";
            throw new Exception("Incorrect username or password", e);
        }

        // Map the User object to UserDTO
        UserDTO userDTO = toUserDTO(user);

        final String jwt = jwtUtil.generateToken(user.getUsername());
        System.out.println(jwt);
        // Create a custom response
        ApiResponse<UserDTO> response = new ApiResponse<>(res,userDTO,jwt);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
