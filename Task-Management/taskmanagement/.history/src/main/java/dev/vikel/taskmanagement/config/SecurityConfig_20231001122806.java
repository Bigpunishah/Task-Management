package dev.vikel.taskmanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

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

    // @Bean
    // public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception{
    //     // http
            






    //     return http.build();
    // }

    // @Bean                                                             
	// public UserDetailsService userDetailsService() throws Exception {
	// 	// ensure the passwords are encoded properly
	// 	UserBuilder users = User.withDefaultPasswordEncoder();
	// 	InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	// 	manager.createUser(users.username("user").password("password").roles("USER").build());
	// 	manager.createUser(users.username("admin").password("password").roles("USER","ADMIN").build());
	// 	return manager;
	// }
 
}
