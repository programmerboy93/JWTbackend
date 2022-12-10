package com.programmer.JwtBackend.controller;

import com.programmer.JwtBackend.controller.dto.AuthenticationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @PostMapping("/login")
    public String authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return "";
    }
}
