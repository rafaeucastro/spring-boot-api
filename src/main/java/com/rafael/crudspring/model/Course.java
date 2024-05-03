package com.rafael.crudspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
// Descomentar a linha abaixo caso já exista uma tabela Cursos criada
// @Table(name = "Cursos")
@SQLDelete(sql="UPDATE Course SET status = 'Inativo' WHERE id = ?")
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

    @Length(max = 10)
    @Pattern(regexp = "Back-end|Front-end")
    @Column(length=10, nullable = false)
    private String category;

    @Length(max = 10)
    @Pattern(regexp = "Ativo|Inativo")
    @Column(length=10, nullable = false)
    private String status="Ativo";
}
