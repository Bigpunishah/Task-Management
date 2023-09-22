package dev.vikel.taskmanagement.users;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, ObjectId>{
    
    
    //Works but dont like it yet
    User findByUserId(int userId);
}