package com.rafael.crudspring.dto.mapper;

import com.rafael.crudspring.enums.Category;
import org.springframework.stereotype.Component;

import com.rafael.crudspring.dto.CourseDTO;
import com.rafael.crudspring.model.Course;

@Component
public class CourseMapper {
    public CourseDTO toDTO(Course course){
        if(course == null){
            return null;
        }
        return new CourseDTO(course.getId(), course.getName(), "Front-end");
    }
    
    public Course fromDTO(CourseDTO dto) {
        if(dto == null){
            return null;
        }

        Course course = new Course();
        if(dto.id() != null) {
            course.setId(dto.id());
        }
        course.setName(dto.name());
        //TODO: Use a mapper for category
        course.setCategory(convertCategoryValue(dto.category()));
        return course;
    }

    public Category convertCategoryValue(String value) {
        if(value == null) return null;

        return switch(value){
            case "Front-end" -> Category.FRONTEND;
            case "Back-end" -> Category.BACKEND;
            default -> throw new IllegalArgumentException("Invalid value: " + value);
        };
    }
}
