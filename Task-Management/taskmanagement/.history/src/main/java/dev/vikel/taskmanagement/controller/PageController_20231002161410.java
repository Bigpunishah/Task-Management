package dev.vikel.taskmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//This controller helps direct lage location
@Controller
public class PageController {
        @GetMapping("/")
    public String home() {
        return "index"; // returns index.html
    }

    @GetMapping("/login.html")
    public String login() {
        return "login"; // returns login.html
    }

    @GetMapping("/about.html")
    public String about() {
        return "about"; // returns about.html
    }

    @GetMapping("/task.html")
    public String task() {
        return "task"; // returns task.html
    }

    @RequestMapping("/error.html")
    public String handleError() {
        return "error"; // returns error.html
    }

    // @Override
    // public String getErrorPath() {
    //     return "/error";
    // }


    
}
