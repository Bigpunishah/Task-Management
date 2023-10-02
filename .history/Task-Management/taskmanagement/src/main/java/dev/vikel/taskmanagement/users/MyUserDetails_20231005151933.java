package dev.vikel.taskmanagement.users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails{

    private User user;

    public MyUserDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        System.out.println("Insude MyUseradETAIL\n\n\n");
        int rolesCount = user.getRoles().size();

        for(int i = 0; i < rolesCount; i++){

            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoles().get(i)));

        }

        return authorities;

            
    }

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
