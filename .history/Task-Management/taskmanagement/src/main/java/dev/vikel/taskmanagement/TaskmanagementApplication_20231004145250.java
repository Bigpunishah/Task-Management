package dev.vikel.taskmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.context.annotation.Import;
import dev.vikel.taskmanagement.config.SecurityConfig;

@SpringBootApplication
@Import(SecurityConfig.class)
public class TaskmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagementApplication.class, args);
	}

}
