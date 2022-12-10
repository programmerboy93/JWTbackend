package com.programmer.JwtBackend.service;

import com.programmer.JwtBackend.domain.AppUser;
import com.programmer.JwtBackend.domain.Role;
import com.programmer.JwtBackend.repository.RoleRepo;
import com.programmer.JwtBackend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<AppUser> findAll() {
        return userRepo.findAll();
    }

    @Override
    public AppUser saveUser(AppUser newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepo.save(newUser);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        Role role = roleRepo.findByName(roleName);
        AppUser user = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.getRoles().add(role);
    }

    @Override
    public Role saveRole(Role newRole) {
        return roleRepo.save(newRole);
    }
}
