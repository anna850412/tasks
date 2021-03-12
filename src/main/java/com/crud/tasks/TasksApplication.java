package com.crud.tasks;

import com.crud.tasks.controller.TrelloController;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksApplication  {
//public class TasksApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);

	}
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
//		return application.sources(TasksApplication.class);
//	}
}
