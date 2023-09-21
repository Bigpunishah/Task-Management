package dev.vikel.taskmanagement.users;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UserRepository extends MongoRepository<User, ObjectId>{
    
    
    //Works but dont like it yet
    User findByUserId(int userId);
}
