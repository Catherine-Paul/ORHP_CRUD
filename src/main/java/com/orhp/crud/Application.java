package com.orhp.crud;


import com.orhp.crud.entitites.Course;
import com.orhp.crud.entitites.User;
import com.orhp.crud.repositories.CourseRepository;
import com.orhp.crud.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	CommandLineRunner init(UserRepository userRepository, CourseRepository courseRepository) {
		return args -> {
			Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
				User user = new User(name, name.toLowerCase() + "@domain.com");
				userRepository.save(user);
			});

			Stream.of("Course1", "Course2", "Course3", "Course4", "Course5").forEach(name -> {
				Course newCourse = new Course(name);
				courseRepository.save(newCourse);
			});
			User userJohn = userRepository.findByName("John");
			userJohn.getCourses().add(courseRepository.findByName("Course1"));
			userJohn.getCourses().add(courseRepository.findByName("Course2"));
			userRepository.save(userJohn);

			for(Course c: userJohn.getCourses()){
				System.out.println(c);
			}

			userRepository.findAll().forEach(System.out::println);
			courseRepository.findAll().forEach(System.out::println);




		};
	}
}
