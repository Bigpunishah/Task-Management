package dev.vikel.taskmanagement.tasks;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.vikel.taskmanagement.users.User;
import dev.vikel.taskmanagement.users.UserService;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    //For the tasks
    @Autowired
    private TaskService taskService;

    //For the users
    @Autowired
    private UserService userService;
    
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
            return ResponseEntity.notFound().build();
        }

        // Handle the case where tasks are found for the user
        return ResponseEntity.ok(userTasks);
    }

    //For updating existing task
    @PutMapping("/{userId}/update/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable int userId, @PathVariable String taskId, @RequestBody Task updatedTask){

        //Task
        Optional<Task> existingTask = taskService.specificTask(taskId);      

        //Checks if task exists
        if(existingTask.isEmpty()){
            return ResponseEntity.notFound().build();        
        }

        

        Task taskToUpdate = existingTask.get();
        taskToUpdate.setTaskName(updatedTask.getTaskName());
        taskToUpdate.setTaskDescription(updatedTask.getTaskDescription());
        taskToUpdate.setPriority(updatedTask.getPriority());
        taskToUpdate.setUserId(userId);
        taskService.updateTask(taskToUpdate);
            
        return ResponseEntity.ok(taskToUpdate); 
    }

    //Creating new task
    @PostMapping("/{userId}/newtask")
    public ResponseEntity<Task> createTask(@PathVariable int userId, @RequestBody Task newTaskInfo){
        
        //Use the UserService to check to see if the user exists.
        Optional<User> existingUser = userService.findUser(userId);

        //Check to see if user is present
        if(existingUser.isPresent()){
            Task newTask = newTaskInfo;
            newTask.setUserId(userId);

            taskService.createTask(newTask);
        
            return ResponseEntity.ok(newTask);
            
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}/delete/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable int userId, @PathVariable String taskId){
        Optional<User> existingUser = userService.findUser(userId);
        Optional<Task> existingTask = taskService.specificTask(taskId);
        
        //Using .get() for comparison
        Task checkTask = existingTask.get();
        User checkUser = existingUser.get();
        if(checkTask.getUserId() != checkUser.getUserId()){
            return ResponseEntity.notFound().build();
        }

        taskService.deleteTask(checkTask);

        return ResponseEntity.ok("Successful deletion");


    } 
}
