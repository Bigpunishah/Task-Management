package dev.vikel.taskmanagement.tasks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
