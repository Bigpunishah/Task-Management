package dev.vikel.taskmanagement.users;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<UserEntity, String>{
    
    //!When using repo in MUST include By
    Optional<UserEntity> findByUserId(String userId);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByPassword(String password);
}