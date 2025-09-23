package org.walletuser.walletuser.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.walletuser.walletuser.ApiResponse;
import org.walletuser.walletuser.Model.User;
import org.walletuser.walletuser.Repository.UserRepository;
import org.walletuser.walletuser.Util.JwtUtil;
import org.walletuser.walletuser.dto.UserDTO;

import java.util.Collections;

@Service
@Slf4j
public class AuthServiceImp implements AuthService, UserDetailsService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthServiceImp(JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found !!!"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList()
        );
    }

    @Override
    public ApiResponse authenticateUser(User userRequest) {
        log.info("authentication request by User {}", userRequest.getUsername());
        User user = userRepository.findByUsername(userRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found after authentication!"));

        String jwt = jwtUtil.generateToken(userRequest.getUsername());
        UserDTO userDTO = new UserDTO(user.getUsername(), user.getEmail());
        return new ApiResponse("Login Successful", userDTO, jwt);
    }

}
