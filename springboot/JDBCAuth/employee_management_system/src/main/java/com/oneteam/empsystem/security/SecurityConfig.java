/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oneteam.empsystem.security;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author mahmoud
 */
@Configuration
public class SecurityConfig {
    
    // ==> to Generate BCrypt Passwords: https://www.bcryptcalculator.com/
    
    @Autowired
    private DataSource dataSource;
    
    
    // JDBC auth: with different tables-names/db-schema --> custom queries 
    @Bean
    public UserDetailsManager userDetailsManager() throws Exception{
        // ==> Spring Security deal with {"authorities", "users"} tables names as a default
        // ==> if u changed those names, u must provide custom queries to handle that
        // ==> code with custom tables names such as {"members", "roles"}
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        // ==> default_query: select username,password,enabled from users where username = ?
        jdbcUserDetailsManager.setUsersByUsernameQuery("select username,password,enabled from ourusers where username=?");
        // ==> default_query: select username,authority from authorities where username = ?
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select ourusers.username,role.name from ourusers "+ 
                "inner join ourusers_roles on ourusers.id = ourusers_roles.ouruser_id "+ 
                "inner join role on ourusers_roles.role_id = role.id where ourusers.username = ?");
        
        return jdbcUserDetailsManager; // tell s_security to use DBC auth with our data source
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
                .httpBasic(data -> {
            try {
                userDetailsManager();
            } catch (Exception ex) {
                Logger.getLogger(SecurityConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        })
                .csrf(csrf -> csrf.disable())
                .build();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() throws Exception {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsManager()); // set the custom user details service
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


///////////////////////////////////////////////////////////
/*
// (2) JDBC auth without frontend

@Configuration
public class SecurityConfig {
    
    // ==> to Generate BCrypt Passwords: https://www.bcryptcalculator.com/
    
    @Autowired
    private DataSource dataSource;
    
    
    @Bean
    public UserDetailsManager userDetailsManager(){
        // ==> Spring Security deal with {"authorities", "users"} tables names as a default
        // ==> if u changed those names, u must provide custom queries to handle that
        // ==> code with custom tables names such as {"members", "roles"}
        // JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        // jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pword, active from members where user_id=?");
        // jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");
        
        // ==> code with default tables names {"users", "authorities"}
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

*/
///////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////
/*
// (1) --> in-memory auth

@Configuration
public class SecurityConfig {
    
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
*/
///////////////////////////////////////////////////////////
