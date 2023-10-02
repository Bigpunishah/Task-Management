package dev.vikel.taskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import dev.vikel.taskmanagement.users.MyUserDetailsService;

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
        System.out.println("Hello mom\n\n\n\n\n\n");

        http
            .csrf(csrf -> csrf.disable())    
        
            .authorizeHttpRequests((auth) -> auth
            .requestMatchers("/").permitAll()
            .requestMatchers("/about").permitAll()
            .requestMatchers("/api/v1/user/newuser").permitAll()
            .requestMatchers("/api/v1/user/login/authenticate").permitAll()
            .requestMatchers("/error").permitAll()

            .requestMatchers("/api/v1/user/**").hasRole("USER")
            .requestMatchers("/api/v1/tasks/**").hasRole("ADMIN")
            .requestMatchers("/task").hasRole("ADMIN")
        
        
        
        
        .anyRequest().authenticated());
        // .httpBasic(withDefaults());
        // .formLogin(withDefaults()); //sending to html page
        // .formLogin(form -> form.loginPage("/login").permitAll()); //sending to html page
        http.logout(logout -> logout.logoutUrl("/logout").permitAll(true));

        
        return http.build();
     }


    @Autowired
    private MyUserDetailsService myUserDetailsService;
    
    @Bean
    public AuthenticationProvider authenticationProviderG(BCryptPasswordEncoder passwordEncoder){
        System.out.println("Hello mom in Authentication provider \n\n");

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // Creating an instance of DaoAuthenticationProvider, 
        // which is an implementation of AuthenticationProvider 
        // that retrieves user details from a UserDetailsService.

        provider.setUserDetailsService(myUserDetailsService);
        // Setting our custom UserDetailsService that will be used by the provider to fetch user data.

        provider.setPasswordEncoder(passwordEncoder);
        // Setting a password encoder to the provider to ensure that password comparison is done securely

        return provider;
        // Registering our configured DaoAuthenticationProvider as a bean to be used within the Spring context.
    }

    @Bean
    public void authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("Hello u suk");
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    

}