package dev.vikel.taskmanagement.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> allUsers(){
        return userRepository.findAll(); 
    }

    public User findUser(int userId){
        return userRepository.findByUserId(userId);
    }



  
}
