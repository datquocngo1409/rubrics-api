package com.example.demo.model.dto;

import com.example.demo.model.StudentRubricPoint;
import lombok.Data;

@Data
public class StudentRubricPointDto {
    private Long id;
    private Long studentId;
    private String studentName;
    private String studentCode;
    private Long rubricImportantId;
    private String rubricImportantName;
    private double rubricImportantImportant;
    private double point;

    public StudentRubricPointDto() {
    }

    public StudentRubricPointDto(StudentRubricPoint srt) {
        this.id = srt.getId();
        this.studentId = srt.getStudent().getId();
        this.studentName = srt.getStudent().getName();
        this.studentCode = srt.getStudent().getCode();
        this.rubricImportantId = srt.getRubricImportant().getId();
        this.rubricImportantName = srt.getRubricImportant().getRubric().getName();
        this.rubricImportantImportant = srt.getRubricImportant().getImportant();
        this.point = srt.getPoint();
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

    public Long getRubricImportantId() {
        return rubricImportantId;
    }

    public void setRubricImportantId(Long rubricImportantId) {
        this.rubricImportantId = rubricImportantId;
    }

    public String getRubricImportantName() {
        return rubricImportantName;
    }

    public void setRubricImportantName(String rubricImportantName) {
        this.rubricImportantName = rubricImportantName;
    }

    public double getRubricImportantImportant() {
        return rubricImportantImportant;
    }

    public void setRubricImportantImportant(double rubricImportantImportant) {
        this.rubricImportantImportant = rubricImportantImportant;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }
}
