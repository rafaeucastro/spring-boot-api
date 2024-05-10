package com.rafael.crudspring;

import com.rafael.crudspring.model.Lesson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rafael.crudspring.enums.Category;
import com.rafael.crudspring.model.Course;
import com.rafael.crudspring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository){
		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular com Spring");
			c.setCategory(Category.BACKEND);

			Lesson l = new Lesson();
			l.setName("Introdução");
			l.setYoutubeUrl("https://you");
			l.setCourse(c);
			c.getLessons().add(l);

			courseRepository.save(c);
		};
	}

}
