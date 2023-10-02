package dev.vikel.taskmanagement.users;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, String>{
    
    //!When using repo in MUST include By
    Optional<User> findByUserId(String userId);

    Optional<User> findByEmail(String email);

    Optional<User> findByPassword(String password);
}