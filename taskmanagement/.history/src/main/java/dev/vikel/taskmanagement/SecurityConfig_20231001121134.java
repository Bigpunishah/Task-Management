package dev.vikel.taskmanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


//This is used to encode password
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //extends 
    //For password encrption
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //!I am here trying to add the manipulation of default login

//     @Bean
// public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//     http.formLogin()
//       .loginPage("/login.html")
//       .loginProcessingUrl("/perform_login")
//       .defaultSuccessUrl("/index.html",true)
//       .failureUrl("/login.html?error=true");
//     return http.build();
// }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return webSecurityCustomizer();
        
    }
    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    //     http.authorizeRequests().anyRequest().permitAll().and();

    //     return http.build();
    // }

     
    
    
  
}
