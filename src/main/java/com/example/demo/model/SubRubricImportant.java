package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_SubRubricImportant")
public class SubRubricImportant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private RubricImportant rubricImportant;

    private Long important;

    private String name;

    public SubRubricImportant() {
    }

    public SubRubricImportant(RubricImportant rubricImportant, Long important, String name) {
        this.rubricImportant = rubricImportant;
        this.important = important;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RubricImportant getRubricImportant() {
        return rubricImportant;
    }

    public void setRubricImportant(RubricImportant rubricImportant) {
        this.rubricImportant = rubricImportant;
    }

    public Long getImportant() {
        return important;
    }

    public void setImportant(Long important) {
        this.important = important;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
