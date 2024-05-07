package com.rafael.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.crudspring.dto.CourseDTO;
import com.rafael.crudspring.exception.RecordNotFoundException;
import com.rafael.crudspring.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/courses")
// @AllArgsConstructor // Gera o construtor automaticamente
public class CursosController {

    private final CourseService courseService;

    public CursosController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDTO> list() {
        return courseService.list();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO create(@RequestBody @Valid @NotNull CourseDTO courseDto) {
        return courseService.create(courseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable @NotNull @Positive Long id) {
        try {
            return ResponseEntity.ok(courseService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid @NotNull CourseDTO courseDTO) {
        try {
            return ResponseEntity.ok(courseService.update(id, courseDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull @Positive Long id) {
        try {
            courseService.delete(id);
            return new ResponseEntity<>("O curso foi deletado.", HttpStatus.NO_CONTENT);
        } catch (RecordNotFoundException e) {
            StringBuilder message = new StringBuilder("O curso com id ");
            message.append(id);
            message.append(" não foi encontrado");
            return new ResponseEntity<>(message.toString(), HttpStatus.NOT_FOUND);
        }
    }
}
