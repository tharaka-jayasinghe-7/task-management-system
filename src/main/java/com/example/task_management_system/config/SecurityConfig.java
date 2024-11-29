package com.example.task_management_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/admin/**", "/task/**", "/user/**")  // Optionally ignore CSRF for these URLs
                )
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/admin/**", "/task/**", "/user/**").permitAll()  // Allow access to these routes without authentication
                                .anyRequest().authenticated()  // All other requests need authentication
                )
                .headers(headers -> headers
                        .contentSecurityPolicy(csp -> csp
                                .policyDirectives("default-src 'self'; script-src 'self'; object-src 'none';"))  // Set strict CSP header
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")  // Customize login page
                                .permitAll()
                );

        return http.build();
    }


}
