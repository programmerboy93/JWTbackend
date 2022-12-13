package com.programmer.JwtBackend.service;

import com.programmer.JwtBackend.config.JwtUtils;
import com.programmer.JwtBackend.config.MyUserDetails;
import com.programmer.JwtBackend.config.validator.EmailValidator;
import com.programmer.JwtBackend.controller.dto.request.JwtAuthenticationRequest;
import com.programmer.JwtBackend.controller.dto.request.UserRequest;
import com.programmer.JwtBackend.domain.AppUser;
import com.programmer.JwtBackend.domain.ERole;
import com.programmer.JwtBackend.domain.Role;
import com.programmer.JwtBackend.repository.RoleRepo;
import com.programmer.JwtBackend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthService {
    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final JwtUtils jwtUtils;

    private final UserService userService;

    private final EmailValidator emailValidator;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public String createJwt(JwtAuthenticationRequest request) {
        try {
            final UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(request.username(), request.password());
            Authentication authenticate = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authenticate);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        final AppUser user = userRepo.findByEmail(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("User could not be found"));
        final MyUserDetails userDetails = new MyUserDetails(user);
        return jwtUtils.generateJwtToken(userDetails);
    }

    public AppUser registerUser(UserRequest request) {
        if (!emailValidator.test(request.username())) {
            throw new IllegalArgumentException("Not a valid email");
        } else if (userRepo.existsByEmail(request.username())) {
            throw new IllegalArgumentException("Email already taken");
        }
        return registerHelper(request);
    }

    private AppUser registerHelper(UserRequest request) {
        final Role byNameE = roleRepo.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Role is not found"));
        final AppUser user = new AppUser(null, request.name(), request.username(), passwordEncoder.encode(request.password()), Set.of(byNameE));
        return userRepo.save(user);
    }
}
