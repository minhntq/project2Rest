package com.hcc.Project2Rest;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Project2RestApplication {
	
	private static void extracted(ConfigurableApplicationContext context) throws IOException {

		StudentController studentController = context.getBean(StudentController.class);
		System.out.println(" Stu: "+ studentController.readData());
	}
	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context = SpringApplication.run(Project2RestApplication.class, args);
		extracted(context);
	}

}
