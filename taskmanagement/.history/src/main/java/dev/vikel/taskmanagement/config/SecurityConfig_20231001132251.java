package dev.vikel.taskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

//This is for the withDefaults()
import static org.springframework.security.config.Customizer.*;

//This is used to encode password
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    
    //For password encrption
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //!I am here trying to add the manipulation of default login

    // @Bean
    // public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception{    
    //     return http
    //             .authorizeHttpRequests(auth -> {
    //                 auth.requestMatchers("/").permitAll(); //Home route anyone can get to it
    //                 auth.anyRequest().authenticated();
    //             })
    //             .oauth2Login(withDefaults())
    //             .formLogin(withDefaults())
    //             .build();
    // }

    
 
}
