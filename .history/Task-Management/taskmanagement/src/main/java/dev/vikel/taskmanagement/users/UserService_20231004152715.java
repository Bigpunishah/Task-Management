package dev.vikel.taskmanagement.users;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  {
    //implements UserDetailsService

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
     @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        // Encode the user's password before storing it in the database
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        //This will run into error unless .and().csrf().disable(); is added to Config file
        user.setPassword(encodedPassword);

        // Save the user to the database
        userRepository.save(user);
    }

    public boolean authenticateUser(String email, String password) {
        Optional <User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            // Use the PasswordEncoder to verify the entered password
            //.get() allows me to go beyond the Optional statement
            System.out.println(passwordEncoder.matches(password, user.get().getPassword())); //raw then encoded
            System.out.println(password);
            System.out.println(passwordEncoder.encode(password));
            System.out.println(user.get().getPassword());
            System.out.println(passwordEncoder);
            String test = "hi";
            String test2 = "hi";
            passwordEncoder.encode(test);
            System.out.println(passwordEncoder.matches(test2, test) + "this is test");
            return passwordEncoder.matches(password, user.get().getPassword());
        }
        return false;
    }

    // todo: not sure if needed at moment.
    // @Override
    // public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
    //     User user = userRepository.findByEmail(email).get();

    //     if(user == null){
    //         throw new UsernameNotFoundException("User not found");
    //     }

    //     return org.springframework.security.core.userdetails.User
    //             .withUsername(email) // Use email here
    //             .password(user.getPassword())
    //             //.authorities(new ArrayList<>()) // Assign authorities/roles as needed
    //             .build();
    // }
    
    
    
  
}
