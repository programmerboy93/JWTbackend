package com.programmer.JwtBackend.user.service;

import com.programmer.JwtBackend.user.entity.AppUser;
import com.programmer.JwtBackend.user.entity.Role;

import java.util.List;

public interface UserService {
    AppUser findByEmail(String email);

    List<AppUser> findAll();

    AppUser saveUser(AppUser newUser);

    void addRoleToUser(String username, String roleName);

    Role saveRole(Role newRole);

    boolean existsByUsername(String username);
}
