package com.rafael.crudspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.rafael.crudspring.enums.Category;
import com.rafael.crudspring.enums.Status;
import com.rafael.crudspring.enums.converters.CategoryConverter;
import com.rafael.crudspring.enums.converters.StatusConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// Descomentar a linha abaixo caso já exista uma tabela Cursos criada
// @Table(name = "Cursos")
@Entity
@SQLDelete(sql="UPDATE Course SET status = 'Inativo' WHERE id = ?")
@Data
@Where(clause = "status = 'Ativo'")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @NotBlank
    @Length(min = 5, max = 100)
    @Column(length=100, nullable = false)
    private String name;

    @Column(nullable = false, length = 10)
    @Convert(converter =  CategoryConverter.class)
    private Category category;

    // @Length(max = 10)
    // @Pattern(regexp = "Ativo|Inativo")
    @Column(length=10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status= Status.ACTIVE;
}
