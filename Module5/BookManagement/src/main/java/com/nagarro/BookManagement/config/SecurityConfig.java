package com.nagarro.BookManagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${app.security.admin-password}")
    private String adminPassword;

    @Value("${app.security.librarian-password}")
    private String librarianPassword;

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode(adminPassword))
                .roles("ADMIN")
                .build();

        UserDetails librarian = User.builder()
                .username("librarian")
                .password(passwordEncoder.encode(librarianPassword))
                .roles("LIBRARIAN")
                .build();

        return new InMemoryUserDetailsManager(admin, librarian);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // Swagger
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/api-docs/**",
                                "/webjars/**")
                        .permitAll()

                        // Actuator
                        .requestMatchers("/actuator/**")
                        .permitAll()

                        // GET APIs
                        .requestMatchers(
                                HttpMethod.GET,
                                "/authors/**",
                                "/books/**")
                        .hasAnyRole("ADMIN", "LIBRARIAN")

                        // ADMIN APIs
                        .requestMatchers(
                                "/authors/**",
                                "/books/**")
                        .hasRole("ADMIN")

                        .anyRequest()
                        .authenticated())

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}