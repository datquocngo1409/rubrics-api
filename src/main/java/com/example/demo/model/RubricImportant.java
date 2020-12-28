package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_RubricImportant")
public class RubricImportant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long classroomId;

    @ManyToOne
    private Rubric rubric;

    private double important;

    public RubricImportant() {
    }

    public RubricImportant(Rubric rubric, double important, Long classroomId) {
        this.rubric = rubric;
        this.important = important;
        this.classroomId = classroomId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rubric getRubric() {
        return rubric;
    }

    public void setRubric(Rubric rubric) {
        this.rubric = rubric;
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
