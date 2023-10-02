package dev.vikel.taskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


// import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

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
            .requestMatchers("/api/v1/user/**").hasAuthority("USER")
            .requestMatchers("/api/v1/tasks/**").hasAuthority("ADMIN")
            .requestMatchers("/task").hasAuthority("ADMIN")
        
        
        
        
        .anyRequest().authenticated())
        .formLogin(form -> form.loginPage("/login").permitAll()); //sending to html page

        
        return http.build();
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

    

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());

        return provider;
    }


}