package com.programmer.JwtBackend.config;

import com.programmer.JwtBackend.domain.AppUser;
import com.programmer.JwtBackend.domain.Role;
import com.programmer.JwtBackend.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
@Transactional
public class InitializationDatabase {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public String initDatabase() {
        Role admin = new Role(null, "ROLE_ADMIN");
        Role user = new Role(null, "ROLE_USER");

        userService.saveRole(admin);
        userService.saveRole(user);


        AppUser ania = new AppUser(null, "Ania", "ania@ania.com", passwordEncoder.encode("password"), new ArrayList<>());
        ania.getRoles().add(admin);

        AppUser mike = new AppUser(null, "Mike", "mike@mike.com", passwordEncoder.encode("password"), new ArrayList<>());
        mike.getRoles().add(admin);
        mike.getRoles().add(user);

        AppUser will = new AppUser(null, "Will", "will@will.com", passwordEncoder.encode("password"), new ArrayList<>());
        will.getRoles().add(user);

        userService.saveUser(ania);
        userService.saveUser(mike);
        userService.saveUser(will);

        return "Databases successfully initialized";
    }
}
