package dev.vikel.taskmanagement.users;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetails implements UserDetails{

    private User user;

    public CustomUserDetails(User user){
        super();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<String> userRoles = user.getRoles(); // Assuming user.getRoles() returns a collection

        return null;
    }
    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    //     Collection<String> userRoles = user.getRoles(); // Assuming user.getRoles() returns a collection

    //     return userRoles.stream()
    //         .map(role -> new SimpleGrantedAuthority(role))
    //         .collect(Collectors.toList());
    // }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //For emails not username
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
