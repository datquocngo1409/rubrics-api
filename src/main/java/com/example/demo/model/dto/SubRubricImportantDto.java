package com.example.demo.model.dto;

import lombok.Data;

@Data
public class SubRubricImportantDto {
    private Long id;
    private Long rubricImportantId;
    private String name;
    private double important;

    public SubRubricImportantDto() {
    }

    public SubRubricImportantDto(Long rubricImportantId, String name, double important) {
        this.rubricImportantId = rubricImportantId;
        this.name = name;
        this.important = important;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRubricImportantId() {
        return rubricImportantId;
    }

    public void setRubricImportantId(Long rubricImportantId) {
        this.rubricImportantId = rubricImportantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getImportant() {
        return important;
    }

    public void setImportant(double important) {
        this.important = important;
    }
}
