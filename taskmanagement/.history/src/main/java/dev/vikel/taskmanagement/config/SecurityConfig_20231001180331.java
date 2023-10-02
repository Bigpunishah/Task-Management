package dev.vikel.taskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


//This is for the withDefaults()
// import static org.springframework.security.config.Customizer.*;

@Configuration
//This is used to encode password
@EnableWebSecurity
public class SecurityConfig {
    
    
    //For password encrption
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
            .requestMatchers("/**", "/js/**", "/css/**", "/login.html", "/about.html").permitAll() //!I have included the select pages i want access before logging in.
            .anyRequest().authenticated()
        .and()
        .oauth2Login()
            .loginPage("/login.html")
            .loginProcessingUrl("/api/v1/user/login/authenticate")// Authentication url
            .defaultSuccessUrl("/tasks.html")// where the user is sent once oncfirmed log in & auth.
        .and()
        .logout() // Configure logout if needed
            .logoutUrl("/logout") // Customize logout URL
            .logoutSuccessUrl("/login.html"); // Customize logout success URL
    return http.build();
    }

    
    
}
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        

    //     return http
    //         .authorizeHttpRequests(auth ->
    //             auth.requestMatchers("/**").permitAll())
    //         .build();

    // }





 

