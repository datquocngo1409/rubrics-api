package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_StudentTotalRubricPoint")
public class StudentTotalRubricPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<StudentRubricPoint> studentRubricPoint;

    private Long studentId;

    private double totalPoint;

    private boolean isPassed;

    public StudentTotalRubricPoint() {
    }

    public StudentTotalRubricPoint(List<StudentRubricPoint> studentRubricPoint, double totalPoint, boolean isPassed, Long studentId) {
        this.studentRubricPoint = studentRubricPoint;
        this.totalPoint = totalPoint;
        this.isPassed = isPassed;
        this.studentId = studentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<StudentRubricPoint> getStudentRubricPoint() {
        return studentRubricPoint;
    }

    public void setStudentRubricPoint(List<StudentRubricPoint> rubricPoints) {
        this.studentRubricPoint = rubricPoints;
    }

    public double getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(double totalPoint) {
        this.totalPoint = totalPoint;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }
}
