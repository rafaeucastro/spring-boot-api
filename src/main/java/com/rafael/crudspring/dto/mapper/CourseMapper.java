package com.rafael.crudspring.dto.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rafael.crudspring.dto.CourseDTO;
import com.rafael.crudspring.enums.Category;
import com.rafael.crudspring.model.Course;
import com.rafael.crudspring.model.Lesson;

@Component
public class CourseMapper {
    public CourseDTO toDTO(Course course){
        if(course == null){
            return null;
        }
        List<LessonDTO> lessons = course.getLessons().stream().map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl())).toList();
        return new CourseDTO(course.getId(), course.getName(), "Front-end", lessons);
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
        
        List<Lesson> lessons = dto.lessons().stream().map(lessonDTO -> {
            var lesson = new Lesson();
            lesson.setId(lessonDTO.id());
            lesson.setName(lessonDTO.name());
            lesson.setYoutubeUrl(lessonDTO.youtubeUrl());
            lesson.setCourse(course);
            return lesson;
        }).toList();
        course.setLessons(lessons);
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
