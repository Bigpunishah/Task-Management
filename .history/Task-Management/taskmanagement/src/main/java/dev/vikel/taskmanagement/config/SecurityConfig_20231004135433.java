package dev.vikel.taskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // todo: Figure out how to get password to authenticate
    // todo: Once authenticated allow access to certain pages
    // todo: may need to include boolean for authenticated then do...

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/", "/api/**").permitAll()
                        .requestMatchers("/about").permitAll() // include the controller mapping URL's EX:
                        .requestMatchers("/user/**").permitAll() //For user access
                                                                    // about.html can be accessed by /about
                        .requestMatchers("/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated() // Any other request needs to be authenticated
                )
                .formLogin(withDefaults()) // Login page with default
                .logout((logout) -> logout.deleteCookies("remove")
                        .invalidateHttpSession(false)
                        .logoutUrl("/login")
                        .logoutSuccessUrl("/"));
        return http.build();
    }

    // Password encrypter
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Custom user in memeory
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // Use the password encoder to encode the password
        String encodedPassword = passwordEncoder().encode("h");

        UserDetails user = User
                .withUsername("h")
                .password(encodedPassword)
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

}