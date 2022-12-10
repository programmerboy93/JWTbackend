package com.programmer.JwtBackend.repository;

import com.programmer.JwtBackend.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepo extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
}
