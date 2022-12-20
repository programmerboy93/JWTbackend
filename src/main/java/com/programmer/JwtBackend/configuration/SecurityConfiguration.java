package com.programmer.JwtBackend.configuration;

import com.programmer.JwtBackend.auth.exception.JwtAccessDeniedHandler;
import com.programmer.JwtBackend.auth.exception.JwtAuthenticationEntryPoint;
import com.programmer.JwtBackend.jwt.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthFilter jwtAuthFilter;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable().cors().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint);

        http.authorizeHttpRequests().requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/api/v1/test/user").hasAuthority("ROLE_USER")
                .requestMatchers("/api/v1/test/admin").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthFilter, BasicAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
