/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oneteam.empsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author mahmoud
 */
@Configuration
public class SecurityConfig1 {
    
    // in memory auth
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        // SpringSecurity algo --> {noop}password it tells ssecurity that the password will be a plain txt
        UserDetails ali = User.builder()
                .username("ali")
                .password("{noop}12345")
                .roles("Employee")
                .build();
        UserDetails osama = User.builder()
                .username("osama")
                .password("{noop}12345")
                .roles("Employee", "Manager")
                .build();
        UserDetails mostafa = User.builder()
                .username("mostafa")
                .password("{noop}12345")
                .roles("Employee", "Manager", "Admin")
                .build();
        return new InMemoryUserDetailsManager(ali, osama, mostafa);
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer -> 
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("Employee")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("Employee")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("Manager")
                        .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("Manager")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("Admin")
        );
        http.httpBasic(data -> userDetailsManager());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
    
}
