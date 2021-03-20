package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_SubStudentRubricPoint")
public class SubStudentRubricPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User student;

    @ManyToOne
    private SubRubricImportant subRubricImportant;

    private double point;

    public SubStudentRubricPoint() {
    }

    public SubStudentRubricPoint(User student, SubRubricImportant subRubricImportant, double point) {
        this.student = student;
        this.subRubricImportant = subRubricImportant;
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

    public SubRubricImportant getSubRubricImportant() {
        return subRubricImportant;
    }

    public void setSubRubricImportant(SubRubricImportant subRubricImportant) {
        this.subRubricImportant = subRubricImportant;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }
}
