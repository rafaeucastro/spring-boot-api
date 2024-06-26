package com.rafael.crudspring.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.rafael.crudspring.dto.CourseDTO;
import com.rafael.crudspring.dto.CoursePageDTO;
import com.rafael.crudspring.dto.mapper.CourseMapper;
import com.rafael.crudspring.exception.RecordNotFoundException;
import com.rafael.crudspring.model.Course;
import com.rafael.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    // public List<CourseDTO> list() {
    // return courseRepository.findAll().stream().map(courseMapper::toDTO).toList();
    // }

    public CoursePageDTO list(@PositiveOrZero int pageNumber, @Positive @Max(50) int pageSize) {
        Page<Course> page = courseRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<CourseDTO> courses = page.get().map(courseMapper::toDTO).toList();
        return new CoursePageDTO(courses, page.getTotalElements(), page.getTotalPages());
    }

    public CourseDTO findById(@NotNull @Positive Long id) throws RecordNotFoundException {
        return courseRepository.findById(id).map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.fromDTO(course)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @RequestBody @Valid CourseDTO courseDTO)
            throws RecordNotFoundException {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    var course = courseMapper.fromDTO(courseDTO);
                    recordFound.setCategory(courseMapper.convertCategoryValue(courseDTO.category()));
                    recordFound.setName(courseDTO.name());
                    recordFound.getLessons().clear();
                    course.getLessons().forEach(recordFound.getLessons()::add);
                    return courseRepository.save(recordFound);
                })
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) throws RecordNotFoundException {
        courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
