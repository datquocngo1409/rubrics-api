package com.example.demo.model.dto;

import com.example.demo.model.Rubric;
import com.example.demo.model.RubricImportant;
import lombok.Data;

@Data
public class RubricImportantDto {
    private Long id;
    private Long rubricId;
    private String rubricName;
    private double important;
    private Long classroomId;

    public RubricImportantDto() {
    }

    public RubricImportantDto(RubricImportant rubricImportant) {
        this.id = rubricImportant.getId();
        this.rubricId = rubricImportant.getRubric().getId();
        this.rubricName = rubricImportant.getRubric().getName();
        this.important = rubricImportant.getImportant();
        this.classroomId = rubricImportant.getClassroomId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRubricId() {
        return rubricId;
    }

    public void setRubricId(Long rubricId) {
        this.rubricId = rubricId;
    }

    public String getRubricName() {
        return rubricName;
    }

    public void setRubricName(String rubricName) {
        this.rubricName = rubricName;
    }

    public double getImportant() {
        return important;
    }

    public void setImportant(double important) {
        this.important = important;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }
}
