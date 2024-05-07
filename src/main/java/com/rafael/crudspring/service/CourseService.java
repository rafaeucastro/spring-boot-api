package com.rafael.crudspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.rafael.crudspring.dto.CourseDTO;
import com.rafael.crudspring.dto.mapper.CourseMapper;
import com.rafael.crudspring.exception.RecordNotFoundException;
import com.rafael.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> list() {
        return courseRepository.findAll().stream().map(courseMapper::toDTO).toList();
    }

    public CourseDTO findById(@PathVariable @NotNull @Positive Long id) throws RecordNotFoundException {
        return courseRepository.findById(id).map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.fromDTO(course)));
    }

    public CourseDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid CourseDTO courseDTO)
            throws RecordNotFoundException {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setCategory(courseDTO.category());
                    recordFound.setName(courseDTO.name());
                    return courseRepository.save(recordFound);
                })
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@PathVariable @NotNull @Positive Long id) throws RecordNotFoundException {
        courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
