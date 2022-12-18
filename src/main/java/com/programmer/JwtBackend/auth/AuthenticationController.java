package com.programmer.JwtBackend.auth;

import com.programmer.JwtBackend.jwt.TokenDto;
import com.programmer.JwtBackend.user.AuthService;
import com.programmer.JwtBackend.user.dto.LoginDto;
import com.programmer.JwtBackend.user.dto.RegisterDto;
import com.programmer.JwtBackend.user.entity.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;
    @PostMapping(value = "/login")
    public ResponseEntity<TokenDto> loginUser(@RequestBody LoginDto dto) throws Exception {
        return ResponseEntity.ok(authService.generateJwtToken(dto));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AppUser> registerUser(@RequestBody RegisterDto request){
        return ResponseEntity.ok(authService.registerUser(request));
    }

}
