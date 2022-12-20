package com.programmer.JwtBackend.user;

import com.programmer.JwtBackend.user.entity.AppUser;
import com.programmer.JwtBackend.user.entity.ERole;
import com.programmer.JwtBackend.user.entity.Role;
import com.programmer.JwtBackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class InitializationDatabase {

    private final UserService userService;

    @EventListener(ApplicationReadyEvent.class)
    public String initDatabase() {
        Role admin = new Role(null, ERole.ROLE_ADMIN);
        Role user = new Role(null, ERole.ROLE_USER);

        userService.saveRole(admin);
        userService.saveRole(user);


        AppUser ania = new AppUser(null, "Ania", "ania", "password", new HashSet<>());
        ania.getRoles().add(admin);

        AppUser mike = new AppUser(null, "Mike", "mike@mike.com", "password", new HashSet<>());
        mike.getRoles().add(admin);
        mike.getRoles().add(user);

        AppUser will = new AppUser(null, "Will", "will@will.com", "password", new HashSet<>());
        will.getRoles().add(user);

        userService.saveUser(ania);
        userService.saveUser(mike);
        userService.saveUser(will);

        return "Databases successfully initialized";
    }
}
