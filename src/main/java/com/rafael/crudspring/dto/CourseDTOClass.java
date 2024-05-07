package com.rafael.crudspring.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CourseDTOClass {
    private Long id;
    
    @NotNull
    @NotBlank
    @Length(min = 5, max = 100)
    private String name;

    @Length(max = 10)
    @Pattern(regexp = "Back-end|Front-end")
    private String category;

    @Length(max = 10)
    @Pattern(regexp = "Ativo|Inativo")
    private String status="Ativo";
}
