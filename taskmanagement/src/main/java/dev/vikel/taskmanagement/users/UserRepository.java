package dev.vikel.taskmanagement.users;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, String>{
    
    
    //Not sure if i need rn
    // User findByUserId(int userId);
}