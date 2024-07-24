package com.managedrink.until.configs;

import com.managedrink.jwt.JwtRequestFilter;
import com.managedrink.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Class: SecurityConfig
 * Author: ACER
 * Date: 7/23/2024
 * Description: [Your description here]
 */

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtRequestFilter jwtFilterSecurity;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/swagger-ui/**",
                            "/v3/api-docs/**").permitAll();

                    auth.requestMatchers("/error").permitAll();
                    auth.requestMatchers("/auth/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/api/**").hasAuthority("READ_PRIVILEGE");
                    auth.requestMatchers(HttpMethod.POST, "/api/**").hasAuthority("WRITE_PRIVILEGE");
                    auth.requestMatchers(HttpMethod.PUT, "/api/**").hasAuthority("WRITE_PRIVILEGE");
                    auth.requestMatchers(HttpMethod.DELETE, "/api/**").hasAuthority("DELETE_PRIVILEGE");
                    auth.requestMatchers("/api/drinks/**").hasRole("ADMIN");
                    auth.requestMatchers("/api/topping/**").hasRole("EMPLOYEE");
                    auth.anyRequest().authenticated();
                });

        http.addFilterBefore(jwtFilterSecurity, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
