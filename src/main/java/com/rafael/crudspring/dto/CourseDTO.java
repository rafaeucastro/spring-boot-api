package com.rafael.crudspring.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.rafael.crudspring.dto.mapper.LessonDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CourseDTO(
        Long id,
        @NotNull @NotBlank @Length(min = 5, max = 100) String name,
        @Length(max = 10) @Pattern(regexp = "Back-end|Front-end") String category,
        @NotNull @NotEmpty @Valid List<LessonDTO> lessons) {
}
