package dev.carrena.security.repository;

import dev.carrena.security.auth.user.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface SecurityUserRepository extends JpaRepository<SecurityUser, Long> {
    Optional<SecurityUser> findByEmail(String email);
}
