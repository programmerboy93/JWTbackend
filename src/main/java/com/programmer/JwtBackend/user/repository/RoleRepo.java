package com.programmer.JwtBackend.user.repository;

import com.programmer.JwtBackend.user.entity.ERole;
import com.programmer.JwtBackend.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
