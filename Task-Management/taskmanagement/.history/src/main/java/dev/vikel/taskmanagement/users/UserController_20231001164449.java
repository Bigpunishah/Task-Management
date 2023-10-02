package dev.vikel.taskmanagement.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/adminpower")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<List<User>>(userService.allUsers(), HttpStatus.OK);
    }  

    // //Create new user
    // @PostMapping("/newuser")
    // public ResponseEntity<User> createNewUser(@RequestBody User newUser) {
    //     // Get the email from the User object or modify the request JSON accordingly
    //     String email = newUser.getEmail();

    //     // Check if a user with the provided email already exists
    //     Optional<User> existingUser = userService.findUserByEmail(email);

    //     // If the user already exists, return a Bad Request response
    //     if (existingUser.isPresent()) {
    //         return ResponseEntity.badRequest().build();
    //     }

    //     // Save the new user
    //     userService.registerUser(newUser);

    //     // Return a successful response with the created user
    //     return ResponseEntity.ok(newUser);
    // }


    // //User authenticaztion
    // @PostMapping("login/authenticate")
    // public ResponseEntity<User> authenticateUser(@RequestParam String email, @RequestParam String password){

    //     Boolean userAuthentication = userService.authenticateUser(email, password);

    //     //if false stop.
    //     if(!userAuthentication){
    //         return ResponseEntity.badRequest().build();
    //     }

    //     Optional<User> authenticatedUser = userService.findUserByEmail(email);

    //     return ResponseEntity.ok(authenticatedUser.get());  
    // }



   

}
