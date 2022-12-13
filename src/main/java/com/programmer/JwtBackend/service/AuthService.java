package com.programmer.JwtBackend.service;

import com.programmer.JwtBackend.config.JwtUtils;
import com.programmer.JwtBackend.config.MyUserDetails;
import com.programmer.JwtBackend.controller.dto.request.JwtAuthenticationRequest;
import com.programmer.JwtBackend.domain.AppUser;
import com.programmer.JwtBackend.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthService {
    private final UserRepo userRepository;

    private final JwtUtils jwtUtils;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final AuthenticationManager authenticationManager;

    public String createJwt(JwtAuthenticationRequest request) {
        try {
            final UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(request.username(), request.password());
            Authentication authenticate = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authenticate);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        final AppUser user = userRepository.findByEmail(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("User could not be found"));
        final MyUserDetails userDetails = new MyUserDetails(user);
        return jwtUtils.generateJwtToken(userDetails);
    }
}
