package com.amy.security.config;


import com.amy.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService UserDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(()-> new  UsernameNotFoundException("User not found"));
    }


}
