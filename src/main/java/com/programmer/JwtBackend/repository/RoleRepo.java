package com.programmer.JwtBackend.repository;

import com.programmer.JwtBackend.domain.ERole;
import com.programmer.JwtBackend.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
