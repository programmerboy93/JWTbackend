package com.programmer.JwtBackend.controller;

import com.programmer.JwtBackend.controller.dto.request.JwtAuthenticationRequest;
import com.programmer.JwtBackend.controller.dto.response.JwtResponse;
import com.programmer.JwtBackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtAuthenticationRequest authRequest) throws Exception {
        return ResponseEntity.ok(new JwtResponse(authService.createJwt(authRequest)));
    }



}
