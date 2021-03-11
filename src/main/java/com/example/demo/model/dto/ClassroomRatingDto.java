package com.example.demo.model.dto;

import com.example.demo.model.rating.ClassroomRating;
import lombok.Data;

@Data
public class ClassroomRatingDto {
    private Long id;
    private Long subjectId;
    private String subjectName;
    private double point;
    private int count;

    public ClassroomRatingDto() {
    }

    public ClassroomRatingDto(ClassroomRating classroomRating) {
        this.id = classroomRating.getId();
        this.subjectId = classroomRating.getSubject().getId();
        this.subjectName = classroomRating.getSubject().getName();
        this.point = classroomRating.getPoint();
        this.count = classroomRating.getCount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
