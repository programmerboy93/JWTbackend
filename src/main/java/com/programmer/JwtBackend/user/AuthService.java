package com.programmer.JwtBackend.user;

import com.programmer.JwtBackend.auth.CustomUserDetailsService;
import com.programmer.JwtBackend.jwt.JwtUtils;
import com.programmer.JwtBackend.jwt.TokenDto;
import com.programmer.JwtBackend.user.dto.LoginDto;
import com.programmer.JwtBackend.user.dto.RegisterDto;
import com.programmer.JwtBackend.user.dto.validator.EmailValidator;
import com.programmer.JwtBackend.user.entity.AppUser;
import com.programmer.JwtBackend.user.entity.ERole;
import com.programmer.JwtBackend.user.entity.Role;
import com.programmer.JwtBackend.user.repository.RoleRepo;
import com.programmer.JwtBackend.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthService {
    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final JwtUtils jwtUtils;

    private final EmailValidator emailValidator;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final CustomUserDetailsService userDetailsService;

    public TokenDto generateJwtToken(LoginDto dto) {

        log.info(String.format("username %s is trying authenticate", dto.username()));

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new TokenDto(jwtUtils.generateJwtToken(authentication));
    }

    public AppUser registerUser(RegisterDto request) {
        if (!emailValidator.test(request.username())) {
            throw new IllegalArgumentException("Not a valid email");
        } else if (userRepo.existsByEmail(request.username())) {
            throw new IllegalArgumentException("Email already taken");
        }
        return registerHelper(request);
    }

    private AppUser registerHelper(RegisterDto request) {
        final Role byNameE = roleRepo.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Role is not found"));
        final AppUser user = new AppUser(null, request.name(), request.username(), passwordEncoder.encode(request.password()), Set.of(byNameE));
        return userRepo.save(user);
    }
}
