package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_StudentRubricPoint")
public class StudentRubricPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User student;

    @ManyToOne
    private RubricImportant rubricImportant;

    private double point;

    public StudentRubricPoint() {
    }

    public StudentRubricPoint(User student, RubricImportant rubricImportant, double point) {
        this.student = student;
        this.rubricImportant = rubricImportant;
        this.point = point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public RubricImportant getRubricImportant() {
        return rubricImportant;
    }

    public void setRubricImportant(RubricImportant rubricImportant) {
        this.rubricImportant = rubricImportant;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }
}
