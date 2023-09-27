package dev.vikel.taskmanagement.tasks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    
    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks(){
        return new ResponseEntity<List<Task>>(taskService.allTasks(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Task>> getUserTasks(@PathVariable int userId) {
        List<Task> userTasks = taskService.userTasks(userId);

        //Error handling
        if (userTasks.isEmpty()) {
            // Handle the case where no tasks are found for the user
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Handle the case where tasks are found for the user
        return new ResponseEntity<>(userTasks, HttpStatus.OK);
    }

    @PostMapping("/{userId}/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable int userId, @PathVariable int taskId, @RequestBody Task updateTask){
        //!Pick up here!!!
       // ResponseEntity<List<Task>> userTasks = getUserTasks(userId);
    }
}
