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



    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http
    //         .authorizeHttpRequests((authz) -> authz
    //             .requestMatchers("/", "/api/**").permitAll()
    //             .requestMatchers("/about").permitAll() //include the controller mapping URL's EX: about.html can be accessed by /about
    //             .requestMatchers("/css/**", "/js/**" ).permitAll()
    //             .anyRequest().authenticated() //Any other request needs to be authenticated
    //         )
    //         .httpBasic(withDefaults());
    //     return http.build();
    // }





    //Password encrypter
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //Custom user in memeory
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // Use the password encoder to encode the password
        String encodedPassword = passwordEncoder().encode("g");
        
        UserDetails user = User
            .withUsername("g")
            .password(encodedPassword)
            .roles("USER")
            .build();
        
        return new InMemoryUserDetailsManager(user);
    }


}
