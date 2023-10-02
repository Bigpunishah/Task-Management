package dev.vikel.taskmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.vikel.taskmanagement.users.UserService;

@Configuration
public class AuthenticationConfig {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, UserService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }
}
