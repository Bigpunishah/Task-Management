package dev.vikel.taskmanagement.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.vikel.taskmanagement.config.SecurityConfig;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> allUsers(){
        return userRepository.findAll(); 
    }

    public Optional<User> findUser(String userId){
        return userRepository.findByUserId(userId);
    }

    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    
    public Optional<User> findUserPassword(String password){
        return userRepository.findByPassword(password);
    }
    
    //Save user
    //  @Autowired
    // private PasswordEncoder passwordEncoder;

    // public void registerUser(User user) {
    //     // Encode the user's password before storing it in the database
    //     String encodedPassword = passwordEncoder.encode(user.getPassword());
    //     user.setPassword(encodedPassword);

    //     // Save the user to the database
    //     userRepository.save(user);
    // }

    public boolean authenticateUser(String email, String password) {
        Optional <User> user = userRepository.findByEmail(email);
        if (user != null) {
            // Use the PasswordEncoder to verify the entered password
            //.get() allows me to go beyond the Optional statement
            return passwordEncoder.matches(password, user.get().getPassword());
        }
        return false;
    }

    @Autowired
    private SecurityConfig securityConfig;

    public void registerUser(User user){
        user.setPassword(securityConfig.cryptoPassword(user.getPassword()));
        userRepository.save(user);
    }
    
    
  
}
