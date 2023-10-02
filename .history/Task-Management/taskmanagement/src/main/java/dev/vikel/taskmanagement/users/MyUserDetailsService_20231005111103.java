package dev.vikel.taskmanagement.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

         // Fetching the user from the database using the provided email
        User user = userRepository.findByEmail(email).get();
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return new MyUserDetails(user);






        
        // // Transforming the roles (Strings) of the user to GrantedAuthority objects.
        // // The "ROLE_" prefix is commonly used in Spring Security to identify roles.
        // List<GrantedAuthority> userRoles = user.getRoles().stream()
        //     .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
        //     .collect(Collectors.toList());
            
        //     // Returning a User object (Spring Security's version), which will be used for authentication.
        //     return new org.springframework.security.core.userdetails.User(
        //         user.getEmail(),
        //         user.getPassword(),
        //         userRoles); //Authorities here are roles which are used for authorization.
    

    }
    
}
