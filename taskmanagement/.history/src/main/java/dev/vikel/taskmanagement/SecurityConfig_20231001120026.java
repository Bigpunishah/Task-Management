package dev.vikel.taskmanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


//This is used to encode password
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
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


    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    //     http.authorizeRequests().anyRequest().permitAll().and();

    //     return http.build();
    // }

    protected void configure(HttpSecurity http)throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/").permitAll() // This will be your home screen URL
            .antMatchers("/css/**").permitAll()
            .antMatchers("/js/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .defaultSuccessUrl("/postloginscreen") //configure screen after login success
            .loginPage("/login")
            .permitAll()
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();
    }

     
    
    
  
}
