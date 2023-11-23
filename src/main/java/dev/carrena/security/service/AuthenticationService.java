package dev.carrena.security.service;

import dev.carrena.security.auth.user.Role;
import dev.carrena.security.auth.user.SecurityUser;
import dev.carrena.security.model.AuthenticationRequest;
import dev.carrena.security.model.AuthenticationResponse;
import dev.carrena.security.model.RegisterRequest;
import dev.carrena.security.repository.SecurityUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final SecurityUserRepository securityUserRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        var securityUser = SecurityUser.builder()
                .userName(request.getUsername())
                .email(request.getEmail())
                .userPassword(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        securityUserRepository.save(securityUser);

        var jwtToken = jwtService.generateToken(securityUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
        );

        var user = securityUserRepository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }
}
