package com.example.demo.model.dto;

import com.example.demo.model.rating.StudentRating;
import lombok.Data;

@Data
public class StudentRatingDto {
    private Long id;
    private Long studentId;
    private String studentName;
    private Long subjectId;
    private String subjectName;
    private double point;
    private String content;

    public StudentRatingDto() {
    }

    public StudentRatingDto(StudentRating studentRating) {
        this.id = studentRating.getId();
        this.studentId = studentRating.getStudent().getId();
        this.studentName = studentRating.getStudent().getName();
        this.subjectId = studentRating.getSubject().getId();
        this.studentName = studentRating.getSubject().getName();
        this.point = studentRating.getPoint();
        this.content = studentRating.getContent();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
