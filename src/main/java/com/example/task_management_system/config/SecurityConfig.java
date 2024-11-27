package com.example.task_management_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/admin/**")  // Optionally ignore CSRF for specific URLs
                )
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/admin/**").permitAll()  // Allow access to admin routes without authentication
                                .anyRequest().authenticated()  // All other requests need authentication
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")  // Customize login page
                                .permitAll()
                );

        return http.build();
    }

}
