package dev.vikel.taskmanagement.tasks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    //All tasks from all users
    public List<Task> allTasks(){
        return taskRepository.findAll();
    }

    //Tasks from specific users
    public List<Task> userTasks(int userId){
        return taskRepository.findByUserId(userId);
    }

}
