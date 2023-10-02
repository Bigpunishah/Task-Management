package dev.vikel.taskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // todo: Figure out how to get password to authenticate
    // todo: Once authenticated allow access to certain pages
    // todo: may need to include boolean for authenticated then do...
    
    // Password encrypter
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())    
        
            .authorizeHttpRequests((auth) -> auth
            .requestMatchers("/").permitAll()
            .requestMatchers("/about").permitAll()
            .requestMatchers("/api/v1/user/newuser").permitAll()
            .requestMatchers("/api/v1/user/login/authenticate").permitAll()
            .requestMatchers("/error").permitAll()
            
            .requestMatchers("/api/v1/user/**").hasRole("USER")
            .requestMatchers("/api/v1/user/**").hasAuthority("ROLE_USER")
            .requestMatchers("/api/v1/tasks/**").hasRole("ADMIN")
            .requestMatchers("/task").hasRole("ADMIN")
        
        
        
        
        .anyRequest().authenticated());
        // .httpBasic(withDefaults());
        // .formLogin(withDefaults()); //sending to html page
        // .formLogin(form -> form.loginPage("/login").permitAll()); //sending to html page
        http.logout(logout -> logout.logoutUrl("/logout").permitAll(true));

        
        return http.build();
     }
}