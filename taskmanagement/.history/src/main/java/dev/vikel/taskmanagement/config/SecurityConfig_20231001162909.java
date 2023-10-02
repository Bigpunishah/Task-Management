package dev.vikel.taskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.SecurityConfigurer;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


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

    //!I am here trying to add the manipulation of default login

    //withDefaults is causing error
    //!pickup here
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


    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http.authorizeRequests()
    //      .anyRequest().authenticated()
    //      .and()
    //      .oauth2Login();
    //     return http.build();
    // }


    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http.authorizeRequests()
    //     .requestMatchers("/", "/js/**", "/css/**")
    //     .permitAll()
    //     .anyRequest()
    //     .authenticated()
    //     .and()
    //     .oauth2Login()
    //     .loginPage("/login.html").permitAll(true);
    //     return http.build();
    // }

    @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
            .requestMatchers("/", "/login.html", "/js/**", "/css/**").permitAll()
            .anyRequest().authenticated()
        .and()
        .oauth2Login()
            .loginPage("/login")
        .and()
        .logout() // Configure logout if needed
            .logoutUrl("/logout") // Customize logout URL
            .logoutSuccessUrl("/login.html"); // Customize logout success URL
    return http.build();
    }

    
    
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        
        
        //     return http
        //         .authorizeHttpRequests(auth ->
        //             auth.requestMatchers("/**").permitAll())
        //         .build();
        
        // }
        
        
}



 

