package com.acd.enrollment_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disabled for testing via Postman/H2
                .authorizeHttpRequests(auth -> auth
                        // Allow all GET requests
                        .requestMatchers(HttpMethod.GET, "/students/**", "/courses/**").permitAll()
                        // Protect everything else (POST, PUT, DELETE)
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // Use Basic Auth for simplicity

        return http.build();
    }

    // Creating a default user for the assessment
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin123") // {noop} means no password encoding (for demo)
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}
