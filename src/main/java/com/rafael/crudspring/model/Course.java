package com.rafael.crudspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
// Descomentar a linha abaixo caso já exista uma tabela Cursos criada
// @Table(name = "Cursos")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(length=100, nullable = false)
    private String name;

    @Column(length=10, nullable = false)
    private String category;

    
}
