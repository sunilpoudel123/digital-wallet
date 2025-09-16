package org.walletuser.walletuser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.walletuser.walletuser.JwtAuthenticationFilter;
import org.walletuser.walletuser.Repository.UserRepository;
import org.walletuser.walletuser.Service.CustomUserDetailsService;
import org.walletuser.walletuser.Util.JwtUtil;

@Configuration
public class FilterConfig {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService, UserRepository userRepository) {
        return new JwtAuthenticationFilter(jwtUtil, userDetailsService, userRepository);
    }
}