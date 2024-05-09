/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oneteam.empsystem.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author mahmoud
 */
@Configuration
public class SecurityConfig {
    
    @Autowired
    private DataSource dataSource;
    
    
    @Bean
    public UserDetailsManager userDetailsManager(){
        return new JdbcUserDetailsManager(dataSource); // tell s_security to use DBC auth with our data source
    }
    
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         // spring security uses "ROLE_" as a prefix for roles
        return http.authorizeHttpRequests(configurer
                -> configurer
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("MANAGER")
                        .anyRequest().authenticated())
                .httpBasic(data -> userDetailsManager())
                .csrf(csrf -> csrf.disable())
                .build();
    }

}


/*
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
 */
