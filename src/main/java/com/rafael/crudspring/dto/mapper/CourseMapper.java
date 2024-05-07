package com.rafael.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.rafael.crudspring.dto.CourseDTO;
import com.rafael.crudspring.model.Course;

@Component
public class CourseMapper {
    public CourseDTO toDTO(Course course){
        if(course == null){
            return null;
        }
        return new CourseDTO(course.getId(), course.getName(), course.getCategory());
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
        course.setCategory(dto.category());
        return course;
    }
}
