package com.programmer.JwtBackend.jwt;

import org.springframework.security.core.Authentication;

public interface JwtUtils{
    String generateJwtToken(Authentication authentication);

    String extractUsernameFromJwtToken(String token);

    boolean validateJwtToken(String token);
}
