package dev.vikel.taskmanagement.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/admin/allusers")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<List<User>>(userService.allUsers(), HttpStatus.OK);
    }  


    @GetMapping("/find")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email){
        Optional<User> user = userService.findUserByEmail(email);

        if(user.isEmpty()){
            return ResponseEntity.badRequest().build();
        }


        return ResponseEntity.ok(user.get());
    }



    //Create new user
    @PostMapping("/newuser")
    public ResponseEntity<User> createNewUser(@RequestBody User newUser) {
        // Get the email from the User object or modify the request JSON accordingly
        String email = newUser.getEmail();

        // Check if a user with the provided email already exists
        Optional<User> existingUser = userService.findUserByEmail(email);

        // If the user already exists, return a Bad Request response
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        //Saves user password encrypted
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));


        // Save the new user
        userService.registerUser(newUser);

        // Return a successful response with the created user
        return ResponseEntity.ok(newUser);
    }


    //User authenticaztion
    @PostMapping("login/authenticate")
    public ResponseEntity<User> authenticateUser(@RequestParam String email, @RequestParam String password){
        System.out.println(email + password);

        Boolean userAuthentication = userService.authenticateUser(email, password);

        //!Currently cannot figure out why my authentication process send 200OK every time even with bad credentials.
        

        //if false stop.
        if(!userAuthentication){
            return ResponseEntity.badRequest().build();
        }

        Optional<User> authenticatedUser = userService.findUserByEmail(email);

        return ResponseEntity.ok(authenticatedUser.get());  
    }



   

}
