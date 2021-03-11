package com.example.demo.model.rating;

import com.example.demo.model.Classroom;
import com.example.demo.model.User;

import javax.persistence.*;

@Entity
@Table(name = "tbl_StudentRating")
public class StudentRating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User student;

    @ManyToOne
    private Classroom subject;

    private String content;

    private double point = 0;

    public StudentRating() {
    }

    public StudentRating(User student, Classroom subject, String content, double point) {
        this.student = student;
        this.subject = subject;
        this.content = content;
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

    public Classroom getSubject() {
        return subject;
    }

    public void setSubject(Classroom subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }
}
