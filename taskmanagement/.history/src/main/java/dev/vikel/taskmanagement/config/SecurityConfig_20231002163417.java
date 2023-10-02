package dev.vikel.taskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
    //     return http.build();
    
    // }


    // @Bean
    // public SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> securityConfigurer() {
    //     return new SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    //         @Override
    //         public void configure(HttpSecurity http) throws Exception {
    //             http
    //                 .authorizeRequests()
    //                 .requestMatchers("/", "/index", "/api/**").permitAll() //Public URLs
    //                 .anyRequest().authenticated() // Any other request should be authenticated
    //                 .and()
    //                 .formLogin()
    //                 .usernameParameter("email") // Use email as the login parameter
    //                 .permitAll();
    //         }
    //     };
    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/", "/api/**").permitAll()
                .requestMatchers("/about", "/login", "**.html").permitAll() //include the controller mapping URL's EX: about.html can be accessed by /about
                .requestMatchers("/css/**", "/js/**" ).permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());
        return http.build();
    }

    


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
