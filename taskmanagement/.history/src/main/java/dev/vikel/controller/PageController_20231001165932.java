package dev.vikel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PageController {
        @GetMapping("/")
    public String home() {
        return "index"; // returns index.html
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // returns login.html
    }

    @GetMapping("/about")
    public String about() {
        return "about"; // returns about.html
    }

    @GetMapping("/task")
    public String task() {
        return "task"; // returns task.html
    }


    
}
