package dev.vikel.taskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public HttpSecurity filterChain(HttpSecurity http) throws Exception {

        return http
            .authorizeRequests()
                .requestMatchers("/public/**").permitAll() // Allow public access
                .anyRequest().authenticated() // All other requests require authentication
                .and()
            .formLogin()
                .loginPage("/login") // Custom login page URL
                .defaultSuccessUrl("/dashboard", true) // Redirect after successful login
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/login") // Redirect after logout
                .permitAll()
                .and()
            .csrf().disable(); // Enable CSRF with HTTP-Only cookies


    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
