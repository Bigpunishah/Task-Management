package dev.vikel.taskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
            .requestMatchers("/", "/js/**", "/css/**", "/about.html", "/api/**").permitAll() //!I have included the select pages i want access before logging in.
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        .and()
        .logout() // Configure logout if needed
            .logoutUrl("/logout") // Customize logout URL
            .logoutSuccessUrl("/login"); // Customize logout success URL
    return http.build();
    


    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
