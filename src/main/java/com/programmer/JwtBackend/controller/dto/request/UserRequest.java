package com.programmer.JwtBackend.controller.dto.request;

public record UserRequest(String username, String password, String name) {
}
