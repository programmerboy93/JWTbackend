package com.programmer.JwtBackend.service;

import com.programmer.JwtBackend.domain.AppUser;
import com.programmer.JwtBackend.domain.Role;

import java.util.List;

public interface UserService {
    AppUser findByEmail(String email);

    List<AppUser> findAll();

    AppUser saveUser(AppUser newUser);

    void addRoleToUser(String username, String roleName);

    Role saveRole(Role newRole);

    boolean existsByUsername(String username);
}
