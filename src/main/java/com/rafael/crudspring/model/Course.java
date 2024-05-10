package com.rafael.crudspring.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import com.rafael.crudspring.enums.Category;
import com.rafael.crudspring.enums.Status;
import com.rafael.crudspring.enums.converters.CategoryConverter;
import com.rafael.crudspring.enums.converters.StatusConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

// Descomentar a linha abaixo caso já exista uma tabela Cursos criada
// @Table(name = "Cursos")
@Entity
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

    @Column(nullable = false, length = 10)
    @Convert(converter =  CategoryConverter.class)
    private Category category;

    // @Length(max = 10)
    // @Pattern(regexp = "Ativo|Inativo")
    @Column(length=10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status= Status.ACTIVE;

    @NotEmpty
    @NotNull
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    //@JoinColumn(name="course_id")
    private List<Lesson> lessons = new ArrayList<>();

    public Course(){}

    public Course(Long id, @NotNull @NotBlank @Length(min = 5, max = 100) String name, Category category, Status status,
            List<Lesson> lessons) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.status = status;
        this.lessons = lessons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", name=" + name + ", category=" + category + ", status=" + status + ", lessons="
                + lessons + "]";
    }
}
