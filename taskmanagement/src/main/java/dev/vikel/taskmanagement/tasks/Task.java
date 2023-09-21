package dev.vikel.taskmanagement.tasks;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import dev.vikel.taskmanagement.users.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private ObjectId id;

    private int taskId;

    private String taskName;
    
    private String taskDescription;

    private String priority;

    @DBRef
    private User user;


}
