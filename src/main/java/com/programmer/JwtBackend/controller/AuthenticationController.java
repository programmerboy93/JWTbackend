package com.programmer.JwtBackend.controller;

import com.programmer.JwtBackend.controller.dto.request.JwtAuthenticationRequest;
import com.programmer.JwtBackend.controller.dto.request.UserRequest;
import com.programmer.JwtBackend.controller.dto.response.JwtResponse;
import com.programmer.JwtBackend.controller.dto.response.MessageResponse;
import com.programmer.JwtBackend.domain.AppUser;
import com.programmer.JwtBackend.service.AuthService;
import com.programmer.JwtBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;
    private final UserService userService;
    @PostMapping(value = "/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody JwtAuthenticationRequest authRequest) throws Exception {
        return ResponseEntity.ok(new JwtResponse(authService.createJwt(authRequest)));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AppUser> registerUser(@RequestBody UserRequest request){
        return ResponseEntity.ok(authService.registerUser(request));
    }

}
