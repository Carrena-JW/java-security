package dev.carrena.security.config;

import dev.carrena.security.repository.SecurityUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApploicationConfig {
    private final SecurityUserRepository securityUserRepository;
    @Bean
    public UserDetailsService userDetailsService(){
        return username ->  securityUserRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
