package com.programmer.JwtBackend.auth;

import com.programmer.JwtBackend.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    private static final String MESSAGE_USER_WITH_USERNAME_NOT_FOUND = "USER %s NOT FOUND";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return new CustomUserDetails(
                userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(MESSAGE_USER_WITH_USERNAME_NOT_FOUND + email))
        );

    }

}
